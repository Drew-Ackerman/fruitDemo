package feeders;

import com.google.gson.Gson;
import objects.Fruit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FruitFeeders{
	public static Iterator<Map<String, Object>> CreateFeeder() {
		Gson gson = new Gson();
		try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("data/Fruits.json")) {
			if (in == null) {
				throw new RuntimeException("InputStream is null, maybe couldn't find the Fruit.json file");
			}

			String jsonString = new BufferedReader(new InputStreamReader(in)).lines().collect(Collectors.joining("\n"));
			Fruit[] fruits = gson.fromJson(jsonString, Fruit[].class);

			return Stream.generate((Supplier<Map<String, Object>>) () -> {
				ArrayList<Map<String,Object>> fruitList = new ArrayList<>();

				for (Fruit fruit: fruits) {

					int randomSize = ThreadLocalRandom.current().nextInt(0, 500);

					// Create a fruit map
					Map<String, Object> fruitMap = Map.of(
							"name", fruit.name,
							"color", fruit.color,
							"size", randomSize);

					//Add to array
					fruitList.add(fruitMap);
				}

				return Map.of("fruits", fruitList);
			}).iterator();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}