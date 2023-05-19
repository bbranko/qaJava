package qajava.lessons.patterns.creational;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Builder pattern adds flexibility to object construction.
 * It is especially useful if class has a lot of fields and/or when created object needs to be read only.
 * <p>
 * Builder pattern is preferred to constructors with arguments.
 */
public class Builder {
  //usage example
  public static void main(String[] args) {
    BuilderPatternInPureJava builtObject = BuilderPatternInPureJava.builder()
      .setNumber(10)
      .setText("text")
      .build();
    System.out.println(builtObject.getNumber());

    BuilderPatternWithLombok builtObjectLombok = BuilderPatternWithLombok.builder()
      .number(10)
      .text("text")
      .build();
    System.out.println(builtObjectLombok.getNumber());

    BuilderPatternWithLombokAndRecord builtObjectRecord = BuilderPatternWithLombokAndRecord.builder()
      .number(10)
      .text("text")
      .build();
    System.out.println(builtObjectRecord.number());
  }
}


class BuilderPatternInPureJava {

  //Dummy read-only class definition, imagine there are a lot of fields
  private final Integer number;
  private final String text;

  //constructor can be private, to force only means of creation class through builder
  //note:
  //- lack of no args constructor breaks Bean standard
  //- if constructor is private, Builder has to be defined in inner class to have access to it
  private BuilderPatternInPureJava(Integer number, String text) {
    this.number = number;
    this.text = text;
  }

  public Integer getNumber() {
    return number;
  }

  public String getText() {
    return text;
  }
  //

  //Builder is usually defined as inner class, though it does not have to be
  public static class Builder {

    //builder usually holds all field data based on which object should be constructed
    private Integer number;
    private String text;

    //builder only has no args constructor
    public Builder() {
    }

    //..., setter methods that return this to allow method chaining
    public Builder setNumber(Integer number) {
      this.number = number;
      return this;
    }

    public Builder setText(String text) {
      this.text = text;
      return this;
    }

    //...and build method that actually constructs a desired object.
    public BuilderPatternInPureJava build() {
      return new BuilderPatternInPureJava(number, text);
    }
  }

  //we need some means to get a hold of a builder, and that method is usually called "builder"
  public static Builder builder() {
    return new Builder();
  }
}


//irrelevant if fields are final or not, for Lombok @Builder, presence of @AllArgsConstructor is mandatory
@AllArgsConstructor
@lombok.Builder
@Getter
class BuilderPatternWithLombok {
  private final Integer number;
  private final String text;
}


//Bonus: for read only objects, we have record as data type since Java 14 :)
@lombok.Builder
record BuilderPatternWithLombokAndRecord(Integer number, String text) {
}
