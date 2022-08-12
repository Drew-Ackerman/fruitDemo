package requests;

import feeders.FruitFeeders;
import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class FruitRequests {

	private static final Logger logger = LogManager.getLogger();
	FeederBuilder.Batchable<String> idFeeder = csv("feeders/Ids.csv").random();


	public ChainBuilder getAllFruit() {
		return feed(idFeeder)
				.feed(FruitFeeders.CreateFeeder())
				.exec(http("Get All Fruit")
						.get("/get")
						.body(StringBody("#{fruits}"))
						.check(status().is(200))
						.check(bodyString().saveAs("responseBody")))
				.exec(session -> {
					logger.info("Info");
					logger.info(session);
					logger.info(session.getString("responseBody"));
					return session;
				});
	}

	public ChainBuilder saveFruits() {
		return feed(idFeeder)
				.feed(FruitFeeders.CreateFeeder())
				.exec(http("Save Fruits")
						.post("/post")
						.body(StringBody("#{fruits}"))
						.check(status().is(200))
						.check(bodyString().saveAs("responseBody")))
				.exec(session -> {
					logger.info("Info");
					logger.info(session);
					logger.info(session.getString("responseBody"));
					return session;
				});
	}

}
