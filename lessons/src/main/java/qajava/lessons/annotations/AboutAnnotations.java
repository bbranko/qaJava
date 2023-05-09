package qajava.lessons.annotations;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.TYPE_PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class AboutAnnotations {

  public static void main(String[] args) {
    //processing annotation in runtime
    Class<Type> annotatedClass = Type.class;
    try {
      Annotation[] classAnnotations = annotatedClass.getAnnotations();
      for (Annotation annotation : classAnnotations) {
        System.out.println(annotation);
      }

      Field annotatedField = annotatedClass.getDeclaredField("field");
      System.out.println(Arrays.toString(annotatedField.getAnnotations()));

      //method must be public or different approach is needed
      Method annotatedMethod = annotatedClass.getMethod("method", Integer.class);
      System.out.println(Arrays.toString(annotatedMethod.getAnnotations()));

      Constructor<Type> annotatedConstructor = annotatedClass.getConstructor();
      System.out.println(Arrays.toString(annotatedConstructor.getAnnotations()));

    } catch (Exception e) {
      e.printStackTrace();
    }


    //processing annotations, and especially processing annotations at source level is out of scope <3
    //but here is the link if you want to know more:
    //https://www.baeldung.com/java-annotation-processing-builder
    //for example, this is how Lombok achieves class enchantment based on annotations you set to minimize boilerplate
    //Lombok adds its annotation processing classes through:
    // compileOnly and annotationProcessor dependency sourceSet in build.gradle :)


    //Lombok goodies:
    LombokDemo builtInstance = LombokDemo.builder()
      .data(10)
      .build();
    System.out.println(builtInstance);
  }

}

//annotations are metadata that can be attached to different parts of code
//example places where java.lang.@Deprecated can be used
@Deprecated
class DeprecatedClass {

  @Deprecated
  Integer deprecatedField;

  @Deprecated
  public DeprecatedClass() {
  }

  @Deprecated
  void deprecatedMethod(@Deprecated Integer deprecatedParameter) {

    @Deprecated
    Integer deprecatedLocalVariable;

  }

}


//only @Documented annotation will be included in
@Documented
@Retention(RetentionPolicy.RUNTIME)
//without @Target set, TYPE_PARAMETER and TYPE_USE is omitted - but it is bad practice to omit @Target anyway...
@Target(value = {TYPE, TYPE_PARAMETER, FIELD, CONSTRUCTOR, METHOD, PARAMETER, LOCAL_VARIABLE, TYPE_USE})
@interface MyCustomAnnotation {
  String value() default "";
}

@MyCustomAnnotation("onClass")
class Type<@MyCustomAnnotation("onGenericType") typeParameter> {

  @MyCustomAnnotation("onField")
  Integer field;

  @MyCustomAnnotation("onConstructor")
  public Type() {
  }

  @MyCustomAnnotation("onMethod")
  public void method(@MyCustomAnnotation Integer parameter) {

    @MyCustomAnnotation("onLocalVariable")
    Integer localVariable;

    List<@MyCustomAnnotation("onTypeUse") Type> list;

  }

}

/**
 * since Java 14...
 * It is intended as immutable storage type.
 * Effectively similar to @AllArgsConstructor and @Getter from Lombok on a class,
 * though defining a record is a bit wierd, and getters do not have `get` prefix.
 */
@MyCustomAnnotation
record Record() {}


@Builder
@AllArgsConstructor
@Getter
@ToString
//find list of all annotations here: https://projectlombok.org/features/
class LombokDemo {
  private Integer data;
}
