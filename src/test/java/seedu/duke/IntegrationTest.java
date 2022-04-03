package seedu.duke;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.duke.data.Module;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.data.ModuleList;
import seedu.duke.exceptions.NoSuchModuleException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

public class IntegrationTest {

    class ModuleStub extends Module {
        public ModuleStub(String moduleCode) {
            super(moduleCode);
        }

        public ModuleStub(String moduleCode, String moduleDescription, int modularCredit) {
            super(moduleCode, moduleDescription, modularCredit);
        }
    }

    class ModuleListStub extends ModuleList {

    }

    @Test
    public void removeModule_withNoTasks_successfully() {
        Module testModule = new ModuleStub("cs2113t", "testDescription", 4);
        ModuleListStub testModuleList = new ModuleListStub();
        testModuleList.addModule(testModule);
        assertEquals(testModuleList.getModuleList().get(0), testModule);
        DeleteCommand c = new DeleteCommand("cs2113t");
        try {
            c.deleteModule(testModuleList);
        } catch (NoSuchModuleException e) {
            fail();
        }
        assertNull(testModuleList.getModule("cs2113t"));
    }
}
