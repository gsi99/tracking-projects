package repository.mailitem;

import javax.annotation.Resource;

import repository.mailitem.mappers.MailItemMapper;
import events.model.MailItem;

public class MailItemRepositoryImpl implements MailItemRepository {

	@Resource
	MailItemMapper mailItemMapper;
	
	public MailItemMapper getMailItemMapper() {
		return mailItemMapper;
	}

	public void setMailItemMapper(MailItemMapper mailItemMapper) {
		this.mailItemMapper = mailItemMapper;
	}

	@Override
	public MailItem getMailItem(String uniqueItemId){
		MailItem mailItem = mailItemMapper.getMailItem(uniqueItemId);
		
		return mailItem;
	}

	@Override
	public void updateMailItem(MailItem retrievedMailItem) {
		mailItemMapper.updateMailItem(retrievedMailItem);
	}

	public void insertMailItem(MailItem newMailItem) {
		mailItemMapper.insertMailItem(newMailItem);
	}
}
