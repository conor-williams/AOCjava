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


// pe.sort(Comparator.comparingInt((TreTuple t) -> (int)t.third).thenComparingInt((TreTuple t) -> (int)t.second).thenComparingInt((TreTuple t) -> (int)t.first));///tuples.sort(Comparator.comparingInt(tuple -> tuple[0]));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

@SuppressWarnings("unchecked")
class year2020_day13_2 {
	//	        public static int maxPath = 0;
	//       public static int sx = 0;
	//      public static int sy = 0;
	//     public static char grid [][] = new char[sy][sx];
	//    public static int already [][] = new int[sy][sx];

	public static void main(String [] args) {
		out.println("		2020 Day13.2");
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
		//		PrintStream originalOut = System.out;
		//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
		//	String firstpart = Pattern.quote("mul(");
		//Pattern p = Pattern.compile("(L|R)(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		int timeDepart = Integer.valueOf(blah.get(0));
		Vector <Tuple<Integer, Integer>> buses = new Vector<>();
		
		
		int maxbus = 0;
		int maxmin = 0;
		int vectpos = 0;
		int vposmin = 0;
		for (int i = 1; i < blah.size(); i++) {
			Scanner scanner = new Scanner(blah.get(i));
			scanner.useDelimiter(",");
			int pos = 0;
			
			while (scanner.hasNext()) {
				String ne = scanner.next();
				if (!ne.equals("x")) {
					
					buses.add(new Tuple<>(Integer.valueOf(ne), pos));

					if (Integer.valueOf(ne) > maxbus) {vposmin = vectpos; maxbus = Integer.valueOf(ne); maxmin = pos;}
					vectpos++;
				}
				pos++;
			}
		}

		long ddd = 1;
		long iii = 0;

		for (int qq = 0; qq < buses.size(); qq++) {
			int offset = buses.get(qq).second;
			long bus = buses.get(qq).first;

			while (true) {
				iii+=ddd;
				if ((iii+offset) %bus == 0) {

					ddd*=bus;
					/*
					out.println(ddd);
					out.println(iii);
					Scanner scanner = new Scanner(System.in); scanner.nextLine();
					*/
					break;
				}
			}
		}


		/*
		long ans = 0;
		int fir = maxbus;
		long bl = 100000000000000L;
		long di = (bl / fir);
		bl = di * fir;
		out.println(bl);
		out.println(bl % fir);
		buses.remove(vposmin);
		for (long qqq = bl; ; qqq+=fir) {
			int found = 0;
			
			for (int ii = 0; ii < buses.size(); ii++) {
				var tuple1 = buses.get(ii);
				if (((qqq+(tuple1.second-maxmin)) % tuple1.first) != 0) {
					found = 1;
					break;
				}
			}
			if (found == 0) {
				ans = qqq;
				break;
			}
		}
		*/


		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(iii);
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
		if (!this.first.equals(tu2.first)) {return false;}
		if (!this.second.equals(tu2.second)) {return false;}

		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

}
