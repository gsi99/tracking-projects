package repository.mailitem;

import static org.junit.Assert.*;

import java.util.UUID;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import events.model.MailItem;
import events.model.ShapeType;

import repository.mailitem.mappers.MailItemMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
public class MailItemRepositoryImplTest {
	
	MailItem mailItem;
	MailItem newMailItem;
	
	@Resource
	MailItemRepositoryImpl mailItemRepositoryImpl;
	
	@Resource
	MailItemMapper mailItemMapper;

	@Before
	public void setUp() throws Exception {
		createMailItemData();
	}

	@After
	public void tearDown() throws Exception {
		clearDownMailItemData();
	}

	@Test
	public void testSimpleRetrieve() {
		
		MailItem myMailItem = mailItemRepositoryImpl.getMailItem(mailItem.getUniqueItemId());
		
		assertNotNull("Expected to retrieve a mailitem with uniqueitemid = " + mailItem.getUniqueItemId(), myMailItem);
		assertEquals(mailItem.getUniqueItemId(), myMailItem.getUniqueItemId());
		assertEquals(mailItem.getStatus(), myMailItem.getStatus());
		assertEquals(mailItem.getPreviousStatus(), myMailItem.getPreviousStatus());
		assertEquals(mailItem.getEventCode(), myMailItem.getEventCode());
		assertEquals(mailItem.getWeightInGrammes(), myMailItem.getWeightInGrammes());
		assertEquals(mailItem.getLengthInMillimetres(), myMailItem.getLengthInMillimetres());
		assertEquals(mailItem.getHeightInMillimetres(), myMailItem.getHeightInMillimetres());
		assertEquals(mailItem.getWidthInMillimetres(), myMailItem.getWidthInMillimetres());
	}
	
	@Test
	public void testSimpleRetrieveUpdateAndRetrieve() {
		
		MailItem updatedMailItem = new MailItem(mailItem);
		updatedMailItem.setPreviousStatus("OUTWARDPROCESSING");
		updatedMailItem.setStatus("ONROUTE");
		updatedMailItem.setEventCode("EXITOUTBOUND");
		
		mailItemRepositoryImpl.updateMailItem(updatedMailItem);
		
		MailItem myMailItem = mailItemRepositoryImpl.getMailItem(updatedMailItem.getUniqueItemId());
		
		assertNotNull("Expected to retrieve a mailitem with uniqueitemid = " + mailItem.getUniqueItemId(), myMailItem);
		assertEquals(myMailItem.getUniqueItemId(), updatedMailItem.getUniqueItemId());
		assertEquals("ONROUTE", myMailItem.getStatus());
		assertEquals("OUTWARDPROCESSING", myMailItem.getPreviousStatus());
		assertEquals("EXITOUTBOUND", myMailItem.getEventCode());
		
	}
	
	@Test
	public void testSimpleCreate() {
		newMailItem = new MailItem();
		String newId = "Test" + UUID.randomUUID().toString();
		newId = newId.substring(0,36);
		newMailItem.setUniqueItemId(newId);
		newMailItem.setEventCode("PREADVICED");
		newMailItem.setStatus("PREADVICED");
		newMailItem.setPreviousStatus("null");
		newMailItem.setCustomerAccountNumber("12332134");
		newMailItem.setWeightInGrammes((short) 1000);
		newMailItem.setDeclaredWeightInGrammes((short) 1010);
		newMailItem.setLengthInMillimetres((short) 200);
		newMailItem.setWidthInMillimetres((short) 100);
		newMailItem.setHeightInMillimetres((short) 50);
		newMailItem.setDeclaredLengthInMillimetres((short) 210);
		newMailItem.setDeclaredWidthInMillimetres((short) 110);
		newMailItem.setDeclaredHeightInMillimetres((short) 60);
		newMailItem.setShapeType(ShapeType.Rectangle);
		newMailItem.setDestinationPostCode("BD21ET");
		newMailItem.setSourcePostCode("BD177NY");
		
		mailItemRepositoryImpl.insertMailItem(newMailItem);
		
		MailItem retrievedMailItem = mailItemMapper.getMailItem(newId);
		assertNotNull("Expected to find a persisted mailitem with uniqueitemid = " + newId, retrievedMailItem);
		assertEquals("PREADVICED", retrievedMailItem.getEventCode());
		assertEquals("PREADVICED", retrievedMailItem.getStatus());
		assertEquals("null", retrievedMailItem.getPreviousStatus());
		assertEquals("12332134", retrievedMailItem.getCustomerAccountNumber());
		assertEquals(1000, retrievedMailItem.getWeightInGrammes());
		assertEquals(1010, retrievedMailItem.getDeclaredWeightInGrammes());
		assertEquals(200, retrievedMailItem.getLengthInMillimetres());
		assertEquals(100, retrievedMailItem.getWidthInMillimetres());
		assertEquals(50, retrievedMailItem.getHeightInMillimetres());
		assertEquals(60, retrievedMailItem.getDeclaredHeightInMillimetres());
		assertEquals(210, retrievedMailItem.getDeclaredLengthInMillimetres());
		assertEquals(60, retrievedMailItem.getDeclaredHeightInMillimetres());
		assertEquals(ShapeType.Rectangle, retrievedMailItem.getShapeType());
		assertEquals("BD21ET", retrievedMailItem.getDestinationPostCode());
		assertEquals("BD177NY", retrievedMailItem.getSourcePostCode());

	}
	

	private void clearDownMailItemData() {
		mailItemMapper.deleteMailItem(mailItem.getUniqueItemId());
		if (newMailItem != null) {
			mailItemMapper.deleteMailItem(newMailItem.getUniqueItemId());
		}
	}

	private void createMailItemData() {
		this.mailItem = new MailItem();
		mailItem.setUniqueItemId(UUID.randomUUID().toString());
		mailItem.setStatus("OUTWARDPROCESSING");
		mailItem.setPreviousStatus("ADVICED");
		mailItem.setEventCode("RECEIVEDOUTBOUND");
		mailItem.setWeightInGrammes((short) 1000);
		mailItem.setLengthInMillimetres((short) 20);
		mailItem.setHeightInMillimetres((short) 10);
		mailItem.setWidthInMillimetres((short) 5);
		mailItemMapper.insertMailItem(mailItem);
	}

}
