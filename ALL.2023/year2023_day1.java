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


class year2023_day1 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2023 Day1.1");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		//Pattern p = Pattern.compile("(L|R)(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//	month= m.group(2);

		//BigInteger tot =  BigInteger.valueOf((long)0);
		int tot = 0;
		for (int i = 0; i < blah.size(); i++) {
			String ne = blah.get(i);
			StringBuilder ans = new StringBuilder("00");
			for (int ii = 0; ii < ne.length(); ii++) { 
				if (Character.isDigit(ne.charAt(ii))) {
					ans.setCharAt(0, ne.charAt(ii));
					break;
				}
			}
			for (int ii = ne.length()-1; ii >= 0; ii--) { 
				if (Character.isDigit(ne.charAt(ii))) {
					ans.setCharAt(1, ne.charAt(ii));
					break;
				}
			}
			tot += (int)Integer.valueOf(ans.toString());
			
		}
		out.print("**j_ans: ");
		out.print(tot);
		out.println("");
	}
}
