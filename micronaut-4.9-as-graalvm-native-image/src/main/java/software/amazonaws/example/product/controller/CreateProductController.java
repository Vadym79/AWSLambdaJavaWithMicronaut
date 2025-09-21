// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package software.amazonaws.example.product.controller;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Put;
import software.amazonaws.example.product.dao.ProductDao;
import software.amazonaws.example.product.entity.Product;

@Controller
public class CreateProductController {
	
  private final ProductDao productDao;

  public CreateProductController(ProductDao productDao) {
    this.productDao = productDao;
  }

  @Put("/products")
  public void createUpdateProduct(@Body Product product) {
    productDao.putProduct(product);
  }
 
}
