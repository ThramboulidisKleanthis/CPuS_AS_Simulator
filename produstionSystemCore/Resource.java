package produstionSystemCore;

public interface Resource {
	void acquire(WorkbenchPos pos);
	void release(WorkbenchPos pos);
}
