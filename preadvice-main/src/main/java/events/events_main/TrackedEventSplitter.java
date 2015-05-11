package events.events_main;

import java.util.Arrays;
import java.util.List;

import events.util.Splitter;

public class TrackedEventSplitter {
	
	public List<String> split(String payload) {

		return splitLines(payload);
	}

	private List<String> splitLines(String payload) {
		return Splitter.splitLines(payload);
	}
}
