package produstionSystemCore;

import products.GregorChair;
import products.UpperSubAssembly;

public class Fixture {
	String name;
	private boolean onDuty;
	private GregorChair.Builder gcb;
	private UpperSubAssembly.Builder usab;
	private boolean fixed;

	public Fixture(String s) {
		name = s;
		onDuty = false;
		gcb=null;
		usab=null;
		fixed=false;
//		onProduction = false;
	}
	
	public void reset() {
		onDuty = false;
		gcb=null;
		usab=null;
		fixed=false;
	}
	
	public boolean isOnDuty() {
		return onDuty;
	}

	public void setOnDuty(boolean onDuty) {
		this.onDuty = onDuty;
		if(!onDuty) {
			gcb=null;
			usab=null;
			fixed=false;
		}
	}
	
	public void fix() {
		fixed=true;
	}
	public void unFix() {
		fixed=false;
	}
	public 	GregorChair.Builder getGcb() {
		return gcb;
	}
	public void setGcb(GregorChair.Builder gc) {
		this.gcb = gc;
	}
	public UpperSubAssembly.Builder getUsab() {
		return usab;
	}
	public void setUsab(UpperSubAssembly.Builder usab) {
		this.usab = usab;
	}
	
}
