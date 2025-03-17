package org.forest.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * AbsorptionResult
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-17T16:23:41.231448500+01:00[Europe/Paris]")
public class AbsorptionResult {

  private UUID forestId;

  private Double absorptionKgPerYear;

  public AbsorptionResult() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public AbsorptionResult(UUID forestId, Double absorptionKgPerYear) {
    this.forestId = forestId;
    this.absorptionKgPerYear = absorptionKgPerYear;
  }

  public AbsorptionResult forestId(UUID forestId) {
    this.forestId = forestId;
    return this;
  }

  /**
   * Get forestId
   * @return forestId
  */
  @NotNull @Valid 
  @Schema(name = "forestId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("forestId")
  public UUID getForestId() {
    return forestId;
  }

  public void setForestId(UUID forestId) {
    this.forestId = forestId;
  }

  public AbsorptionResult absorptionKgPerYear(Double absorptionKgPerYear) {
    this.absorptionKgPerYear = absorptionKgPerYear;
    return this;
  }

  /**
   * Get absorptionKgPerYear
   * @return absorptionKgPerYear
  */
  @NotNull 
  @Schema(name = "absorptionKgPerYear", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("absorptionKgPerYear")
  public Double getAbsorptionKgPerYear() {
    return absorptionKgPerYear;
  }

  public void setAbsorptionKgPerYear(Double absorptionKgPerYear) {
    this.absorptionKgPerYear = absorptionKgPerYear;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AbsorptionResult absorptionResult = (AbsorptionResult) o;
    return Objects.equals(this.forestId, absorptionResult.forestId) &&
        Objects.equals(this.absorptionKgPerYear, absorptionResult.absorptionKgPerYear);
  }

  @Override
  public int hashCode() {
    return Objects.hash(forestId, absorptionKgPerYear);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AbsorptionResult {\n");
    sb.append("    forestId: ").append(toIndentedString(forestId)).append("\n");
    sb.append("    absorptionKgPerYear: ").append(toIndentedString(absorptionKgPerYear)).append("\n");
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

