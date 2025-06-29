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
import java.util.*;
import java.io.*;
import java.lang.*;
// /java -Xmx2g year2019_day3.java *i1.txt


///tuples.sort(Comparator.comparingInt(tuple -> tuple[0]));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
                                // System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

@SuppressWarnings("unchecked")
class year2015_day13 {
//	        public static int maxPath = 0;
 //       public static int sx = 0;
  //      public static int sy = 0;
   //     public static char grid [][] = new char[sy][sx];
    //    public static int already [][] = new int[sy][sx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2015 Day13.1");
		out.flush();
		Vector<String> blah = new Vector<>();
		//int leny = 0;
                //int lenx = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
                                //if (lenx == 0) {lenx = line.length();}
				blah.add(line);
				//leny++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
                /*sx = lenx;
                sy = leny;
                grid = new char[sy][sx];
                already = new int[sy][sx];
                for (int i = 0; i < blah.size();i++) {
                        grid[i] = blah.get(i).toCharArray();
                }
		*/

//		PrintStream originalOut = System.out;
//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("([A-Za-z]+) would (lose|gain) (\\d+) happiness units by sitting next to ([A-Za-z]+).");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		List <String> people = new ArrayList<>();
		Map <Tuple<String, String>, Integer> mp = new HashMap<>();

		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			if (!people.contains(m.group(1))) {
				people.add(m.group(1));
			}
			int hap = Integer.valueOf(m.group(3));
			if (m.group(2).equals("lose")) {
				hap = -hap;
			}
			Tuple tu1 = new Tuple(m.group(1), m.group(4));
			mp.put(tu1, hap);
		}

		List<String> peopleOrig = new ArrayList<>(people);

		int max = 0;
		do {
			int score = 0;
			List <String> peps = new ArrayList<>();
			peps.add(people.get(people.size()-1));
			peps.addAll(people);
			peps.add(people.get(0));
			for (int ii = 1; ii < peps.size()-1; ii++) {
				score += mp.get(new Tuple(peps.get(ii), peps.get(ii-1)))
				  + mp.get(new Tuple(peps.get(ii), peps.get(ii+1)));
			}
			if (score > max) {max = score;}
			nextPermutation(people);
		} while (!people.equals(peopleOrig));

//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(max);
		out.println("");
	}
        public static boolean nextPermutation(List<String> list) {
                int n = list.size();
                if (n <= 1) return false;

                // Step 1: Find the largest index `i` such that list[i] < list[i + 1]
                int i = n - 2;
                while (i >= 0 && list.get(i).compareTo(list.get(i + 1)) >=0) {
                        i--;
                }

                // If no such index exists, the list is in descending order (last permutation)
                if (i < 0) {
                        Collections.reverse(list);
                        return false;
                }

                // Step 2: Find the largest index `j` such that list[j] > list[i]
                int j = n - 1;
                while (list.get(j).compareTo(list.get(i)) <= 0) {
                        j--;
                }

                // Step 3: Swap elements at indices `i` and `j`
                Collections.swap(list, i, j);

                // Step 4: Reverse the sublist from `i + 1` to the end
                Collections.reverse(list.subList(i + 1, n));

                return true;
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
		if (!this.first.equals(tu2.first)) {return false;}
                if (!this.second.equals(tu2.second)) {return false;}

		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

}
