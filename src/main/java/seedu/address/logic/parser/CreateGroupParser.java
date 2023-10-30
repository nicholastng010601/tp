package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPTAG;

import java.util.stream.Stream;

import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;

/**
 * Parses input arguments and creates a new newCommand object
 */
public class CreateGroupParser implements Parser<CreateGroupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CreateGroupCommand
     * and returns a CreateGroupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public CreateGroupCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_GROUPTAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_GROUPTAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateGroupCommand.MESSAGE_USAGE));
        }

        String groupName = ParserUtil.parseGroupName(argMultimap.getValue(PREFIX_GROUPTAG).get());
        Group group = new Group(groupName);

        return new CreateGroupCommand(group);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
