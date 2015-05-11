package repository.mailitem;

import events.model.MailItem;

public interface MailItemRepository {

	public MailItem getMailItem(String uniqueItemId);

	public void updateMailItem(MailItem retrievedMailItem);
	
	public void insertMailItem(MailItem newMailItem);
	
}