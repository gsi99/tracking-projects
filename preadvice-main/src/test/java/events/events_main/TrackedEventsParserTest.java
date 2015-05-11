package events.events_main;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.jsonassert.JsonAssert;

import preadvice.preadvice_main.PreadviceParser;

public class TrackedEventsParserTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testParseShort() {
		
		// Create a string payload of csv field
		String payload1 = "I love Spring Integration";
		
		// call the transform method
		TrackedEventParser teParser = new TrackedEventParser();
		String response=null;
		try {
			response = teParser.transform(payload1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// check the json string response
		assertNotNull("Null returned from trackedevent parser", response);
		assertTrue("Expected json format reponse", response.startsWith("{\""));
		assertTrue("Expected json format reponse", response.endsWith("}")); 
	}
	
	// To Do Full test with trackedscan payload (test json payload is created).
	@Test
	public void testParse() {
		
		// Create a string payload of csv field	   YYYY-MM-dd'T'HH:mm:ss'Z'
		String payload1 = "v1.0,Scanner01,LON03,2014-10-09T15:37:57.953Z,EV01,1.0,-1.0,"
				//+ "UniqueItemId-pgqorighfa,WeightGrams,Length,Width,Height,DeclaredWeightGrams,"
				+ "UniqueItemId-pgqorighfa,1000,200,100,300,1010,"
				//+ "DeclaredLength,DeclaredWidth,DeclaredHeight,ShapeType,DestinationPostcode,SourcePostcode,AccountNumber";
				+ "210,110,310,Rectangle,SL54WD,BD21ET,1142756416";
		
		String payload2 = "v1.0,Scanner01,LON03,2014-10-09T15:37:57.953Z,EV01,1.0,-1.0,UniqueItemId-pgqorighfa,1000,200,100,300,1010,210,110,310,Rectangle,SL54WD,BD21ET,1142756416";
		
		
		// call the transform method
		TrackedEventParser teParser = new TrackedEventParser();
		String response=null;
		try {
			response = teParser.transform(payload2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// check the json string response
		assertNotNull("Null returned from trackedevent parser", response);
		assertTrue("Expected json format reponse but got" + response, response.startsWith("{\"scannerId\":\"Scanner01"));
		assertTrue("Expected json format reponse", response.endsWith("}"));
		
		System.out.println(response);
		
		// More detailed check
	    JsonAssert.with(response).assertThat("$.scannerId", Matchers.equalTo("Scanner01"))
	    	.assertThat("$.location.longitude", Matchers.equalTo(1.0))
	    	.assertThat("$.location.latitude", Matchers.equalTo(-1.0))
	    	.assertThat("$.location.unitCode", Matchers.equalTo("LON03"))
    		.assertThat("$.timestamp", Matchers.equalTo("2014-10-09T16:37:57.953+0100"))
    		.assertThat("$.eventCode", Matchers.equalTo("EV01"))
    		.assertThat("$.processedMailItemDetails.uniqueItemId", Matchers.equalTo("UniqueItemId-pgqorighfa"))
    		.assertThat("$.processedMailItemDetails.weightInGrammes", Matchers.equalTo(1000))
    		.assertThat("$.processedMailItemDetails.lengthInMillimetres", Matchers.equalTo(200))
    		.assertThat("$.processedMailItemDetails.widthInMillimetres", Matchers.equalTo(100))
    		.assertThat("$.processedMailItemDetails.heightInMillimetres", Matchers.equalTo(300))
    		.assertThat("$.processedMailItemDetails.declaredWeightInGrammes", Matchers.equalTo(1010))
    		.assertThat("$.processedMailItemDetails.declaredLengthInMillimetres", Matchers.equalTo(210))
    		.assertThat("$.processedMailItemDetails.declaredWidthInMillimetres", Matchers.equalTo(110))
    		.assertThat("$.processedMailItemDetails.declaredHeightInMillimetres", Matchers.equalTo(310))
			.assertThat("$.processedMailItemDetails.shapeType", Matchers.equalTo("Rectangle"))
			.assertThat("$.processedMailItemDetails.destinationPostCode.postcode", Matchers.equalTo("SL54WD"))
			.assertThat("$.processedMailItemDetails.sourcePostCode.postcode", Matchers.equalTo("BD21ET"))
			.assertThat("$.processedMailItemDetails.customerAccountNumber", Matchers.equalTo("1142756416"))
        ;	    
	}	

}
