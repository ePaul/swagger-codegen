package io.swagger.client.model;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * MapTest
 */

public class MapTest   {
  
  private Map<String, Map<String, String>> mapMapOfString = new HashMap<String, Map<String, String>>();

  /**
   * Gets or Sets mapMapOfEnum
   */
  public enum Map&lt;String, Map&lt;String, InnerEnum&gt;&gt; {
    

    private Map&lt;String, Map&lt;String, String&gt;&gt; value;

    Map&lt;String, Map&lt;String, InnerEnum&gt;&gt;(Map&lt;String, Map&lt;String, String&gt;&gt; value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }
  }


  /**
   * Gets or Sets inner
   */
  public enum Map&lt;String, InnerEnum&gt; {
    

    private Map&lt;String, String&gt; value;

    Map&lt;String, InnerEnum&gt;(Map&lt;String, String&gt; value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }
  }

  private Map<String, Map<String, InnerEnum>> mapMapOfEnum = new HashMap<String, InnerEnum><String, Map<String, InnerEnum><String, String>>();

  /**
   * Gets or Sets mapOfEnumString
   */
  public enum Map&lt;String, InnerEnum&gt; {
    

    private Map&lt;String, String&gt; value;

    Map&lt;String, InnerEnum&gt;(Map&lt;String, String&gt; value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }
  }


  /**
   * Gets or Sets inner
   */
  public enum InnerEnum {
    UPPER("UPPER"),
    LOWER("lower");

    private String value;

    InnerEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }
  }

  private Map<String, InnerEnum> mapOfEnumString = new HashMap<InnerEnum, InnerEnum>();

  
  /**
   **/
  public MapTest mapMapOfString(Map<String, Map<String, String>> mapMapOfString) {
    this.mapMapOfString = mapMapOfString;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "")
  @JsonProperty("map_map_of_string")
  public Map<String, Map<String, String>> getMapMapOfString() {
    return mapMapOfString;
  }
  public void setMapMapOfString(Map<String, Map<String, String>> mapMapOfString) {
    this.mapMapOfString = mapMapOfString;
  }


  /**
   **/
  public MapTest mapMapOfEnum(Map<String, Map<String, InnerEnum>> mapMapOfEnum) {
    this.mapMapOfEnum = mapMapOfEnum;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "")
  @JsonProperty("map_map_of_enum")
  public Map<String, Map<String, InnerEnum>> getMapMapOfEnum() {
    return mapMapOfEnum;
  }
  public void setMapMapOfEnum(Map<String, Map<String, InnerEnum>> mapMapOfEnum) {
    this.mapMapOfEnum = mapMapOfEnum;
  }


  /**
   **/
  public MapTest mapOfEnumString(Map<String, InnerEnum> mapOfEnumString) {
    this.mapOfEnumString = mapOfEnumString;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "")
  @JsonProperty("map_of_enum_string")
  public Map<String, InnerEnum> getMapOfEnumString() {
    return mapOfEnumString;
  }
  public void setMapOfEnumString(Map<String, InnerEnum> mapOfEnumString) {
    this.mapOfEnumString = mapOfEnumString;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MapTest mapTest = (MapTest) o;
    return Objects.equals(this.mapMapOfString, mapTest.mapMapOfString) &&
        Objects.equals(this.mapMapOfEnum, mapTest.mapMapOfEnum) &&
        Objects.equals(this.mapOfEnumString, mapTest.mapOfEnumString);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mapMapOfString, mapMapOfEnum, mapOfEnumString);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MapTest {\n");
    
    sb.append("    mapMapOfString: ").append(toIndentedString(mapMapOfString)).append("\n");
    sb.append("    mapMapOfEnum: ").append(toIndentedString(mapMapOfEnum)).append("\n");
    sb.append("    mapOfEnumString: ").append(toIndentedString(mapOfEnumString)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

