package io.swagger.client.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.ReadOnlyFirst;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;





public class ArrayTest   {
  
  @SerializedName("array_of_string")
  private List<String> arrayOfString = new ArrayList<String>();

  @SerializedName("array_array_of_integer")
  private List<List<Long>> arrayArrayOfInteger = new ArrayList<List<Long>>();

  @SerializedName("array_array_of_model")
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
    public String toString() {
      return String.valueOf(value);
    }
  }


  /**
   * Gets or Sets arrayOfEnum
   */
  public enum ArrayOfEnumEnum {
    @SerializedName("UPPER")
    UPPER("UPPER"),

    @SerializedName("lower")
    LOWER("lower");

    private String value;

    ArrayOfEnumEnum(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
  }

  @SerializedName("array_of_enum")
  private List<ArrayOfEnumEnum> arrayOfEnum = new ArrayList<ArrayOfEnumEnum>();

  /**
   **/
  @ApiModelProperty(value = "")
  public List<String> getArrayOfString() {
    return arrayOfString;
  }
  public void setArrayOfString(List<String> arrayOfString) {
    this.arrayOfString = arrayOfString;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public List<List<Long>> getArrayArrayOfInteger() {
    return arrayArrayOfInteger;
  }
  public void setArrayArrayOfInteger(List<List<Long>> arrayArrayOfInteger) {
    this.arrayArrayOfInteger = arrayArrayOfInteger;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public List<List<ReadOnlyFirst>> getArrayArrayOfModel() {
    return arrayArrayOfModel;
  }
  public void setArrayArrayOfModel(List<List<ReadOnlyFirst>> arrayArrayOfModel) {
    this.arrayArrayOfModel = arrayArrayOfModel;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public List<ArrayOfEnumEnum> getArrayOfEnum() {
    return arrayOfEnum;
  }
  public void setArrayOfEnum(List<ArrayOfEnumEnum> arrayOfEnum) {
    this.arrayOfEnum = arrayOfEnum;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ArrayTest arrayTest = (ArrayTest) o;
    return Objects.equals(arrayOfString, arrayTest.arrayOfString) &&
        Objects.equals(arrayArrayOfInteger, arrayTest.arrayArrayOfInteger) &&
        Objects.equals(arrayArrayOfModel, arrayTest.arrayArrayOfModel) &&
        Objects.equals(arrayOfEnum, arrayTest.arrayOfEnum);
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
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
