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


class year2018_day3_2 {
	public static int sz = 3000;
	public static int [][] grid = new int[sz][sz];
	public static void main(String [] args) {
		out.println("		2018 Day3.2");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		Pattern p = Pattern.compile("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			int id = Integer.valueOf(m.group(1));
			int left = Integer.valueOf(m.group(2));
			int top = Integer.valueOf(m.group(3));
			int width = Integer.valueOf(m.group(4));
			int height = Integer.valueOf(m.group(5));

			for (int xx = 0; xx < width; xx++) {
				for (int yy = 0; yy < height; yy++) {
					grid[yy+top][xx+left]++;
				}
			}
		}
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			int id = Integer.valueOf(m.group(1));
			int left = Integer.valueOf(m.group(2));
			int top = Integer.valueOf(m.group(3));
			int width = Integer.valueOf(m.group(4));
			int height = Integer.valueOf(m.group(5));

			int found = 0;
after:
			for (int xx = 0; xx < width; xx++) {
				for (int yy = 0; yy < height; yy++) {
					if (grid[yy+top][xx+left] != 1) {
						found = 1;
						break after;
					}
				}
			}
			if (found == 0) {
				out.print("**j_ans: ");
				out.print(id);
				out.println("");
				Runtime.getRuntime().halt(0);
			}

		}
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

