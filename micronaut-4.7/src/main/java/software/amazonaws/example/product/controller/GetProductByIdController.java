// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package software.amazonaws.example.product.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.inject.Inject;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import software.amazonaws.example.product.dao.DynamoProductDao;
import software.amazonaws.example.product.dao.ProductDao;
import software.amazonaws.example.product.entity.Product;

@Controller
public class GetProductByIdController {

  @Inject
  private ProductDao productDao;
  
  private static final Logger logger = LoggerFactory.getLogger(GetProductByIdController.class);
 
  @Get("/products/{id}")
  public Optional<Product> getProductById(@PathVariable String id) {
	Optional<Product> optionalProduct = productDao.getProduct(id);
	if (optionalProduct.isPresent())
		logger.info(" product found : " + optionalProduct.get());
	else
		logger.info(" product with id "+ id+" not found ");
	return optionalProduct;
  }

}
