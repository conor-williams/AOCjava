import java.math.BigInteger;
import java.util.Scanner;
import java.util.Arrays;
import java.math.BigInteger;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import static java.lang.System.out;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Objects;
import java.util.Collections;
import java.lang.Character;
import java.util.HashMap;
import java.util.Map;
// /java -Xmx2g year2019_day3.java *i1.txt


class year2021_day3 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2021 Day3.1");
		Vector<String> blah = new Vector<>();
		int leny = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
			leny++;
		} catch (IOException e) {
			e.printStackTrace();
		}	

		//Pattern p = Pattern.compile("(L|R)(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		char [] digitsG = new char[20];
		char [] digitsE = new char[20];
		int iii = 0;
		for (iii = 0; iii < blah.get(0).length(); iii++) {
			int count0 = 0;
			int count1 = 1;
			for (int i = 0; i < blah.size(); i++) {
				String ne = blah.get(i);
				if (ne.charAt(iii) == '0') {
					count0++;
				} else {
					count1++;
				}
			}
			if (count0 > count1) {
				digitsG[iii] = '0';
				digitsE[iii] = '1';
			} else {
				digitsE[iii] = '0';
				digitsG[iii] = '1';
			}

		}
		//binaryString = binaryString.replaceAll("[^01]", ""); // Remove invalid characters
		
		/*
		String binaryString = "10110";
if (!binaryString.isEmpty()) {
    int number = Integer.parseInt(binaryString, 2);
    System.out.println("Parsed number: " + number);
} else {
    System.out.println("No valid binary digits found.");
}
*/
		//digitsG[iii] = '\0';
		//String dG = new String(digitsG, 0, iii); out.println("["+dG+"]");
		//String dGs = Integer.toString(Integer.parseInt(new String(digitsG), 2), 10);
		String dGs = Integer.toString(Integer.parseInt(new String(digitsG, 0, iii), 2), 10);
		//out.println(dGs);

		//digitsE[iii] = '\0'; String dE = new String(digitsE, 0, iii); out.println("["+dE+"]"); int decE = 
		String dEs = Integer.toString(Integer.parseInt(new String(digitsE, 0, iii), 2), 10);

		int ans = Integer.valueOf(dGs) * Integer.valueOf(dEs);
		out.print("**j_ans: ");
		out.print(ans);
		out.println("");
	}
}

class Tuple<X,Y > {
	public final X first;
	public final Y second;

	public Tuple(X first, Y second) {
		this.first = first;
		this.second = second;
	}
	@Override
	public boolean equals(Object o) {
		Tuple tu2 = (Tuple) o;
		if (this == o) return true;
		if (!(o instanceof Tuple)) return false;
		int fir1 = (int)first;
		int fir2 = (int)tu2.first;
		int sec1 = (int)second;
		int sec2 = (int)tu2.second;
		if (fir1 != fir2) {return false;}
		if (sec1 != sec2) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

}

