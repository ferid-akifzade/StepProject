import controller.CommandParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandParserTest {

    @Test
    void parseComand() {
        CommandParser commandParser = new CommandParser("-s     55,  3");
        assertEquals("s", commandParser.getCommand().toString());
        assertEquals("55", commandParser.getData().get(0).toString());
        assertEquals("3", commandParser.getData().get(1).toString());
    }
}