package scenarios;

import io.gatling.javaapi.core.ScenarioBuilder;
import requests.FruitRequests;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.scenario;

public class FruitScenarios {

	public ScenarioBuilder findAllFruit() {
		return scenario("Find all Fruit")
				.exitBlockOnFail(
						exec(session -> {
							session = session.set("var", 10);
							return session;
						})
								.exec(new FruitRequests().getAllFruit())
				);
	}

	public ScenarioBuilder saveFruits() {
		return scenario("Find all Fruit")
				.exitBlockOnFail(
						exec(session -> {
							session = session.set("var", 10);
							return session;
						})
								.exec(new FruitRequests().saveFruits())
				);
	}

}
