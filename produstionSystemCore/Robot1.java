package produstionSystemCore;

import java.util.logging.Level;

import assemblysystemCore.CPAssemblySystemSim;
import common.Order;
import products.Arm;
import products.Back;
import products.GasLift;
import products.GregorChair;
import products.LegsConnector;
import products.Seat;
import products.SeatPlate;
import products.UpperSubAssembly;

public class Robot1 implements Runnable {
	ProductionSystem ps;
	Order order;
	int ordersCompletedSoFar=0;
	Fixture f;
	GregorChair.Builder gcb;
	
	public Robot1(ProductionSystem ps) {
		this.ps= ps;
//		System.out.println("->[ROBOT1] Initialized ok");
		CPAssemblySystemSim.LOGGER.log(Level.CONFIG,"->[ROBOT1] Initialized ok");
	}

	@Override
	public void run() {
//		targetPos("R1pos1");
		while(true) {
			gcb= new GregorChair.Builder();
			order=ps.getNextOrder();
//			System.out.println("->[ROBOT1] executing order" + order);
			CPAssemblySystemSim.LOGGER.log(Level.FINER,"->[ROBOT1] executing order" + order);
			ps.w1.acquire(ps.w1.pos1);
			ordersCompletedSoFar++;
			workOnPos1(gcb);
//			targetPos("R1pos2");
			ps.w1.release(ps.w1.pos1);
					
			ps.w2.aquire();
			workOnPos2(gcb);
//			System.out.println("->[ROBOT1] ordersCompletedSoFar: " + ordersCompletedSoFar);
			CPAssemblySystemSim.LOGGER.log(Level.FINE,"->[ROBOT1] ordersCompletedSoFar: " + ordersCompletedSoFar);
//			targetPos("R1pos1");
			ps.w2.release();
			
		}
	}

	private void workOnPos1(GregorChair.Builder gcb){
//		System.out.println("->[ROBOT1] working on pos1 on order:"+ordersCompletedSoFar);
		CPAssemblySystemSim.LOGGER.log(Level.FINER,"->[ROBOT1] working on pos1 on order:"+ordersCompletedSoFar);
		f=ps.w1.pos1.f;
		f.setGcb(gcb);
		f.fix();
		gcb.getBaseBuilder().addLc(new LegsConnector(12));
		gcb.getBaseBuilder().addLegs();
//		if(f.getGcb().getUsaBuilder()==null)
//			System.out.println("f.getGcb().getUsaBuilder():null");
		try {
			Thread.sleep(DelayTime.R1WORKONPOS1TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		r1.aquire(W1.pos1);  //sends aquire request to w1
//		r1.getLegsConnector();
//		r1.moveTo(W1.pos1);
//		w1.fix(LegsConnector);
//		for(int leg=1; leg<6;i++) {
//			r1.getLeg();
//			r1.moveTo(w1.pos1);
//			r1.fix2LegsConnector();
//			w1.rotate(60);
//		}
//		r1.release(W1.pos1);   //sends release request to w1
	}

	private void workOnPos2(GregorChair.Builder gcb) {
//		System.out.println("->[ROBOT1] working on pos2 on order:"+ordersCompletedSoFar);
		CPAssemblySystemSim.LOGGER.log(Level.FINER,"->[ROBOT1] working on pos2 on order:"+ordersCompletedSoFar);
		f=ps.w2.f;
		var usab = gcb.getUsaBuilder();
		f.setUsab(usab);
		usab.addSeat(new Seat(134));
		f.fix();
		usab.addSp(new SeatPlate(32));
		try {
			Thread.sleep(DelayTime.R1WORKONPOS2TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void targetPos(String s) {
		CPAssemblySystemSim.LOGGER.log(Level.FINER,"->[ROBOT1]  moved to " + s);
//		System.out.println("->[ROBOT1]  moved to " + s);
	}

//	private boolean aquire(Resource r, String s) {
//		return r.aquire();
//	}
}
