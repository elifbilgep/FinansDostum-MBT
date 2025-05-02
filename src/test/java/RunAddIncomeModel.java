import org.graphwalker.java.test.Executor;
import org.graphwalker.java.test.Result;
import org.graphwalker.java.test.TestExecutor;
import org.graphwalker.websocket.WebSocketServer;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;

public class RunAddIncomeModel {
    private static final Logger logger = LoggerFactory.getLogger(RunAddIncomeModel.class);

    @Test
    public void runAddIncomeModel() {
        WebSocketServer server = null;
        long startTime = System.currentTimeMillis();

        try {
            initializeTestLogging();
            Executor executor = new TestExecutor(AddIncomeTestModel.class);
            server = startWebSocketServer(executor);
            executeModelAndHandleResults(executor);

        } catch (Exception e) {
            handleTestFailure(e);
            throw wrapException(e);
        } finally {
            long testDuration = System.currentTimeMillis() - startTime;
            logger.info("Total test time: {} ms", testDuration);

            stopWebSocketServer(server);
        }
    }

    private void initializeTestLogging() {
        try {
            String logFilePath = TestLogger.initializeLogger("AddIncomeTest");
            logger.info("Test logs will be saved to: {}", logFilePath);
            TestLogger.logInfo("Test execution started");
        } catch (IOException e) {
            logger.error("Failed to initialize test logger: {}", e.getMessage());
        }
    }

    private WebSocketServer startWebSocketServer(Executor executor) throws IOException {
        TestLogger.logInfo("Starting WebSocket server on port 8890");
        WebSocketServer server = new WebSocketServer(8890, executor.getMachine());
        server.start();
        logger.info("WebSocket server started on port 8890");
        return server;
    }

    private void executeModelAndHandleResults(Executor executor) {
        try {
            TestLogger.logInfo("Executing GraphWalker model");

            long startTime = System.currentTimeMillis();
            Result result = executor.execute(true);
            long duration = System.currentTimeMillis() - startTime;

            logExecutionStatistics(duration, result);

            if (result.hasErrors()) {
                handleErrors(result);
            } else {
                handleSuccess();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error during model execution", e);
        }
    }

    private void logExecutionStatistics(long duration, Result result) {
        TestLogger.logInfo("Model execution statistics:");
        TestLogger.logInfo("- Execution time: " + duration + " ms");
        TestLogger.logInfo("- Test result: " + (result.hasErrors() ? "FAILED" : "PASSED"));
    }

    private void handleErrors(Result result) {
        logger.error("Test failed with errors:");
        TestLogger.logInfo("Test failed with the following errors:");

        for (String error : result.getErrors()) {
            logger.error("Error: " + error);
            TestLogger.logError("TestExecution", error, null);
        }

        TestLogger.finalizeLog(false, result.getErrors());
        throw new AssertionError("Test failed with errors: " + result.getErrors());
    }

    private void handleSuccess() {
        logger.info("Test execution completed successfully");
        TestLogger.logInfo("Test execution completed successfully");
        TestLogger.finalizeLog(true, null);
    }

    private void handleTestFailure(Exception e) {
        logger.error("Test failed: " + e.getMessage(), e);
        TestLogger.logError("TestExecution", "Test failed: " + e.getMessage(), e);
        TestLogger.finalizeLog(false, Collections.singletonList(e.getMessage()));
    }

    private RuntimeException wrapException(Exception e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        }
        return new RuntimeException("Test execution failed", e);
    }

    private void stopWebSocketServer(WebSocketServer server) {
        if (server != null) {
            try {
                server.stop();
                logger.info("WebSocket server stopped");
            } catch (Exception e) {
                logger.error("Error stopping WebSocket server", e);
            }
        }
    }
}