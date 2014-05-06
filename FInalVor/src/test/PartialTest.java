package test;

import vor.VorReceiver;


public class PartialTest {

	public void partialToFromTest() {
		// degrees go from 1 - 360, where 0 is also 360. program will automatically changed a 0 argument into 360
		try {	
			int incomingRadial = 270;
			String morse = "LCL";
			VorReceiver v = new VorReceiver(incomingRadial, morse);
			int obs = 360;
			
			/*
			System.err.println("360 or 0 degrees test...");
			incomingRadial = 91;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 90;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 89;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 269;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 270;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 271;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			*/
			
			/*
			obs = 45;
			System.err.println("Quadrant 1 test...");
			System.out.println("\n");
			incomingRadial = 136;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 135;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 134;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 314;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 315;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 316;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			*/
			
			/*
			obs = 90;
			System.err.println("90 degrees test...");
			System.out.println("\n");
			incomingRadial = 181;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 180;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 179;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial =  359;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 360;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 1;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			*/
			
			/*
			obs = 135;
			System.err.println("Quadrant 2 test...");
			System.out.println("\n");
			incomingRadial = 226;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 225;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 224;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial =  44;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 45;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 46;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			*/
			
			/*
			obs = 180;
			System.err.println("180 degrees test...");
			System.out.println("\n");
			incomingRadial = 271;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 270;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 269;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial =  89;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 90;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 91;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			*/
			
			/*
			obs = 225;
			System.err.println("Quadrant 3 test...");
			System.out.println("\n");
			incomingRadial = 316;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 315;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 314;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial =  134;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 135;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 136;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			*/
			
			/*
			obs = 270;
			System.err.println("270 degrees test...");
			System.out.println("\n");
			incomingRadial = 1;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 360;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 359;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial =  179;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 180;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 181;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			*/
			
			
			obs = 315;
			System.err.println("Quadrant 4 test...");
			System.out.println("\n");
			incomingRadial = 46;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 45;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 44;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial =  224;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 225;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			System.out.println("\n");
			incomingRadial = 226;
			v.updateIncomingRadial(incomingRadial);
			v.setOBS(obs);
			v.printVorStatus_v1();
			
			
		} catch (IllegalArgumentException e) {
			System.err.println("ERROR: OBS values must be within 1-360 degrees, where 360 can also be zero");
		}
	}

	
	public void partialNeedleTest() {
		try {
			int incomingRadial = 270;
			String morse = "LCL";
			VorReceiver v = new VorReceiver(incomingRadial, morse);
			int obs = 360;
			int start = 350;
			int end = 360;
			System.err.println("OBS @ 360 or 0 degrees test...");
			for (int i = start; i <= end; i++) {
				incomingRadial = i;
				v.updateIncomingRadial(incomingRadial);
				v.setOBS(obs);
				v.printVorStatus_v1();
				System.out.println();
			}

		} catch (IllegalArgumentException e) {
			System.err.println("ERROR: OBS values must be within 1-360 degrees, where 360 can also be zero");
		}
	}
}
