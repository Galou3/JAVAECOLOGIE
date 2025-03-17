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
 * Gets or Sets Species
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-17T12:46:50.901899400+01:00[Europe/Paris]")
public enum Species {
  
  OAK("OAK"),
  
  EVERGREEN_OAK("EVERGREEN_OAK"),
  
  FIR("FIR"),
  
  ASH("ASH"),
  
  BEECH("BEECH");

  private String value;

  Species(String value) {
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
  public static Species fromValue(String value) {
    for (Species b : Species.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

