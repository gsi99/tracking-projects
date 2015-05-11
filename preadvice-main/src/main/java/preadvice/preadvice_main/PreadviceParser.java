package preadvice.preadvice_main;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import preadvice.model.PreadviceRecord;

import com.google.gson.Gson;

public class PreadviceParser {
	
	static Logger log = Logger.getLogger(
            PreadviceParser.class.getName());
	
	public String transform(String payload) {
		
		List<String> items = Arrays.asList(payload.split("\\s*,\\s*"));
		
		PreadviceRecord paRecord = mapToPreadviceRecord(items);
		
		Gson gson = new Gson();
		String json = gson.toJson(paRecord);
		
		log.info("Parsed preadvice and converted to json:" + json);
		
		return json.toString();
	}

	private PreadviceRecord mapToPreadviceRecord(List<String> items) {
		PreadviceRecord paRecord = new PreadviceRecord();
		paRecord.setRecordTypeIndicator(getValueFromList(items, 1));
		paRecord.setVersionNumber(getValueFromList(items, 2));
		paRecord.setTrackingNumber(getValueFromList(items, 3));
		paRecord.setRecipientName(getValueFromList(items, 4));
		paRecord.setBusinessName(getValueFromList(items, 5));
		paRecord.setDeliveryAddress1(getValueFromList(items, 6));
		paRecord.setDeliveryAddress2(getValueFromList(items, 7));
		paRecord.setDeliveryAddress3(getValueFromList(items, 8));
		paRecord.setPosttown(getValueFromList(items, 9));
		paRecord.setPostcode(getValueFromList(items, 10));
		paRecord.setSortCode(getValueFromList(items, 11));
		paRecord.setContract(getValueFromList(items, 12));
		paRecord.setServiceID(getValueFromList(items, 13));
		paRecord.setServiceEnhancement(getValueFromList(items, 14));
		paRecord.setAccountNumber(getValueFromList(items, 15));
		paRecord.setSendersReference1(getValueFromList(items, 16));
		paRecord.setSendersReference2(getValueFromList(items, 17));
		paRecord.setSafeplace(getValueFromList(items, 18));
		paRecord.setItemWeight(getValueFromList(items, 19));
		paRecord.setWeightType(getValueFromList(items, 20));
		paRecord.setFormat(getValueFromList(items, 21));
		paRecord.setDeliveryOption(getValueFromList(items, 22));
		paRecord.setDeclaredValue(getValueFromList(items, 23));
		paRecord.setNotificationCode(getValueFromList(items, 24));
		paRecord.setRecipientEmail(getValueFromList(items, 25));
		paRecord.setRecipientTelephone(getValueFromList(items, 26));
		paRecord.setUniqueItemID(getValueFromList(items, 27));
		paRecord.setDeliveryCountry(getValueFromList(items, 28));
		paRecord.setRecipientContactNo(getValueFromList(items, 29));
		paRecord.setPricePaid(getValueFromList(items, 30));
		paRecord.setPOLFADcode(getValueFromList(items, 31));
		paRecord.setRequiredatDelivery(getValueFromList(items, 32));
		paRecord.setDienumber(getValueFromList(items, 33));
		paRecord.setProductGroupCode(getValueFromList(items, 34));
		paRecord.setPostageExpiryDate(getValueFromList(items, 35));
		paRecord.setTariffRate(getValueFromList(items, 36));
		paRecord.setTariffVersion(getValueFromList(items, 37));
		paRecord.setChannelIdentifier(getValueFromList(items, 38));
		paRecord.setHashAuthenticationKey(getValueFromList(items, 39));
		paRecord.setDimensions(getValueFromList(items, 40));
		paRecord.setDimensionType(getValueFromList(items, 41));
		paRecord.setTypeOfItem(getValueFromList(items, 42));
		
		return paRecord;
	}

	private String getValueFromList(List<String> items, int index) {
		if (index < 0) {
			return null;
		} else {
			return (items.size() >= index) ? items.get(index-1) : null;
		}
	}
}
