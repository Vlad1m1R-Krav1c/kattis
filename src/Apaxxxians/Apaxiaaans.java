package apaxiaaans;
import java.util.Scanner;
public class Apaxiaaans {
	
	public static String removeDuplicateCharacters(String inputName) {
	
		StringBuilder outputName = new StringBuilder();
		outputName.append(inputName.charAt(0));
		
		for (int i = 1; i < inputName.length(); i++) {
			if (inputName.charAt(i) == outputName.charAt(outputName.length() - 1)) {
				continue; 
			}
			else {
				outputName.append(inputName.charAt(i));
			}
		}
		return outputName.toString();
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String inputName = scanner.next();
 	
		System.out.println("Input apaxiaaan name: "+inputName);
		System.out.println("Compacted apaxiaaan name: "+removeDuplicateCharacters(inputName));
		scanner.close();
		
		
}
}
