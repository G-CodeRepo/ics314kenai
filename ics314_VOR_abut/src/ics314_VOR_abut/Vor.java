package ics314_VOR_abut;

import java.util.ArrayList;

public class Vor {
	final int MAX_DEGREE = 360;
	final int OPPOSITE_DEGREE = 180;
	final int ABEAMED = 90;
	int currRadial;
	int intendedRadial;
	String morse;
	ArrayList<Integer> degrees;
	
	// constructors
	public Vor(int radial, String morse) {
		// store valid degrees
		this.degrees = new ArrayList<Integer>();
		for (int i = 0; i < this.MAX_DEGREE; i++) {
			this.degrees.add(i);
		}
		this.degrees.trimToSize();	// trim unneeded array storage
		//System.out.println(this.degrees);
		
		// check if the given degree is valid
		this.getIncomingRadial(radial);
		// save the start radial to compare later 
		this.intendedRadial = this.currRadial;
		
		// store morse code
		this.morse = morse;
	}
	public Vor (int radial) {
		this.degrees = new ArrayList<Integer>();
		for (int i = 0; i < this.MAX_DEGREE; i++) {
			this.degrees.add(i);
		}
		this.degrees.trimToSize();	// trim unneeded array storage
	}
	
	/**
	 * getCurrRadial returns the current radial
	 * @return int
	 */
	public int getCurrRadial() {
		return this.currRadial;
	}
	
	/**
	 * getIncomingRadial gets the radial from the vor station and updates the current radial 
	 * of the plane
	 * @param radial
	 * @return void
	 */
	public void getIncomingRadial(int radial) {
		if (radial >= 0 && radial < this.MAX_DEGREE) { // valid 0-359
			this.currRadial = radial;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * getMorse returns the morse code given by the VOR station
	 * @return String
	 */
	public String getMorse() {
		return this.morse;
	}
	
	/**
	 * isFrom returns true if plane is moving away from the vor station
	 * and returns false if the plane is either in the deadzone (abeamed to vor station)
	 * or returns false if the plane is moving towards the vor station
	 * @return boolean
	 */
	public boolean isFrom() {
		int[] abeamed = this.getDeadZoneRadials();
		int intendedRadial = this.getIntendedRadial();
		if ((intendedRadial < abeamed[0]) && (intendedRadial > abeamed[1])) { // is it going "away from" the VOR station?
			return true;	// away from the vor station
		} else {
			return false ; 	// towards the vor station or directed abeamed from the vor station
		}
	}
	
	/**
	 * isTo returns true if plane is moving towards the vor station
	 * and returns false if the plane is either in the deadzone (abeamed to vor station)
	 * or returns false if the plane is moving away from the vor station
	 * @return boolean
	 */
	public boolean isTo() {
		int[] abeamed = this.getDeadZoneRadials();
		return (this.getIntendedRadial() < abeamed[0]);			// is it going "towards" the VOR station?
	}
	
	/**
	 * isDeadZone returns true if plane is currently on the deadzone radial (abeamed from vor station)
	 * and returns false otherwise
	 * @return boolean
	 */
	public boolean isDeadZone() {
		int[] abeamedValues =this.getDeadZoneRadials();
		boolean deadzone = false;
		if ((this.currRadial == abeamedValues[0]) || (this.currRadial == abeamedValues[0])) {
			deadzone = true;
			return deadzone;
		} else 
			return deadzone;
	}
	
	/**
	 * getDeadZoneRadials returns the two radials that are abeamed from the vor station based on the intended radial 
	 * in an int[]. int[0] represents abeamed to the left of the vor station, and int[1] represents abeamed to the right
	 * of the vor station
	 * @return int[]
	 */
	public int[] getDeadZoneRadials() {
		int arrayOffset = this.degrees.size() - 1;
		int leftAbeamedOffset = (this.intendedRadial + ABEAMED + arrayOffset) % arrayOffset;
		int rightAbeamedOffset = (this.intendedRadial + ABEAMED + ABEAMED + ABEAMED + arrayOffset) % arrayOffset;
		int[] abeamedValues = {leftAbeamedOffset, rightAbeamedOffset};
		return abeamedValues;
	}
	
	/**
	 * incOBS 
	 * @return void
	 */
	public void incOBS() {
		this.intendedRadial++;
	}
	
	/**
	 * decOBS
	 * @return void
	 */
	public void decOBS() {
		this.intendedRadial--;
	}
	
	/**
	 * getIntendedRadial returns the opposite degree of the vor stations radial that the plane intends to go to
	 * @return int
	 */
	public int getIntendedRadial() {
		int arrayOffset = this.degrees.size() - 1;
		return (this.intendedRadial + OPPOSITE_DEGREE + arrayOffset) % arrayOffset;	// wrap around from left to right if obs <= 179 degrees
	}
	
	public static void main(String[] args) {
		// remember the degrees go from 0 - 359, where 0 is 360
		int radial = 89; // this is 180 degrees, the intended radial will be 0 (360 degrees), deadzone should be 90 degrees
		String morse = "LCL";
		try {
			Vor v = new Vor(radial, morse);
			System.out.println("Current Radial From VOR Station: " + v.getCurrRadial());
			System.out.println("Intended Radial: " + v.getIntendedRadial());
			System.out.println("Morse Code: " + v.getMorse());
			System.out.println("Deadzone (left of vor station): " + v.getDeadZoneRadials()[0]);
			System.out.println("Deadzone (right of vor station): " + v.getDeadZoneRadials()[1]);
			System.out.println("Getting incoming radial 89 degrees...");
			v.getIncomingRadial(89);
			System.out.println("Current Radial: " + v.getCurrRadial());
			System.out.println("Is Deadzone: " + v.isDeadZone());
			System.out.println("Is TO: " + v.isTo());
			System.out.println("Is FROM: " + v.isFrom());
			System.out.println("Getting incoming radial 91 degrees...");
			v.getIncomingRadial(91);
			System.out.println("Is TO: " + v.isTo());
			System.out.println("Is FROM: " + v.isFrom());
			System.out.println("Changing intended radial, adjusting OBS++");
			System.out.println("");
		} catch (IllegalArgumentException e) {
			System.err.println("ERROR: obs values must be within 0-359 degrees");
		}
	}
}
