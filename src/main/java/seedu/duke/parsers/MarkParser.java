package seedu.duke.parsers;

import java.util.HashMap;

import seedu.duke.commands.Command;
import seedu.duke.commands.MarkCommand;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ParseException;
import seedu.duke.util.StringConstants;

/**
 * This Parser supports the "mark" command.
 */
public class MarkParser extends Parser {
    private static final String FLAG = StringConstants.FLAG;
    private static final String TASK_INDEX = StringConstants.TASK_INDEX;
    private static final String TASK_MODULE = StringConstants.TASK_MODULE;
    private static final String COMPLETED_FLAG = StringConstants.COMPLETED_FLAG;
    private static final String UNCOMPLETED_FLAG = StringConstants.UNCOMPLETED_FLAG;

    // Unescaped regex for testing:
    // (?<flag>(c|u))\s+(?<taskIndex>\d+)(\s+-m\s+(?<taskModule>\w+))?
    private static final String MARK_FORMAT = "(?<flag>(c|u))\\s+(?<taskIndex>\\d+)(\\s+-m\\s+(?<taskModule>\\w+))?";

    public MarkParser() {
        super();
        // See also https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
        this.commandFormat = MARK_FORMAT;
        groupNames.add(FLAG);
        groupNames.add(TASK_INDEX);
        groupNames.add(TASK_MODULE);
    }

    /**
     * Parses user's input for "mark" command.
     *
     * @param userInput User input of completed flag or uncompleted flag, task index and task module.
     * @throws ModHappyException if completed flag or uncompleted flag is not detected
     */
    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        HashMap<String, String> parsedArguments = parseString(userInput);
        final String commandFlag = parsedArguments.get(FLAG);
        final String taskModule = parsedArguments.get(TASK_MODULE);
        try {
            // Account for the zero-indexing
            final int taskIndex = Integer.parseInt(parsedArguments.get(TASK_INDEX)) - 1;
            switch (commandFlag) {
            case (COMPLETED_FLAG):
                return new MarkCommand(taskIndex, taskModule, true);
            case (UNCOMPLETED_FLAG):
                return new MarkCommand(taskIndex, taskModule, false);
            default:
                throw new ParseException();
            }
        } catch (NumberFormatException e) {
            throw new ParseException();
        }
    }
}
