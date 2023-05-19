package qajava.lessons.patterns.creational;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * More standard than a pattern. Bean must have:
 * - constructor with no arguments
 * - methods with 'get' and 'set' prefix for interaction
 * - (optional) inner state (fields), but if present they are private
 * - (optional) is serializable (by implementing Serializable interface)
 */
//example implementation:
public class Bean implements Serializable {
  private Integer field;

  //this can be omitted, since it is present by default
  public Bean() {
  }

  public Integer getField() {
    return field;
  }

  public void setField(Integer field) {
    this.field = field;
  }
}


//with lombok:
@Getter
@Setter
class LombokBean implements Serializable{
  private Integer field;
}


//with lombok shorter
@Data
class LombokDataBean implements Serializable{
  private Integer field;
}
//However, if final fields are present, no args constructor has to be manually added
//Furthermore, with @Data, custom toString, equals and hashCode methods are being generated too.


