package seedu.duke.storage;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import seedu.duke.tasks.Task;


public class TaskListStorageTest {
    private TaskListStorage taskListStorage;
    private ArrayList<seedu.duke.tasks.Task> taskList;
    private String path = "data/task.json";

    @BeforeEach
    public void setUp() {
        taskListStorage = new TaskListStorage();
        taskList = new ArrayList<>();
    }

    @Test
    public void store_empty_module_list_and_read() {
        try {
            String path = "data/task.json";
            taskListStorage.jsonWriter(taskList, path);
            ArrayList<Task> list = taskListStorage.jsonReader(path);
            assertTrue(list.containsAll(taskList) && taskList.containsAll(list));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void store_task_list_and_read() {
        try {
            Task task1 = new Task("t1", "dt1", "2h");
            Task task2 = new Task("t2", "dt2", "3h");
            Task task3 = new Task("t3", "dt3", "4h");
            taskList.add(task1);
            taskList.add(task2);
            taskList.add(task3);
            String path = "data/task.json";
            taskListStorage.jsonWriter(taskList, path);
            ArrayList<seedu.duke.tasks.Task> list = taskListStorage.jsonReader(path);
            assertEquals(list.size(), taskList.size());
            for (int i = 0; i < list.size(); i++) {
                assertEquals(list.get(i).getTaskName(), taskList.get(i).getTaskName());
                assertEquals(list.get(i).getTaskDescription(), taskList.get(i).getTaskDescription());
                assertEquals(list.get(i).getEstimatedWorkingTime(), taskList.get(i).getEstimatedWorkingTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

}