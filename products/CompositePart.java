package products;

import java.util.Date;

public abstract class CompositePart {
	int id;
	Date pd;
	int partsNumber=0;
	
	public CompositePart(int curId,Date d) {
		this.id = curId;
		pd=d;
	}
	public String toString() {
		return getName() +" id: " + id + (completed()?"\tCOMPLETE!":"\tUNCOMPLETE" )+ " NumberOfParts:" + partsNumber + "\t production date:" + pd;  
		
	}
	
	private boolean completed() {
//		System.out.println("partsNumber:" + partsNumber + " noOfParts:" +noOfParts );
		return partsNumber==getPARTSNO();
	}
	
	abstract String getName();
	abstract int getPARTSNO();
	
	public int getId() {
		return id;
	}

	public int getPartsNumber() {
		return partsNumber;
	}
}
