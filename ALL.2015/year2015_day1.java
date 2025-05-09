import java.math.BigInteger;
import java.util.Scanner;
import java.util.Arrays;
import java.math.BigInteger;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

class year2015_day1 {
	public static void main(String [] args) {
		System.out.println("		2015 Day1.1");
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
		for (int i = 0; i < blah.size(); i++) {
			for (int z = blah.get(i).length() -1; z >= 0; z--) {
				if (blah.get(i).charAt(z) == '(') {
					level++;
				} else {
					level--;
				}
			}
		}
		System.out.print("**j_ans: ");
		System.out.println(level);
					
	}
}
