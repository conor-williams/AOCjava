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


class year2024_day2 {
	public static void main(String [] args) {
		out.println("		2024 Day2.1");
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
		int count = 0;
		for (int i = 0; i < blah.size(); i++) {
			Scanner scanner = new Scanner(blah.get(i));
			scanner.useDelimiter("[\t\\s ]+");
			Vector <Integer> var_ints = new Vector<>();
			while (scanner.hasNext()) {
				Integer ne = Integer.valueOf(scanner.next());
				var_ints.add(ne);
			}

			int bad = 0;
			int none = 0;
			int dec = 0;
			int inc = 0;
after:
			
			for (int ii = 0; ii < var_ints.size()-1; ii++) { 
				if (var_ints.get(ii) == var_ints.get(ii+1)) {
					bad = 1;
					break after;
				}
				if (none == 0 && var_ints.get(0) > var_ints.get(1)) {
					none = 1;
					inc = 1;
				}
				if (none == 0 && var_ints.get(0) < var_ints.get(1)) {
					none = 1;
					dec = 1;
				}

				if (inc == 1 && var_ints.get(ii) > var_ints.get(ii+1)) {
					int diff1 = var_ints.get(ii) - var_ints.get(ii+1);
					if (diff1 >= 1 && diff1 <= 3) {
						//ok
					} else {
						bad = 1;
						break after;
					}
				} else if (inc == 1 && var_ints.get(ii) < var_ints.get(ii+1)) {
					bad = 1;
			       		break after;	       
				}

				if (dec == 1 && var_ints.get(ii) < var_ints.get(ii+1)) {
					int diff1 = var_ints.get(ii+1) - var_ints.get(ii);
					if (diff1 >= 1 && diff1 <= 3) {
						//ok
					} else {
						bad = 1;
						break after;
					}
				} else if (dec == 1 && var_ints.get(ii) > var_ints.get(ii+1)) {
					bad = 1;
					break after;
				}
			}
			if (bad == 0) {
				count++;
			}

		}
		out.print("**j_ans: ");
		out.print(count);
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

