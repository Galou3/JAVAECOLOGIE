package org.forest.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.forest.api.model.ForestType;
import org.forest.api.model.Tree;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ForestInput
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-17T16:23:41.231448500+01:00[Europe/Paris]")
public class ForestInput {

  private ForestType type;

  @Valid
  private List<@Valid Tree> trees = new ArrayList<>();

  private Double surface;

  public ForestInput() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ForestInput(ForestType type, List<@Valid Tree> trees, Double surface) {
    this.type = type;
    this.trees = trees;
    this.surface = surface;
  }

  public ForestInput type(ForestType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  */
  @NotNull @Valid 
  @Schema(name = "type", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public ForestType getType() {
    return type;
  }

  public void setType(ForestType type) {
    this.type = type;
  }

  public ForestInput trees(List<@Valid Tree> trees) {
    this.trees = trees;
    return this;
  }

  public ForestInput addTreesItem(Tree treesItem) {
    if (this.trees == null) {
      this.trees = new ArrayList<>();
    }
    this.trees.add(treesItem);
    return this;
  }

  /**
   * Get trees
   * @return trees
  */
  @NotNull @Valid 
  @Schema(name = "trees", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("trees")
  public List<@Valid Tree> getTrees() {
    return trees;
  }

  public void setTrees(List<@Valid Tree> trees) {
    this.trees = trees;
  }

  public ForestInput surface(Double surface) {
    this.surface = surface;
    return this;
  }

  /**
   * Get surface
   * @return surface
  */
  @NotNull 
  @Schema(name = "surface", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("surface")
  public Double getSurface() {
    return surface;
  }

  public void setSurface(Double surface) {
    this.surface = surface;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ForestInput forestInput = (ForestInput) o;
    return Objects.equals(this.type, forestInput.type) &&
        Objects.equals(this.trees, forestInput.trees) &&
        Objects.equals(this.surface, forestInput.surface);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, trees, surface);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ForestInput {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    trees: ").append(toIndentedString(trees)).append("\n");
    sb.append("    surface: ").append(toIndentedString(surface)).append("\n");
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

