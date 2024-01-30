package DL;

import UI.ApplicationException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ErrorLogHandler {
    private static final String LOG_FILE_PATH = "src/log.txt";
    private static final File file = new File(LOG_FILE_PATH);

    public static void add(Exception exception) {
        try (FileWriter writer = new FileWriter(file, true)) {
            if (isFileNotEmpty()) {
                writer.append("\n");
            }
            writer.append(format(exception));
        } catch (IOException e) {
            throw new ApplicationException();
        }
    }

    private static boolean isFileNotEmpty() {
        return file.length() != 0;
    }

    private static String format(Exception exception) {
        StackTraceElement[] stackTrace = exception.getStackTrace();
        String a = Arrays.stream(stackTrace)
                .map(trace -> trace.toString() + "\n")
                .collect(Collectors.joining());
        StringBuilder builder = new StringBuilder();
        builder.append("\n*****\n[").append(LocalDateTime.now()).append("] ");
        if (stackTrace.length > 0) {
            StackTraceElement firstStackTraceElement = stackTrace[0];
            builder.append(a)
                    .append("\n")
                    .append(exception.getClass().getSimpleName())
                    .append(": ")
                    .append(exception.getMessage());
        } else {
            builder.append("No stack trace information available.");
        }
        return builder.toString();
    }
}
