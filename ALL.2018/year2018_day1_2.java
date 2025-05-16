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

class year2018_day1_2 {
	public static void main(String [] args) {
		out.println("		2018 Day1.2");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		Set <Integer> se = new HashSet<>();

		int tot = 0;
		se.add(0);
		for (int i = 0; i < blah.size(); i++) {
			int val = Integer.valueOf(blah.get(i));
			tot += val;
			if (se.contains((Integer)tot)) {
				out.print("**j_ans: ");
				out.println(tot);
				Runtime.getRuntime().halt(0);
			}

			se.add((Integer)tot);
			if (i == blah.size() -1) {i = -1;}
		}
	}
}

