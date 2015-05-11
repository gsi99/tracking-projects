package eventsprocessing.mailitem;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import repository.mailitem.MailItemRepository;
import statuscalculation.events.MailItemStatusCalculator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import events.model.ItemProcessEvent;
import events.model.MailItem;

public class MailItemEventProcessor {

	@Autowired
	MailItemRepository mailItemRepo;

	@Autowired
	MailItemStatusCalculator mailItemStatusCalculator;

	Gson gson = null;
	ItemProcessEvent teRecord = null;
	Logger logger;

	public MailItemEventProcessor() {
		gson = new GsonBuilder().setDateFormat("YYYY-MM-dd'T'HH:mm:ss.SSSZ")
				.create();
		logger = Logger.getLogger(getClass());
	}

	public String processEvent(String payload) {

		logger.info("Processing payload:" + payload);
		createItemProcessEvent(payload);

		MailItem retrievedMailItem = retrieveMailItem();

		String newStatus = determineNewStatus(retrievedMailItem);

		if (retrievedMailItem != null) {
			updateMailItem(retrievedMailItem, newStatus);
		} else {
			createMailItem(newStatus);
		}

		teRecord.setStatus(newStatus);
		String json = gson.toJson(teRecord);

		return json;
	}

	private void createMailItem(String newStatus) {
		logger.info("createMailItem, newStatus :" + newStatus);
		MailItem newMailItem = new MailItem(teRecord);
		newMailItem.setValueList(teRecord.getValueList());
		newMailItem.setStatus(newStatus);
		mailItemRepo.insertMailItem(newMailItem);
		
	}

	private void updateMailItem(MailItem retrievedMailItem, String newStatus) {
		logger.info("updateMailItem, newStatus :" + retrievedMailItem + ":" + newStatus);
		retrievedMailItem.setPreviousStatus(retrievedMailItem.getStatus());
		retrievedMailItem.setEventCode(teRecord.getEventCode());
		if (retrievedMailItem.getEventList() != null) {
			retrievedMailItem.getEventList().add(teRecord);
		}
		retrievedMailItem.setStatus(newStatus);
		mailItemRepo.updateMailItem(retrievedMailItem);

	}

	private String determineNewStatus(MailItem retrievedMailItem) {
		logger.info("determineNewStatus :" + retrievedMailItem);
		String eventCode = teRecord.getEventCode();
		String newStatus = mailItemStatusCalculator.determineStatus(
				(retrievedMailItem == null) ? null : retrievedMailItem.getStatus(), 
				eventCode);
		return newStatus;
	}

	private MailItem retrieveMailItem() {
		String uniqueItemId = teRecord.getUniqueItemId();
		logger.info("retrieveMailItem uniqueItemId:" + uniqueItemId);
		MailItem retrievedMailItem = mailItemRepo.getMailItem(uniqueItemId);
		return retrievedMailItem;
	}

	private void createItemProcessEvent(String payload) {
		teRecord = gson.fromJson(payload, ItemProcessEvent.class);
	}

	public MailItemRepository getMailItemRepo() {
		return mailItemRepo;
	}

	public void setMailItemRepo(MailItemRepository mailItemRepo) {
		this.mailItemRepo = mailItemRepo;
	}

	public MailItemStatusCalculator getMailItemStatusCalculator() {
		return mailItemStatusCalculator;
	}

	public void setMailItemStatusCalculator(
			MailItemStatusCalculator mailItemStatusCalculator) {
		this.mailItemStatusCalculator = mailItemStatusCalculator;
	}

}
