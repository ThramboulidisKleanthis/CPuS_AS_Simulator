package products;
import java.util.Date;
import java.util.logging.Level;

import assemblysystemCore.CPAssemblySystemSim;

public class GregorChair extends CompositePart{
	private static int curId=0;
	static String name;
	static final int PARTSNO;
	private Base base;
	private GasLift gl;
	private UpperSubAssembly usa;
	private Arm leftArm;
	private Arm rightArm;
	private Back back;
	
	static {
		PARTSNO=17;
		name = "GregorChair";
	}
	
	public static class Builder {
		private int id;
		private Base.Builder baseBuilder;
		private GasLift gl;
		private UpperSubAssembly.Builder usaBuilder;
		private Arm leftArm;
		private Arm rightArm;
		private Back back;
		private int partsNumber=0;
		private Date d;


		public Builder() {
			id=++curId;
			baseBuilder = new Base.Builder();
			usaBuilder = new UpperSubAssembly.Builder();
        }
		public GregorChair build(){
			CPAssemblySystemSim.LOGGER.log(Level.FINER,"Build:" + partsNumber+"\t"+baseBuilder.getPartsNumber()+"\t"+usaBuilder.getPartsNumber());
//			System.out.println("Build:" + partsNumber+"\t"+baseBuilder.getPartsNumber()+"\t"+usaBuilder.getPartsNumber());
			int pn=partsNumber+baseBuilder.getPartsNumber()+usaBuilder.getPartsNumber();
            return new GregorChair(id,baseBuilder.build(),gl,usaBuilder.build(), leftArm,rightArm,back,pn,d);  
       }
		
		public Builder baseBuilder(Base.Builder baseBuilder) {
			this.baseBuilder = baseBuilder;
			baseBuilder.setPartsNumber(baseBuilder.getPartsNumber()+1); 
			d= new Date();
			return this;
		}
		public Builder addGl(GasLift gl) {
			this.gl = gl;
			partsNumber ++;
			d= new Date();
			return this;
		}
		
		public Builder addUsa(UpperSubAssembly.Builder usaBuilder) {
			this.usaBuilder = usaBuilder;
			usaBuilder.setPartsNumber(usaBuilder.getPartsNumber()+1);
			d= new Date();
			return this;
		}
		
		public Builder addLeftArm(Arm a) {
			this.leftArm = a;
			partsNumber ++;
			d= new Date();
			return this;
		}
		
		public Builder addRightArm(Arm a) {
			this.rightArm = a;
			partsNumber ++;
			d= new Date();
			return this;
		}
		
		public Builder addBack(Back b) {
			this.back = b;
			partsNumber ++;
			d= new Date();
			return this;
		}
		public UpperSubAssembly.Builder getUsaBuilder() {
			return usaBuilder;
		}

		public void setUsaBuilder(UpperSubAssembly.Builder usaBuilder) {
			this.usaBuilder = usaBuilder;
		}
		public Base.Builder getBaseBuilder() {
			return baseBuilder;
		}
	}
	
	private GregorChair(int id, Base base,GasLift gl,UpperSubAssembly u, Arm la,Arm ra,	Back back,int pn,Date d) {
		super(id,d);
		this.base=base;
		this.gl=gl;
		usa = u;
		leftArm=la;
		rightArm=ra;
		this.back=back;
		partsNumber=pn;
		pd = d;
	}
	
	public Base getBase() {
		return base;
	}

	public GasLift getGl() {
		return gl;
	}

	public UpperSubAssembly getUsa() {
		return usa;
	}

	public Arm getLeftArm() {
		return leftArm;
	}

	public Arm getRightArm() {
		return rightArm;
	}

	public Back getBack() {
		return back;
	}

	@Override
	String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public 	int getPARTSNO() {
		// TODO Auto-generated method stub
		return PARTSNO;
	}

}
