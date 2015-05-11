package statuscalculation.events;

public interface MailItemStatusCalculator {
	public String determineStatus(String currentStatus, String event);
}
