package victor.training.cleancode;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

class SomeController {
  SomeService someService;

  @GetMapping("blue/{storeId}")
  public void blueEndpoint(@PathVariable int storeId, @RequestBody Task task) {
    someService.blueMethod(storeId, task);
  }

  @GetMapping("red/{storeId}")
  public void redEndpoint(@PathVariable int storeId, @RequestBody Task task) {
    someService.redMethod(storeId, task);
  }
}

class SomeService {
  public void blueMethod(int id, Task task) {
    BooleanParameters.bigUglyMethod(id, task);
  }

  public void greenMethod(int id, Task task) {
    BooleanParameters.bigUglyMethod(id, task);
  }

  public void yellowMethod(int id, Task task) {
    BooleanParameters.bigUglyMethod(id, task);
  }

  public void redMethod(int id, Task task) {
    BooleanParameters.bigUglyMethod(id, task);
  }
}

class MyService {
  public void useCase323(int id, Task task) {
    // TODO The shared called method must execute logic specific for my use-case #323
    BooleanParameters.bigUglyMethod(id, task);
  }
}

public class BooleanParameters {

  public static final String SPACE = " ";

  // Warning⚠️: this method might be called from multiple places in the codebase ...
  public static void bigUglyMethod(int storeId, Task task) {
    System.out.println("Cow" + SPACE + "Logic 1 " + task + SPACE + "and " + storeId);
    System.out.println(task);
    System.out.println("Cow" + SPACE + "Logic 3 " + task);

    // System.out.println("Logic just for CR#323 : " + task);

    System.out.println("Donkey" + SPACE + "Logic 1 " + storeId);
    System.out.println("Donkey" + SPACE + "Logic 2 " + storeId);
    System.out.println("Donkey" + SPACE + "Logic 3 " + storeId);
  }


  // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

  // Lord gave us tests! 👌 TODO run the tests
  public void bossLevel(boolean fluff, List<Task> tasks, boolean cr323) {
    int index = 0;
    int taskCount = tasks.size();
    System.out.println("Logic1" + SPACE);
    List<Integer> taskIds = new ArrayList<>();
    if (fluff) {
      System.out.println("Logic3" + SPACE);
      for (Task task : tasks) {
        System.out.println("Starting" + SPACE + task);
        task.setStarted(true);

        taskIds.add(task.getId());

        if (cr323) { // TODO task = remove the boolean
          System.out.println("My" + SPACE + "Logic: " + task);
        }

        index++;
        System.out.println("Audit" + SPACE + "task #" + index + ":" + SPACE + task);
      }
      System.out.println("Logic6" + SPACE + taskCount);
      System.out.println("Task" + SPACE + "Ids: " + taskIds);
    } else {
      System.out.println("Logic7" + SPACE + "on fluff=false " + tasks);
    }
    System.out.println("Logic8");
  }

}


@Data
class Task {
  private final int id;
  private boolean started;
}