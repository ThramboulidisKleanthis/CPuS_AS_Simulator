package produstionSystemCore;

public abstract class WorkbenchPos {
	boolean jobPending;
	boolean ok2rot;
	public Fixture f;
	Workbench1 w;
	
	public WorkbenchPos(Workbench1 w, boolean b,Fixture f) {
		ok2rot = true;
		jobPending = b;
		this.f=f;
		this.w=w;
	}

	public void reset() {
		ok2rot = true;
		jobPending =true;
	}
	public void setF(Fixture f) {
		this.f = f;
	}

	public abstract boolean ok2proceed(); 
}
