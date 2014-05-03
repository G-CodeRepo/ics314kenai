import java.util.*;
import java.io.*;

public class vorPrototype {
	private int CDI;	//the needle of the VOR
	private String flag;
	private int currentRadial;
	private String mc1;
	private String mc2;
	private String mc3;
	
	/**
	 * Constructor for the VOR prototype
	 * Function: The constructor will set the basic values of the VOR
	 * 	The vorStatinoRadial is going to be the radial in which the VOR station is, and 
	 * 	the boolean bearing is going to indicate whether or not you are going to or from 
	 * 	the VOR station
	 * @param int vorStationRadial, boolean bearing
	 */
	public vorPrototype(int vorStationRadial, String stationIdentifier, boolean bearing){
		currentRadial = vorStationRadial;
		if(bearing){
			flag = "TO";
		}
		else{
			flag = "FROM";
		}
	}
	
	/**
	 * Function: This method will be used in the GUI interface. If it is clicked left,
	 * the CDI needle will move one radial to the left. It will also adjust accordingly
	 * if the plane is going FROM or TO the station
	 */
	public void leftOBS(){
		CDI--;
		//todo if station is FROM or TO
	}
	
	/**
	 * Function: This method will be used in the GUI interface. If it is clicked right,
	 * the CDI needle will move one radial to the right. It will also adjust accordingly
	 * if the plane is going FROM or TO the station
	 */
	public void rightOBS(){
		CDI++;
		//todo if station is fROM or TO
	}
	
	/**
	 * Function: This method will be used in the GUI interface. It will let the user manually 
	 * set which radial the CDI will point to. It will also adjust accordingly if the plane 
	 * is going FROM or TO the station
	 * @param degree
	 */
	public void setOBS(int degree){
		if(degree < 0 || degree > 360){
			System.out.println("Error cannot set");
		}
		CDI = degree;
	}
	
	public void morseCodeIdentifier(String mc){
		String[] mcSplit = mc.split("\\s+");
		for(int i = 0; i < mcSplit.length; i++){
			switch(mcSplit[i]){
			case ".-": 		mcSplit[i] = "A";
				break;
			case "-...": 	mcSplit[i] = "B";
				break;
			case "-.-.": 	mcSplit[i] = "C";
				break;
			case "-..": 	mcSplit[i] = "D";
				break;
			case ".": 		mcSplit[i] = "E";
				break;
			case "..-.": 	mcSplit[i] = "F";
				break;
			case "--.": 	mcSplit[i] = "G";
				break;	
			case "....": 	mcSplit[i] = "H";
				break;
			case "..": 		mcSplit[i] = "I";
				break;
			case ".---": 	mcSplit[i] = "J";
				break;
			case "-.-": 	mcSplit[i] = "K";
				break;
			case ".-..": 	mcSplit[i] = "L";
				break;
			case "--": 		mcSplit[i] = "M";
				break;
			case "-.": 		mcSplit[i] = "N";
				break;
			case "---": 	mcSplit[i] = "O";
				break;
			case ".--.": 	mcSplit[i] = "P";
				break;
			case "--.-": 	mcSplit[i] = "Q";
				break;
			case ".-.": 	mcSplit[i] = "R";
				break;
			case "...": 	mcSplit[i] = "S";
				break;
			case "-": 		mcSplit[i] = "T";
				break;
			case "..-": 	mcSplit[i] = "U";
				break;
			case "...-": 	mcSplit[i] = "V";
				break;
			case ".--": 	mcSplit[i] = "W";
				break;
			case "-..-": 	mcSplit[i] = "X";
				break;
			case "-.--": 	mcSplit[i] = "Y";
				break;
			case "--..": 	mcSplit[i] = "Z";
				break;
			}
			System.out.println("testing arrays: " + mcSplit[i]);
		}
		mc1 = mcSplit[0];
		mc2 = mcSplit[1];
		if(mcSplit[2] != null){
			mc2 = mcSplit[1];
		}
	}
	
	public String getmc1(){
		return mc1;
	}
	
	public String getmc2(){
		return mc2;
	}
	
	public String getmc3(){
		return mc3;
	}
	
	public void updateVOR(){
		
	}
	
}
