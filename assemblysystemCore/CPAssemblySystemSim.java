package assemblysystemCore;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;


import common.Order;
import distributionSystem.DistributionSystem;
import orderSystem.OrderSystem;
import produstionSystemCore.ProductionSystem;

public class CPAssemblySystemSim {
	static CPAssemblySystemSim cpass;
	OrderSystem os;
	ProductionSystem ps;
	DistributionSystem ds;
	public final static Logger LOGGER = Logger.getLogger(CPAssemblySystemSim.class.getName());
	static FileHandler fh =null;
	
	public static void main(String[] args) {
		
		LOGGER.setLevel(Level.INFO);
//		LOGGER.setLevel(Level.INFO);
//		 System.setProperty("java.util.logging.SimpleFormatter.format","%1$tF %1$tT %4$s %2$s %5$s%6$s%n");
//		 System.setProperty("java.util.logging.SimpleFormatter.format","%1$tT %4$s %2$s %5$s%6$s%n");
		 System.setProperty("java.util.logging.SimpleFormatter.format","%4$s %2$s %5$s%6$s%n");
		//see https://stackoverflow.com/questions/194765/how-do-i-get-java-logging-output-to-appear-on-a-single-line
//		LOGGER.setLevel(Level.ALL);
		try {
			fh = new FileHandler("CPAssemblySystemSimLog.xml");
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.addHandler(fh);
		LOGGER.log(Level.INFO,"[CPASSim] Plant set up started");

		var os = new OrderSystem();
		addOrders(os,7);
		cpass = new CPAssemblySystemSim(os,new ProductionSystem(),new DistributionSystem());
		cpass.ps.setCpass(cpass);
		cpass.ds.setCpass(cpass);
		LOGGER.log(Level.INFO,"----------------PLANT INITIALIZED AND STARTED---------------------\n\n");
//		System.out.println("------------------------------------PLANT INITIALIZED AND STARTED---------------------");		
		cpass.ps.start();
	}

	protected void finalize(){
	fh.close();	
	}
	
	private static void addOrders(OrderSystem os,int n) {
		int i;
		for(i=1;i<n+1;i++) {
			os.placeOrder(new Order(i,new Date(),"Client No"+i));
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		LOGGER.log(Level.FINE,"[GCAS] " + --i + " orders placed");
//		System.out.println("[GCAS] " + --i + " orders placed");
	}

	private CPAssemblySystemSim(OrderSystem os,ProductionSystem ps,DistributionSystem ds) {
		this.os=os;
		this.ps=ps;
		this.ds=ds;
		this.os.setCpass(this);
		this.ps.setCpass(this);
		this.getDs().setCpass(this);
	}

	public Order getNextOrder() {
		return os.getOrder();
	}

	public DistributionSystem getDs() {
		return ds;
	}


}
