package events.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProcessItemEventTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetValue() {
		/*
		 * Create a item
		 * retrieve a value - should get nothing back
		 * Add a value
		 * Retrieve the added value - should get nothing back
		 * Retrieve a non-existent value - should get nothing back
		 */
		ItemProcessEvent item = new ItemProcessEvent();
		
		String returnedValue = 	item.getValue("nonexistent");
		assertNull(returnedValue);
		
		item.setValue("Name1", "Value1");
		returnedValue = item.getValue("Name1");
		assertNotNull(returnedValue);
		assertEquals("Value1", returnedValue);
		
		returnedValue = 	item.getValue("nonexistent");
		assertNull(returnedValue);
	}
	
	@Test 
	public void testPostCodes() {
		ItemProcessEvent item = new ItemProcessEvent();
		
		String sourcePostCode = item.getSourcePostCode();
		assertNull(sourcePostCode);
		item.setSourcePostCode(null);
		assertNull(sourcePostCode);
		String destinationPostCode = item.getDestinationPostcode();
		assertNull(destinationPostCode);
		item.setDestinationPostcode(null);
		assertNull(destinationPostCode);
		
		item.setSourcePostCode("BD2 1ET");
		assertEquals(item.getSourcePostCode(), "BD21ET");

		item.setDestinationPostcode(("BD4 1ET"));
		assertEquals(item.getDestinationPostcode(), "BD41ET");
	}
}
