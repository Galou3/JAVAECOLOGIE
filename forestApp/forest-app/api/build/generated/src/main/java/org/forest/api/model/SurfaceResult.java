package org.forest.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * SurfaceResult
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-17T12:46:50.901899400+01:00[Europe/Paris]")
public class SurfaceResult {

  private Double co2Amount;

  private Double requiredSurfaceSqMeters;

  public SurfaceResult() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SurfaceResult(Double co2Amount, Double requiredSurfaceSqMeters) {
    this.co2Amount = co2Amount;
    this.requiredSurfaceSqMeters = requiredSurfaceSqMeters;
  }

  public SurfaceResult co2Amount(Double co2Amount) {
    this.co2Amount = co2Amount;
    return this;
  }

  /**
   * Get co2Amount
   * @return co2Amount
  */
  @NotNull 
  @Schema(name = "co2Amount", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("co2Amount")
  public Double getCo2Amount() {
    return co2Amount;
  }

  public void setCo2Amount(Double co2Amount) {
    this.co2Amount = co2Amount;
  }

  public SurfaceResult requiredSurfaceSqMeters(Double requiredSurfaceSqMeters) {
    this.requiredSurfaceSqMeters = requiredSurfaceSqMeters;
    return this;
  }

  /**
   * Get requiredSurfaceSqMeters
   * @return requiredSurfaceSqMeters
  */
  @NotNull 
  @Schema(name = "requiredSurfaceSqMeters", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("requiredSurfaceSqMeters")
  public Double getRequiredSurfaceSqMeters() {
    return requiredSurfaceSqMeters;
  }

  public void setRequiredSurfaceSqMeters(Double requiredSurfaceSqMeters) {
    this.requiredSurfaceSqMeters = requiredSurfaceSqMeters;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SurfaceResult surfaceResult = (SurfaceResult) o;
    return Objects.equals(this.co2Amount, surfaceResult.co2Amount) &&
        Objects.equals(this.requiredSurfaceSqMeters, surfaceResult.requiredSurfaceSqMeters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(co2Amount, requiredSurfaceSqMeters);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SurfaceResult {\n");
    sb.append("    co2Amount: ").append(toIndentedString(co2Amount)).append("\n");
    sb.append("    requiredSurfaceSqMeters: ").append(toIndentedString(requiredSurfaceSqMeters)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

