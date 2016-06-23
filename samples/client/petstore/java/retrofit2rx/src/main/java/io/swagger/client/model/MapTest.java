package io.swagger.client.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;





public class MapTest   {
  
  @SerializedName("map_map_of_string")
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
    public String toString() {
      return String.valueOf(value);
    }
  }

  @SerializedName("map_map_of_enum")
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
    public String toString() {
      return String.valueOf(value);
    }
  }


  /**
   * Gets or Sets inner
   */
  public enum InnerEnum {
    @SerializedName("UPPER")
    UPPER("UPPER"),

    @SerializedName("lower")
    LOWER("lower");

    private String value;

    InnerEnum(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
  }

  @SerializedName("map_of_enum_string")
  private Map<String, InnerEnum> mapOfEnumString = new HashMap<InnerEnum, InnerEnum>();

  /**
   **/
  @ApiModelProperty(value = "")
  public Map<String, Map<String, String>> getMapMapOfString() {
    return mapMapOfString;
  }
  public void setMapMapOfString(Map<String, Map<String, String>> mapMapOfString) {
    this.mapMapOfString = mapMapOfString;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public Map<String, Map<String, InnerEnum>> getMapMapOfEnum() {
    return mapMapOfEnum;
  }
  public void setMapMapOfEnum(Map<String, Map<String, InnerEnum>> mapMapOfEnum) {
    this.mapMapOfEnum = mapMapOfEnum;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public Map<String, InnerEnum> getMapOfEnumString() {
    return mapOfEnumString;
  }
  public void setMapOfEnumString(Map<String, InnerEnum> mapOfEnumString) {
    this.mapOfEnumString = mapOfEnumString;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MapTest mapTest = (MapTest) o;
    return Objects.equals(mapMapOfString, mapTest.mapMapOfString) &&
        Objects.equals(mapMapOfEnum, mapTest.mapMapOfEnum) &&
        Objects.equals(mapOfEnumString, mapTest.mapOfEnumString);
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
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
