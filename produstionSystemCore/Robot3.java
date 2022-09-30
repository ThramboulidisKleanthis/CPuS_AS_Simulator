package produstionSystemCore;
import java.util.Date;
import java.util.logging.Level;

import assemblysystemCore.CPAssemblySystemSim;
import products.Arm;
import products.Back;
import products.GregorChair;

public class Robot3 implements Runnable {
	ProductionSystem ps;
	int ordersCompletedSoFar =0;
	Fixture f;
	GregorChair gc;
	GregorChair.Builder gcb;
	
	public Robot3(ProductionSystem ps) {
		this.ps= ps;
		CPAssemblySystemSim.LOGGER.log(Level.CONFIG,"--->[ROBOT3] Initialized ok");
//		System.out.println("--->[ROBOT3] Initialized ok");
	}
	@Override
	public void run() {
		while(true) {
			ps.w1.acquire(ps.w1.pos3);
			f=ps.w1.pos3.f;
			gcb=f.getGcb();
//			if(gcb==null)
//				System.out.println("--->[ROBOT3] gcb=null");
			workOnPos();
			ps.w1.release(ps.w1.pos3);
			ordersCompletedSoFar++;
			CPAssemblySystemSim.LOGGER.log(Level.FINE,"--->[ROBOT3] ordersCompletedSoFar: " + ordersCompletedSoFar);
//			System.out.println("--->[ROBOT3] ordersCompletedSoFar: " + ordersCompletedSoFar);
//			GregorChair gc= ps.createGregorChair();
//			storeToDistributionSystem(gc);
		}
	}
	
	private void workOnPos() {
		CPAssemblySystemSim.LOGGER.log(Level.FINE,"--->[ROBOT3] working on order:"+ordersCompletedSoFar);
//		System.out.println("--->[ROBOT3] working on order:"+ordersCompletedSoFar);
		gcb.addBack(new Back(33));
		gcb.addLeftArm(new Arm(44));
		gcb.addRightArm(new Arm(55));
		gc=gcb.build();
		f.unFix();
		f.reset();
		ps.getCpass().getDs().store(gc);
		try {
			Thread.sleep(DelayTime.R3WORKTIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void storeToDistributionSystem(GregorChair gc) {
		ps.getCpass().getDs().store(gc);
	}
}
