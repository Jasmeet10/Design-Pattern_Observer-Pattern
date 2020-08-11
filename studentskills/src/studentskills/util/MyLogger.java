package studentskills.util;

/***
 * Creating a logger for the below enums.
 */
public class MyLogger{

    // FIXME: Add more enum values as needed for the assignment
    public static enum DebugLevel { CONSTRUCTOR, FILE_PROCESSOR ,NODE_UPDATE, CONSOLE_WRITE, FILE_WRITE
    };

    private static DebugLevel debugLevel;


    // FIXME: Add switch cases for all the levels
    public static void setDebugValue (int levelIn) {
        switch (levelIn) {
            case 5: debugLevel = DebugLevel.FILE_WRITE; break;
            case 4: debugLevel = DebugLevel.CONSOLE_WRITE; break;
            case 3: debugLevel = DebugLevel.NODE_UPDATE; break;
            case 2: debugLevel = DebugLevel.CONSTRUCTOR; break;
            case 1: debugLevel = DebugLevel.FILE_PROCESSOR; break;
            // default: debugLevel = DebugLevel.NONE; break;
        }
    }

    public static void setDebugValue (DebugLevel levelIn) {
        debugLevel = levelIn;
    }

    public static void writeMessage (String message, DebugLevel levelIn ) {
        if (levelIn == debugLevel)
            System.out.println(message);
    }
    public static void Message (String message ) {
            System.out.println(message);
    }

    public String toString() {
        return "The debug level has been set to the following " + debugLevel;
    }
}