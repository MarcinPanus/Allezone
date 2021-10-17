package pl.edu.pjwstk.jaz;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public class RestAssurePortListener implements TestExecutionListener {

    public void beforeTestClass(TestContext testContext) {

//        var environment = testContext.getApplicationContext().getBean(Environment.class);
//        var portAsString = Objects.requireNonNull(Environment.getProperties().getProperty("local.server.port"));
//        RestAssured.port = Integer.parseInt(portAsString);
    }
}
