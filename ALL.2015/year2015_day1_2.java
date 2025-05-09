import java.math.BigInteger;
import java.util.Scanner;
import java.util.Arrays;
import java.math.BigInteger;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

class year2015_day1_2 {
	public static void main(String [] args) {
		System.out.println("		2015 Day1.2");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		int level = 0;
		int posB = 0;
outerLoop:
		for (int i = 0; i < blah.size(); i++) {
			for (int z = 0; z < blah.get(i).length(); z++) {
				if (blah.get(i).charAt(z) == '(') {
					level++;
				} else {
					level--;
				}
				if (level == -1) {
					posB = z+1;
					break outerLoop;
				}
			}
		}
		System.out.print("**j_ans: ");
		System.out.println(posB);
					
	}
}
