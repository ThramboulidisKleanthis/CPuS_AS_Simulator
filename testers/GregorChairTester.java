package testers;

import products.Arm;
import products.Back;
import products.Base;
import products.GasLift;
import products.GregorChair;
import products.LegsConnector;
import products.Seat;
import products.SeatPlate;
import products.UpperSubAssembly;
import products.GregorChair.Builder;

public class GregorChairTester {

	public static void main(String[] args) {
		Base base;
		UpperSubAssembly us;
		GregorChair.Builder gcb;
		int partsNo=123;

		
		gcb= new GregorChair.Builder();
		gcb.getBaseBuilder()
		.addLc(new LegsConnector(partsNo))
		.addLegs()
		.addTc()
		.build();
		gcb.getUsaBuilder()
		.addSeat(new Seat(partsNo))
		.addSp(new SeatPlate(partsNo))
		.build();
		GregorChair gc=gcb
		.addBack(new Back(12))
		.addGl(new GasLift(123))
		.addLeftArm(new Arm(1))
		.addRightArm(new Arm(1))
		.build();
		System.out.println(gc);
		
//		gc=createGregorChair();
//		System.out.println(gc);
//
//		base = new Base.Builder()
//				.addLc(new LegsConnector(partsNo))
//				.addLegs()
//				.build();
//		System.out.println(base);
//		base = new Base.Builder()
//				.addLegs()
//				.addTc()
//				.build();
//		System.out.println(base);
//
//
//		us= new UpperSubAssembly.Builder()
//				.addSeat(new Seat(partsNo))
//				.addSp(new SeatPlate(partsNo))
//				.build();
//		System.out.println(us);
//
//		gc = new GregorChair.Builder()
//				.addBase(base)
//				.addUsa(us)
//				.addGl(new GasLift(123))
//				.addLeftArm(new Arm(1))
//				.addRightArm(new Arm(1))
//				.build();
//
//		System.out.println(gc);
	}
}
