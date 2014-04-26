package ics314_VOR_abut;
import java.util.ArrayList;
import java.util.List;


// Change log 4/15/2014
// Modifier by: Gerald Abut
// changed constructor to use new methods updateIncomingRadial, updateMorsecode, and setIntendedRadial
// changed name from getIncomingRadial to updateIncomingRadial and have it return void
// create new method setIntendedRadial
// utilize the updateMorseCode method to centralize code
// validateRadial now has case where the input is 0 or 360. it will always change it to 360 if it is 0
// degrees now goes from 1-360 instead of 0-359 to make calculations easier with the offsets
// adjusted isTo and isFrom functions for cases where the intended radial is between 0 (360) to 180, and 180 to 0 (360)
// offsets no longer have a "-1" so that there can only be 1-360 degrees, however the mod arithmetic causes the equal values to be zero.
//	in that case, the 0 will be changed to 360
// in getDeadZoneRadial method, if the mod returns 0, then it will change the degree to 360
// Vor class name is changed to VorReceiver to make it more obvious that this is the plane's receiver and not the vor station
// removed the arraylist method trimTosize() because a List interface is used instead

// Change log 4/22/2014
// Modified by: Gerald Abut
// fixed IntendedRadial to just output the intended radial instead of the opposite radial
// created a function getOppositeRadial to output the opposite radial
// fixed isDeadZone to detected both the left and right dead zones
// fixed getDeadZoneRadials [0] must be the left radial, [1] is the right radial
// incOBS and decOBS now return the new intended radial
// changed instance variable ABEAMED to ABEAMED_OFFSET because this is not the abeamed angle

// Change log 4/25/2014
// Modified by: Gerald Abut
// renamed"setIntendedRadial" to "setOBS"
// renamed "getIntendedRadial" to "getOBS"
// renamed instance variable "intendedRadial" to "obs_val"
// renamed "isDeadZone" to "isAbeamed"
// renamed "getDeadZoneRadials" to "getAbeamedRadials"
// getAbeamedRadials modified to removed code redundancy, now uses methods getLeftAbeamdRadial and getRightAbeamedRadial
// instance variable "degrees" is now "DEGREES" of type final List<Integer> to prevent change
// validateRadial now returns -1 if an invalid radial is given instead of throwing an exception
// adjusted constructor (option 2 to have a default value for the morse code
// setOBS throws IllegalArgumentException 
// updateIncomingRadial throws IllegalArgumentException
// both constructors throw IllegalArgumentException
// isFrom method now works as intended but should be unit tested
// isTo method now works as intended but should be unit tested

// NEXT TIME
// need a method that can detect how close the plane is from the vor station based on the vor station's radar impulse speed.
// the closer the plane is to the station, the faster the impulse. Just like how the planet Mercury orbits around the sun faster
// then earth, and has less hours in a day. the closer the plane will be to the vor station, the faster the system clock will be.
// I still need to figure out how to implement that though. Any suggestions?


public class VorReceiver {
	final int MAX_DEGREE = 360;
	final int OPPOSITE_DEGREE = 180;
	final int ABEAMED_OFFSET = 90;
	final int INTERPRET_360 = 360;
	int currRadial;
	int obs_val;
	String currMorseCode;
	final List<Integer> DEGREES;
	
	// constructors (option 1)
	public VorReceiver(int incomingRadial, String morse) throws IllegalArgumentException {
		// store valid degrees
		this.DEGREES = new ArrayList<Integer>();
		for (int i = 1; i <= this.MAX_DEGREE; i++) {
			this.DEGREES.add(i);
		}
		this.updateIncomingRadial(incomingRadial); // store value of incoming radial
		this.setOBS(this.currRadial); // save the start radial to compare later 
		this.updateMorseCode(morse); // store morse code
	}
	// constructors (option 2)
	public VorReceiver(int incomingRadial) throws IllegalArgumentException {	// constructor version does not take in a morse code
		// store valid degrees
		this.DEGREES = new ArrayList<Integer>();
		for (int i = 1; i <= this.MAX_DEGREE; i++) {
			this.DEGREES.add(i);
		}
		this.updateIncomingRadial(incomingRadial); // store value of incoming radial
		this.setOBS(this.currRadial); // save the start radial to compare later 
		this.updateMorseCode("DID NOT RECEIVE MORSE CODE"); // set the default value of the more code
	}
	
	/**
	 * getCurrRadial returns the current radial
	 * @return int
	 */
	public int getCurrRadial() {
		return this.currRadial;
	}
	
	/**
	 * updateIncomingRadial updates the current radial radial position
	 * throws IllegalArgumentException if radial is not between 0-360
	 * @return void
	 * @throws IllegalArgumentException
	 */
	public void updateIncomingRadial(int incomingRadial) {
		// check if the given degree is valid
		int testRadial = 0;
		if ((testRadial = this.validateRadial(incomingRadial)) >= 0) {
			this.currRadial = testRadial;
		} else {
			throw new IllegalArgumentException();
		}
	}
		
	/**
	 * validateRadial validates a given radial. valid radials are from 0-360. were 0 degrees is also 360 degrees
	 * returns the valid radial or -1 if invalid
	 * @param radial
	 * @return int
	 */
	private int validateRadial(int radial) {
		int radialTest = radial;
		if (radialTest == 0) {
			radialTest = 360;	// treat degree zero as 360 degrees
		}
		
		if (radialTest >= 1 && radialTest <= this.MAX_DEGREE) { 
			return radialTest;	// set the current radial to the incoming radial and return radial
		} 	else {
			return -1;	// given radial is invalid
		}
	}
	
	/**
	 * getMorse returns the morse code given by the VOR station
	 * @return String
	 */
	public String getMorse() {
		return this.currMorseCode;
	}
		
	/**
	 * updateMorse updates the current morseCode
	 * @param newMorseCode
	 * @return void
	 */
	private void updateMorseCode(String newMorseCode) {
		this.currMorseCode = newMorseCode;
	}
	
	/**
	 * getDegrees returns all the valid degrees in a list
	 * @return List<Integer>
	 */
	public List<Integer> getDegrees() {
		return this.DEGREES;
	}
	
	/**
	 * isFrom returns true if plane is moving away from the vor station
	 * and returns false if the plane is either in the deadzone (abeamed to vor station)
	 * or returns false if the plane is moving towards the vor station
	 * @return boolean
	 */
	public boolean isFrom() { 		
		int[] abeamed = this.getAbeamedRadials();
		int currOBS = this.getOBS();
		int currRadial = this.getCurrRadial();
		boolean test = false;
		if (currRadial == abeamed[0] || currRadial == abeamed[1]) { // current radial == deadZone, cannot detect this radial
			test = false;
		} else if ((currOBS >= 1) && (currOBS <= 180)) {	// OBS radial is between 1 and 180
			test = (currRadial < abeamed[0] || currRadial > abeamed[1]);
		} else if ((currOBS <= 360) && (currOBS >= 180)) {	// OBS radial is between 180 and 360 
			test = (currRadial > abeamed[1] && currRadial < abeamed[0]);
		} else {
			// do nothing. should return false at this point
		}
		return test;
	}
		
	/**
	 * isTo returns true if plane is moving towards the vor station
	 * and returns false if the plane is either in the deadzone (abeamed to vor station)
	 * or returns false if the plane is moving away from the vor station
	 * @return boolean
	 */
	public boolean isTo() {		
		int[] abeamed = this.getAbeamedRadials();
		int currOBS = this.getOBS();
		int currRadial = this.getCurrRadial();
		boolean test = false;
		if (currRadial == abeamed[0] || currRadial == abeamed[1]) { // current radial == deadZone, cannot detect this radial
			test = false;
		} else if ((currOBS >= 1) && (currOBS <= 180)) {	// OBS radial is between 1 and 180
			test = (currRadial > abeamed[0] && currRadial < abeamed[1]);
		} else if ((currOBS <= 360) && (currOBS >= 180)) {	// OBS radial is between 180 and 360 
			test = (currRadial < abeamed[1] || currRadial > abeamed[0]);
		} else {
			// do nothing. should return false at this point
		}
		return test;
	}
	
	/**
	 * NOT IMPLEMENTED. USE IF YOU WANT TO OUTPUT 360 DEGREES INTO ZERO DEGREES.
	 * CURRENTLY THE PROGRAM ONLY PRINTS 1-360. 
	 * 
	 * adjust360ToZero set the value 360 to 0 and returns 0, else it will just return the given radial
	 * @param radial
	 * @return int
	 */
	private int adjust360ToZero(int radial){
		int adjusted = radial;	
		if (adjusted == INTERPRET_360) {
			adjusted = 0;
		}		
		return adjusted;
	}
	
	/**
	 * adjustZeroTo360 sets the value of 0 to 360 and returns 360, else it will jsut return the given radial
	 * @param radial
	 * @return int
	 */
	private int adjustZeroTo360(int radial) {
		if (radial == 0) {
			radial = 360;	// 0 is also 360
		}
		return radial;
	}
	
	/**
	 * isAbeamed returns true if plane is currently on the deadzone radial (abeamed from vor station)
	 * and returns false otherwise
	 * @return boolean
	 */
	public boolean isAbeamed() {
		int[] abeamedValues = this.getAbeamedRadials();
		return ((this.currRadial == abeamedValues[0]) || (this.currRadial == abeamedValues[1]));
	}
	
	/**
	 * getAbeamedRadials returns the two radials that are abeamed from the vor station based on the intended radial
	 * from the OBS into an int[]. int[0] represents abeamed to the left of the vor station, and int[1] represents abeamed to the right
	 * of the vor station
	 * @return int[]
	 */
	public int[] getAbeamedRadials() {
		int[] abeamedValues = {this.getLeftAbeamedRadial(), this.getRightAbeamedRadial()};
		return abeamedValues;
	}
	
	/**
	 * getLeftAbeamedRadial calculates the degree that is abeamed to the left of the radial
	 * @return int
	 */
	public int getLeftAbeamedRadial() {
		int arrayOffset = this.DEGREES.size();
		int leftAbeamedOffset = (this.obs_val + ABEAMED_OFFSET + arrayOffset) % arrayOffset;
		return this.adjustZeroTo360(leftAbeamedOffset);
	}
	
	/**
	 * getRightAbeamedRadial calculates the degree that is abeamed to the right of the radial
	 * @return int
	 */
	public int getRightAbeamedRadial() {
		int arrayOffset = this.DEGREES.size();
		int rightAbeamedOffset = (this.obs_val + ABEAMED_OFFSET + ABEAMED_OFFSET + ABEAMED_OFFSET + arrayOffset) % arrayOffset;
		return this.adjustZeroTo360(rightAbeamedOffset);
	}
	
	/**
	 * getOBS returns the intended radial
	 * @return int
	 */
	public int getOBS() {
		return this.obs_val;
	}
	
	/**
	 * getOppositeRadial returns the opposite degree of the vor stations radial that the plane intends to go to
	 * @return int
	 */
	public int getOppositeRadial() {
		int arrayOffset = this.DEGREES.size();
		return (this.currRadial + OPPOSITE_DEGREE + arrayOffset) % arrayOffset;	// wrap around from left to right
	}
	
	
	/**
	 * setOBS manually sets an intended radial. Throws an IllegalArgumentException
	 * if the the radial is invalid
	 * @param radial
	 * @return void
	 * @throws IllegalArgumentException
	 */
	public void setOBS(int radial) {
		int destination = 0;		
		if ((destination = this.validateRadial(radial)) >= 0) {
			this.obs_val = destination;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * incOBS increments the OBS settings (to adjust the intended radial)
	 * and returns the new radial
	 * @return int
	 */
	public int incOBS() {
		return this.obs_val++;
	}
	
	/**
	 * decOBS increments the OBS settings (to adjust the intended radial) 
	 * and returns the new radial
	 * @return int
	 */
	public int decOBS() {
		return this.obs_val--;
	}
	
	/**
	 * getVorStatus returns the current values of the vor receiver
	 * @return String
	 */
	public String getVorStatus() {                
		return "Detecting incoming radial from VOR Station..." + this.getCurrRadial() +
			   "\nCurrent radial: " + this.getCurrRadial() +
			   "\nMorse code: " + this.getMorse() +
		       "\nOpposite Radial: " + this.getOppositeRadial() +
		       "\nOBS (Intended Radial): " + this.getOBS() +
		       "\nAbeamed (left of vor station): " + this.getLeftAbeamedRadial() +
		       "\nAbeamed (right of vor station): " + this.getRightAbeamedRadial() +
		       "\nIs Abeamed: " + this.isAbeamed() +
		       "\nIs TO: " + this.isTo() +
		       "\nIs FROM: " + this.isFrom() + "\n";	
	}
	
	public static void main(String[] args) {
		// degrees go from 1 - 360, where 0 is also 360. program will automatically changed a 0 argument into 360
		int incomingRadial = 270;
		String morse = "LCL";
		VorReceiver v = new VorReceiver(incomingRadial, morse);
		v.setOBS(v.getOppositeRadial());	 // OBS set to 90 degrees	

		try {
			// partial test when OBS (the intended radial) is between 1 - 180 degrees
			System.out.println(v.getVorStatus());
			
			System.out.println(); // newline
			incomingRadial = 181;
			v.updateIncomingRadial(incomingRadial);
			System.out.println(v.getVorStatus());
			
			System.out.println(); // newline
			incomingRadial = 180;
			v.updateIncomingRadial(incomingRadial);
			System.out.println(v.getVorStatus());
			
			System.out.println(); // newline
			incomingRadial = 179;
			v.updateIncomingRadial(incomingRadial);
			System.out.println(v.getVorStatus());
			
			System.out.println(); // newline
			incomingRadial = 1;
			v.updateIncomingRadial(incomingRadial);
			System.out.println(v.getVorStatus());
			
			System.out.println(); // newline
			incomingRadial = 0;  // zero is also 360 (zero is a valid argument but is converted to 360 in the program)
			v.updateIncomingRadial(incomingRadial);
			System.out.println(v.getVorStatus());
			
			System.out.println(); // newline
			incomingRadial = 359;
			v.updateIncomingRadial(incomingRadial);
			System.out.println(v.getVorStatus());
			
			////////////////////////////////////////////////////
			// partial test when OBS (the intended radial) is between 180 - 360 degrees
			System.out.println("Setting new OBS radial...");
			v.setOBS(90);
			incomingRadial = 90;
			v.updateIncomingRadial(incomingRadial);
			System.out.println(v.getVorStatus());
			
			System.out.println(); // newline
			incomingRadial = 1;
			v.updateIncomingRadial(incomingRadial);
			System.out.println(v.getVorStatus());
			
			System.out.println(); // newline
			incomingRadial = 360;	// zero is also a valid argument for 360
			v.updateIncomingRadial(incomingRadial);
			System.out.println(v.getVorStatus());
			
			System.out.println(); // newline
			incomingRadial = 359;
			v.updateIncomingRadial(incomingRadial);
			System.out.println(v.getVorStatus());
			
			System.out.println(); // newline
			incomingRadial = 181;
			v.updateIncomingRadial(incomingRadial);
			System.out.println(v.getVorStatus());
			
			System.out.println(); // newline
			incomingRadial = 180;
			v.updateIncomingRadial(incomingRadial);
			System.out.println(v.getVorStatus());
			
			System.out.println(); // newline
			incomingRadial = 179;
			v.updateIncomingRadial(incomingRadial);
			System.out.println(v.getVorStatus());
		} catch (IllegalArgumentException e) {
			System.err.println("ERROR: OBS values must be within 1-360 degrees, where 360 can also be zero");
		}
	}
}
