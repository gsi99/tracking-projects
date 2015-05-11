package preadvice.preadvice_main;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import events.util.Splitter;

public class PreadviceSplitter {
	
	static Logger log = Logger.getLogger(
			PreadviceSplitter.class.getName());

	public List<String> split(String payload) {

		log.info("Got payload of:" + payload);
		
		return splitLines(payload);
	}

	private List<String> splitLines(String payload) {
		List<String> splitLines = Splitter.splitLines(payload);
		
		log.info("Split lines to:");
		for (int i = 0; i < splitLines.size(); i++) {
			String splitLine = (String) splitLines.get(i);
			log.info("Split line " + i + " to:" + splitLine);
		}
		
		return splitLines;
	}
}
