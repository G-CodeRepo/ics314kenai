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

// Change log 4/29/2014
// Modified by: Gerald Abut
// all instance variables are now private
// added getNeedPosition method
// added setNeedPosition method
// added moveNeedle method
// modified getVorStatus to also include needle position
// modified the constructors to set needle position
// modified getOppositeRadial to have parameter to take in radial

// Change log 4/30/2014
// Modified by: Gerald Abut
// modified constructors, updateIncomingRadial, incOBS and decOBS to also update the needle
// added adjustDegree method to compute the correct degrees based on modulo arithmetic
// modified incOBS and decOBS to use "adjustDegree" method to wrap around if too many calls to incOBS or decOBS
// fix constructor so that it actually throws an IllegalArgumentException if the given radial is invalid
// modified "getOppositeRadial" method so that it returns 360 if 0 degrees was calculated
// added more condition if OBS is between 90 and 270 degrees for the isTo and isFrom functions


public class VorReceiver {
	private final int MAX_DEGREE = 360;
	private final int OPPOSITE_DEGREE = 180;
	private final int ABEAMED_OFFSET = 90;
	private final int INTERPRET_360 = 360;
	private int currRadial;
	private int obs_val;
	private String currMorseCode;
	private final List<Integer> DEGREES;
	private int needle;
	private int NEEDLE_LIMIT = 10; 	// -10, -8, -6... 0, 2, 4, ..., 10

	// constructors (option 1)
	public VorReceiver(int incomingRadial, String morse) {
		if ((this.validateRadial(incomingRadial)) >= 0) {
			// store valid degrees
			this.DEGREES = new ArrayList<Integer>();
			for (int i = 1; i <= this.MAX_DEGREE; i++) {
				this.DEGREES.add(i);
			}
			this.updateIncomingRadial(incomingRadial); // store value of incoming radial
			this.setOBS(this.currRadial); // save the start radial to compare later 
			this.updateMorseCode(morse); // store morse code
			
			// set needle position
			if (this.currRadial == this.obs_val) {
				this.setNeedlePosition(0);	// needle position is centered
			} else if (this.currRadial > this.obs_val) {
				this.setNeedlePosition(NEEDLE_LIMIT);// needle positioned to the right
			} else {
				// currRadial < obs_val
				this.setNeedlePosition(-NEEDLE_LIMIT); // needle positioned to the left (negative number)
			}
			this.moveNeedle();	// update needle
		} else {
			throw new IllegalArgumentException();
		}

	}
	// constructors (option 2)
	public VorReceiver(int incomingRadial) {	// constructor version does not take in a morse code
		if ((this.validateRadial(incomingRadial)) >= 0) {
			// store valid degrees
			this.DEGREES = new ArrayList<Integer>();
			for (int i = 1; i <= this.MAX_DEGREE; i++) {
				this.DEGREES.add(i);
			}
			this.updateIncomingRadial(incomingRadial); // store value of incoming radial
			this.setOBS(this.currRadial); // save the start radial to compare later 
			this.updateMorseCode("DID NOT RECEIVE MORSE CODE"); // set the default value of the more code
			
			// set needle position
			if (this.currRadial == this.obs_val) {
				this.setNeedlePosition(0);	// needle position is centered
			} else if (this.currRadial > this.obs_val) {
				this.setNeedlePosition(NEEDLE_LIMIT);// needle positioned to the right
			} else {
				// currRadial < obs_val
				this.setNeedlePosition(-NEEDLE_LIMIT); // needle positioned to the left (negative number)
			}
			this.moveNeedle();	// update needle
		} else {
			throw new IllegalArgumentException();
		}
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
			this.moveNeedle();	// update needle
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
		} else {
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
		} else if (currOBS < 270 || currOBS > 90) {		// OBS radial is between 90 and 270
			test = (currRadial > abeamed[0]) || (currRadial < abeamed[1]);
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
		} else if (currOBS < 270 || currOBS > 90) { 	// OBS radial is between 90 and 270
			test = (currRadial < abeamed[0] && currRadial > abeamed[1]);
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
	private int getLeftAbeamedRadial() {
		int arrayOffset = this.DEGREES.size();
		int leftAbeamedOffset = (this.obs_val + ABEAMED_OFFSET + arrayOffset) % arrayOffset;
		return this.adjustZeroTo360(leftAbeamedOffset);
	}
	
	/**
	 * getRightAbeamedRadial calculates the degree that is abeamed to the right of the radial
	 * @return int
	 */
	private int getRightAbeamedRadial() {
		int arrayOffset = this.DEGREES.size();
		int rightAbeamedOffset = (this.obs_val + ABEAMED_OFFSET + ABEAMED_OFFSET + ABEAMED_OFFSET + arrayOffset) % arrayOffset;
		return this.adjustZeroTo360(rightAbeamedOffset);
	}
	
	/**
	 * adjustDegree takes two arguments. The first argument is for the degree you want to adjust.
	 * The second argument is for the number of degrees you want to adjust it to.
	 * It will outputs the correct degree based on the module between 1-360 
	 * Throws an illegal argument exception if given degree is not between 1-360
	 * ex: if degree is 360 and you adjust +1 degree, it will wrap to 1 degree
	 * ex: if degree is 1 and you add -1 degree, it will wrap to 360 degree
	 * @return int
	 * @throws IllegalArgumentException
	 */
	private int adjustDegree(int degree, int adjust) {
		int arrayOffset = this.DEGREES.size();
		int deg = this.adjustZeroTo360(degree);	// if zero degrees is given, change to 360

		if (this.validateRadial(deg) >= 0) {	// valid radial
			if (adjust != 0) {
				return  this.adjustZeroTo360((deg + adjust + arrayOffset) % arrayOffset);	// wrap from right to left
			} else {
				return deg;
			}
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * getOBS returns the intended radial
	 * @return int
	 */
	public int getOBS() {
		return this.obs_val;
	}
	
	/**
	 * getOppositeRadial returns the opposite degree of the given radial
	 * @return int
	 */
	public int getOppositeRadial(int radial) {
		int arrayOffset = this.DEGREES.size();
		return this.adjustZeroTo360((radial + OPPOSITE_DEGREE + arrayOffset) % arrayOffset);	// wrap around from left to right
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
			this.moveNeedle();	// update needle
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
		this.obs_val = this.adjustDegree(this.obs_val, 1);
		this.moveNeedle();	// update needle
		return this.obs_val;
	}
	
	/**
	 * decOBS increments the OBS settings (to adjust the intended radial) 
	 * and returns the new radial
	 * @return int
	 */
	public int decOBS() {
		this.obs_val = this.adjustDegree(this.obs_val, -1);
		this.moveNeedle();	// update needle
		return this.obs_val;
	}
	
	/**
	 * getNeedlePosition returns the current position of the needle
	 * -10 -> 0 = left of intended radial, 0 -> 10 = right of intended radial
	 * 0 means the plane is currently in-line with the OBS (intended radial or opposite of intended radial)
	 * @return int
	 */
	public int getNeedlePosition() {
		return this.needle;
	}
	
	/**
	 * moveNeedle sets the value of the position of the needle
	 * needles range is between -10 to 10
	 * needle positions are even numbers from -10 to 10 (e.g. -10, -8,...2, 4, 6...)
	 * if numbers are odd, needle will be set to the next even number
	 * ex: if positive needle position: 1 == 2, 2 == 2, 3 == 4, 4 == 4, ...
	 * ex: if negative needle position: -10 == -10, -9 == -10, -8 == -8, -7 == -8, ...
	 */
	private void moveNeedle() {
		int obs = this.getOBS();
		int obs_opposite = this.getOppositeRadial(this.getOBS());
		int currRad = this.getCurrRadial();
		
		if (this.isTo()) { // plane is pointing in the degrees towards the intended radial
			if (currRad <= (this.adjustDegree(obs_opposite, -9))) {																	// left of opposite of intended radial
				this.needle = -10;
			} else if ((currRad == (this.adjustDegree(obs_opposite, -8))) || (currRad == (this.adjustDegree(obs_opposite, -7)))) {	// left of opposite of intended radial 
				this.needle = -8;
			} else if ((currRad == (this.adjustDegree(obs_opposite, -6))) || (currRad == (this.adjustDegree(obs_opposite, -5)))) {	// left of opposite of intended radial
				this.needle = -6;
			} else if ((currRad == (this.adjustDegree(obs_opposite, -4))) || (currRad == (this.adjustDegree(obs_opposite, -3)))) {	// left of opposite of intended radial
				this.needle = -4;
			} else if ((currRad == (this.adjustDegree(obs_opposite, -2))) || (currRad == (this.adjustDegree(obs_opposite, -1)))) {	// left of opposite of intended radial
				this.needle = -2;
			} else if ((currRad == obs_opposite)  || (currRad == obs)) {															// equal to opposite of intended radial
				this.needle = 0;
			} else if ((currRad == (this.adjustDegree(obs_opposite, 1))) || (currRad == (this.adjustDegree(obs_opposite, 2)))) {	// right of opposite of intended radial
				this.needle = 2;
			} else if ((currRad == (this.adjustDegree(obs_opposite, 3))) || (currRad == (this.adjustDegree(obs_opposite, 4)))) {	// right of opposite of intended radial
				this.needle = 4;
			} else if ((currRad == (this.adjustDegree(obs_opposite, 5))) || (currRad == (this.adjustDegree(obs_opposite, 6)))) {	// right of opposite of intended radial
				this.needle = 6;
			} else if ((currRad == (this.adjustDegree(obs_opposite, 7))) || (currRad == (this.adjustDegree(obs_opposite, 8)))) {	// right of opposite of intended radial
				this.needle = 8;
			} else if (currRad >= (this.adjustDegree(obs_opposite, 9))) {															// right of opposite of intended radial
				this.needle = 10;
			} else {
				// SHOULD NOT GO HERE.
			}
		} else { // plane is pointing in the degrees away from the intended radial
			// isTo = true
			if (currRad >= (this.adjustDegree(obs, 9))) {															// left of intended radial
				this.needle = -10;
			} else if ((currRad == (this.adjustDegree(obs, 8))) || (currRad == (this.adjustDegree(obs, 7)))) {		// left of intended radial
				this.needle = -8;
			} else if ((currRad == (this.adjustDegree(obs, 6))) || (currRad == (this.adjustDegree(obs, 5)))) {		// left of intended radial
				this.needle = -6;
			} else if ((currRad == (this.adjustDegree(obs, 4))) || (currRad == (this.adjustDegree(obs, 3)))) {		// left of intended radial
				this.needle = -4;
			} else if ((currRad == (this.adjustDegree(obs, 2))) || (currRad == (this.adjustDegree(obs, 1)))) {		// left of intended radial
				this.needle = -2;
			} else if (currRad == obs) {																			// equal to intended radial
				this.needle = 0;
			} else if ((currRad == (this.adjustDegree(obs, -1))) || (currRad == (this.adjustDegree(obs, -2)))) {	// right of  intended radial
				this.needle = 2;
			} else if ((currRad == (this.adjustDegree(obs, -3))) || (currRad == (this.adjustDegree(obs, -4)))) {	// right of  intended radial
				this.needle = 4;
			} else if ((currRad == (this.adjustDegree(obs, -5))) || (currRad == (this.adjustDegree(obs, -6)))) {	// right of  intended radial
				this.needle = 6;
			} else if ((currRad == (this.adjustDegree(obs, -7))) || (currRad == (this.adjustDegree(obs, -8)))) {	// right of  intended radial
				this.needle = 8;
			} else if (currRad <= (this.adjustDegree(obs, -9))) {													// right of  intended radial
				this.needle = 10;
			} else {
				// SHOULD NOT GO HERE.
			}
		}
	}
	
	/**
	 * setNeedlePosition sets the needle position (ONLY USED BY THE CONSTRUCTOR)
	 * @param position
	 */
	private void setNeedlePosition(int position) {
		this.needle = position;
	}
	
	/**
	 * USE FOR DEBUGGING
	 * getVorStatus returns the current values of the vor receiver
	 * @return String
	 */
	public String getVorStatus() {                
		return "Detecting incoming radial from VOR Station..." + this.getCurrRadial() +
			   "\nCurrent radial: " + this.getCurrRadial() +
			   "\nMorse code: " + this.getMorse() +
		       "\nOpposite Radial: " + this.getOppositeRadial(this.getCurrRadial()) +
		       "\nOBS (Currently set Intended Radial): " + this.getOBS() +
		       "\nAbeamed (left of vor station): " + this.getLeftAbeamedRadial() +
		       "\nAbeamed (right of vor station): " + this.getRightAbeamedRadial() +
		       "\nIs Abeamed: " + this.isAbeamed() +
		       "\nIs TO: " + this.isTo() +
		       "\nIs FROM: " + this.isFrom() + 
		       "\nNeedle Position: " + this.getNeedlePosition() + "\n";	
	}
	
	
	/**
	 * USE FOR DEBUGGING
	 * printVorStatus prints out the current stats of the vor receiver
	 * similar to getVorStatus except that it just prints out the content
	 * instead of returning it as a string
	 */
	public void printVorStatus() {   
		String s = "%-50s";		// formatted to left justified with 50 characters
		String i = "%d";		// integer
		String l = "\n";		// newline
		String mod_s = "%-10s";	// formatted to left justified with 10 characters
		String abeamed = "";
		String to = "";
		String from = "";
		if (this.isAbeamed()) {
			abeamed = "true";
		} else {
			abeamed = "false";
		}
		if (this.isTo()) {
			to = "true";
		} else {
			to = "false";
		}
		if (this.isFrom()) {
			from = "true";
		} else {
			from = "false";
		}
		System.out.printf(s + i + l + 
						  s + i + l + 
						  s + mod_s + l +
						  s + i + l +
						  s + i + l +
						  s + i + l + 
						  s + i + l +
						  s + mod_s + l +
						  s + mod_s + l +
						  s + mod_s + l +
						  s + i + l,
						"Detecting incoming radial from VOR Station...", this.getCurrRadial(),
						"Current radial:", this.getCurrRadial(),
						"Morse code:", this.getMorse(),
						"Opposite Radial:", this.getOppositeRadial(this.getCurrRadial()),
						"OBS (Currently set Intended Radial):", this.getOBS(),
						"Abeamed (left of vor station):", this.getLeftAbeamedRadial(),
						"Abeamed (right of vor station):", this.getRightAbeamedRadial(),
						"Is Abeamed:", abeamed,
						"Is TO: ", to,
						"Is FROM: ", from,
						"Needle Position:", this.getNeedlePosition());
	}
	public static void main(String[] args) {
		// degrees go from 1 - 360, where 0 is also 360. program will automatically changed a 0 argument into 360
		int incomingRadial = 270;
		String morse = "LCL";
		VorReceiver v = new VorReceiver(incomingRadial, morse);
		v.setOBS(v.getOppositeRadial(v.getCurrRadial()));	 // OBS set to 90 degrees	

		try {
			
			/* PARTIAL TEST WHEN OBS (THE INTENDED RADIAL) IS BETWEEN 1 - 180 DEGREES*/
			System.err.println("Beginning radial test...");
			v.printVorStatus();
			
			System.out.println("\n"); // double newline
			incomingRadial = 181;
			v.updateIncomingRadial(incomingRadial);
			v.printVorStatus();
			
			System.out.println("\n"); // double newline
			incomingRadial = 180;
			v.updateIncomingRadial(incomingRadial);
			v.printVorStatus();
			
			System.out.println("\n"); // double newline
			incomingRadial = 179;
			v.updateIncomingRadial(incomingRadial);
			v.printVorStatus();
			
			System.out.println("\n"); // double newline
			incomingRadial = 1;
			v.updateIncomingRadial(incomingRadial);
			v.printVorStatus();
			
			System.out.println("\n"); // double newline
			incomingRadial = 0;  // zero is also 360 (zero is a valid argument but is converted to 360 in the program)
			v.updateIncomingRadial(incomingRadial);
			v.printVorStatus();
			
			System.out.println("\n"); // double newline
			incomingRadial = 359;
			v.updateIncomingRadial(incomingRadial);
			v.printVorStatus();
			
			///////////////////////////////////////////////////////////////////////////
			/*PARTIAL TEST WHEN OBS (THE INTENDED RADIAL) IS BETWEEN 180 -360 DEGREES*/
			System.out.println("\n"); // double newline
			System.err.println("Setting new OBS radial...");
			v.setOBS(90);
			incomingRadial = 90;
			v.updateIncomingRadial(incomingRadial);
			v.printVorStatus();
			
			System.out.println("\n"); // double newline
			incomingRadial = 1;
			v.updateIncomingRadial(incomingRadial);
			v.printVorStatus();
			
			System.out.println("\n"); // double newline
			incomingRadial = 360;	// zero is also a valid argument for 360
			v.updateIncomingRadial(incomingRadial);
			v.printVorStatus();
			
			System.out.println("\n"); // double newline
			incomingRadial = 359;
			v.updateIncomingRadial(incomingRadial);
			v.printVorStatus();	
			
			System.out.println("\n"); // double newline
			incomingRadial = 181;
			v.updateIncomingRadial(incomingRadial);
			v.printVorStatus();	
			
			System.out.println("\n"); // double newline
			incomingRadial = 180;
			v.updateIncomingRadial(incomingRadial);
			v.printVorStatus();	
			
			System.out.println("\n"); // double newline
			incomingRadial = 179;
			v.updateIncomingRadial(incomingRadial);
			v.printVorStatus();		
			
			///////////////////////////////////////////////////
			/*PARTIAL NEEDLE TEST*/
			System.out.println("\n"); // double newline
			System.err.println("Beginning needle Test...");
			incomingRadial = 270;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(90);
			v.printVorStatus();	
			
			System.out.println("\n"); // double newline
			incomingRadial = 280;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(90);
			v.printVorStatus();	
			
			System.out.println("\n"); // double newline
			incomingRadial = 290;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(90);
			v.printVorStatus();	
			
			System.out.println("\n"); // double newline
			incomingRadial = 300;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(90);
			v.printVorStatus();	
			
			System.out.println("\n"); // double newline
			incomingRadial = 310;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(90);
			v.printVorStatus();	
			
			System.out.println("\n"); // double newline
			incomingRadial = 320;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(90);
			v.printVorStatus();	
			
			System.out.println("\n"); // double newline
			incomingRadial = 330;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(90);
			v.printVorStatus();	
			
			System.out.println("\n"); // double newline
			incomingRadial = 340;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(90);
			v.printVorStatus();	
			
			System.out.println("\n"); // double newline
			incomingRadial = 350;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(90);
			v.printVorStatus();	
			
			System.out.println("\n"); // double newline
			incomingRadial = 360;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(90);
			v.printVorStatus();	
			
			System.out.println("\n"); // double newline
			incomingRadial = 10;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(90);
			v.printVorStatus();
		
		} catch (IllegalArgumentException e) {
			System.err.println("ERROR: OBS values must be within 1-360 degrees, where 360 can also be zero");
		}
	}
}
