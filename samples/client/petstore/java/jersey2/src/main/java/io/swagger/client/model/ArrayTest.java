package io.swagger.client.model;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.ReadOnlyFirst;
import java.util.ArrayList;
import java.util.List;


/**
 * ArrayTest
 */

public class ArrayTest   {
  
  private List<String> arrayOfString = new ArrayList<String>();
  private List<List<Long>> arrayArrayOfInteger = new ArrayList<List<Long>>();
  private List<List<ReadOnlyFirst>> arrayArrayOfModel = new ArrayList<List<ReadOnlyFirst>>();

  /**
   * Gets or Sets arrayOfEnum
   */
  public enum List&lt;ArrayOfEnumEnum&gt; {
    

    private List&lt;String&gt; value;

    List&lt;ArrayOfEnumEnum&gt;(List&lt;String&gt; value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }
  }


  /**
   * Gets or Sets arrayOfEnum
   */
  public enum ArrayOfEnumEnum {
    UPPER("UPPER"),
    LOWER("lower");

    private String value;

    ArrayOfEnumEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }
  }

  private List<ArrayOfEnumEnum> arrayOfEnum = new ArrayList<ArrayOfEnumEnum>();

  
  /**
   **/
  public ArrayTest arrayOfString(List<String> arrayOfString) {
    this.arrayOfString = arrayOfString;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "")
  @JsonProperty("array_of_string")
  public List<String> getArrayOfString() {
    return arrayOfString;
  }
  public void setArrayOfString(List<String> arrayOfString) {
    this.arrayOfString = arrayOfString;
  }


  /**
   **/
  public ArrayTest arrayArrayOfInteger(List<List<Long>> arrayArrayOfInteger) {
    this.arrayArrayOfInteger = arrayArrayOfInteger;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "")
  @JsonProperty("array_array_of_integer")
  public List<List<Long>> getArrayArrayOfInteger() {
    return arrayArrayOfInteger;
  }
  public void setArrayArrayOfInteger(List<List<Long>> arrayArrayOfInteger) {
    this.arrayArrayOfInteger = arrayArrayOfInteger;
  }


  /**
   **/
  public ArrayTest arrayArrayOfModel(List<List<ReadOnlyFirst>> arrayArrayOfModel) {
    this.arrayArrayOfModel = arrayArrayOfModel;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "")
  @JsonProperty("array_array_of_model")
  public List<List<ReadOnlyFirst>> getArrayArrayOfModel() {
    return arrayArrayOfModel;
  }
  public void setArrayArrayOfModel(List<List<ReadOnlyFirst>> arrayArrayOfModel) {
    this.arrayArrayOfModel = arrayArrayOfModel;
  }


  /**
   **/
  public ArrayTest arrayOfEnum(List<ArrayOfEnumEnum> arrayOfEnum) {
    this.arrayOfEnum = arrayOfEnum;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "")
  @JsonProperty("array_of_enum")
  public List<ArrayOfEnumEnum> getArrayOfEnum() {
    return arrayOfEnum;
  }
  public void setArrayOfEnum(List<ArrayOfEnumEnum> arrayOfEnum) {
    this.arrayOfEnum = arrayOfEnum;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ArrayTest arrayTest = (ArrayTest) o;
    return Objects.equals(this.arrayOfString, arrayTest.arrayOfString) &&
        Objects.equals(this.arrayArrayOfInteger, arrayTest.arrayArrayOfInteger) &&
        Objects.equals(this.arrayArrayOfModel, arrayTest.arrayArrayOfModel) &&
        Objects.equals(this.arrayOfEnum, arrayTest.arrayOfEnum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(arrayOfString, arrayArrayOfInteger, arrayArrayOfModel, arrayOfEnum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ArrayTest {\n");
    
    sb.append("    arrayOfString: ").append(toIndentedString(arrayOfString)).append("\n");
    sb.append("    arrayArrayOfInteger: ").append(toIndentedString(arrayArrayOfInteger)).append("\n");
    sb.append("    arrayArrayOfModel: ").append(toIndentedString(arrayArrayOfModel)).append("\n");
    sb.append("    arrayOfEnum: ").append(toIndentedString(arrayOfEnum)).append("\n");
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

