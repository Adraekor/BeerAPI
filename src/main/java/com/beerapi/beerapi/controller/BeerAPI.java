package com.beerapi.beerapi.controller;

import com.beerapi.beerapi.model.dto.BeerDTO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface BeerAPI
{
  @ApiOperation(value = "Retrieve all the beers", nickname = "Retrieve all the beers", tags={ "Beers" })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success") })
  Iterable<BeerDTO> getAllBeers();

  @ApiOperation(value = "Retrieve a beer by its ID", nickname = "Retrieve a beer by ID", tags={ "Beers" })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success"),
      @ApiResponse(code = 404, message = "Beer with id {id} not found") })
  BeerDTO getById(Long id);

  @ApiOperation(value = "Deletes a beer by its ID", nickname = "Delete a beer by ID", tags={ "Beers" })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success"),
      @ApiResponse(code = 404, message = "Beer with id {id} not found") })
  void deleteById(Long id);

  @ApiOperation(value = "Adds a beer", nickname = "Adds a beer", tags={ "Beers" })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success"),
      @ApiResponse(code = 404, message = "Beer with id {id} not found") })
  BeerDTO addBeer(BeerDTO newBeer);

  @ApiOperation(value = "Retrieve the beers that are like the one passed as parameter", nickname = "Similar Beers", tags={ "Beers"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success")})
  Iterable<BeerDTO> getSimilarBeers(Long id);
}