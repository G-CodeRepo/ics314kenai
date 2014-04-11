
public class TestVor {

	public static void main(String[] args) {
		vorPrototype test = new vorPrototype(4, "-..", true);
		test.morseCodeIdentifier("--.. .- .- ");
		System.out.println(test.getmc1());
		System.out.println(test.getmc2());

	}

}
