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
// /java -Xmx2g year2019_day3.java *i1.txt


class year2020_day4_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2020 Day4.2");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("([a-z]{3}):([a-z\\d#]+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		int qq = 0;
		Vector <Map <String, String>> ve = new Vector<>();
		Map <String, String> mp = new HashMap<>();		
		for (int i = 0; i < blah.size(); i++) {
			if (blah.get(i).length() == 0) {
				Map<String, String> clonedMap = new HashMap<>(mp);
				ve.add(clonedMap);
				mp.clear();
			} else {
				Scanner scanner = new Scanner(blah.get(i));
				scanner.useDelimiter("[\t\\s ]");
			  	//Vector <String> var_strs = new Vector<>();
			   	while (scanner.hasNext()) {
					String ne = scanner.next();
					Matcher m = p.matcher(ne);
					m.find();
					String tag = m.group(1);
					if (tag.equals("cid")) {
					} else {
						String val = m.group(2);
				   		mp.put(tag, val); 
					}
						
				}
			}
		}
		Map<String, String> clonedMap2 = new HashMap<>(mp);
		ve.add(clonedMap2);
		mp.clear();
		int tot = 0;
		for (int ii = 0; ii < ve.size(); ii++) {
			Map <String, String> mp2 = ve.get(ii);
			int valid = 1;
			if (mp2.size() != 7) {
				continue;
			}
after:
			for (Map.Entry<String, String> entry : mp2.entrySet()) {
				String kee = entry.getKey();
				String vval = entry.getValue();
				
				if (kee.equals("byr")) {
					if (vval.length() != 4) {
						valid = 0;
						break after;
					}
					for (int kk = 0; kk < vval.length(); kk++) {
						if (Character.isDigit(vval.charAt(kk))) { 
						} else {
							valid = 0;
							break after;
						}
					}
					int numer = Integer.valueOf(vval);
					if (numer >= 1920 && numer <= 2002) {
					} else {
						valid = 0;
						break after;
					}
				} else if (kee.equals("hgt")) {
					int cm = 0;
					int in = 0;
					if (vval.length() <= 3) {
						valid = 0;
						break after;
					}

					if (vval.charAt(vval.length()-1) == 'm' &&
							vval.charAt(vval.length()-2) == 'c') {
						//ok
						cm = 1;
					} else if (vval.charAt(vval.length()-1) == 'n' &&
								vval.charAt(vval.length()-2) == 'i') {
						//ok
						in = 1;
					} else {
						valid = 0;
						break after;
					}
					String height = vval.substring(0, vval.length()-2);
					int he = Integer.valueOf(height);
					if (cm == 1) {
						if (he >= 150 && he <= 193) {
							//ok
						} else {
							valid = 0;
							break after;
						}
					} else if (in == 1) {
						if (he >= 59 && he <= 76) {
							//ok
						} else {
							valid = 0;
							break after;
						}
					}
				} else if (kee.equals("hcl")) {
					if (vval.charAt(0) != '#') {
						valid = 0;
						break after;
					}
					String rest = vval.substring(1, vval.length()-1);
					for (int kk = 0; kk < rest.length(); kk++) {
						if (Character.isDigit(rest.charAt(kk))) {
							//ok
						} else if (rest.charAt(kk) >= 'a' &&
								rest.charAt(kk) <= 'f') {
							//ok
						} else {
							valid = 0;
							break after;
						}
					}
				} else if (kee.equals("ecl")) {
					if (vval.equals("amb") || vval.equals("blu") ||
						vval.equals("brn") || vval.equals("gry") ||
					       	vval.equals("grn") || vval.equals("hzl") ||
					       	vval.equals("oth")) {
						//ok
					} else {
						valid = 0;
						break after;
					}
				} else if (kee.equals("pid")) {
					if (vval.length() != 9) {
						valid = 0;
						break after;
					}
					for (int kk = 0; kk < vval.length(); kk++) {
						if (Character.isDigit(vval.charAt(kk))) {
							//ok
						} else {
							valid = 0;
							break after;
						}
					}
				} else if (kee.equals("iyr")) {
					if (vval.length() != 4) {
						valid = 0;
						break after;
					}
					for (int kk = 0; kk < vval.length(); kk++) {
						if (Character.isDigit(vval.charAt(kk))) { 
						} else {
							valid = 0;
							break after;
						}
					}
					int numer = Integer.valueOf(vval);
					if (numer >= 2010 && numer <= 2020) {
					} else {
						valid = 0;
						break after;
					}
				} else if (kee.equals("eyr")) {
					if (vval.length() != 4) {
						valid = 0;
						break after;
					}
					for (int kk = 0; kk < vval.length(); kk++) {
						if (Character.isDigit(vval.charAt(kk))) { 
						} else {
							valid = 0;
							break after;
						}
					}
					int numer = Integer.valueOf(vval);
					if (numer >= 2020 && numer <= 2030) {
					} else {
						valid = 0;
						break after;
					}
				}
			}
			if (valid == 1) {
				tot++;
			}

		}
		out.print("**j_ans: ");
		out.print(tot);
		out.println("");
	}
}

public class Tuple<X,Y > {
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

//System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
