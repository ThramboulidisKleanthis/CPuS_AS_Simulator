package distributionSystem;
import java.util.Stack;
import java.util.logging.Level;

import assemblysystemCore.CPAssemblySystemSim;
import products.GregorChair;

public class DistributionSystem {
	private CPAssemblySystemSim cpass;
	public Stack<GregorChair> stack;
	private int previousId=0;
	
	public DistributionSystem() {
//		this.gcas = gcas;
		stack = new Stack<GregorChair>();
//		System.out.println("[DISTRIBUTION SYSTEM] initialized ");
		CPAssemblySystemSim.LOGGER.log(Level.CONFIG,"[DISTRIBUTION SYSTEM] initialized ");
	}

	public void store(GregorChair gc) {
		stack.push(gc);
		checkProduct(gc);
		CPAssemblySystemSim.LOGGER.log(Level.INFO,"\n----->[DISTRIBUTION SYSTEM] Gregor Chair DELIVERED: " + gc+"\n");
//		System.out.println("----->[DISTRIBUTION SYSTEM] Gregor Chair DELIVERED: " + gc);
	}
	
	private void checkProduct(GregorChair gc) {
		if(gc.getId()!=++previousId)
			CPAssemblySystemSim.LOGGER.log(Level.WARNING,"----->[DISTRIBUTION SYSTEM] product out of order " + gc);
//			System.out.println("----->[DISTRIBUTION SYSTEM] product out of order " + gc);
		if(gc.getPartsNumber()!=gc.getPARTSNO())
			CPAssemblySystemSim.LOGGER.log(Level.WARNING,"----->[DISTRIBUTION SYSTEM] BROKEN product " + gc);
//			System.out.println("----->[DISTRIBUTION SYSTEM] BROKEN product " + gc);
	}

	public void showStock() {
		System.out.println(this.toString());
	}
	
	public String toString() {
		return "Distribution System stack: " + stack.toString();
	}
	
	public void setCpass(CPAssemblySystemSim cpass) {
		this.cpass = cpass;
	}
}
