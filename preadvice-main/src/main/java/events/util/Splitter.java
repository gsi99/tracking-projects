package events.util;

import java.util.Arrays;
import java.util.List;

public class Splitter {

	public static List<String> splitLines(String payload) {

		List<String> collection = Arrays.asList(payload.split("\\r?\\n"));

		return collection;
	}

}