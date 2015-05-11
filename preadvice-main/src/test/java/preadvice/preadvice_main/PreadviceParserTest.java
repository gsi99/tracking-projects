package preadvice.preadvice_main;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.jayway.jsonassert.JsonAssert;
import com.jayway.jsonpath.JsonPath;

public class PreadviceParserTest {

	@Test
	public void testParse() {
		
		// Create a string payload of csv field
		String payload1 = "03,03,ZZ123456789GB,Chuck Castillo,businessName,deliveryAddress1,deliveryAddress2,deliveryAddress3,posttown,LS59IL,sortcode,"
				+ "contract,serviceid,serviceenhancement,0808439555,sendersref1,sendersref2,safeplace,itemWeight,weightType,format,deliveryOption,"
				+ "DeclaredValue,NotificationCode,RecipientEmail,RecipientTelephone,UniqueItemID,DeliveryCountry,RecipientContactNo,PricePaid,POLFADcode,"
				+ "RequiredatDelivery,DieNumber,RM48+,PostageExpiryDate,TariffRate,TariffVersion,Channelidentifier,HashAuthenticationKey,Dimensions,"
				+ "DimensionType,TypeOfItem";
		
		
		// call the transform method
		PreadviceParser paParser = new PreadviceParser();
		String response = paParser.transform(payload1);
		
		// check the json string response
		assertNotNull("Null returned from preadvice parser", response);
		assertTrue("Expected json format reponse", response.startsWith("{\"RecordTypeIndicator\":\"03\",\"VersionNumber\":\"03"));
		assertTrue("Expected json format reponse", response.endsWith("}"));
		
		// More detailed check
	    JsonAssert.with(response).assertThat("$.RecordTypeIndicator", Matchers.equalTo("03"))
	    	.assertThat("$.VersionNumber", Matchers.equalTo("03"))
			.assertThat("$.TrackingNumber", Matchers.equalTo("ZZ123456789GB"))
			.assertThat("$.RecipientName", Matchers.equalTo("Chuck Castillo"))
			.assertThat("$.BusinessName", Matchers.equalTo("businessName"))
			.assertThat("$.DeliveryAddress1", Matchers.equalTo("deliveryAddress1"))
			.assertThat("$.DeliveryAddress2", Matchers.equalTo("deliveryAddress2"))
			.assertThat("$.DeliveryAddress3", Matchers.equalTo("deliveryAddress3"))
			.assertThat("$.Posttown", Matchers.equalTo("posttown"))
			.assertThat("$.Postcode", Matchers.equalTo("LS59IL"))
			.assertThat("$.SortCode", Matchers.equalTo("sortcode"))
			.assertThat("$.Contract", Matchers.equalTo("contract"))
			.assertThat("$.ServiceID", Matchers.equalTo("serviceid"))
			.assertThat("$.ServiceEnhancement", Matchers.equalTo("serviceenhancement"))
			.assertThat("$.AccountNumber", Matchers.equalTo("0808439555"))
			.assertThat("$.SendersReference1", Matchers.equalTo("sendersref1"))
			.assertThat("$.SendersReference2", Matchers.equalTo("sendersref2"))
			.assertThat("$.Safeplace", Matchers.equalTo("safeplace"))
			.assertThat("$.ItemWeight", Matchers.equalTo("itemWeight"))
			.assertThat("$.WeightType", Matchers.equalTo("weightType"))
			.assertThat("$.Format", Matchers.equalTo("format"))
			.assertThat("$.DeliveryOption", Matchers.equalTo("deliveryOption"))
			.assertThat("$.DeclaredValue", Matchers.equalTo("DeclaredValue"))
			.assertThat("$.NotificationCode", Matchers.equalTo("NotificationCode"))
			.assertThat("$.RecipientEmail", Matchers.equalTo("RecipientEmail"))
			.assertThat("$.RecipientTelephone", Matchers.equalTo("RecipientTelephone"))
			.assertThat("$.UniqueItemID", Matchers.equalTo("UniqueItemID"))
			.assertThat("$.DeliveryCountry", Matchers.equalTo("DeliveryCountry"))
			.assertThat("$.RecipientContactNo", Matchers.equalTo("RecipientContactNo"))
			.assertThat("$.PricePaid", Matchers.equalTo("PricePaid"))
			.assertThat("$.POLFADcode", Matchers.equalTo("POLFADcode"))
			.assertThat("$.RequiredatDelivery", Matchers.equalTo("RequiredatDelivery"))
			.assertThat("$.Dienumber", Matchers.equalTo("DieNumber"))
			.assertThat("$.ProductGroupCode", Matchers.equalTo("RM48+"))
			.assertThat("$.PostageExpiryDate", Matchers.equalTo("PostageExpiryDate"))
			.assertThat("$.TariffRate", Matchers.equalTo("TariffRate"))
			.assertThat("$.TariffVersion", Matchers.equalTo("TariffVersion"))
			.assertThat("$.ChannelIdentifier", Matchers.equalTo("Channelidentifier"))
			.assertThat("$.HashAuthenticationKey", Matchers.equalTo("HashAuthenticationKey"))
			.assertThat("$.Dimensions", Matchers.equalTo("Dimensions"))
			.assertThat("$.DimensionType", Matchers.equalTo("DimensionType"))
			.assertThat("$.TypeOfItem", Matchers.equalTo("TypeOfItem"))	    	
        ;	    
	}

	
	@Test
	public void testParseShort() {
		
		// Create a string payload of csv field
		String payload1 = "I love Spring Integration";
		
		// call the transform method
		PreadviceParser paParser = new PreadviceParser();
		String response = paParser.transform(payload1);
		
		// check the json string response
		assertNotNull("Null returned from preadvice parser", response);
		assertTrue("Expected json format reponse", response.startsWith("{\"RecordTypeIndicator\":\"I love Spring Integration"));
		assertTrue("Expected json format reponse", response.endsWith("}")); 
	}
	
}
