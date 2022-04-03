package seedu.duke;

import org.junit.jupiter.api.Test;

import seedu.duke.data.Module;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.data.ModuleList;
import seedu.duke.data.Task;
import seedu.duke.exceptions.ModHappyException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

public class UnitTest {

    static class TestSystem {
        private ModuleList moduleList = new ModuleList();

        private TestSystem() {
            Module testModule = new Module("CS2113T", null, 4);
            Module testModule2 = new Module("CS2101", null, 4);
            Task testTask = null;
            try {
                testTask = new Task("taskName", "taskDescription", "1h");
            } catch (ModHappyException e) {
                fail();
            }
            moduleList.addModule(testModule);
            moduleList.addModule(testModule2);
            moduleList.getGeneralTasks().addTask(testTask);
        }

        @Test
        public void deleteModuleWithNoTasks_successfully() {
            try {
                TestSystem testSystem = new TestSystem();
                DeleteCommand c = new DeleteCommand("CS2113T");
                c.deleteModule(testSystem.moduleList);
                assertNull(testSystem.moduleList.getModule("CS2113T"));
            } catch (ModHappyException e) {
                fail();
            }
        }

        @Test
        public void deleteGeneralTask_successfully() {
            try {
                TestSystem testSystem = new TestSystem();
                DeleteCommand c = new DeleteCommand(0, null);
                c.deleteTaskFromModule(testSystem.moduleList.getGeneralTasks());
                assertEquals(testSystem.moduleList.getGeneralTasks().getTaskList().size(), 0);
            } catch (ModHappyException e) {
                fail();
            }
        }
    }
}
