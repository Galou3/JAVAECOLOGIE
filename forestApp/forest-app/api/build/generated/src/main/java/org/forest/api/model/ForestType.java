package org.forest.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets ForestType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-17T16:23:41.231448500+01:00[Europe/Paris]")
public enum ForestType {
  
  BOREAL("BOREAL"),
  
  TEMPERATE("TEMPERATE"),
  
  TROPICAL("TROPICAL");

  private String value;

  ForestType(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ForestType fromValue(String value) {
    for (ForestType b : ForestType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

