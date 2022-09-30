package products;

import java.util.Date;

import products.Base.Builder;

public class UpperSubAssembly extends CompositePart{
	private static int curId=0;
	static String name;
	static final int PARTSNO;
	private SeatPlate sp;
	private Seat seat;
	
	static {
		PARTSNO=2;
		name="UpperSubAssembly" ;
	}
	public static class Builder {
		private int id;
		private SeatPlate sp;
		private Seat seat;
		private int partsNumber=0;
		private Date d;
		
		public Builder() {
			id=++curId;
	       }
		public UpperSubAssembly build(){
            return new UpperSubAssembly(id,sp,seat,partsNumber,d);  
       }

		public Builder addSeat(Seat s) {
			this.seat = s;
			partsNumber++;
			d= new Date();
			return this;
		}
		
		public Builder addSp(SeatPlate sp) {
			this.sp = sp;
			partsNumber++;
			d= new Date();
			return this;
		}
		public int getPartsNumber() {
			return partsNumber;
		}
		public void setPartsNumber(int partsNumber) {
			this.partsNumber = partsNumber;
		}

	
	}
	
	private UpperSubAssembly(int id,SeatPlate sp,Seat seat, int pn,Date d) {
		super(id, d);
		this.sp = sp;
		this.seat = seat;
		this.partsNumber=pn;
		this.pd=d;
	}
	public int getPartsNumber() {
		return partsNumber;
	}

	public SeatPlate getSp() {
		return sp;
	}

	public Seat getSeat() {
		return seat;
	}
	@Override
	String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	@Override
	int getPARTSNO() {
		// TODO Auto-generated method stub
		return PARTSNO;
	}
}
