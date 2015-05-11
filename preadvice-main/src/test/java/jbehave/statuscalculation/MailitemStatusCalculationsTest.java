package jbehave.statuscalculation;

import static org.junit.Assert.*;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import statuscalculation.events.MailItemStatusCalculatorImpl;

public class MailitemStatusCalculationsTest {
	
	String initialStatus = null;
	String theStatus = null;
	MailItemStatusCalculatorImpl statusCalculator = null;
	
	@Given("a mail item at status $status")
	public void givenAMailItemAtStatus(String status) {
		if (status.equalsIgnoreCase("NOSTATUS")) {
			initialStatus = "null";
		} else {
			initialStatus = status;
		}
	}

	@When("a $eventCode scan event is processed")
	public void whenAnEventIsProcessed(String eventCode) {
		 ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:testContext.xml");
		
		assertNotNull("Got null value for bean mailItemStatusCalculator", applicationContext.getBean("mailItemStatusCalculator"));
		statusCalculator = (MailItemStatusCalculatorImpl) applicationContext.getBean("mailItemStatusCalculator");

		theStatus = statusCalculator.determineStatus(initialStatus, eventCode);
		
	}

	@Then("the mail item status is $nextStatus")
	public void thenTheMailItemStatusIs(String nextStatus) {
		assertEquals("Wrong status calculated : ", nextStatus, theStatus);
	}

}
