package Temp;
public class Round {
	private boolean drakePlaintiff=true;
	private int[] drakeRoster=new int[22];
	private int[] witnessesCalled=new int[6];
	private int[] scores = new int[56];
	public Round(){
		for(int i=0;i<drakeRoster.length;i++){
			drakeRoster[i]=-1;
		}
		for(int i=0;i<witnessesCalled.length;i++){
			witnessesCalled[i]=-1;
		}
		for(int i=0;i<scores.length;i++){
			scores[i]=0;
		}
	}
	public void setSide(boolean s){
		drakePlaintiff=s;
	}
	public boolean getSide(){
		return drakePlaintiff;
	}
	public void setDrakeParts(int[] nameIndexes){
		for(int i=0;i<22;i++){
			drakeRoster[i]=nameIndexes[i];
		}
	}
	public int[] getDrakeRoles(){
		return drakeRoster;
	}
	public void setWitnessesCalled(int[] witnessIndexes){
		for(int i=0;i<6;i++){
			witnessesCalled[i]=witnessIndexes[i];
		}
	}
	public int[] getWitnessesCalled(){
		return witnessesCalled;
	}
	public void setAll(int[] data){
		if(data[0]==0){
			drakePlaintiff=true;
		}else{
			drakePlaintiff=false;
		}
		for(int i=0;i<6;i++){
			witnessesCalled[i]=data[i+1];
		}
		for(int i=0;i<22;i++){
			drakeRoster[i]=data[7+i];
		}
		for(int i=0;i<56;i++){
			scores[i]=data[29+i];
		}
	}
	public int[] getAll(){
		int[] retVal = new int[85];
		if(drakePlaintiff){
			retVal[0]=0;
		}else{
			retVal[0]=1;
		}
		for(int i=0;i<6;i++){
			retVal[1+i]=witnessesCalled[i];
		}
		for(int i=0;i<22;i++){
			retVal[7+i]=drakeRoster[i];
		}
		for(int i=0;i<56;i++){
			retVal[29+i]=scores[i];
		}
		return retVal;
	}
	public double getBallot1Average(){
		double retVal=0.0;
		for(int i=0;i<14;i++){
			retVal+=scores[i];
		}
		for(int i=28;i<42;i++){
			retVal+=scores[i];
		}
		retVal/=28.0;
		return retVal;
	}
	public double getBallot2Average(){
		double retVal=0.0;
		for(int i=14;i<28;i++){
			retVal+=scores[i];
		}
		for(int i=42;i<56;i++){
			retVal+=scores[i];
		}
		retVal/=28.0;
		return retVal;
	}
	public double getBallot1TeamAverage(){
		double retVal=0.0;
		if(drakePlaintiff){
			for(int i=0;i<14;i++){
				retVal+=scores[i];
			}
		}else{
			for(int i=28;i<42;i++){
				retVal+=scores[i];
			}
		}
		retVal/=14.0;
		return retVal;
	}
	public double getBallot2TeamAverage(){
		double retVal=0.0;
		if(drakePlaintiff){
			for(int i=14;i<28;i++){
				retVal+=scores[i];
			}
		}else{
			for(int i=42;i<56;i++){
				retVal+=scores[i];
			}
		}
		retVal/=14.0;
		return retVal;
	}
}
