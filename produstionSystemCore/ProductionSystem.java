package produstionSystemCore;
import java.util.logging.Level;

import assemblysystemCore.CPAssemblySystemSim;
import common.Order;
import products.Arm;
import products.Back;
import products.Base;
import products.GasLift;
import products.GregorChair;
import products.LegsConnector;
import products.Seat;
import products.SeatPlate;
import products.UpperSubAssembly;

public class ProductionSystem {
	private CPAssemblySystemSim cpass;
	Robot1 r1;
	Robot2 r2;
	Robot3 r3;
	Workbench1 w1;
	Workbench2 w2;
//	GcasController ctrl;
	
	public ProductionSystem() {
//		this.gcas = gcas;
//		System.out.println("[PRODUCTION SYSTEM] initialized");
		CPAssemblySystemSim.LOGGER.log(Level.CONFIG,"[PRODUCTION SYSTEM] initialized");
		r1 = new Robot1(this);
		r2 = new Robot2(this);
		r3 = new Robot3(this);
		w1 = new Workbench1(this);
		w2 = new Workbench2(this);
	}

	public void start() {
		Thread t;
		t=new Thread(r1);
		t.setName("ROBOT1");
		t.start();
		t=new Thread(r2);
		t.setName("ROBOT2");
		t.start();
		t=new Thread(r3);
		t.setName("ROBOT3");
		t.start();
//		new Thread(w1).start();
//		new Thread(w2).start();
//		System.out.println("[PRODUCTION SYSTEM] Working ... ");
	}
	public Order getNextOrder() {
		return cpass.getNextOrder();
	}

	public void setCpass(CPAssemblySystemSim cpass) {
		this.cpass = cpass;
	}

	public CPAssemblySystemSim getCpass() {
		return cpass;
	}
	
//	GregorChair createGregorChair() {
//		Base base;
//		UpperSubAssembly us;
//		GregorChair gc;
//		int partsNo=123;
//
//		base = new Base.Builder()
//				.addLc(new LegsConnector(partsNo))
//				.addLegs()
//				.addTc()
//				.build();
//		System.out.println(base);
//
//		us= new UpperSubAssembly.Builder()
//				.addSeat(new Seat(partsNo))
//				.addSp(new SeatPlate(partsNo))
//				.build();
//		System.out.println(us);
//
//
//		gc = new GregorChair.Builder()
//				.addBase(base)
//				.addUsa(us)
//				.addBack(new Back(12))
//				.addGl(new GasLift(123))
//				.addLeftArm(new Arm(1))
//				.addRightArm(new Arm(1))
//				.build();
//		return gc;
//	}

}
