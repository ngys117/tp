package seedu.duke.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import seedu.duke.util.Configuration;
import seedu.duke.util.StringConstants;
import static seedu.duke.util.Configuration.ConfigurationGroup.COMPLETED_TASKS_SHOWN;




public class ConfigurationStorageTest {

    private final String path = StringConstants.CONFIGURATION_TEST_PATH;
    private ConfigurationStorage configurationStorage;
    private Configuration configuration;

    @BeforeEach
    public void setUp() {
        configurationStorage = new ConfigurationStorage();
        configuration = new Configuration();
    }

    @Test
    public void modifyConfig_saveAndReload() {
        try {
            assertEquals("false", configuration.getConfigurationValue(COMPLETED_TASKS_SHOWN));
            configuration.configurationGroupHashMap.put(COMPLETED_TASKS_SHOWN, "true");
            configurationStorage.writeData(configuration, path);
            Configuration loadedConfiguration = configurationStorage.loadData(path);
            assertEquals(configuration.getConfigurationsReport(), loadedConfiguration.getConfigurationsReport());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

}