package voicespeaker;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;

public class UrlArgumentType implements ArgumentType<String> {
    private static final Collection<String> EXAMPLE_URL = Arrays.asList("https://example.org/test.ogg");

    public static String getString(final CommandContext<?> context, final String name) {
        return context.getArgument(name, String.class);
    }

    public static UrlArgumentType string() {
        return new UrlArgumentType();
    }

    @Override
    public String parse(StringReader reader) throws CommandSyntaxException {
        final int start = reader.getCursor();
        final String result = reader.readString();
        if (Regexer.isURI(result)) {
            reader.setCursor(start);
            throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.dispatcherUnknownArgument().createWithContext(reader);
        }
        return result;
    }

    public static Collection<String> getExampleUrl() {
        return EXAMPLE_URL;
    }
}

class Regexer {
    public static boolean isURI(String input) {
        String uriRegex = "^(?:(?:https?|ftp)://)?[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(?:/[^\\s]*)?$";
        return Pattern.matches(uriRegex, input);
    }
}
