package qajava.lessons.patterns.behavioural;

/**
 * State pattern defines how to change behaviour(strategy) of entire GROUP of methods within one context.
 * To be fully decoupled, state usually implements all interaction methods.
 * That way we can change behaviour of an object in its entirety just by swapping its state.
 * <p>
 * State pattern is usually associated with implementation of a State Machine.
 * State Machine is broadly used architectural pattern where we design our work to be done (app in our case)
 * as graph of state switches which depend on interaction.
 * <p>
 * Another way to implement State Machine and if we are working with group of objects,
 * is through Mediator pattern by applying State pattern to it,
 * or we can express state change by simple changes currentState variable and adjust mediator behavior based on its value.
 */
public class State {
  //example code
  public static void main(String[] args) {
    //since we defined our state machine through state changes in classes bellow
    //we can now have fun with our context objects (forms)

    //example1: create form and immediately delete it;
    Form form1 = new Form();
    form1.send();   //trying to send empty form for teh lulz
    form1.delete();
    form1.send();   //trying to send deleted form for teh lulz

    //example1: filling out the form and then sending it;
    Form form2 = new Form();
    form2.fill("Just started filling out the form...");
    form2.send();   //trying to send incomplete form for teh lulz
    form2.fill("Entering of form data is now done!");
    form2.send();
    form2.delete(); //trying to delete sent form for teh lulz
  }
}

//we need to define common API for states of our context object
//we can achieve that via interface or a base class
interface FormStateInterface {
  void fill(String data);
  boolean isValid();
  void send();
  void delete();
}

//This will be our demo context class that we will be swapping states of. Here is the flow of our state machine that we will implement:
//When we first create a Form it is in a state of NEW
//Once we enter some data, Form enters DRAFT state
//If entered data isValid, we can send our Form, and then it enters SENT state.
//At any time before sending, we can delete our form and then ig goes to DELETED state.
class Form {
  FormStateInterface state;
  String formData;

  public Form() {
    //one way to go around swapping states is to always create a new state object
    //ooor we could make them all once per parent object and store them all within parent object fields
    //ooooor we could make them into a singletons with pure functions, by always passing context object as first argument etc.etc.
    this.state = new NewFormState(this);
  }

  public void setState(FormStateInterface state) {
    this.state = state;
  }

  public String getFormData() {
    return formData;
  }

  public void setFormData(String formData) {
    this.formData = formData;
  }

  //As you can see, here whole behaviour (except setters and getters) of a context object is dictated by the state in which it is.
  //This does not have to always be the case. For example, there may be part of API that remains same irrelevant of current state.
  public void fill(String data) {
    state.fill(data);
  }

  public boolean isValid() {
    return state.isValid();
  }

  public void send() {
    state.send();
  }

  public void delete() {
    state.delete();
  }
}

class NewFormState implements FormStateInterface {
  final Form context;

  public NewFormState(Form context) {
    System.out.println("New form created!");
    this.context = context;
  }

  @Override
  public void fill(String data) {
    System.out.println("Entering data: " + data);
    context.setFormData(data);
    context.setState(new DraftFormState(context));
    System.out.println("Form saved as a DRAFT.");
  }

  @Override
  public boolean isValid() {
    //form is empty so of course it is not valid
    return false;
  }

  @Override
  public void send() {
    System.out.println("Error: cannot send an empty form!");
  }

  @Override
  public void delete() {
    System.out.println("Deleting empty form...");
    context.setState(DeletedFormState.getInstance());
    System.out.println("Form deleted...");
  }
}

class DraftFormState implements FormStateInterface {
  final Form context;

  public DraftFormState(Form context) {
    this.context = context;
  }

  @Override
  public void fill(String data) {
    System.out.println("Entering data: " + data);
    context.setFormData(data);
    System.out.println("Form saved as a DRAFT.");
  }

  @Override
  public boolean isValid() {
    //for demo purposes, we just look for form data to contain word "done"
    return context.getFormData().contains("done");
  }

  @Override
  public void send() {
    if (isValid()) {
      System.out.println("Form is complete, sending...");
      context.setState(SentFormState.getInstance());
      System.out.println("Form sent...");
    } else {
      System.out.println("Error: Form data is incomplete, finish it before sending...");
    }
  }

  @Override
  public void delete() {
    System.out.println("Deleting form...");
    context.setState(DeletedFormState.getInstance());
    System.out.println("Form deleted...");
  }
}

//SENT and DELETED states are end states which effectively lock the context object, but do not interact with it.
//As such they can be singletons :D
class SentFormState implements FormStateInterface {
  private static class InstanceHolder {
    static final SentFormState INSTANCE = new SentFormState();
  }

  static SentFormState getInstance() {
    return SentFormState.InstanceHolder.INSTANCE;
  }

  @Override
  public void fill(String data) {
    System.out.println("Error: form is sent and cannot be interacted with.");
  }

  @Override
  public boolean isValid() {
    System.out.println("Error: form is sent and cannot be interacted with.");
    return true;
  }

  @Override
  public void send() {
    System.out.println("Error: form is already sent.");
  }

  @Override
  public void delete() {
    System.out.println("Error: sent form cannot be deleted!");
  }
}

class DeletedFormState implements FormStateInterface {
  private DeletedFormState() {}

  private static class InstanceHolder {
    static final DeletedFormState INSTANCE = new DeletedFormState();
  }

  static DeletedFormState getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public void fill(String data) {
    System.out.println("Error: form is deleted and cannot be interacted with.");
  }

  @Override
  public boolean isValid() {
    System.out.println("Error: form is deleted and cannot be interacted with.");
    return false;
  }

  @Override
  public void send() {
    System.out.println("Error: deleted form cannot be sent!");
  }

  @Override
  public void delete() {
    System.out.println("Error: form is already deleted.");
  }
}
