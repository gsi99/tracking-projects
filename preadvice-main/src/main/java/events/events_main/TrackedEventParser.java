package events.events_main;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import events.model.ItemProcessEvent;
import events.model.ShapeType;

public class TrackedEventParser {

	public String transform(String payload) throws ParseException {
		
		List<String> items = Arrays.asList(payload.split("\\s*,\\s*"));
		
		ItemProcessEvent teRecord = mapToTrackedEventRecord(items);
		
		Gson gson = new GsonBuilder().setDateFormat("YYYY-MM-dd'T'HH:mm:ss.SSSZ").create();
		String json = gson.toJson(teRecord);
		
		return json;
	}

	private ItemProcessEvent mapToTrackedEventRecord(List<String> items) throws ParseException {
		ItemProcessEvent teRecord = new ItemProcessEvent();
		
		String versionNumber = getValueFromList(items, 1);
		teRecord.setScannerId(getValueFromList(items, 2));
		teRecord.setLocation(getValueFromList(items, 3));
		if (getValueFromList(items, 4) != null) {
			DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
			System.out.println("Parsing " + getValueFromList(items, 4).toString() + " to " );
			System.out.print(fmt.parseDateTime(getValueFromList(items, 4)).toString());
			teRecord.setTimestamp(fmt.parseDateTime(getValueFromList(items, 4)).toDate());
		}		
		teRecord.setEventCode(getValueFromList(items, 5));
		if (getValueFromListAsDouble(items, 6) != null) {
			teRecord.getLocation().setLongitude(getValueFromListAsDouble(items, 6));
		}
		if (getValueFromListAsDouble(items, 7) != null) {
			teRecord.getLocation().setLatitude(getValueFromListAsDouble(items, 7));
		}
		teRecord.setUniqueItemId(getValueFromList(items, 8));
		if (getValueFromListAsDouble(items, 9) != null) {
			teRecord.setWeightGrams(getValueFromListAsShort(items, 9));
		}
		if (getValueFromListAsDouble(items, 10) != null) {
			teRecord.setLenghMillimetres(getValueFromListAsShort(items, 10));
		}
		if (getValueFromListAsDouble(items, 11) != null) {
			teRecord.setWidthMillimetres(getValueFromListAsShort(items, 11));
		}
		if (getValueFromListAsDouble(items, 12) != null) {
			teRecord.setHeighMillimetres(getValueFromListAsShort(items, 12));
		}
		if (getValueFromListAsDouble(items, 13) != null) {
			teRecord.setDeclaredWeightGrams(getValueFromListAsShort(items, 13));
		}
		if (getValueFromListAsDouble(items, 14) != null) {
			teRecord.setDeclaredLenghMillimetres(getValueFromListAsShort(items,
					14));
		}
		if (getValueFromListAsDouble(items, 15) != null) {
			teRecord.setDeclaredWidthMillimetres(getValueFromListAsShort(items,
					15));
		}
		if (getValueFromListAsDouble(items, 16) != null) {
			teRecord.setDeclaredHeighMillimetres(getValueFromListAsShort(items,
					16));
		}
		if (getValueFromList(items, 17) != null) {
			teRecord.setShapeType(ShapeType.valueOf(getValueFromList(items, 17)));
		}
		if (getValueFromList(items, 18) != null) {
			teRecord.setDestinationPostcode(getValueFromList(items, 18));
		}
		if (getValueFromList(items, 19) != null) {
			teRecord.setSourcePostCode(getValueFromList(items, 19));
		}
		if (getValueFromList(items, 20) != null) {
			teRecord.setCustomerAccountNumber(getValueFromList(items, 20));
		}

		return teRecord;
	}
	
	private String getValueFromList(List<String> items, int index) {
		String retValue = null;
		if (index < 0) {
			retValue = null;
		} else {
			retValue =  (items.size() >= index) ? items.get(index-1) : null;
		}
		return retValue;
	}
	
	private Double getValueFromListAsDouble(List<String> items, int index) {
		String retValue = getValueFromList(items, index);
		if (retValue == null) {
			return null;
		} else {
			return Double.parseDouble(retValue);
		}
	}
	
	private Short getValueFromListAsShort(List<String> items, int index) {
		String retValue = getValueFromList(items, index);
		if (retValue == null) {
			return null;
		} else {
			return Short.parseShort(retValue);
		}
	}
}
