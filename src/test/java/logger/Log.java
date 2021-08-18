package logger;

import org.apache.log4j.*;

import java.io.IOException;

/**
 * contains all the methods to show the logs on console
 * save the logs in LogFile.txt.
 */
public class Log {
    private static final Logger LOGGER = Logger.getLogger("com/assignment/logger");
    private static PatternLayout layout = new PatternLayout("%d{dd MMM yyyy HH:mm:ss} %5p %c{1} - %m%n");
    private static FileAppender appender;
    private static ConsoleAppender consoleAppender;

    static {
        try {
            consoleAppender = new ConsoleAppender(layout, "System.out");
            appender= new FileAppender(layout,"LogFile.txt",true);
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * method to display errors in log.
     * @param exception stack trace of exception
     */
    public static void logError (String exception) {
        LOGGER.addAppender(appender);
        LOGGER.setLevel(Level.INFO);
        LOGGER.info("Exception :" +exception);
        LOGGER.info("-----------------------------------------------------------------------------------");
    }

    /**
     * method to display information in logs
     * @param message message to be displayed
     */
    public static void info(String message) {
        consoleAppender.setName("Console");
        LOGGER.addAppender(consoleAppender);
        LOGGER.addAppender(appender);
        LOGGER.setLevel(Level.INFO);
        LOGGER.info(message);
    }
}
