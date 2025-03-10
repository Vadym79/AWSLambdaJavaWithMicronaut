// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package software.amazonaws.example.product.entity;

import java.util.List;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Products {
  private List<Product> products;

  public Products() {
  }

  public Products(List<Product> products) {
    this.products = products;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }
}