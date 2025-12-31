package hooks;

import base.BaseTest;
import base.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends BaseTest {

	public Hooks(TestContext context) {
		super(context);
	}

	@Before
	public void beforeScenario() {
		setup();
	}
}
