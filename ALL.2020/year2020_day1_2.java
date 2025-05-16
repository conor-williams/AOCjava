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

class year2020_day1_2 {
	public static void main(String [] args) {
		out.println("		2020 Day1.2");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		for (int i = 0; i < blah.size(); i++) {
			int val1 = Integer.valueOf(blah.get(i));
			for (int j = 0; j < blah.size(); j++) {
				int val2 = Integer.valueOf(blah.get(j));
				for (int k = 0; k < blah.size(); k++) {
					int val3 = Integer.valueOf(blah.get(k));
					int sum1 = val1 + val2 + val3;
					if (sum1 == 2020) {
						out.print("**j_ans: ");
						out.println(val1*val2*val3);
						Runtime.getRuntime().halt(0);
					}
				}
			}

		}
	}
}

