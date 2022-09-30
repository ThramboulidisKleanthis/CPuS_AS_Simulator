package produstionSystemCore;

import java.util.logging.Level;

import assemblysystemCore.CPAssemblySystemSim;

public class Workbench2 {
	ProductionSystem ps;
	boolean available;
	Fixture f;
	
	public Workbench2(ProductionSystem ps) {
		this.ps = ps;
		available = true;
		f = new Fixture("Fw2");
		CPAssemblySystemSim.LOGGER.log(Level.CONFIG,"[WORKBENCH2] Initialized ok");
//		System.out.println("[WORKBENCH2] Initialized ok");
		
	}

	public synchronized boolean aquire() {
		while(!available)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		available= false;
		return true;
	}

	public synchronized void release() {
		available= true;
		notifyAll();
	}
}
