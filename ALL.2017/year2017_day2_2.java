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

class year2017_day2_2 {
	public static void main(String [] args) {
		out.println("		2017 Day2.2");
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
		for (int i = 0; i < blah.size(); i++) {
                        Scanner scanner = new Scanner(blah.get(i));
                        scanner.useDelimiter("[\t ]");
			Vector <Integer> var_ints = new Vector<>();
                        while (scanner.hasNext()) {
                                String ne = scanner.next();
				var_ints.add(Integer.valueOf(ne));
			}

			//Collections.sort(var_ints);
			//int diff1 = var_ints.get(0) - var_ints.get(var_ints.size()-1); 

after:
			for (int ii = 0; ii < var_ints.size(); ii++) {
				for (int jj = 0; jj < var_ints.size(); jj++) {
					if (ii == jj) {continue;}
					int i1 = var_ints.get(ii);
					int i2 = var_ints.get(jj);
					if (i1 == 0) {continue;}
					if (i2 == 0) {continue;}
					if (i1 > i2) {
						if (i1 % i2 == 0) {tot+= i1/i2; break after;}
					} else {
						if (i2 % i1 == 0) {tot+= i2/i1; break after;}
					}
				}
			}
		}
		out.print("**j_ans: ");
		out.println(tot);
	}
}
