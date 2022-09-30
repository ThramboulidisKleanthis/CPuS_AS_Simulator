package produstionSystemCore;

import java.util.logging.Level;

import assemblysystemCore.CPAssemblySystemSim;

public class Workbench1 implements Resource{
	ProductionSystem ps;
	//	private WorkbenchPos ipos1,ipos2,ipos3;
	public WorkbenchPos pos1,pos2,pos3; 
	private Fixture f1,f2,f3;
	int rotNum=0;


	public Workbench1(ProductionSystem ps) {
		this.ps = ps;
		f1= new Fixture("F1");
		f2= new Fixture("F2");
		f3= new Fixture("F3");
		pos1 = new FWorkbenchPos(this,true,f1);
		pos2 = new SWorkbenchPos(this,true,f2);
		pos3 = new TWorkbenchPos(this,true,f3);
		CPAssemblySystemSim.LOGGER.log(Level.CONFIG,"[WORKBENCH1] Initialized ok");
//		System.out.println("[WORKBENCH1] Initialized ok");
	}

	public synchronized void acquire(WorkbenchPos pos) {
		CPAssemblySystemSim.LOGGER.log(Level.FINE,"\n[WORKBENCH1.aquire()] by:" + Thread.currentThread().getName());
//		System.out.println("\n[WORKBENCH1.aquire()] by:" + Thread.currentThread().getName());
//		boolean f2wait=false;
//		boolean s2wait=false;
//		boolean t2wait=false;
//		System.out.println("[WORKBENCH1 status] \n" + this.toSting()  + "?2wait: "+ f2wait +"\t" + s2wait +"\t" + t2wait+ "\n");
		while(!pos.ok2proceed()) {
			try {
				wait();
				CPAssemblySystemSim.LOGGER.log(Level.FINER,"went to wait state");
//				System.out.println("went to wait state");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pos instanceof FWorkbenchPos ) {
			pos.f.setOnDuty(true);
			CPAssemblySystemSim.LOGGER.log(Level.FINEST,"SETONDUTY");
		}
		pos.ok2rot=false;		
//		pos.available= false;
		return ;
	}

	public synchronized void release(WorkbenchPos pos) {
		if(pos instanceof TWorkbenchPos ) 
			pos.f.reset();
		pos.ok2rot=true;
		pos.jobPending=false;
		rotate();
		CPAssemblySystemSim.LOGGER.log(Level.FINE,"\n[WORKBENCH1.release()] by:" + Thread.currentThread().getName());
//		System.out.println("\n[WORKBENCH1.release()] by:" + Thread.currentThread().getName());
		notifyAll();
	}



	public void rotate() {
		CPAssemblySystemSim.LOGGER.log(Level.FINER,"\n[WORKBENCH1.rotate()] by:" + Thread.currentThread().getName());
//		System.out.println("\n[WORKBENCH1.rotate()] by:" + Thread.currentThread().getName());
		if(!pos1.ok2rot||!pos2.ok2rot||!pos3.ok2rot)
			return;
		Fixture f;
		f=pos1.f;
		pos1.f=pos3.f;
		pos3.f=pos2.f;
		pos2.f=f;
		pos1.reset();
		pos2.reset();
		pos3.reset();
		rotNum++;
		CPAssemblySystemSim.LOGGER.log(Level.FINE,"\t\t[WORKBENCH1] rotation No: " +rotNum +" completed");
//		System.out.println("\t\t[WORKBENCH1] rotation No: " +rotNum +" completed");
	}


	public String toSting() {
		return "pos availability: " + pos1.jobPending + "\t" + pos2.jobPending + "\t" + pos3.jobPending +"\n" +
				"pos ok2rot\t:" + pos1.ok2rot +"\t"+ pos2.ok2rot +"\t"+ pos3.ok2rot+ "\n" +
				"pos f.isOnDuty():" + pos1.f.isOnDuty() +"\t"+ pos2.f.isOnDuty() +"\t"+pos3.f.isOnDuty() + 
				"\n------------------------------\n";
	}
}
class FWorkbenchPos extends WorkbenchPos {

	public FWorkbenchPos(Workbench1 w, boolean b,Fixture f) {
		super(w,b,f);
	}

	@Override
	public boolean ok2proceed() {
		return jobPending;
	}
}

class SWorkbenchPos extends WorkbenchPos {

		public SWorkbenchPos(Workbench1 w, boolean b,Fixture f) {
			super(w,b,f);
		}

		@Override
		public boolean ok2proceed() {
			if(jobPending && f.isOnDuty())
				return true;
			else
				return false;
		}
}

class TWorkbenchPos extends WorkbenchPos {

	public TWorkbenchPos(Workbench1 w, boolean b,Fixture f) {
		super(w,b,f);
	}

	@Override
	public boolean ok2proceed() {
		if(jobPending && f.isOnDuty())
			return true;
		else
			return false;
	}
}


