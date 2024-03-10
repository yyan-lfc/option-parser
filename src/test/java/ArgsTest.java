import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArgsTest {
    @Test
    void should_parse_multi_options() {
        Options options = Args.parse(Options.class, "-l", "-p", "8080", "-d", "/usr/logs");

        assertTrue(options.logging());
        assertEquals(8080, options.port());
        assertEquals("/usr/logs", options.directory());
    }

    @Test
    void should_set_boolean_option_true_if_flag_present() {
        BooleanOption option = Args.parse(BooleanOption.class, "-l");

        assertTrue(option.logging());
    }

    @Test
    void should_set_boolean_option_false_if_flag_not_present() {
        BooleanOption option = Args.parse(BooleanOption.class);

        assertFalse(option.logging());
    }

    @Test
    void should_parse_int_as_option_value() {
        IntOption option = Args.parse(IntOption.class, "-p", "8080");

        assertEquals(8080, option.port());

    }

    @Test
    void should_get_directory_as_option_value() {
        StringOption option = Args.parse(StringOption.class, "-d", "/usr/log");

        assertEquals("/usr/log", option.directory());
    }

    static record IntOption(@Option("p") int port) {}
    static record BooleanOption(@Option("l") boolean logging) {}
    static record StringOption(@Option("d") String directory){}
    static record Options(@Option("l") boolean logging, @Option("p") int port, @Option("d") String directory) { }
}
