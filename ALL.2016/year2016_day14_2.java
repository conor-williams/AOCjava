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


class year2016_day14_2 {
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
		out.println("		2016 Day14.2");
		out.println("	SLOW ~1minute");
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
		String regex = "([a-z\\d])\\1\\1";
		//String regex2 = "([0-9a-f]{5})";
		Pattern p = Pattern.compile(regex);
		int keyNum = 0;
		int sixtyfourthkeyindex = 0;
		String mdmdmd[] = new String[40000];
		for (int iii = 0; iii < 40000; iii++) {
			mdmdmd[iii] = new String("");
		}
after:
		do {
			String md5Hash = "";
			if (mdmdmd[i].equals("")) {
				String an = Integer.toString(i);
				String input = in + an;
				md5Hash = generateMD5(input);
				for (int kkk = 0; kkk < 2016; kkk++) {
					md5Hash = generateMD5(md5Hash);
				}
				mdmdmd[i] = new String(md5Hash);
			} else {
				md5Hash = new String(mdmdmd[i]);
			}
			
			Matcher m = p.matcher(md5Hash);
			if (m.find()) {
				char threeOf = m.group(1).charAt(0);
				String regex2 = "(" + threeOf + "){5}";
				Pattern p2 = Pattern.compile(regex2);

				//out.println(md5Hash); out.println(threeOf);

				for (int ii = i+1; ii < i+1+1000; ii++) {
					String md5Hash2 = "";
					if (mdmdmd[ii].equals("")) {
						String an2 = Integer.toString(ii);
						String input2 = in + an2;
						md5Hash2 = generateMD5(input2);
						for (int kkk = 0; kkk < 2016; kkk++) {
							md5Hash2 = generateMD5(md5Hash2);
						}
						mdmdmd[ii] = new String(md5Hash2);
					} else {
						md5Hash2 = new String(mdmdmd[ii]);
					}
					Matcher m2 = p2.matcher(md5Hash2);
					if (m2.find()) {
						//out.println(md5Hash2);
						//break after;
						keyNum++;
						if (keyNum == 64) {
							sixtyfourthkeyindex = i;
							/*
							out.println(i);
							out.println(ii);
							out.println(md5Hash);
							out.println(mdmdmd[i]);
							out.println(mdmdmd[ii]);
							out.println(md5Hash2);
							*/
							break after;
						}
						break;
					}
				}
			}
			i++;
		} while (true);

		out.print("**j_ans: ");
		out.print(sixtyfourthkeyindex);
		out.println("");
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

