import org.graphwalker.java.test.Executor;
import org.graphwalker.java.test.TestExecutor;
import org.graphwalker.websocket.WebSocketServer;
import org.junit.Test;

import java.io.IOException;


public class RunAddIncomeModel {

    @Test
    public void runAddIncomeModel() {
        try {
            Executor executor = new TestExecutor(AddIncomeTestModel.class);
            WebSocketServer server = new WebSocketServer(8890, executor.getMachine());
            server.start();

            executor.execute(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
