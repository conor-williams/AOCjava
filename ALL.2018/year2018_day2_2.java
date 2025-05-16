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

class year2018_day2_2 {
	public static void main(String [] args) {
		out.println("		2018 Day2.2");
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
			String one = blah.get(i);
			for (int j = 0; j < blah.size(); j++) {
				if (i == j) {continue;}
				String two = blah.get(j);

				//comp one V two
				int len = one.length();
				int differing = 0;
				int differingPos = 0;
				for (int ii = 0; ii < len; ii++) {
					if (one.charAt(ii) != two.charAt(ii)) {
						differing++;
						differingPos = ii;
					}
					if (differing > 1) {break;}
				}
				if (differing == 1) {
					out.print("**j_ans: ");
					for (int ii = 0; ii < len; ii++) {
						if (ii != differingPos) {
							out.print(one.charAt(ii));
						}
					}
					out.println("");
					Runtime.getRuntime().halt(0);
				}
			}
		}

	}
}
