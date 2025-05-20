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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
// /java -Xmx2g year2019_day3.java *i1.txt


class year2016_day5_2 {
	public static String generateMD5(String input) {
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");

			// Compute the hash
			byte[] hashBytes = md.digest(input.getBytes());

			// Convert byte array to hexadecimal string
			StringBuilder hexString = new StringBuilder();
			for (byte b : hashBytes) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5 algorithm not found", e);
		}
	}
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2015 Day5.2");
		out.println("	SLOW ~25seconds");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		////////////////////

		String in = blah.get(0); 
		int i = 0;
		char [] ans = new char[10];
		int times = 0;
		Vector <Integer> tried = new Vector<>();
		do {
			//String an = Integer.toHexString(i).toUpperCase();
			String an = Integer.toString(i);
			String input = in + an;
			String md5Hash = generateMD5(input);
			int found = 0;
			for (int kk = 0; kk < 5; kk++) {
				if (md5Hash.charAt(kk) != '0') {
					found = 1;
					break;
				}
			}
after:
			if (found == 0) {
				int pos = md5Hash.charAt(5) - '0';
				if (pos >= 0 && pos <= 7) {
					if (tried.contains(pos)) {break after;}
					//ok
				} else {break after;}
				tried.add(pos);
				ans[pos] = md5Hash.charAt(6);
				//out.print(pos);  out.print(": "); out.println(ans[pos]);
				times++;
				if (times == 8) {break;}

			}
			i++;
		} while (1 == 1);

		out.print("**j_ans: ");
		out.print(new String(ans, 0, 8));
		out.println("");
		///////////////////
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

