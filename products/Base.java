package products;

import java.util.Date;

public class Base extends CompositePart{
	private static int curId=0;
	static String name;
	static final int PARTSNO;
	private LegsConnector lc;
	private Leg [] legs;
	private TwinCastor [] tc;
	
	static {
		PARTSNO=11;
		name = "Base";
	}
	public static class Builder {
		private LegsConnector lc;
		private Leg [] legs;
		private TwinCastor [] tc;
		private int partsNumber=0;
		private Date d;
	
		
		public Builder() {
			curId++;
        }
		public Base build(){
            return new Base(lc,legs,tc,partsNumber,d);  
       }
		
		public LegsConnector getLc() {
			return lc;
		}

		public Builder addLc(LegsConnector lc) {
			this.lc = lc;
			partsNumber++;
			return this;
		}

		public Leg[] getLegs() {
			return legs;
		}

		public Builder  addLegs() {
			legs = new Leg[5];
			for(int i=0;i<5;i++)
				legs[i] = new Leg(curId);
			partsNumber+=5;
			return this;
		}

		public TwinCastor[] getTc() {
			return tc;
		}

		public Builder addTc() {
			tc = new TwinCastor[5];
			for(int i=0;i<5;i++)
				tc[i] = new TwinCastor(curId);
			d= new Date();
			partsNumber+=5;
			return this;
		}
		
		public int getPartsNumber() {
			return partsNumber;
		}
		public void setPartsNumber(int partsNumber) {
			this.partsNumber = partsNumber;
		}
	}
	
	private Base(LegsConnector lc,Leg [] legs,TwinCastor [] tc, int pn, Date d) {
		super(curId,d);
		this.lc = lc;
		this.legs = legs;
		this.tc = tc;
		this.partsNumber=pn;
		this.pd=d;
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

	
//	public String toString() {
//		return "Base id: " + id + " production date:" + pd + " completed:" + completed();  
//		
//	}

//	private boolean completed() {
//		return partsNumber==noOfParts;
//	}
}
