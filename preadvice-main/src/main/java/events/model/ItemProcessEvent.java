package events.model;

import java.util.TreeMap;

/*
 * An event indicating an item received at a processing stage.
 */
public class ItemProcessEvent extends Event {
	
	private String scannerId;
	private String eventCode;
	private MailItem processedMailItemDetails;
	private ProcessingLocation location;
	
	public ItemProcessEvent() {
		createProcessingLocationIfNull();
		createProcessedMailItemIfNull();
	}
	
	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
	
	public void setValue(String name, String value) {

		processedMailItemDetails.setValue(name, value);
	}

	private void createProcessedMailItemIfNull() {
		if (processedMailItemDetails == null) {
			processedMailItemDetails = new MailItem();
		}
	}
	
	public String getValue(String name) {
		SimpleAttribute sAttribute = processedMailItemDetails.getValue(name);
		if (sAttribute == null) {
			return null;
		}
		return processedMailItemDetails.getValue(name).getValue();
	}

	public ProcessingLocation getLocation() {
		return location;
	}

	public void setLocation(String locationUnitCode) {
		this.location.setUnitCode(locationUnitCode);
	}

	private void createProcessingLocationIfNull() {
		if (this.location == null) {
			this.location = new ProcessingLocation();
		}
	}

	public String getScannerId() {
		return scannerId;
	}

	public void setScannerId(String scannerId) {
		this.scannerId = scannerId;
	}

	public String getUniqueItemId() {
		return processedMailItemDetails.getUniqueItemId();
	}

	public void setUniqueItemId(String uniqueItemId) {
		this.processedMailItemDetails.setUniqueItemId(uniqueItemId);
	}

	public short getWeightGrams() {
		return processedMailItemDetails.getWeightInGrammes();
	}

	public void setWeightGrams(short weightGrams) {
		this.processedMailItemDetails.setWeightInGrammes(weightGrams);
	}

	public short getDeclaredWeightGrams() {
		return this.processedMailItemDetails.getDeclaredWeightInGrammes();
	}

	public void setDeclaredWeightGrams(short declaredWeightGrams) {
		this.processedMailItemDetails.setDeclaredWeightInGrammes(declaredWeightGrams);
	}

	public short getLenghMillimetres() {
		return this.processedMailItemDetails.getLengthInMillimetres();
	}

	public void setLenghMillimetres(short lenghMillimetres) {
		this.processedMailItemDetails.setLengthInMillimetres(lenghMillimetres);
	}

	public short getWidthMillimetres() {
		return this.processedMailItemDetails.getWidthInMillimetres();
	}

	public void setWidthMillimetres(short widthMillimetres) {
		this.processedMailItemDetails.setWidthInMillimetres(widthMillimetres);
	}

	public short getHeighMillimetres() {
		return this.processedMailItemDetails.getHeightInMillimetres();
	}

	public void setHeighMillimetres(short heighMillimetres) {
		this.processedMailItemDetails.setHeightInMillimetres(heighMillimetres);
	}

	public short getDeclaredLenghMillimetres() {
		return this.processedMailItemDetails.getDeclaredLengthInMillimetres();
	}

	public void setDeclaredLenghMillimetres(short declaredLenghMillimetres) {
		this.processedMailItemDetails.setDeclaredLengthInMillimetres(declaredLenghMillimetres);
	}

	public short getDeclaredWidthMillimetres() {
		return this.processedMailItemDetails.getDeclaredWidthInMillimetres();
	}

	public void setDeclaredWidthMillimetres(short declaredWidthMillimetres) {
		this.processedMailItemDetails.setDeclaredWidthInMillimetres(declaredWidthMillimetres);
	}

	public short getDeclaredHeighMillimetres() {
		return this.processedMailItemDetails.getDeclaredHeightInMillimetres();
	}

	public void setDeclaredHeighMillimetres(short declaredHeighMillimetres) {
		this.processedMailItemDetails.setDeclaredHeightInMillimetres(declaredHeighMillimetres);
	}

	public ShapeType getShapeType() {
		return this.processedMailItemDetails.getShapeType();
	}

	public void setShapeType(ShapeType shapeType) {
		this.processedMailItemDetails.setShapeType(shapeType);
	}

	public String getDestinationPostcode() {
		return this.processedMailItemDetails.getDestinationPostCode();
	}

	public void setDestinationPostcode(String destinationPostcode) {
		this.processedMailItemDetails.setDestinationPostCode(destinationPostcode);
	}

	public String getSourcePostCode() {
		return this.processedMailItemDetails.getSourcePostCode();
	}

	public void setSourcePostCode(String sourcePostCode) {
		this.processedMailItemDetails.setSourcePostCode(sourcePostCode);
	}

	public String getCustomerAccountNumber() {
		return this.processedMailItemDetails.getCustomerAccountNumber();
	}

	public void setCustomerAccountNumber(String customerAccountNumber) {
		this.processedMailItemDetails.setCustomerAccountNumber(customerAccountNumber);
	}

	public String getStatus() {
		return this.processedMailItemDetails.getStatus();
	}

	public void setStatus(String status) {
		this.processedMailItemDetails.setStatus(status);
	}

	public TreeMap<String, SimpleAttribute> getValueList() {
		return this.processedMailItemDetails.getValueList();
	}

}
