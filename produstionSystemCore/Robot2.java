package produstionSystemCore;

import java.util.logging.Level;

import assemblysystemCore.CPAssemblySystemSim;
import products.GasLift;
import products.GregorChair;
import products.UpperSubAssembly;

public class Robot2 implements Runnable {
	ProductionSystem ps;
	int ordersCompletedSoFar=0;
	private Fixture f;
	private UpperSubAssembly.Builder usab;
	private GregorChair.Builder gcb;
	
	public Robot2(ProductionSystem ps) {
		this.ps= ps;
//		System.out.println("-->[ROBOT2] Initialized ok");
		CPAssemblySystemSim.LOGGER.log(Level.CONFIG, "-->[ROBOT2] Initialized ok");
	}

	@Override
	public void run() {
		targetPos("pos1");
		while(true) {
			ps.w1.acquire(ps.w1.pos2);
			ordersCompletedSoFar++;
			gcb=ps.w1.pos2.f.getGcb();
			usab = gcb.getUsaBuilder();
			workOnPos1_1();
			targetPos("pos2");

			ps.w2.aquire();
			workOnPos2();
			targetPos("pos1");
			ps.w2.release();
			
			workOnPos1_2();
//			if(gcb==null)
//				System.out.println("-->[ROBOT2] gcb=null");
			CPAssemblySystemSim.LOGGER.log(Level.FINE, "-->[ROBOT2] ordersCompletedSoFar: " + ordersCompletedSoFar);
//			System.out.println("-->[ROBOT2] ordersCompletedSoFar: " + ordersCompletedSoFar);
			ps.w1.release(ps.w1.pos2);
		}
	}

	private void workOnPos1_1(){
		CPAssemblySystemSim.LOGGER.log(Level.FINER, "-->[ROBOT2] working on pos1a");
//		System.out.println("-->[ROBOT2] working on pos1a");
		f=ps.w1.pos2.f;
		gcb.getBaseBuilder().addTc();
		gcb.addGl(new GasLift(11));
		try {
			Thread.sleep(DelayTime.R2WORKONPOS1TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void workOnPos2() {
		f=ps.w2.f;
		f.unFix();
//		usab=f.getUsab();
//		f.reset();
		CPAssemblySystemSim.LOGGER.log(Level.FINER, "-->[ROBOT2] working on pos2 on order:"+ordersCompletedSoFar);
//		System.out.println("-->[ROBOT2] working on pos2 on order:"+ordersCompletedSoFar);
		try {
			Thread.sleep(DelayTime.R2WORKONPOS2TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private void workOnPos1_2() {
//		f=ps.w1.pos2.f;
//		f.getGcb().setUsaBuilder(usab);
//		gcb.setUsaBuilder(usab);  	// add UpperSubAssembly
		CPAssemblySystemSim.LOGGER.log(Level.FINER, "-->[ROBOT2] working on pos1b on order:"+ordersCompletedSoFar);
//		System.out.println("-->[ROBOT2] working on pos1b on order:"+ordersCompletedSoFar);
		try {
			Thread.sleep(DelayTime.R2WORKONPOS2TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private void targetPos(String s) {
		CPAssemblySystemSim.LOGGER.log(Level.FINER, "-->[ROBOT2]  moved to " + s);
//		System.out.println("-->[ROBOT2]  moved to " + s);
	}
}
