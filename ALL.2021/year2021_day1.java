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

class year2021_day1 {
	public static void main(String [] args) {
		out.println("		2021 Day1.1");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		int tot = 0;
		int prevVal = Integer.valueOf(blah.get(0));
		for (int i = 1; i < blah.size(); i++) {
			int val1 = Integer.valueOf(blah.get(i));

			if (val1 > prevVal) {tot++;}
			prevVal = val1;
		}
		out.print("**j_ans: ");
		out.print(tot);
		out.println();
	}
}

