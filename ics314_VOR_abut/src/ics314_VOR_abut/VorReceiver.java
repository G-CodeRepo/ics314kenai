package ics314_VOR_abut;
import java.util.ArrayList;
import java.util.List;

import test.JUnit;
import test.PartialTest;


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
// added getNeedlePosition method
// added setNeedlePosition method
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
// added more condition if OBS is between 90 and 270 degrees for the isTo and isFrom methods

// Change log 4/3/2014
// Modified by: Gerald Abut
// isTo and isFrom methods are completely overhauled
// create a PartialTest class
// create printVorStatus_v1 and printVorStatus_v2 for debugging prints (tests)
// modified moveNeedle conditions to only include == instead of >= or <=
// adjust360ToZero and adjustZeroTo360 methods are now public methods
// getOppositeRadial and setNeedlePosition methods now throws an IllegalArgumentException if given a wrong degree
// all values of 10 or -10 are now a constant "NEEDLE_LIMIT"


// Change log 4/4/2014
// Modified by: Gerald Abut
// validateRadial is now public
// getLeftAbeamedRadial and getRightAbeamedRadial are now public
// create JUnit Testing class

// Change log 4/4/2014
// included the finaVORGUI.java file from Adrian
// reverted the moveNeedle to DO NOTHING for the last "else" clause for each case
// adjusted Junit testing to reflect changes made from the moveNeedle method

// Change log 4/5/2014
// included morseCodeIdentifier
// number of morse code must only be 3. less than 3 or greater than 3 will have ???

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
			this.setOBS(this.currRadial); // initial OBS will be set to the current radial
			this.updateMorseCode(this.morseCodeIdentifier(morse)); // store morse code
			
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
			this.setOBS(this.currRadial); // initial OBS is set to the current radial 
			this.updateMorseCode(this.morseCodeIdentifier("NO MORSE CODE")); // set the default value of the more code
			
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
	public int validateRadial(int radial) {
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
	 * moreseCodeIndentifier translate the given morse code
	 * and returns a string. if the number of morse code
	 * is not 2 or 3 then return ???
	 * ex: "." => "A"
	 * ex: ".." => "B"
	 * @param mc
	 * @return string
	 */
	public String morseCodeIdentifier(String mc){
		  String[] mcSplit = mc.split("\\s+");
		  if (mcSplit.length < 2) {
			  return "???";	// unknown
		  } else if ( mcSplit.length > 3) {
			  return "???"; // unknown
		  }
		  String morseCode = "";
		  for(int i = 0; i < mcSplit.length; i++){
			   switch(mcSplit[i]){
				   case ".-":   mcSplit[i] = "A";
				    	break;
				   case "-...":  mcSplit[i] = "B";
				    	break;
				   case "-.-.":  mcSplit[i] = "C";
				    	break;
				   case "-..":  mcSplit[i] = "D";
				    	break;
				   case ".":   mcSplit[i] = "E";
				    	break;
				   case "..-.":  mcSplit[i] = "F";
				    	break;
				   case "--.":  mcSplit[i] = "G";
				    	break; 
				   case "....":  mcSplit[i] = "H";
				    	break;
				   case "..":   mcSplit[i] = "I";
				    	break;
				   case ".---":  mcSplit[i] = "J";
				    	break;
				   case "-.-":  mcSplit[i] = "K";
				    	break;
				   case ".-..":  mcSplit[i] = "L";
				    	break;
				   case "--":   mcSplit[i] = "M";
				    	break;
				   case "-.":   mcSplit[i] = "N";
				    	break;
				   case "---":  mcSplit[i] = "O";
				    	break;
				   case ".--.":  mcSplit[i] = "P";
				    	break;
				   case "--.-":  mcSplit[i] = "Q";
				    	break;
				   case ".-.":  mcSplit[i] = "R";
				    	break;
				   case "...":  mcSplit[i] = "S";
				    	break;
				   case "-":   mcSplit[i] = "T";
				    	break;
				   case "..-":  mcSplit[i] = "U";
				    	break;
				   case "...-":  mcSplit[i] = "V";
				    	break;
				   case ".--":  mcSplit[i] = "W";
				    	break;
				   case "-..-":  mcSplit[i] = "X";
				    	break;
				   case "-.--":  mcSplit[i] = "Y";
				    	break;
				   case "--..":  mcSplit[i] = "Z";
				   		break;	
				   default:
					   	mcSplit[i] = "?";
					   	break;
			   }
		  }
		  morseCode += mcSplit[0];
		  morseCode += mcSplit[1];
		  if(mcSplit.length == 3 && mcSplit[2] != null) {
			  morseCode += mcSplit[2];
		  }
		  return morseCode;
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
		
		if (currOBS == 360 || currOBS == 0) {	// exactly 360 or 0 degrees
			if (currRadial > abeamed[0] && currRadial < abeamed[1]) {
				test = true;
			}
		} else if (currOBS < 90 && currOBS > 0) {	// quadrant 1
			if (currRadial > abeamed[0] && currRadial < abeamed[1]) {
				test = true;
			}
		} else if (currOBS == 90) {	// exactly 90 degrees
			if (currRadial > 180 && currRadial < 360) {
				test = true;
			}
		} else if (currOBS < 180 && currOBS > 90) { // quadrant 2
			if ((currRadial > abeamed[0]) || (currRadial < abeamed[1])) {
				test = true;
			}	
		} else if (currOBS == 180) {	// exactly 180 degrees
			if (currRadial > abeamed[0] || currRadial < abeamed[1]) {
				test = true;
			}
		} else if (currOBS < 270 && currOBS > 180) { 	// quadrant 3
			if (currRadial > abeamed[0] || currRadial < abeamed[1]) {
				test = true;
			}
		} else if (currOBS == 270) {	// exactly 270 degrees
			if (currRadial > 0 && currRadial < abeamed[1]) {
				test = true;
			}
		} else if (currOBS < 360 && currOBS > 270) { // quadrant 4
			if (currRadial > abeamed[0] && currRadial < abeamed[1]) {
				test = true;
			}
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
		
		if (currOBS == 360 || currOBS == 0) {	// exactly 360 or 0 degrees
			if (currRadial < abeamed[0] || currRadial > abeamed[1]) {
				test = true;
			}
		} else if (currOBS < 90 && currOBS > 0) {	// quadrant 1
			if (currRadial < abeamed[0] || currRadial > abeamed[1]) {
				test = true;
			}
		} else if (currOBS == 90) {	// exactly 90 degrees
			if (currRadial < 180) {
				test = true;
			}
		} else if (currOBS < 180 && currOBS > 90) { // quadrant 2
			if ((currRadial < abeamed[0]) && (currRadial > abeamed[1])) {
				test = true;
			}	
		} else if (currOBS == 180) {	// exactly 180 degrees
			if (currRadial < abeamed[0] && currRadial > abeamed[1]) {
				test = true;
			}
		} else if (currOBS < 270 && currOBS > 180) { 	// quadrant 3
			if (currRadial < abeamed[0] && currRadial > abeamed[1]) {
				test = true;
			}
		} else if (currOBS == 270) {	// exactly 270 degrees
			if (currRadial < abeamed[0] && currRadial > abeamed[1]) {
				test = true;
			}
		} else if (currOBS < 360 && currOBS > 270) { // quadrant 4
			if (currRadial < abeamed[0] || currRadial > abeamed[1]) {
				test = true;
			}
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
	public int adjust360ToZero(int radial){
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
	public int adjustZeroTo360(int radial) {
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
	 * getLeftAbeamedRadial calculates the degree that is abeamed to the left of the vor station
	 * @return int
	 */
	public int getLeftAbeamedRadial() {
		int arrayOffset = this.DEGREES.size();
		int leftAbeamedOffset = (this.obs_val + ABEAMED_OFFSET + arrayOffset) % arrayOffset;		
		return this.adjustZeroTo360(leftAbeamedOffset);
	}
	
	/**
	 * getRightAbeamedRadial calculates the degree that is abeamed to the right of the vor station
	 * @return int
	 */
	public int getRightAbeamedRadial() {
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
	 * @throws IllegalArgumentException
	 */
	public int getOppositeRadial(int radial) {
		int arrayOffset = this.DEGREES.size();
		if (this.validateRadial(radial) >= 0) {
			return this.adjustZeroTo360((radial + OPPOSITE_DEGREE + arrayOffset) % arrayOffset);	// wrap around from left to right
		} else {
			throw new IllegalArgumentException();
		}
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
	 * needle's range is between -10 to 10
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
			if ((currRad == (this.adjustDegree(obs_opposite, -9))) || (currRad == (this.adjustDegree(obs_opposite, -this.NEEDLE_LIMIT)))) {																	// left of opposite of intended radial
				this.needle = -this.NEEDLE_LIMIT;
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
			} else if ((currRad == (this.adjustDegree(obs_opposite, 9))) || (currRad == (this.adjustDegree(obs_opposite, this.NEEDLE_LIMIT)))) {															// right of opposite of intended radial
				this.needle = this.NEEDLE_LIMIT;
			} else {
				// DO NOTHING
			}
		} else { // plane is pointing in the degrees away from the intended radial
			// isFrom = true
			if ((currRad == (this.adjustDegree(obs, 9))) || (currRad == (this.adjustDegree(obs, this.NEEDLE_LIMIT)))) {															// left of intended radial
				this.needle = -this.NEEDLE_LIMIT;
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
			} else if ((currRad == (this.adjustDegree(obs, -9))) || (currRad == (this.adjustDegree(obs, -this.NEEDLE_LIMIT)))) {													// right of  intended radial
				this.needle = this.NEEDLE_LIMIT;
			} else {
				// DO NOTHING
			}
		}
	}
	
	/**
	 * setNeedlePosition sets the needle position (ONLY USED BY THE CONSTRUCTOR)
	 * @param position
	 * @throws IllegalArgumentException
	 */
	private void setNeedlePosition(int position) {
		if (this.validateRadial(position) >= 0) {
			this.needle = position;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * USE FOR DEBUGGING
	 * getVorStatus returns the current values of the vor receiver
	 * @return String
	 */
	public String getVorStatus() {                
		return "Detecting incoming radial from VOR Station..."+
			   "\nCurrent radial: " + this.getCurrRadial() +
			   "\nMorse code: " + this.getMorse() +
		       "\nOpposite Radial: " + this.getOppositeRadial(this.getCurrRadial()) +
		       "\nOBS (Currently set Intended Radial): " + this.getOBS() +
		       "\nAbeamed (left of vor station): " + this.getLeftAbeamedRadial() +
		       "\nAbeamed (right of vor station): " + this.getRightAbeamedRadial() +
		       "\nNeedle Position: " + this.getNeedlePosition() + 	
		       "\nIs Abeamed: " + this.isAbeamed() +
		       "\nIs TO: " + this.isTo() +
		       "\nIs FROM: " + this.isFrom();

	}
	
	
	/**
	 * USE FOR DEBUGGING (RECOMMENDED)
	 * printVorStatus_v1 prints out the current stats of the vor receiver
	 * similar to getVorStatus except that it just prints out the content
	 * instead of returning it as a string. The print is formatted for
	 * easy viewing
	 */
	public void printVorStatus_v1() {   
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
		System.out.printf(s + l + 
						  s + i + l + 
						  s + mod_s + l +
						  s + i + l +
						  s + i + l +
						  s + i + l + 
						  s + i + l +
						  s + i + l +
						  s + mod_s + l +
						  s + mod_s + l +
						  s + mod_s + l,
						"Detecting incoming radial from VOR Station...",
						"Current radial:", this.getCurrRadial(),
						"Morse code:", this.getMorse(),
						"Opposite Radial:", this.getOppositeRadial(this.getCurrRadial()),
						"OBS (Currently set Intended Radial):", this.getOBS(),
						"Abeamed (left of vor station):", this.getLeftAbeamedRadial(),
						"Abeamed (right of vor station):", this.getRightAbeamedRadial(),
						"Needle Position:", this.getNeedlePosition(),
						"Is Abeamed:", abeamed,
						"Is TO: ", to,
						"Is FROM: ", from);
		}
	
	/**
	 * USE FOR DEBUGGING (UNSTABLE FOR THE FIRST PRINT BUT SHOULD BE NORMAL FOR THE REST OF THE PRINTS)
	 * printVorStatus_V2 is similar to printVor_V1 but prints out any false values in red (error stream)
	 */
	public void printVorStatus_v2() {
		String s = "%-50s";		// formatted to left justified with 50 characters
		String i = "%d";		// integer
		String l = "\n";		// newline
		String mod_s = "%-10s";	// formatted to left justified with 10 characters

		System.out.printf(s + l, "Detecting incoming radial from VOR Station...");
		System.out.printf(s + i + l, "Current radial:",  this.getCurrRadial());
		System.out.printf(s + mod_s + l, "Morse code:", this.getMorse());
		System.out.printf(s + i + l, "Opposite Radial:", this.getOppositeRadial(this.getCurrRadial()));
		System.out.printf(s + i + l, "OBS (Currently set Intended Radial):", this.getOBS());
		System.out.printf(s + i + l, "Abeamed (left of vor station):", this.getLeftAbeamedRadial());
		System.out.printf(s + i + l, "Abeamed (right of vor station):", this.getRightAbeamedRadial());
		System.out.printf(s + i + l, "Needle Position:", this.getNeedlePosition());
		if (this.isAbeamed()) {
			System.out.printf(s + mod_s + l, "Is Abeamed:", "true");
		} else {
			System.err.printf(s + mod_s + l, "Is Abeamed:", "false");
		}
		if (this.isTo()) {
			System.out.printf(s + mod_s + l, "Is TO", "true");
		} else {
			System.err.printf(s + mod_s + l, "Is TO", "false");
		}
		if (this.isFrom()) {
			System.out.printf(s + mod_s + l, "Is FROM:", "true");
		} else {
			System.err.printf(s + mod_s + l, "Is FROM:", "false");
		}
	}
}
