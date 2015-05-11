package events.events_main;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;

import preadvice.preadvice_main.PreadviceSplitter;

public class TrackedEventsSplitterTst {

	public static void main(String[] args) throws Exception {
		BrokerService broker = new BrokerService();
		broker.setBrokerName("broker");
		String brokerURL = "tcp://localhost:61616";
		broker.addConnector(brokerURL);
		broker.start();
		ConnectionFactory cf = new ActiveMQConnectionFactory(brokerURL);
		JmsTemplate template = new JmsTemplate(cf);
		while (System.in.read() >= 0) {
			template.convertAndSend("jmstest", "testFoo");
			
		}
	}
	
	@Test
	public void testSplit() {

		TrackedEventSplitter teSplitter = new TrackedEventSplitter();
		String payloadToSplitString = "a csv line\r\nsecond csv line";

		List<String> arrayOfLineStrings = teSplitter.split(payloadToSplitString);

		assertNotNull("Null returned from splitter", arrayOfLineStrings);
		assertTrue(arrayOfLineStrings.size() == 2);
		Iterator<String> itr = arrayOfLineStrings.iterator();
		String test = itr.next();
		assertTrue("Expected Testing1", test.equalsIgnoreCase("a csv line"));
		test = itr.next();
		assertTrue("Expected Testing2", test.equalsIgnoreCase("second csv line"));
	}	
}
