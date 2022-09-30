package orderSystem;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;

import assemblysystemCore.CPAssemblySystemSim;
import common.Order;

public class OrderSystem {
	private CPAssemblySystemSim cpass;
	ArrayBlockingQueue<Order> orders ;
	
	public OrderSystem() {
//		this.gcas=gcas;
		orders = new ArrayBlockingQueue<Order>(100);
//		System.out.println("[ORDER SYSTEM] initialized");
		CPAssemblySystemSim.LOGGER.log(Level.CONFIG,"[ORDER SYSTEM] initialized");
	}
	
	public void placeOrder(Order o) {
		try {
			orders.put(o);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("[ORDER SYSTEM] Order received:" + o);
		CPAssemblySystemSim.LOGGER.log(Level.INFO,"[ORDER SYSTEM] Order received:" + o);
	}
	
	public Order getOrder() {
		try {
			return orders.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void setCpass(CPAssemblySystemSim cpass) {
		this.cpass = cpass;
	}
}
