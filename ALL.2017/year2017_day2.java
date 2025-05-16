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

class year2017_day2 {
	public static void main(String [] args) {
		out.println("		2017 Day2.1");
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

			Collections.sort(var_ints);
			int diff1 = var_ints.get(0) - var_ints.get(var_ints.size()-1); 
			tot += Math.abs(diff1);
			
		}
		out.print("**j_ans: ");
		out.println(tot);
	}
}
