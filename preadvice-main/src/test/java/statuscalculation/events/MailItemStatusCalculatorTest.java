package statuscalculation.events;

import javax.annotation.Resource;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
public class MailItemStatusCalculatorTest {

	@Resource
	MailItemStatusCalculatorImpl mailItemStatusCalculator;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDetermineStatus() {
		// Given a mail item at status NOSTATUS
		// when a received scan event is processed
		// the new mail item status is ADVICED
		String newStatusString = mailItemStatusCalculator.determineStatus(null, "ADVICED");
		assertNotNull(newStatusString);
		assertEquals("ADVICED", newStatusString);
	}

}
