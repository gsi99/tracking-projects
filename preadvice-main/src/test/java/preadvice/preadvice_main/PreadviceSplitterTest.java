package preadvice.preadvice_main;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class PreadviceSplitterTest {

	@Test
	public void testSplit() {

		PreadviceSplitter paSplitter = new PreadviceSplitter();
		String payloadToSplitString = "a csv line\r\nsecond csv line";
		// payloadToSplitString = "23456789GB,Karen Mayer,businessName,deliveryAddress1,deliveryAddress2,deliveryAddress3,posttown,SL54WD,sortcode,sortcode,contract,serviceid,serviceenhancement,1142756416,sendersref1,sendersref2,safeplace,itemWeight,weightType,format,deliveryOption,NotificationCode,RecipientEmail,RecipientTelephone,UniqueItemId-pgqorighfa,DeliveryCountry,RecipientContactNo,PricePaid,POLFADcode,RequiredatDelivery,DieNumber,RM24+,PostageExpiryDate,TariffRate,TariffVersion,Channelidentifier,HashAuthenticationKey,Dimensions,DimensionType,TypeOfItem\r\n03,03,ZZ123456789GB,Kendra Allen,businessName,deliveryAddress1,deliveryAddress2,deliveryAddress3,posttown,WV69IL,sortcode,sortcode,contract,serviceid,serviceenhancement,1142756416,sendersref1,sendersref2,safeplace,itemWeight,weightType,format,deliveryOption,NotificationCode,RecipientEmail,RecipientTelephone,UniqueItemId-xclmndaawe,DeliveryCountry,RecipientContactNo,PricePaid,POLFADcode,RequiredatDelivery,DieNumber,SF01,PostageExpiryDate,TariffRate,TariffVersion,Channelidentifier,HashAuthenticationKey,Dimensions,DimensionType,TypeOfItem";

		List<String> arrayOfLineStrings = paSplitter.split(payloadToSplitString);

		assertNotNull("Null returned from splitter", arrayOfLineStrings);
		assertTrue(arrayOfLineStrings.size() == 2);
		Iterator<String> itr = arrayOfLineStrings.iterator();
		String test = itr.next();
		assertTrue("Expected \"a csv line\" but got " + test, test.equalsIgnoreCase("a csv line"));
		test = itr.next();
		assertTrue("Expected \"second csv line\" bit got " + test, test.equalsIgnoreCase("second csv line"));
	}

}
