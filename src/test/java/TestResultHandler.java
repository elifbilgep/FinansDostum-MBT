import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestResultHandler extends TestWatcher {
    private static final Logger logger = LoggerFactory.getLogger(TestResultHandler.class);

    protected void starting(Description description) {
        logger.info("=========================================================================");
        String[] classNameArray = description.getClassName().split("\\.");
        String testClassName = classNameArray[classNameArray.length-1];
        String testCaseName = description.getMethodName();
        logger.info("Starting Test: " + testCaseName);
        logger.info("If The Test fails, The Faulty Step is the last step");
    }

    protected void succeeded(Description description) {
        logger.info("Test Result: TEST SUCCESSFUL");
    }

    public void failed(Throwable e, Description description) {
        logger.error("Test Result: TEST FAILED");
        logger.error("Error: ", e);
    }

    protected void finished(Description description) {
        logger.info("=========================================================================");
    }
}