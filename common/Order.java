package common;
import java.util.Date;

public class Order {
	int id;
	Date entrydate;
	String clientName;
	
	public Order(int id, Date d, String clientName) {
		this.id = id;
		entrydate = d;
		this.clientName = clientName; 
	}
	
	public String toString() {
		return "[ORDER] -> id: " + id + " entrydate: " + entrydate + " clientName:" + clientName;
	}
}
