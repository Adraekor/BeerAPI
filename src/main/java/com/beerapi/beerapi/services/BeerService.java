package com.beerapi.beerapi.services;

import com.beerapi.beerapi.controller.BeerController;
import com.beerapi.beerapi.exception.BeerNotFoundException;
import com.beerapi.beerapi.model.entities.Beer;
import com.beerapi.beerapi.model.resources.BeerResource;
import com.beerapi.beerapi.repository.BeerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BeerService
{

  private BeerRepository beerRepository;
  private static final Logger logger = LoggerFactory.getLogger(BeerController.class);

  public BeerService(BeerRepository beerRepository)
  {
    this.beerRepository = beerRepository;
  }

  public Iterable<Beer> getSimilarBeer(Long id) {
    Optional<Beer> initialBeer = beerRepository.findById(id);

    if (!initialBeer.isPresent())
    {
      String message = MessageFormat.format("Beer with id {0} not found", id);
      logger.warn(message);
      throw new BeerNotFoundException(message);
    }

    double alcoholPercentage = initialBeer.get().getAlcoholPercentage();
    Iterable<Beer> beerIterable = beerRepository.findBeerByAlcoholPercentage(alcoholPercentage);
    return StreamSupport.stream(beerIterable.spliterator(), false)
        .filter(b -> !b.getId().equals(id))
        .collect(Collectors.toList());

  }

  public Beer addBeer(BeerResource newBeer) {
    Beer beer = new Beer(newBeer);
    return beerRepository.save(beer);
  }

  public void deleteById(Long id) {
    beerRepository.deleteById(id);
  }

  public Beer getById(Long id) {
    Optional<Beer> beer = beerRepository.findById(id);

    if (!beer.isPresent())
    {
      String message = MessageFormat.format("Beer with id {0} not found", id);
      logger.warn(message);
      throw new BeerNotFoundException(message);
    }

    return beer.get();
  }

  public Iterable<Beer> getAllBeers()
  {
    logger.info("Getting all the beers.");
    return beerRepository.findAll();
  }

  public Iterable<Beer> searchBeer(String query) {
    logger.info("Searching beers with query : {}", query);
    query = query.replace("œ", "oe");
    return beerRepository.findBeerByName(query);
  }
}

