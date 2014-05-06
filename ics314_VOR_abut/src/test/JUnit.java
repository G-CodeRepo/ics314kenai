package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import ics314_VOR_abut.VorReceiver;

public class JUnit {	
	@Test
	public void constructor1_test1() {
		String morsecode = ".- -... -.-.";
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial, morsecode);
		printResult("constructor1: getCurrRadial", 360, v.getCurrRadial());
		assertEquals(360, v.getCurrRadial());
	}
	@Test
	public void constructor1_test2() {
		String morsecode = ".- -... -.-.";
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial, morsecode);
		printResult("constructor1: getOBS", 360, v.getCurrRadial());
		assertEquals(360, v.getOBS());
	}
	@Test
	public void constructor1_test3() {
		String morsecode = ".- -... -.-.";
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial, morsecode);
		printResult("constructor1: getMorse", "ABC", v.getMorse());
		assertEquals("ABC", v.getMorse());
	}
	@Test
	public void constructor1_test4() {
		String morsecode = ".- -... -.-.";
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial, morsecode);
		printResult("constructor1: getNeedlePosition", 360, v.getCurrRadial());
		assertEquals(0, v.getNeedlePosition());
	}
	//************************************************************************
	@Test
	public void constructor2_test1() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		printResult("constructor2: getCurrRadial", 360, v.getCurrRadial());
		assertEquals(360, v.getCurrRadial());
	}
	@Test
	public void constructor2_test2() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		printResult("constructor2: getOBS", 360, v.getCurrRadial());
		assertEquals(360, v.getOBS());
	}
	@Test
	public void constructor2_test3() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		printResult("constructor2: getMorse", "???", v.getMorse());
		assertEquals("???", v.getMorse());
	}
	@Test
	public void constructor2_test4() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		printResult("constructor2: getNeedlePosition", 360, v.getCurrRadial());
		assertEquals(0, v.getNeedlePosition());
	}
	//************************************************************************
	@Test
	public void getCurrRadial_test1() {
		int incomingRadial = 356;
		VorReceiver v = new VorReceiver(incomingRadial);
		printResult("getCurrRadial", incomingRadial, v.getCurrRadial());
		assertEquals(incomingRadial, v.getCurrRadial());
		
	}
	@Test
	public void getCurrRadial_test2() {
		int incomingRadial = 357;
		VorReceiver v = new VorReceiver(incomingRadial);
		printResult("getCurrRadial", incomingRadial, v.getCurrRadial());
		assertEquals(incomingRadial, v.getCurrRadial());
		
	}
	@Test
	public void getCurrRadial_test3() {
		int incomingRadial = 358;
		VorReceiver v = new VorReceiver(incomingRadial);
		printResult("getCurrRadial", incomingRadial, v.getCurrRadial());
		assertEquals(incomingRadial, v.getCurrRadial());
		
	}
	@Test
	public void getCurrRadial_test4() {
		int incomingRadial = 359;
		VorReceiver v = new VorReceiver(incomingRadial);
		printResult("getCurrRadial", incomingRadial, v.getCurrRadial());
		assertEquals(incomingRadial, v.getCurrRadial());
	}
	@Test
	public void getCurrRadial_test5() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		printResult("getCurrRadial", incomingRadial, v.getCurrRadial());
		assertEquals(incomingRadial, v.getCurrRadial());
	}
	@Test
	public void getCurrRadial_test6() {
		int incomingRadial = 1;
		VorReceiver v = new VorReceiver(incomingRadial);
		printResult("getCurrRadial", incomingRadial, v.getCurrRadial());
		assertEquals(incomingRadial, v.getCurrRadial());	
	}
	@Test
	public void getCurrRadial_test7() {
		int incomingRadial = 2;
		VorReceiver v = new VorReceiver(incomingRadial);
		printResult("getCurrRadial", incomingRadial, v.getCurrRadial());
		assertEquals(incomingRadial, v.getCurrRadial());	
	}
	@Test
	public void getCurrRadial_test8() {
		int incomingRadial = 3;
		VorReceiver v = new VorReceiver(incomingRadial);
		printResult("getCurrRadial", incomingRadial, v.getCurrRadial());
		assertEquals(incomingRadial, v.getCurrRadial());
	}
	@Test
	public void getCurrRadial_test9() {
		int incomingRadial = 4;
		VorReceiver v = new VorReceiver(incomingRadial);
		printResult("getCurrRadial", incomingRadial, v.getCurrRadial());
		assertEquals(incomingRadial, v.getCurrRadial());	
	}
	@Test
	public void getCurrRadial_test10() {
		int incomingRadial = 5;
		VorReceiver v = new VorReceiver(incomingRadial);
		printResult("getCurrRadial", incomingRadial, v.getCurrRadial());
		assertEquals(incomingRadial, v.getCurrRadial());	
	}
	//************************************************************************
	@Test
	public void updateIncomingRadial_test1() {
		int incomingRadial = 1;
		int update = 175;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.updateIncomingRadial(update);
		printResult("updateIncomingRadial", update, v.getCurrRadial());
		assertEquals(update, v.getCurrRadial());
	}
	
	@Test
	public void updateIncomingRadial_test2() {
		int incomingRadial = 1;
		int update = 180;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.updateIncomingRadial(update);
		printResult("updateIncomingRadial", update, v.getCurrRadial());
		assertEquals(update, v.getCurrRadial());
	}
	@Test
	public void updateIncomingRadial_test3() {
		int incomingRadial = 1;
		int update = 181;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.updateIncomingRadial(update);
		printResult("updateIncomingRadial", update, v.getCurrRadial());
		assertEquals(update, v.getCurrRadial());
	}
	@Test
	public void updateIncomingRadial_test4() {
		int incomingRadial = 1;
		int update = 182;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.updateIncomingRadial(update);
		printResult("updateIncomingRadial", update, v.getCurrRadial());
		assertEquals(update, v.getCurrRadial());
	}
	
	@Test
	public void updateIncomingRadial_test5() {
		int incomingRadial = 1;
		int update = 269;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.updateIncomingRadial(update);
		printResult("updateIncomingRadial", update, v.getCurrRadial());
		assertEquals(update, v.getCurrRadial());
	}
	
	@Test
	public void updateIncomingRadial_test6() {
		int incomingRadial = 1;
		int update = 270;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.updateIncomingRadial(update);
		printResult("updateIncomingRadial", update, v.getCurrRadial());
		assertEquals(update, v.getCurrRadial());
	}
	@Test
	public void updateIncomingRadial_test7() {
		int incomingRadial = 1;
		int update = 271;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.updateIncomingRadial(update);
		printResult("updateIncomingRadial", update, v.getCurrRadial());
		assertEquals(update, v.getCurrRadial());
	}
	@Test
	public void updateIncomingRadial_test8() {
		int incomingRadial = 1;
		int update = 272;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.updateIncomingRadial(update);
		printResult("updateIncomingRadial", update, v.getCurrRadial());
		assertEquals(update, v.getCurrRadial());
	}
	@Test
	public void updateIncomingRadial_test9() {
		int incomingRadial = 1;
		int update = 359;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.updateIncomingRadial(update);
		printResult("updateIncomingRadial", update, v.getCurrRadial());
		assertEquals(update, v.getCurrRadial());
	}
	@Test
	public void updateIncomingRadial_test10() {
		int incomingRadial = 1;
		int update = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.updateIncomingRadial(update);
		printResult("updateIncomingRadial", update, v.getCurrRadial());
		assertEquals(update, v.getCurrRadial());
	}
	//************************************************************************
	@Test
	public void validateRadial_test1() {
		int incomingRadial = 1;
		VorReceiver v = new VorReceiver(incomingRadial);
		int deg = 375;
		printResult("validateRadial", -1, v.validateRadial(deg));
		assertEquals(-1, v.validateRadial(deg));
	}
	@Test
	public void validateRadial_test2() {
		int incomingRadial = 1;
		VorReceiver v = new VorReceiver(incomingRadial);
		int deg = 0;
		printResult("validateRadial", 360, v.validateRadial(deg));
		assertEquals(360, v.validateRadial(deg));
	}
	@Test
	public void validateRadial_test3() {
		int incomingRadial = 1;
		VorReceiver v = new VorReceiver(incomingRadial);
		int deg = 360;
		printResult("validateRadial", deg, v.validateRadial(deg));
		assertEquals(deg, v.validateRadial(deg));
	}
	@Test
	public void validateRadial_test4() {
		int incomingRadial = 1;
		VorReceiver v = new VorReceiver(incomingRadial);
		int deg = 170;
		printResult("validateRadial", deg, v.validateRadial(deg));
		assertEquals(deg, v.validateRadial(deg));
	}
	@Test
	public void validateRadial_test5() {
		int incomingRadial = 1;
		VorReceiver v = new VorReceiver(incomingRadial);
		int deg = -15;
		printResult("validateRadial", -1, v.validateRadial(deg));
		assertEquals(-1, v.validateRadial(deg));
	}
	//************************************************************************
	@Test
	public void getMorse_test1() {
		String morsecode = "- --- --";
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial, morsecode);
		printResult("getMorse", "TOM", v.getMorse());
		assertEquals("TOM", v.getMorse());
	}
	@Test
	public void getMorse_test2() {
		String morsecode = "--.- ... -.-";
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial, morsecode);
		printResult("getMorse", "QSK", v.getMorse());
		assertEquals("QSK", v.getMorse());
	}
	@Test
	public void getMorse_test3() {
		String morsecode = ".-.. . -..";
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial, morsecode);
		printResult("getMorse", "LED", v.getMorse());
		assertEquals("LED", v.getMorse());
	}
	@Test
	public void getMorse_test4() {
		String morsecode = "..-. .. .---";
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial, morsecode);
		printResult("getMorse", "FIJ", v.getMorse());
		assertEquals("FIJ", v.getMorse());
	}
	@Test
	public void getMorse_test5() {
		String morsecode = "..-. .. .--- ..-";
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial, morsecode);
		printResult("getMorse", "???", v.getMorse());
		assertEquals("???", v.getMorse());
	}
	
	@Test
	public void getMorse_test6() {
		String morsecode = "..-";
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial, morsecode);
		printResult("getMorse", "???", v.getMorse());
		assertEquals("???", v.getMorse());
	}
	//************************************************************************	
	@Test
	public void updateMorseCode_test1() {	// updatedMorseCode is a private method
		String morsecode = ".- -... -.-.";			// constructor uses updatedMorseCode method 
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial, morsecode);
		printResult("updateMorseCode", "ABC", v.getMorse()); 
		assertEquals("ABC", v.getMorse());
	}
	@Test
	public void updateMorseCode_test2() {	// updatedMorseCode is a private method
		String morsecode = "-..- -.-- --..";// constructor uses updatedMorseCode method 
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial, morsecode);
		printResult("updateMorseCode", "XYZ", v.getMorse()); 
		assertEquals("XYZ", v.getMorse());
	}
	@Test
	public void updateMorseCode_test3() {	// updatedMorseCode is a private method
		String morsecode = "--..";// constructor uses updatedMorseCode method 
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial, morsecode);
		printResult("updateMorseCode", "???", v.getMorse()); 
		assertEquals("???", v.getMorse());
	}
	//************************************************************************
	@Test
	public void morseCodeIdentifier_test1() {	
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		String morsecode = "--..";
		printResult("updateMorseCode", "???", v.morseCodeIdentifier(morsecode)); 
		assertEquals("???", v.morseCodeIdentifier(morsecode));
	}
	@Test
	public void morseCodeIdentifier_test2() {	
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		String morsecode = "--. .. -";
		printResult("updateMorseCode", "GIT", v.morseCodeIdentifier(morsecode)); 
		assertEquals("GIT", v.morseCodeIdentifier(morsecode));
	}
	
	@Test
	public void morseCodeIdentifier_test3() {	
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		String morsecode = "--.";
		printResult("updateMorseCode", "???", v.morseCodeIdentifier(morsecode)); 
		assertEquals("???", v.morseCodeIdentifier(morsecode));
	}	
	//************************************************************************
	// OUTPUT IS TOO LARGE (UNCOMMENT TO SEE RESULTS
	/*@Test
	public void getDegrees_test1() {	
		String morsecode = "CAT";			 
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial, morsecode);
		List<Integer> deg = new ArrayList<Integer>();
		for (int i = 1; i <= 360; i++) {
			deg.add(i);
		}
		printResult("getDegrees_test1", deg, v.getDegrees()); 
		assertEquals(deg, v.getDegrees().toString());
	}*/
	//************************************************************************
	@Test
	public void isTo_test1() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(180);
		printResult("isTo", true, v.isTo());
		assertEquals(true, v.isTo()); 
	}
	@Test
	public void isTo_test2() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(91);
		printResult("isTo", true, v.isTo()); 
		assertEquals(true, v.isTo()); 
	}
	@Test
	public void isTo_test3() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(90);
		printResult("isTo", false, v.isTo());
		assertEquals(false, v.isTo()); 
	}
	@Test
	public void isTo_test4() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(89);
		printResult("isTo", false, v.isTo()); 
		assertEquals(false, v.isTo()); 
	}
	@Test
	public void isTo_test5() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(360);
		printResult("isTo", false, v.isTo()); 
		assertEquals(false, v.isTo()); 
	}
	@Test
	public void isTo_test6() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(271);
		printResult("isTo", false, v.isTo()); 
		assertEquals(false, v.isTo()); 
	}
	@Test
	public void isTo_test7() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(270);
		printResult("isTo", false, v.isTo()); 
		assertEquals(false, v.isTo()); 
	}
	@Test
	public void isTo_test8() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(269);
		printResult("isTo", true, v.isTo()); 
		assertEquals(true, v.isTo()); 
	}
	//************************************************************************
	@Test
	public void isFrom_test1() {
		int incomingRadial = 180;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(360);
		printResult("isFrom", false, v.isFrom()); 
		assertEquals(false, v.isFrom()); 
	}
	@Test
	public void isFrom_test2() {
		int incomingRadial = 180;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(271);
		printResult("isFrom", false, v.isFrom()); 
		assertEquals(false, v.isFrom()); 
	}
	@Test
	public void isFrom_test3() {
		int incomingRadial = 180;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(270);
		printResult("isFrom", false, v.isFrom()); 
		assertEquals(false, v.isFrom()); 
	}
	@Test
	public void isFrom_test4() {
		int incomingRadial = 180;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(269);
		printResult("isFrom", true, v.isFrom()); 
		assertEquals(true, v.isFrom()); 
	}
	@Test
	public void isFrom_test5() {
		int incomingRadial = 180;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(180);
		printResult("isFrom", true, v.isFrom()); 
		assertEquals(true, v.isFrom()); 
	}
	@Test
	public void isFrom_test6() {
		int incomingRadial = 180;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(91);
		printResult("isFrom", true, v.isFrom()); 
		assertEquals(true, v.isFrom()); 
	}
	@Test
	public void isFrom_test7() {
		int incomingRadial = 180;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(90);
		printResult("isFrom", false, v.isFrom()); 
		assertEquals(false, v.isFrom()); 
	}
	@Test
	public void isFrom_test8() {
		int incomingRadial = 180;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(89);
		printResult("isFrom", false, v.isFrom()); 
		assertEquals(false, v.isFrom()); 
	}
	@Test
	public void isFrom_test9() {
		int incomingRadial = 135;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(180);
		printResult("isFrom", true, v.isFrom()); 
		assertEquals(true, v.isFrom()); 
	}
	@Test
	public void isFrom_test10() {
		int incomingRadial = 260;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(180);
		printResult("isFrom", true, v.isFrom()); 
		assertEquals(true, v.isFrom()); 
	}
	//************************************************************************
	@Test
	public void adjust360ToZero_test1() {
		int incomingRadial = 180;
		VorReceiver v = new VorReceiver(incomingRadial);
		printResult("adjust360ToZero", 0, v.adjust360ToZero(360)); 
		assertEquals(0, v.adjust360ToZero(360)); 
	}
	@Test
	public void adjust360ToZero_test2() {
		int incomingRadial = 180;
		VorReceiver v = new VorReceiver(incomingRadial);
		printResult("adjust360ToZero", 35, v.adjust360ToZero(35)); 
		assertEquals(35, v.adjust360ToZero(35)); 
	}
	@Test
	public void adjust360ToZero_test3() {
		int incomingRadial = 180;
		VorReceiver v = new VorReceiver(incomingRadial);
		printResult("adjust360ToZero", 0, v.adjust360ToZero(0)); 
		assertEquals(0, v.adjust360ToZero(0)); 
	}
	@Test
	public void adjust360ToZero_test4() {
		int incomingRadial = 180;
		VorReceiver v = new VorReceiver(incomingRadial);
		printResult("adjust360ToZero", 359, v.adjust360ToZero(359)); 
		assertEquals(359, v.adjust360ToZero(359)); 
	}
	//************************************************************************
	@Test
	public void adjustZeroTo360_test1() {
		int incomingRadial = 180;
		VorReceiver v = new VorReceiver(incomingRadial);
		printResult("adjustZeroto360", 360, v.adjustZeroTo360(0)); 
		assertEquals(360, v.adjustZeroTo360(0)); 
	}
	@Test
	public void adjustZeroTo360_test2() {
		int incomingRadial = 180;
		VorReceiver v = new VorReceiver(incomingRadial);
		printResult("adjustZeroto360", 270, v.adjustZeroTo360(270)); 
		assertEquals(270, v.adjustZeroTo360(270)); 
	}
	@Test
	public void adjustZeroTo360_test3() {
		int incomingRadial = 180;
		VorReceiver v = new VorReceiver(incomingRadial);
		printResult("adjustZeroto360", 360, v.adjustZeroTo360(360)); 
		assertEquals(360, v.adjustZeroTo360(360)); 
	}
	//************************************************************************
	@Test
	public void isAbeamed_test1() {
		int incomingRadial = 180;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.updateIncomingRadial(89);
		printResult("isAbeamed", false, v.isAbeamed()); 
		assertEquals(false, v.isAbeamed()); 
	}
	@Test
	public void isAbeamed_test2() {
		int incomingRadial = 180;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.updateIncomingRadial(90);
		printResult("isAbeamed", true, v.isAbeamed()); 
		assertEquals(true, v.isAbeamed()); 
	}
	@Test
	public void isAbeamed_test3() {
		int incomingRadial = 180;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.updateIncomingRadial(89);
		printResult("isAbeamed", false, v.isAbeamed()); 
		assertEquals(false, v.isAbeamed()); 
	}
	@Test
	public void isAbeamed_test4() {
		int incomingRadial = 180;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.updateIncomingRadial(269);
		printResult("isAbeamed", false, v.isAbeamed()); 
		assertEquals(false, v.isAbeamed()); 
	}
	@Test
	public void isAbeamed_test5() {
		int incomingRadial = 180;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.updateIncomingRadial(270);
		printResult("isAbeamed", true, v.isAbeamed()); 
		assertEquals(true, v.isAbeamed()); 
	}
	@Test
	public void isAbeamed_test6() {
		int incomingRadial = 180;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.updateIncomingRadial(271);
		printResult("isAbeamed", false, v.isAbeamed()); 
		assertEquals(false, v.isAbeamed()); 
	}
	@Test
	public void isAbeamed_test7() {
		int incomingRadial = 225;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.updateIncomingRadial(315);
		printResult("isAbeamed", true, v.isAbeamed()); 
		assertEquals(true, v.isAbeamed()); 
	}
	@Test
	public void isAbeamed_test8() {
		int incomingRadial = 225;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.updateIncomingRadial(135);
		printResult("isAbeamed", true, v.isAbeamed()); 
		assertEquals(true, v.isAbeamed()); 
	}
	@Test
	public void isAbeamed_test9() {
		int incomingRadial = 135;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.updateIncomingRadial(45);
		printResult("isAbeamed", true, v.isAbeamed()); 
		assertEquals(true, v.isAbeamed()); 
	}
	@Test
	public void isAbeamed_test10() {
		int incomingRadial = 135;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.updateIncomingRadial(225);
		printResult("isAbeamed", true, v.isAbeamed()); 
		assertEquals(true, v.isAbeamed()); 
	}
	//************************************************************************
	@Test
	public void getAbeamedRadials_test1() {
		int incomingRadial = 135;
		VorReceiver v = new VorReceiver(incomingRadial);
		int[] exp = {225,45};
		printResult("getAbeamedRadials", exp[0], v.getAbeamedRadials()[0]); 
		assertEquals(exp[0], v.getAbeamedRadials()[0]); 
	}
	@Test
	public void getAbeamedRadials_test2() {
		int incomingRadial = 135;
		VorReceiver v = new VorReceiver(incomingRadial);
		int[] exp = {225,45};
		printResult("getAbeamedRadials", exp[1], v.getAbeamedRadials()[1]); 
		assertEquals(exp[1], v.getAbeamedRadials()[1]); 
	}
	@Test
	public void getAbeamedRadials_test3() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int[] exp = {270, 90};
		printResult("getAbeamedRadials", exp[0], v.getAbeamedRadials()[0]); 
		assertEquals(exp[0], v.getAbeamedRadials()[0]); 
	}
	@Test
	public void getAbeamedRadials_test4() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int[] exp = {270, 90};
		printResult("getAbeamedRadials", exp[1], v.getAbeamedRadials()[1]); 
		assertEquals(exp[1], v.getAbeamedRadials()[1]); 
	}
	//************************************************************************	
	@Test
	public void getLeftAbeamedRadial_test1() {
		int incomingRadial = 270;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int deg = 180;
		printResult("getLeftAbeamedRadial", deg, v.getLeftAbeamedRadial()); 
		assertEquals(deg, v.getLeftAbeamedRadial()); 
	}
	@Test
	public void getLeftAbeamedRadial_test2() {
		int incomingRadial = 90;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int deg = 360;
		printResult("getLeftAbeamedRadial", deg, v.getLeftAbeamedRadial()); 
		assertEquals(deg, v.getLeftAbeamedRadial()); 
	}
	@Test
	public void getLeftAbeamedRadial_test3() {
		int incomingRadial = 180;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int deg = 90;
		printResult("getLeftAbeamedRadial", deg, v.getLeftAbeamedRadial()); 
		assertEquals(deg, v.getLeftAbeamedRadial()); 
	}
	
	@Test
	public void getLeftAbeamedRadial_test4() {
		int incomingRadial = 135;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int deg = 45;
		printResult("getLeftAbeamedRadial", deg, v.getLeftAbeamedRadial()); 
		assertEquals(deg, v.getLeftAbeamedRadial()); 
	}
	//************************************************************************
	@Test
	public void getRightAbeamedRadial_test1() {
		int incomingRadial = 270;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int deg = 360;
		printResult("getRightAbeamedRadial", deg, v.getRightAbeamedRadial()); 
		assertEquals(deg, v.getRightAbeamedRadial()); 
	}
	@Test
	public void getRightAbeamedRadial_test2() {
		int incomingRadial = 90;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int deg = 180;
		printResult("getRightAbeamedRadial", deg, v.getRightAbeamedRadial()); 
		assertEquals(deg, v.getRightAbeamedRadial()); 
	}
	@Test
	public void getRightAbeamedRadial_test3() {
		int incomingRadial = 180;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int deg = 270;
		printResult("getRightAbeamedRadial", deg, v.getRightAbeamedRadial()); 
		assertEquals(deg, v.getRightAbeamedRadial()); 
	}
	@Test
	public void getRightAbeamedRadial_test4() {
		int incomingRadial = 135;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int deg = 225;
		printResult("getRightAbeamedRadial", deg, v.getRightAbeamedRadial()); 
		assertEquals(deg, v.getRightAbeamedRadial()); 
	}
	//************************************************************************
	@Test
	public void getOBS_test1() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int deg = 10;
		v.setOBS(deg);
		printResult("getOBS", deg, v.getOBS()); 
		assertEquals(deg, v.getOBS()); 
	}
	@Test
	public void getOBS_test2() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int deg = 271;
		v.setOBS(deg);
		printResult("getOBS", deg, v.getOBS()); 
		assertEquals(deg, v.getOBS()); 
	}
	@Test
	public void getOBS_test3() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int deg = 1;
		v.setOBS(deg);
		printResult("getOBS", deg, v.getOBS()); 
		assertEquals(deg, v.getOBS()); 
	}
	@Test
	public void getOBS_test4() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int deg = 335;
		v.setOBS(deg);
		printResult("getOBS", deg, v.getOBS()); 
		assertEquals(deg, v.getOBS()); 
	}
	//************************************************************************
	@Test
	public void getOppositeRadial_test1() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int deg = 180;
		printResult("getOBS", deg, v.getOppositeRadial(incomingRadial)); 
		assertEquals(deg, v.getOppositeRadial(incomingRadial)); 
	}
	@Test
	public void getOppositeRadial_test2() {
		int incomingRadial = 225;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int deg = 45;
		printResult("getOBS", deg, v.getOppositeRadial(incomingRadial)); 
		assertEquals(deg, v.getOppositeRadial(incomingRadial)); 
	}
	@Test
	public void getOppositeRadial_test3() {
		int incomingRadial = 1;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int deg = 181;
		printResult("getOBS", deg, v.getOppositeRadial(incomingRadial)); 
		assertEquals(deg, v.getOppositeRadial(incomingRadial)); 
	}
	@Test
	public void getOppositeRadial_test4() {
		int incomingRadial = 95;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int deg = 275;
		printResult("getOBS", deg, v.getOppositeRadial(incomingRadial)); 
		assertEquals(deg, v.getOppositeRadial(incomingRadial)); 
	}
	//************************************************************************
	@Test
	public void setOBS_test1() {
		int incomingRadial = 95;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int deg = 275;
		v.setOBS(deg);
		printResult("setOBS", deg, v.getOBS()); 
		assertEquals(deg, v.getOBS()); 
	}
	@Test
	public void setOBS_test2() {
		int incomingRadial = 95;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int deg = 300;
		v.setOBS(deg);
		printResult("setOBS", deg, v.getOBS()); 
		assertEquals(deg, v.getOBS()); 
	}
	@Test
	public void setOBS_test3() {
		int incomingRadial = 95;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int deg = 360;
		v.setOBS(deg);
		printResult("setOBS", deg, v.getOBS()); 
		assertEquals(deg, v.getOBS()); 
	}
	@Test
	public void setOBS_test4() {
		int incomingRadial = 95;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int deg = 77;
		v.setOBS(deg);
		printResult("setOBS", deg, v.getOBS()); 
		assertEquals(deg, v.getOBS()); 
	}
	//************************************************************************
	@Test
	public void incOBS_test1() {
		int incomingRadial = 95;
		VorReceiver v = new VorReceiver(incomingRadial);
		int incremented = v.incOBS();
		printResult("incOBS", incomingRadial + 1, incremented); 
		assertEquals(incomingRadial + 1, incremented); 
	}
	@Test
	public void incOBS_test2() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		int incremented = v.incOBS();
		printResult("incOBS", 1, incremented); 
		assertEquals(1, incremented); 
	}
	@Test
	public void incOBS_test3() {
		int incomingRadial = 0;
		VorReceiver v = new VorReceiver(incomingRadial);
		int incremented = v.incOBS();
		printResult("incOBS", 1, incremented); 
		assertEquals(1, incremented); 
	}
	@Test
	public void incOBS_test4() {
		int incomingRadial = 90;
		VorReceiver v = new VorReceiver(incomingRadial);
		int incremented = v.incOBS();
		printResult("incOBS", incomingRadial + 1, incremented); 
		assertEquals(incomingRadial + 1, incremented); 
	}
	//************************************************************************
	@Test
	public void decOBS_test1() {
		int incomingRadial = 95;
		VorReceiver v = new VorReceiver(incomingRadial);
		int decrement = v.decOBS();
		printResult("decOBS", incomingRadial - 1, decrement); 
		assertEquals(incomingRadial - 1, decrement); 
	}
	@Test
	public void decOBS_test2() {
		int incomingRadial = 1;
		VorReceiver v = new VorReceiver(incomingRadial);
		int decrement = v.decOBS();
		printResult("getOBS", 360, decrement); 
		assertEquals(360, decrement); 
	}
	@Test
	public void decOBS_test3() {
		int incomingRadial = 0;
		VorReceiver v = new VorReceiver(incomingRadial);
		int decrement = v.decOBS();
		printResult("decOBS", 359, decrement); 
		assertEquals(359, decrement); 
	}
	@Test
	public void decOBS_test4() {
		int incomingRadial = 90;
		VorReceiver v = new VorReceiver(incomingRadial);
		int decrement = v.decOBS();
		printResult("decOBS", incomingRadial - 1, decrement); 
		assertEquals(incomingRadial - 1, decrement); 
	}
	//************************************************************************
	@Test
	public void getNeedlePosition_test1() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int deg = 0;
		printResult("getNeedlePosition", deg, v.getNeedlePosition()); 
		assertEquals(deg, v.getNeedlePosition()); 
	}
	@Test
	public void getNeedlePosition_test2() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int needleValue = -10;
		v.updateIncomingRadial(incomingRadial-10);
		printResult("getNeedlePosition", needleValue, v.getNeedlePosition()); 
		assertEquals(needleValue, v.getNeedlePosition()); 
	}
	@Test
	public void getNeedlePosition_test3() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int needleValue = -10;
		v.updateIncomingRadial(incomingRadial-9);
		printResult("getNeedlePosition", needleValue, v.getNeedlePosition()); 
		assertEquals(needleValue, v.getNeedlePosition()); 
	}
	@Test
	public void getNeedlePosition_test4() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int needleValue = -8;
		v.updateIncomingRadial(incomingRadial-8);
		printResult("getNeedlePosition", needleValue, v.getNeedlePosition()); 
		assertEquals(needleValue, v.getNeedlePosition()); 
	}
	@Test
	public void getNeedlePosition_test5() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int needleValue = -2;
		v.updateIncomingRadial(incomingRadial-2);
		printResult("getNeedlePosition", needleValue, v.getNeedlePosition()); 
		assertEquals(needleValue, v.getNeedlePosition()); 
	}
	@Test
	public void getNeedlePosition_test6() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int needleValue = 2;
		v.updateIncomingRadial(1);
		printResult("getNeedlePosition", needleValue, v.getNeedlePosition()); 
		assertEquals(needleValue, v.getNeedlePosition()); 
	}
	@Test
	public void getNeedlePosition_test7() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int needleValue = 6;
		v.updateIncomingRadial(5);
		printResult("getNeedlePosition", needleValue, v.getNeedlePosition()); 
		assertEquals(needleValue, v.getNeedlePosition()); 
	}
	@Test
	public void getNeedlePosition_test8() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int needleValue = 0;
		v.updateIncomingRadial(91);
		printResult("getNeedlePosition", needleValue, v.getNeedlePosition()); 
		assertEquals(needleValue, v.getNeedlePosition()); 
	}
	@Test
	public void getNeedlePosition_test9() {
		int incomingRadial = 360;
		VorReceiver v = new VorReceiver(incomingRadial);
		v.setOBS(v.getOppositeRadial(incomingRadial));
		int needleValue = -10;
		v.updateIncomingRadial(350);
		v.updateIncomingRadial(345);
		printResult("getNeedlePosition", needleValue, v.getNeedlePosition()); 
		assertEquals(needleValue, v.getNeedlePosition()); 
	}
	//************************************************************************
	/**
	 * printResult prints out "PASS" or "FAIL" based on the given test
	 * @param testName
	 * @param expected
	 * @param actual
	 */
	private void printResult(String testName, Object expected, Object actual) {
		// FORMATTING
		String nm = "%-40s";
		String exp = "%-30s";
		String act = "%-30s";
	    System.out.printf(nm + exp + act, testName, "[" + expected + "]", "[" + actual + "]");
		if (expected.equals(actual)) {
			System.out.println("[PASS]");
		} else {
			System.out.println("[FAIL]");
		}
	}
}
