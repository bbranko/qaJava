package qajava.lessons.patterns.structural;

import lombok.Data;

/**
 * Adapts interface of a class to desired one.
 * <p>
 * We usually use adapter when:
 * - we need to adapt some legacy class to newer code
 * - we need to adapt response of one service to make it compatible for use with another service
 */
public class Adapter {
  //example usage
  public static void main(String[] args) {
    //Having instance of legacy class...
    OldClunkyClass oldClassObjectToBeAdapted = new OldClunkyClass();
    //...we wrap it in adapter...
    NewJobInterface adaptedClassObject = new AdapterForOldClunkyClass(oldClassObjectToBeAdapted);

    NewJobConfig config = new NewJobConfig(10, true); //(dummy config for demo purposes)
    //...and now we can use old class in a new way!
    adaptedClassObject.runNewJob(config);


    //Having instance of one service, we can wrap it to adapt response to incompatible service
    IntInterface concreteIntService = new IntService();
    StringInterface concreteStringService = new StringService();
    IntInterface adaptedStringService = new StringToIntAdapter(concreteStringService);
    //and now we can make them "communicate" in one common way
    adaptedStringService.setData(concreteIntService.getData());
    //where without adapter, this code errors out due to bad type:
    // concreteStringService.setData(concreteIntService.getData());

  }
}

//for adapting to legacy
class OldClunkyClass {
  String someParameterizedMethodToRunOldJob(Integer data, Boolean switch1, Boolean switch2) {
    return "SOME RESULT";
  }
}

//written like this, this config class could be a record! (Java 14+)
@Data
class NewJobConfig {
  final Integer data;
  final Boolean switch1;
}

interface NewJobInterface {
  String runNewJob(NewJobConfig config);
}

class AdapterForOldClunkyClass implements NewJobInterface {
  private final OldClunkyClass oldClunkyClass;

  //we can wrap old class in adapter as so:
  public AdapterForOldClunkyClass(OldClunkyClass oldClunkyClass) {
    this.oldClunkyClass = oldClunkyClass;
  }

  //we adapt to new interfaces by wrapping old method code
  @Override
  public String runNewJob(NewJobConfig config) {
    //we may have to adapt (process input data)
    Integer data = config.getData();
    Boolean switch1 = config.getSwitch1();
    Boolean switch2 = null;

    //we than call the old method
    String rawResult = oldClunkyClass.someParameterizedMethodToRunOldJob(data, switch1, switch2);

    //and we may have to adapt old method output
    String adaptedResult = rawResult.toLowerCase();
    return adaptedResult;
  }
}
//--


//for adapting incompatible services
interface IntInterface {
  Integer getData();
  void setData(Integer data);
}

interface StringInterface {
  String getData();
  void setData(String data);
}

class IntService implements IntInterface {
  Integer data;

  @Override
  public Integer getData() {
    return data;
  }

  @Override
  public void setData(Integer data) {
    this.data = data;
  }
}

class StringService implements StringInterface {
  String data;

  @Override
  public String getData() {
    return data;
  }

  @Override
  public void setData(String data) {
    this.data = data;
  }
}


class StringToIntAdapter implements IntInterface{
  final StringInterface stringServiceInstance;

  public StringToIntAdapter(StringInterface stringServiceInstance) {
    this.stringServiceInstance = stringServiceInstance;
  }

  @Override
  public Integer getData() {
    //adapt one response to the other
    String rawData =  stringServiceInstance.getData();
    Integer adaptedData = Integer.valueOf(rawData);
    return adaptedData;
  }

  @Override
  public void setData(Integer rawData) {
    String adaptedData = rawData.toString();
    stringServiceInstance.setData(adaptedData);
  }


}
//of course, we can implement adapter to work in another direction too...
