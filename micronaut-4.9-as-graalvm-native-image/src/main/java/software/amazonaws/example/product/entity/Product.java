// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package software.amazonaws.example.product.entity;

import java.math.BigDecimal;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import io.micronaut.serde.annotation.Serdeable.Deserializable;
import io.micronaut.serde.annotation.Serdeable.Serializable;

@Introspected
@Serdeable.Deserializable
@Serdeable.Serializable
public record Product(String id, String name, BigDecimal price) {
}
  