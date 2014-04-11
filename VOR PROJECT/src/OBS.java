
public class OBS {
	private int obsNum; //obs input num
	private int vorDir; //opposite direction of obs num, station position
	private int initialPos = 260; //initial pos, unknown to pilot
	private String morseID = "STAT"; //morse code id
	private int needle;
	
	public OBS(int obsNum) {
		this.obsNum = obsNum; //constructors 
	}
	
	public int obsCal() { //calculates vor position
		if(obsNum > 180) {
			vorDir = obsNum - 180;
		}
		else {
			vorDir = obsNum + 180;
		}
		
		return vorDir;
	}
	public int calNeedle() { //add left and right needle deflector depending on degree of direction 
		if(vorDir == initialPos) { //needs change!!!! add degrees 
			return 0;
		}
		else {
			return 1;
		}
	}
	public String returnId() { //returns morse id
		return morseID;
	}
	public boolean dirId() { //calculates to and from 
		if(((obsNum + 90) < initialPos) && ((obsNum - 90) < initialPos)) { //fix
			return true; //to
		}
		else {
			return false; //from 
		}
	}
}

