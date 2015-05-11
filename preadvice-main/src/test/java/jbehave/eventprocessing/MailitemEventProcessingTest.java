package jbehave.eventprocessing;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Pending;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.reporters.NullStoryReporter;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import events.model.ItemProcessEvent;
import events.model.MailItem;
import eventsprocessing.mailitem.MailItemEventProcessor;

import repository.mailitem.MailItemRepositoryImpl;
import repository.mailitem.mappers.MailItemMapper;
import statuscalculation.events.MailItemStatusCalculatorImpl;

public class MailitemEventProcessingTest {
	
	ApplicationContext applicationContext = null;
	MailItem theInitialMailItem = null;
	String theCreatedMailItemId = null;
	MailItemEventProcessor eventProcessor = null; 
	MailItemMapper mailItemMapper = null;
	ItemProcessEvent teRecord = null;
	Gson gson = null;

	@Given("no existing mail item")
	public void givenNoExistingMailItem() {
	  theInitialMailItem = null;
	}
	
	@Given("a mail item of <status>, <previousstatus>, <eventcode>, <widthmm>, <lengthmm>, <heightmm>, <sourcepostcode>, <destinationpostcode> and <accountnumber>")
	public void theInitialMailItem(@Named("status") String status,
			@Named("previousstatus") String previousStatus,
			@Named("eventcode") String eventCode,
			@Named("widthmm") String widthMM,
			@Named("lengthmm") String lengthMM,
			@Named("heightmm") String heightMM,
			@Named("sourcepostcode") String sourcePostCode,
			@Named("destinationpostcode") String destinationPostCode,
			@Named("accountnumber") String accountNumber) {
		
		status = (status.equals("null")) ? null : status;
		previousStatus = (previousStatus.equals("null")) ? null : previousStatus;
		eventCode = (eventCode.equals("null")) ? null : eventCode;
		widthMM = (widthMM.equals("null")) ? null : widthMM;
		lengthMM = (lengthMM.equals("null")) ? null : lengthMM;
		heightMM = (heightMM.equals("null")) ? null : heightMM;
		sourcePostCode = (sourcePostCode.equals("null")) ? null : sourcePostCode;
		destinationPostCode = (destinationPostCode.equals("null")) ? null : destinationPostCode;
		accountNumber = (accountNumber.equals("null")) ? null : accountNumber;

		theInitialMailItem = new MailItem();
		theInitialMailItem.setUniqueItemId(UUID.randomUUID().toString());
		theInitialMailItem.setStatus(status);
		theInitialMailItem.setPreviousStatus(previousStatus);
		theInitialMailItem.setEventCode(eventCode);
		theInitialMailItem.setWidthInMillimetres(new Short(widthMM));
		theInitialMailItem.setLengthInMillimetres(new Short(lengthMM));
		theInitialMailItem.setHeightInMillimetres(new Short(heightMM));
		theInitialMailItem.setSourcePostCode(sourcePostCode);
		theInitialMailItem.setDestinationPostCode(destinationPostCode);
		theInitialMailItem.setCustomerAccountNumber(accountNumber);

		mailItemMapper.insertMailItem(theInitialMailItem);
		
	}
	
	@When("an event:<scanevent> is processed with uniqueitemid:<id>, accountnumber:<accountnumber>, location:<locationunitcode>, time from now in minutes:<timefromnowinminutes>, scannerid:<scannerid>, destination:<destinationpostcode>")
	public void whenAnEventscaneventIsProcessedWithUniqueItemId(@Named("scanevent") String eventCode,
			@Named("id") String uniqueItemId,
			@Named("accountnumber") String accountNumber,
			@Named("locationunitcode") String locationUnitCode,
			@Named("timefromnowinminutes") int timeFromNowInMinutes,
			@Named("scannerid") String scannerId,
			@Named("destinationpostcode") String destinationPostCode
			) {
		uniqueItemId = (uniqueItemId.equals("null")) ? null : uniqueItemId;
		accountNumber = (accountNumber.equals("null")) ? null : accountNumber;
		locationUnitCode = (locationUnitCode.equals("null")) ? null : locationUnitCode;
		scannerId = (scannerId.equals("null")) ? null : scannerId;
		destinationPostCode = (destinationPostCode.equals("null")) ? null : destinationPostCode;

		theCreatedMailItemId = uniqueItemId + UUID.randomUUID().toString().substring(0, 5);
		processMailItemEvent(eventCode, accountNumber, locationUnitCode,
				timeFromNowInMinutes, scannerId, theCreatedMailItemId);
	}	
	
	//When a <scanevent> is processed with <accountnumber>, <locationunitcode>, <timefromnowinminutes> and <scannerid>
	@When("a <scanevent> is processed with <accountnumber>, <locationunitcode>, <timefromnowinminutes> and <scannerid>")
	public void whenAnEventIsProcessed(@Named("scanevent") String eventCode,
			@Named("accountnumber") String accountNumber,
			@Named("locationunitcode") String locationUnitCode,
			@Named("timefromnowinminutes") int timeFromNowInMinutes,
			@Named("scannerid") String scannerId) {
		
		accountNumber = (accountNumber.equals("null")) ? null : accountNumber;
		locationUnitCode = (locationUnitCode.equals("null")) ? null : locationUnitCode;
		scannerId = (scannerId.equals("null")) ? null : scannerId;
		
		processMailItemEvent(eventCode, accountNumber, locationUnitCode,
				timeFromNowInMinutes, scannerId, theInitialMailItem.getUniqueItemId());

	}

	private void processMailItemEvent(String eventCode, String accountNumber,
			String locationUnitCode, int timeFromNowInMinutes, String scannerId, String uniqueItemId) {
		applicationContext = new ClassPathXmlApplicationContext("classpath:testContext.xml");

		assertNotNull("Got null value for bean mailItemEventProcessor",	applicationContext.getBean("mailItemEventProcessor"));
		eventProcessor = (MailItemEventProcessor) applicationContext.getBean("mailItemEventProcessor");

		teRecord = new ItemProcessEvent();
		teRecord.setCustomerAccountNumber(accountNumber);
		teRecord.setEventCode(eventCode);
		teRecord.setUniqueItemId(uniqueItemId);
		teRecord.setLocation(locationUnitCode);
		teRecord.setScannerId(scannerId);
		Calendar myCalendar = Calendar.getInstance();
		myCalendar.add(Calendar.MINUTE, timeFromNowInMinutes);
		myCalendar.getTime();
		teRecord.setTimestamp(myCalendar.getTime());
		
		String json = gson.toJson(teRecord);
		eventProcessor.processEvent(json);
	}

	//Then the mail item status is updated to <newstatus>, <newpreviousstatus> and <neweventcode>
	@Then("the mail item status is updated to <newstatus>, <newpreviousstatus> and <neweventcode>")
	public void mailItemStatusIsUpdated(@Named("newstatus") String newStatus,
			@Named("newpreviousstatus") String newPreviousStatus,
			@Named("neweventcode") String newEventCode) {
		
		newStatus = (newStatus.equals("null")) ? null : newStatus;
		newPreviousStatus = (newPreviousStatus.equals("null")) ? null : newPreviousStatus;
		newEventCode = (newEventCode.equals("null")) ? null : newEventCode;
		
		MailItem retrievedMailItem = mailItemMapper.getMailItem(theInitialMailItem.getUniqueItemId());
		
		assertEquals("Wrong status calculated : ", newStatus, retrievedMailItem.getStatus());
		assertEquals("Wrong previousStatus is set : ", newPreviousStatus, retrievedMailItem.getPreviousStatus());
		assertEquals("Wrong current event code is set : ", newStatus, retrievedMailItem.getEventCode());
	}
	
	
	@Then("the mail item is created with status:<newstatus>, previous status:<newpreviousstatus> and event code:<neweventcode>")
	public void thenTheMailItemIsCreated(@Named("newstatus") String newStatus,
			@Named("newpreviousstatus") String newPreviousStatus,
			@Named("neweventcode") String newEventCode) {

		newStatus = (newStatus.equals("null")) ? null : newStatus;
		newPreviousStatus = (newPreviousStatus.equals("null")) ? null : newPreviousStatus;
		newEventCode = (newEventCode.equals("null")) ? null : newEventCode;

		MailItem theCreatedMailItem = mailItemMapper.getMailItem(theCreatedMailItemId);
		assertNotNull("Expecting a new mailitem to be created and stored, but it wasn't",	theCreatedMailItem);
		assertEquals("Expecting status to be:" + newStatus + " but was:" + theCreatedMailItem.getStatus(), newStatus, theCreatedMailItem.getStatus());
		assertEquals("Expecting previous status to be:" + newPreviousStatus + " but was:" + theCreatedMailItem.getPreviousStatus(), newPreviousStatus, theCreatedMailItem.getPreviousStatus());
		assertEquals("Expecting event code to be:" + newEventCode + " but was:" + theCreatedMailItem.getEventCode(), newEventCode, theCreatedMailItem.getEventCode());
		
	}	

	
	@BeforeStory
	public void beforeStory() {
		gson = new GsonBuilder().setDateFormat("YYYY-MM-dd'T'HH:mm:ss.SSSZ")
				.create();
		applicationContext = new ClassPathXmlApplicationContext("classpath:testContext.xml");
		assertNotNull("Got null value for bean mailItemMapper", applicationContext.getBean("mailItemMapper"));
		mailItemMapper = (MailItemMapper) applicationContext.getBean("mailItemMapper");

		assertNotNull("Got null value for bean mailItemEventProcessor",	applicationContext.getBean("mailItemEventProcessor"));
		eventProcessor = (MailItemEventProcessor) applicationContext.getBean("mailItemEventProcessor");
	}
	
	@AfterStory
	public void afterStory() {
		if (theInitialMailItem != null) {
			mailItemMapper.deleteMailItem(theInitialMailItem.getUniqueItemId());
		}
		if (theCreatedMailItemId != null) {
			mailItemMapper.deleteMailItem(theCreatedMailItemId);
		}
	}

}
