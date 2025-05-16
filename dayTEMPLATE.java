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


class year2016_day2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2016 Day2.1");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		//	String firstpart = Pattern.quote("mul(");
		//Pattern p = Pattern.compile("(L|R)(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		int x = 1;
		int y = 1;
		int [] ar = new int[blah.size()];
		for (int i = 0; i < blah.size(); i++) {
			/*
			   Scanner scanner = new Scanner(blah.get(i));
			   scanner.useDelimiter("[\t\\s ]");
			   Vector <Integer> var_ints = new Vector<>();
			   while (scanner.hasNext()) {
			   String ne = scanner.next();
			   var_ints.add(Integer.valueOf(ne));
			   }
			   Collections.sort(var_ints);
			   Map<String, Integer> m = new HashMap<>();

			// Adding key-value pairs to the map
			m.put("Geek1", 1);

*/

			String ne = blah.get(i);
			for (int ii = 0; ii < ne.length(); ii++) { 
				switch (ne.charAt(ii)) {
					case 'U':
						if (y == 0) {continue;}
						y--;
						break;
					case 'R':
						if (x == 2) {continue;}
						x++;
						break;
					case 'D':
						if (y == 2) {continue;}
						y++;
						break;
					case 'L':
						if (x == 0) {continue;}
						x--;
						break;
				}

			}
			ar[i] = keypad[y][x];
			//	out.println(ar[i]);

		}
		out.print("**j_ans: ");
		for (int num: ar) {
			out.print(num);
		}
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

