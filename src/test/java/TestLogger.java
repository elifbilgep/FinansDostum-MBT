import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TestLogger class for logging test execution details to a file.
 * This class provides methods to log test steps, execution time, and results.
 */
public class TestLogger {
    private static final String LOG_DIRECTORY = "test-logs";
    private static String logFilePath;
    private static PrintWriter logWriter;
    private static long testStartTime;
    private static long stepStartTime;

    /**
     * Initializes the logger and creates a log file with timestamp in the filename.
     *
     * @param testClassName The name of the test class being executed
     * @return The path to the created log file
     * @throws IOException If there's an error creating the log file
     */
    public static String initializeLogger(String testClassName) throws IOException {
        testStartTime = System.currentTimeMillis();

        // Create logs directory if it doesn't exist
        File logDir = new File(LOG_DIRECTORY);
        if (!logDir.exists()) {
            logDir.mkdirs();
        }

        // Create log file with timestamp
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());
        logFilePath = LOG_DIRECTORY + "/" + testClassName + "_" + timestamp + ".log";

        logWriter = new PrintWriter(new FileWriter(logFilePath));

        // Write header to log file
        logWriter.println("=========================================================================");
        logWriter.println("TEST EXECUTION LOG - " + testClassName);
        logWriter.println("Started at: " + new Date());
        logWriter.println("=========================================================================");
        logWriter.flush();

        return logFilePath;
    }

    /**
     * Logs the start of a test step.
     *
     * @param stepName The name of the step being executed
     */
    public static void logStepStart(String stepName) {
        if (logWriter != null) {
            stepStartTime = System.currentTimeMillis();
            logWriter.println("\n➡️ STARTING STEP: " + stepName);
            logWriter.flush();
        }
    }

    /**
     * Logs the end of a test step with execution time.
     *
     * @param stepName The name of the step that completed
     */
    public static void logStepEnd(String stepName) {
        if (logWriter != null) {
            long duration = System.currentTimeMillis() - stepStartTime;
            logWriter.println("✅ COMPLETED STEP: " + stepName + " (Duration: " + duration + " ms)");
            logWriter.flush();
        }
    }

    /**
     * Logs an error or exception that occurred during test execution.
     *
     * @param stepName The name of the step where the error occurred
     * @param message The error message
     * @param e The exception that was thrown
     */
    public static void logError(String stepName, String message, Throwable e) {
        if (logWriter != null) {
            logWriter.println("❌ ERROR in " + stepName + ": " + message);
            if (e != null) {
                logWriter.println("Exception details:");
                e.printStackTrace(logWriter);
            }
            logWriter.flush();
        }
    }

    /**
     * Logs a message or information during test execution.
     *
     * @param message The message to log
     */
    public static void logInfo(String message) {
        if (logWriter != null) {
            logWriter.println("ℹ️ " + message);
            logWriter.flush();
        }
    }

    /**
     * Finalizes the log file with test results and execution time.
     *
     * @param success Whether the test passed successfully
     * @param errors List of errors if the test failed
     */
    public static void finalizeLog(boolean success, java.util.List<String> errors) {
        if (logWriter != null) {
            long totalDuration = System.currentTimeMillis() - testStartTime;

            logWriter.println("\n=========================================================================");
            logWriter.println("TEST EXECUTION COMPLETED");
            logWriter.println("Result: " + (success ? "PASSED ✅" : "FAILED ❌"));
            if (!success && errors != null && !errors.isEmpty()) {
                logWriter.println("Errors encountered:");
                for (String error : errors) {
                    logWriter.println("  - " + error);
                }
            }
            logWriter.println("Total execution time: " + totalDuration + " ms");
            logWriter.println("Ended at: " + new Date());
            logWriter.println("=========================================================================");

            logWriter.close();
        }
    }
}