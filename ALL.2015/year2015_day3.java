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


class year2015_day3 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2015 Day3.1");
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

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		Map <Tuple <Integer, Integer>, Integer> mp = new HashMap<>();
		int houseCount = 0;
		for (int i = 0; i < blah.size(); i++) {
			/*
			   Scanner scanner = new Scanner(blah.get(i));
			   scanner.useDelimiter("[\t\\s ]+");
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
			int x = 0;
			int y = 0;
			Tuple <Integer, Integer> tu = new Tuple<>(x, y);
			mp.put(tu, 1);
			for (int ii = 0; ii < ne.length(); ii++) { 
				switch (ne.charAt(ii)) {
					case '^':
						y--;
						break;
					case '>':
						x++;
						break;
					case 'v':
						y++;
						break;
					case '<':
						x--;
						break;
				}
				Tuple <Integer, Integer> tu2 = new Tuple<>(x, y);
				if (!mp.containsKey(tu2)) {
					//houseCount++;
					mp.put(tu2, 1);
				} else {
					mp.put(tu2, mp.get(tu2)+1);
				}
			}
		}
		out.print("**j_ans: ");
		out.print(mp.size());
		out.println("");
	}
}

public class Tuple<X,Y > {
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
		/*
		int fir1 = (int)first;
		int fir2 = (int)tu2.first;
		int sec1 = (int)second;
		int sec2 = (int)tu2.second;
		*/
		if (!first.equals(tu2.first)) {return false;}
		if (!second.equals(tu2.second)) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

}

