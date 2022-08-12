import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import scenarios.FruitScenarios;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class FruitSim extends Simulation {

	HttpProtocolBuilder httpProtocol = http.baseUrl("https://postman-echo.com");
	FruitScenarios fs = new FruitScenarios();

	public FruitSim() {
		setUp(
//				fs.findAllFruit().injectClosed(
//						constantConcurrentUsers(10).during(5)
//				)
				fs.saveFruits().injectClosed(
						constantConcurrentUsers(10).during(5)
				)
		).protocols(httpProtocol);
	}
}
