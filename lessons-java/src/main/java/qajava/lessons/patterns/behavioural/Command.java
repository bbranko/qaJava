package qajava.lessons.patterns.behavioural;

import java.util.ArrayList;
import java.util.List;

/**
 * Command pattern defines how to encapsulate a behaviour(strategy) for purposes of delayed execution, queueing, reuse etc..
 */
public class Command {
  //example code
  public static void main(String[] args) {
    //imagine we designed a system where we unified execution of all tasks by passing them to task executor
    TaskExecutor taskExecutor = new TaskExecutor();

    //for example, we can have then tasks(commands) generated from different parts of our application, ie
    //imagine periodic tasks doing maintenance on DB
    CommandInterface dbTask = new DbTask("long SQL script");
    taskExecutor.addTask(dbTask);
    //...or user task being created due to user using our application
    CommandInterface userTask = new UserTask("custom user task", 10);
    taskExecutor.addTask(userTask);
    //...or admin requesting health check of all system services
    CommandInterface healthCheckTask = new HealthCheckTask();
    taskExecutor.addTask(healthCheckTask);

    //we add them all to our executor, and since each task(command) has all information needed for its execution
    //we can delay it, until execution is convenient (or possible)
    taskExecutor.executeAll();

    //Another good examples of Command pattern could be any handler code, such as for UI, chain of responsibility handler, etc.
    //If Strategy pattern considers how to swap algorithms at runtime within one context,
    // Command is interested how to wrap Strategy and all that it needs in an object to be executed in any context.
    //Note that, Command, same as Strategy, follows single responsibility principle.
  }
}

interface CommandInterface{
  void execute();
}

class DbTask implements CommandInterface{
  final String sqlScript;

  public DbTask(String sqlScript) {
    this.sqlScript = sqlScript;
  }

  @Override
  public void execute() {
    System.out.println("Executing: " + sqlScript);
  }
}

class UserTask implements CommandInterface{
  final String task;
  final int priority;

  public UserTask(String task, int priority) {
    this.task = task;
    this.priority = priority;
  }

  @Override
  public void execute() {
    System.out.println("Executing: " + task + " with priority " + priority);
  }
}
class HealthCheckTask implements CommandInterface{
  @Override
  public void execute() {
    System.out.println("Executing health check... All systems ok!");
  }
}

class TaskExecutor{

  List<CommandInterface> taskQueue = new ArrayList<>();

  public void addTask(CommandInterface dbTask) {
    taskQueue.add(dbTask);
  }

  public void executeAll() {
    for (CommandInterface task : taskQueue){
      task.execute();
    }
  }
}
