import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		//loop 
		while(true) {
			System.out.println("Enter OBS setting"); //enter 0 to exit 
			int obsNum = scan.nextInt();
			OBS obs1 = new OBS(obsNum);
			
			System.out.println("Station Identification: " + obs1.returnId());
			System.out.println("OBS Input: " + obsNum);
			if(obsNum == 0) {
				System.exit(0);
			}
			System.out.println("OBS Directional Reading:" + obs1.obsCal());
			if(obs1.calNeedle() == 0) {
				System.out.println("Needle: In Line. ");
			}
			else {
				System.out.println("Needle: Not in Line. ");
			}
			if(obs1.dirId() == true) {
				System.out.println("Direction: To.");
			}
			else {
				System.out.println("Direction: From.");
			}
			
		}
	}

}
