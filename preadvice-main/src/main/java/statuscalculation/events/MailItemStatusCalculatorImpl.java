package statuscalculation.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MailItemStatusCalculatorImpl implements MailItemStatusCalculator{

	@Autowired
	MailItemStatus statusMap;
	
	@Override
	public String determineStatus(String currentStatus, String event) {
		String nextStatus = statusMap.get(currentStatus + "," + event);
		return nextStatus;
	}

	public MailItemStatus getStatusMap() {
		return statusMap;
	}

	public void setStatusMap(MailItemStatus statusMap) {
		this.statusMap = statusMap;
	}

}
