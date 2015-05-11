package statuscalculation.events;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class MailItemStatus extends HashMap<String, String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5693105294565903653L;

	public MailItemStatus() {
		// "Current Status, Event Code", "New status"

		// preadviced
		this.put("null,PREADVICED", "PREADVICED");
		
		// adviced
		this.put("null,ADVICED", "ADVICED");
		this.put("PREADVICED,ADVICED", "ADVICED");
		
		// outbound processing
		this.put("null,RECEIVEDOUTBOUND", "OUTWARDPROCESSING");
		this.put("ADVICED,RECEIVEDOUTBOUND", "OUTWARDPROCESSING");
		this.put("OUTWARDPROCESSING,PROCESSED", "OUTWARDPROCESSING");

		// on route
		this.put("OUTWARDPROCESSING,EXITOUTBOUND", "ONROUTE");
		this.put("INWARDPROCESSING,EXITINBOUND", "ONROUTE");
		
		// inbound processing
		this.put("ONROUTE,RECEIVEDINBOUND", "INWARDPROCESSING");
		this.put("INWARDPROCESSING,PROCESSED", "INWARDPROCESSING");

		
		// delivery office
		this.put("ONROUTE,RECEIVEDATDELIVERY", "RECEIVEDFORDELIVERY");
		
		// on delivery
		this.put("RECEIVEDFORDELIVERY,EXITDELIVERY", "BEINGDELIVERED");
		
		// delivered
		this.put("BEINGDELIVERED,DELIVERED", "DELIVERED");
	}
	
}
