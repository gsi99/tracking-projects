package events.model;

import java.util.List;
import java.util.TreeMap;

public class MailItem {
	
	// ArrayList<SimpleAttribute> values;
	TreeMap<String, SimpleAttribute> valueList;
	
	String uniqueItemId;
	short weightInGrammes;
	short declaredWeightInGrammes;
	short lengthInMillimetres;
	short widthInMillimetres;
	short heightInMillimetres;
	short declaredLengthInMillimetres;
	short declaredWidthInMillimetres;
	short declaredHeightInMillimetres;	
	ShapeType shapeType;
	PostCode destinationPostCode;
	PostCode sourcePostCode;
	String customerAccountNumber;
	
	String status;
	String previousStatus;
	String eventCode;
	List<Event> eventList;
	
	public MailItem() {
		if (valueList == null ) {
			valueList = new TreeMap<String, SimpleAttribute>();
		}
	}
	
	public MailItem(MailItem mailItem) {
		if (valueList == null ) {
			valueList = new TreeMap<String, SimpleAttribute>();
		}
		this.setUniqueItemId(mailItem.getUniqueItemId());
		this.setWeightInGrammes(mailItem.getWeightInGrammes());
		this.setDeclaredWeightInGrammes(mailItem.getDeclaredWeightInGrammes());
		this.setLengthInMillimetres(mailItem.getLengthInMillimetres());
		this.setWidthInMillimetres(mailItem.getWidthInMillimetres());
		this.setHeightInMillimetres(mailItem.getHeightInMillimetres());
		this.setDeclaredLengthInMillimetres(mailItem.getDeclaredLengthInMillimetres());
		this.setDeclaredWidthInMillimetres(mailItem.getDeclaredWidthInMillimetres());
		this.setDeclaredHeightInMillimetres(mailItem.getDeclaredHeightInMillimetres());
		this.setShapeType(mailItem.getShapeType());
		this.setDestinationPostCode(mailItem.getDestinationPostCode());
		this.setSourcePostCode(mailItem.getSourcePostCode());
		this.setCustomerAccountNumber(mailItem.getCustomerAccountNumber());
		this.setStatus(mailItem.getStatus());
		this.setPreviousStatus(mailItem.getPreviousStatus());
		this.setEventCode(mailItem.getEventCode());
		this.valueList.putAll(mailItem.valueList);
		
	}
	
	public MailItem(ItemProcessEvent teRecord) {
		if (valueList == null ) {
			valueList = new TreeMap<String, SimpleAttribute>();
		}
		this.setUniqueItemId(teRecord.getUniqueItemId());
		this.setWeightInGrammes(teRecord.getWeightGrams());
		this.setDeclaredWeightInGrammes(teRecord.getDeclaredWeightGrams());
		this.setLengthInMillimetres(teRecord.getLenghMillimetres());
		this.setWidthInMillimetres(teRecord.getWidthMillimetres());
		this.setHeightInMillimetres(teRecord.getHeighMillimetres());
		this.setDeclaredLengthInMillimetres(teRecord.getDeclaredLenghMillimetres());
		this.setDeclaredWidthInMillimetres(teRecord.getDeclaredWidthMillimetres());
		this.setDeclaredHeightInMillimetres(teRecord.getDeclaredHeighMillimetres());
		this.setShapeType(teRecord.getShapeType());
		this.setDestinationPostCode(teRecord.getDestinationPostcode());
		this.setSourcePostCode(teRecord.getSourcePostCode());
		this.setCustomerAccountNumber(teRecord.getCustomerAccountNumber());
		this.setStatus(teRecord.getStatus());
		this.setEventCode(teRecord.getEventCode());
	}

	public void setValue(String name, String value) {
		SimpleAttribute sa = new SimpleAttribute(name, value);
		valueList.put(name, sa);
	}
	
	public SimpleAttribute getValue(String name) {
		return valueList.get(name);
	}


	public String getUniqueItemId() {
		return uniqueItemId;
	}


	public void setUniqueItemId(String uniqueItemId) {
		this.uniqueItemId = uniqueItemId;
	}

	public ShapeType getShapeType() {
		return shapeType;
	}


	public void setShapeType(ShapeType shapeType) {
		this.shapeType = shapeType;
	}


	public String getCustomerAccountNumber() {
		return customerAccountNumber;
	}


	public void setCustomerAccountNumber(String customerAccountNumber) {
		this.customerAccountNumber = customerAccountNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPreviousStatus() {
		return previousStatus;
	}

	public void setPreviousStatus(String previousStatus) {
		this.previousStatus = previousStatus;
	}

	public List<Event> getEventList() {
		return eventList;
	}

	public void setEventList(List<Event> eventCollection) {
		this.eventList = eventCollection;
	}

	public String getDestinationPostCode() {
		if (destinationPostCode != null) {
			return destinationPostCode.getPostcode();
		}
		return null;
	}

	public void setDestinationPostCode(String destinationPostcode) {
		this.destinationPostCode = new PostCode(destinationPostcode);
	}

	public String getSourcePostCode() {
		if (sourcePostCode != null) {
			return sourcePostCode.getPostcode();
		}
		return null;
	}

	public void setSourcePostCode(String sourcePostCode) {
		this.sourcePostCode = new PostCode(sourcePostCode);
	}

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public short getWeightInGrammes() {
		return weightInGrammes;
	}

	public void setWeightInGrammes(short weightInGrammes) {
		this.weightInGrammes = weightInGrammes;
	}

	public short getDeclaredWeightInGrammes() {
		return declaredWeightInGrammes;
	}

	public void setDeclaredWeightInGrammes(short declaredWeightInGrammes) {
		this.declaredWeightInGrammes = declaredWeightInGrammes;
	}

	public short getLengthInMillimetres() {
		return lengthInMillimetres;
	}

	public void setLengthInMillimetres(short lenghInMillimetres) {
		this.lengthInMillimetres = lenghInMillimetres;
	}

	public short getWidthInMillimetres() {
		return widthInMillimetres;
	}

	public void setWidthInMillimetres(short widthInMillimetres) {
		this.widthInMillimetres = widthInMillimetres;
	}

	public short getHeightInMillimetres() {
		return heightInMillimetres;
	}

	public void setHeightInMillimetres(short heighInMillimetres) {
		this.heightInMillimetres = heighInMillimetres;
	}

	public short getDeclaredLengthInMillimetres() {
		return declaredLengthInMillimetres;
	}

	public void setDeclaredLengthInMillimetres(short declaredLenghInMillimetres) {
		this.declaredLengthInMillimetres = declaredLenghInMillimetres;
	}

	public short getDeclaredWidthInMillimetres() {
		return declaredWidthInMillimetres;
	}

	public void setDeclaredWidthInMillimetres(short declaredWidthInMillimetres) {
		this.declaredWidthInMillimetres = declaredWidthInMillimetres;
	}

	public short getDeclaredHeightInMillimetres() {
		return declaredHeightInMillimetres;
	}

	public void setDeclaredHeightInMillimetres(short declaredHeighInMillimetres) {
		this.declaredHeightInMillimetres = declaredHeighInMillimetres;
	}

	public TreeMap<String, SimpleAttribute> getValueList() {
		return valueList;
	}

	public void setValueList(TreeMap<String, SimpleAttribute> newValueList) {
		this.valueList.putAll(newValueList);
	}

}
