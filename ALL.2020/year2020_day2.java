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

class year2020_day2 {
	public static void main(String [] args) {
		out.println("		2020 Day2.1");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		Pattern p = Pattern.compile("(\\d+)-(\\d+) ([a-z]): ([a-z]+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	month= m.group(2);

		//BigInteger tot =  BigInteger.valueOf((long)0);
		int goodPassword = 0;
		for (int i = 0; i < blah.size(); i++) {
			String ne = blah.get(i);
			Matcher m = p.matcher(ne);
			m.find();
			int least = Integer.valueOf(m.group(1));
			int most = Integer.valueOf(m.group(2));
			char ov = m.group(3).charAt(0);
			String check = m.group(4);

			int count = 0;
			for (int ii = 0; ii < check.length(); ii++) { 
				if (check.charAt(ii) == ov) {
					count++;
				}

			}
			if (count >= least && count <= most) {
				goodPassword++;
			}
			
		}
		out.print("**j_ans: ");
		out.print(goodPassword);
		out.println("");
	}
}
