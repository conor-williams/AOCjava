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

class year2019_day1_2 {
	public static void main(String [] args) {
		out.println("		2019 Day1.2");
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
			int val = Integer.valueOf(blah.get(i));
			while (true) {
				val = (val / 3) - 2;
				if (val >= 0) {
					tot += val;
				} else {
					break;
				}
			}
		}
		out.print("**j_ans: ");
                out.println(tot);

	}
}

