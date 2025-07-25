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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// /java -Xmx2g year2019_day3.java *i1.txt



//                        grid = Arrays.stream(gridTmp).map(char[]::clone).toArray(char[][]::new);
// pe.sort(Comparator.comparingInt((TreTuple t) -> (int)t.third).thenComparingInt((TreTuple t) -> (int)t.second).thenComparingInt((TreTuple t) -> (int)t.first));///tuples.sort(Comparator.comparingInt(tuple -> tuple[0]));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (var entry : mp.entrySet()) {
// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}
//// Arrays.stream(array).forEach(row -> Arrays.fill(row, 0));
@SuppressWarnings("unchecked")
class year2022_day19_2 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	//   public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	public static int maxmaxGeode = 0;
	public static int maxGeode = 0;
	public static String maxCombo = new String();
	public static int currentN = 0;
	public static long qualTot = 0;
	public static int maxPosition = 0;
	public static String CUR = new String();
	public static String CUR1 = new String();
	public static long ansOverall = 0;
	public static int ORE = 0;
	public static int CLAY = 1;
	public static int OBSIDIAN = 2;
	public static int GEODE = 3;
	public static int have[] = new int[5];
	public static int produce[] = new int[5];
	public static Deque<String> COM[] = new ArrayDeque[24];
	public static int minuteOrig[] = new int[24];
	public static int haveOrig[][] = new int[24][5];
	public static int produceOrig[][] = new int[24][5];
	public static int minute = 0;
	public static int havePOT[] = new int[5];
	public static Set<String> refset[] = new HashSet[24];
	public static Deque<String> combos[] = new ArrayDeque[24];
	public static int COMBOSLEN = 1;

	public static blue_s blue[] = new blue_s[100];
	public static String line1 = new String();
	public static String line2 = new String();
	public static void main(String [] args) {
		out.println("		2022 Day19.2");
		out.flush();
		Vector<String> blah = new Vector<>();
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
		if (false) {
			for (int i = 0; i < 24; i++) {
				COM[i] = new ArrayDeque<>();
				refset[i] = new HashSet<>();
				combos[i] = new ArrayDeque<>();
			}
			for (int i = 0; i < 100; i++) {
				blue[i] = new blue_s();
			}

			/*
			   grid = new char[leny][lenx];
			   already = new int[leny][lenx];
			   for (int i = 0; i < blah.size();i++) {
			   grid[i] = blah.get(i).toCharArray();
			   }
			   */


			//	String firstpart = Pattern.quote("mul(");
			Pattern p = Pattern.compile("Blueprint (\\d+): Each ore robot costs (\\d+) ore. Each clay robot costs (\\d+) ore. Each obsidian robot costs (\\d+) ore and (\\d+) clay. Each geode robot costs (\\d+) ore and (\\d+) obsidian.");

			//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
			//	m.find();
			//
			//	month= m.group(2);

			// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
			//BigInteger tot =  BigInteger.valueOf((long)0);
			//BigInteger one =  new BigInteger("1");
			//BigInteger zero =  new BigInteger("0");
			for (int i = 0; i < blah.size(); i++) {
				int num = 0;
				Matcher m = p.matcher(blah.get(i));
				m.find();
				num = Integer.valueOf(m.group(1));

				blue[i].costOreRobot[ORE] = Integer.valueOf(m.group(2));
				blue[i].costClayRobot[ORE] = Integer.valueOf(m.group(3));
				blue[i].costObsidianRobot[ORE] = Integer.valueOf(m.group(4));
				blue[i].costObsidianRobot[CLAY] = Integer.valueOf(m.group(5));
				blue[i].costGeodeRobot[ORE] = Integer.valueOf(m.group(6));
				blue[i].costGeodeRobot[OBSIDIAN] = Integer.valueOf(m.group(7));
				blue[i].num = num-1;

			}
			int MAINSTR = 1;
			for (int zz = 1; zz <= MAINSTR; zz++) {
				queueit(zz);
			}

			for (int i = 0; i < 24; i++) {
				COM[i] = combos[COMBOSLEN];
			}
			for (int n = 0; n < blah.size(); n++) {
				currentN = n; maxGeode = 0; maxCombo = new String();

				Iterator <String> it2 = combos[MAINSTR].iterator();
				while (it2.hasNext()) {
					botit(it2.next(), n, MAINSTR);
				}
				qualTot += (n+1) * maxGeode;
				ansOverall = qualTot;
			}

			//
			//		System.setOut(originalOut);
		}
		out.print("**j_ans: ");
		out.print(" TODO TODO");
		out.println("");
	}

	public static void queueit(int xyz) {
		for (long i = 0; i < Math.pow(4, xyz); i++) {
			/*
			   char var_s[30];
			   functoa(i, var_s);

			   char v2[60];
			   String s1 = new String();
			   s1 = "0" + xyz + "s";

			   String s2 = "%";
			   s2 += s1;
			   sprintf(v2, s2, var_s);
			   v2 = 
			   */

			String s78 = String.valueOf(i);
			if (!refset[xyz].contains(s78)) {
				combos[xyz].addLast(s78);
				refset[xyz].add(s78);
			}
			/*
			   if (var_s.length != xyz) {
			   reverseit(v2);
			   String s77 = new String(v2);
			   if (!refset[xyz].contains(s77)) {
			   combos[xyz].addLast(s77);
			   refset[xyz].add(s77);
			   }
			   }
			   */
		}

		List<String> list = new ArrayList<>(combos[xyz]);
		Collections.sort(list);
		combos[xyz].clear();
		combos[xyz].addAll(list);
	}
	public static class blue_s {
		int num;
		int costOreRobot[] = new int[5];
		int costClayRobot[] = new int[5];
		int costObsidianRobot[] = new int[5];
		int costGeodeRobot[] = new int[5];
	}
	/*
	   public static char digits[] = "01234";
	   public static int base = 4;
	   public static void functoa(int n, char s[]) {
	   int i = 0;
	   do {
	   s[i++] = digits[n % base];
	   } while ((n /= base) > 0);
	   s[i] = '\0';
	   }
	   public static void reverseit(char arr[])
	   {
	   int len= arr.length - 1;

	   for(int i=0; i<=len/2; i++)
	   {
	   char temp=arr[i];
	   arr[i]=arr[len-i];
	   arr[len-i]=temp;
	   }
	   }
	   */

	public static void botit(String str, int n, int END) {
		String strit;
		strit = new String(str);

		//robots
		have[ORE] = 1;
		have[CLAY] = 0;
		have[OBSIDIAN] = 0;
		have[GEODE] = 0;

		//the produce
		for (int i = 0; i < 5; i++) {
			produce[i] = 0;
		}
		for (int jj = 0; jj < 24; jj++) {
			for (int i = 0; i < 5; i++) {
				haveOrig[jj][i] = 0;
			}
		}
		//Robots
		haveOrig[0][ORE] = have[ORE]; haveOrig[0][CLAY] = have[CLAY];
		haveOrig[0][OBSIDIAN] = have[OBSIDIAN]; haveOrig[0][GEODE] = have[GEODE];

		// Produce
		for (int jj = 0; jj < 24; jj++) {
			for (int i = 0; i < 5; i++) {
				produceOrig[jj][i] = 0;
			}
		}
		produce[ORE] = 0;
		produce[CLAY] = 0;
		produce[OBSIDIAN] = 0;
		produce[GEODE] = 0;
		produceOrig[0][ORE] = produce[ORE]; produceOrig[0][CLAY] = produce[CLAY];
		produceOrig[0][OBSIDIAN] = produce[OBSIDIAN]; produceOrig[0][GEODE] = produce[GEODE];
		//minute - 14 ->  23
		minuteOrig[0] = 0;

		recur(1, n);
	}

	public static void recur(int pos, int n) {
		if (pos > 24) {out.println("ERR55"); Runtime.getRuntime().halt(0);}
		Iterator <String> it3 = COM[pos].iterator();

		while (it3.hasNext()) {
			String dd1 = it3.next();
			doit(minuteOrig[pos-1]+1, dd1, n, haveOrig[pos-1], produceOrig[pos-1]);
			if (minute >= 24) {continue;}
			haveOrig[pos][ORE] = have[ORE]; haveOrig[pos][CLAY] = have[CLAY];
			haveOrig[pos][OBSIDIAN] = have[OBSIDIAN]; haveOrig[pos][GEODE] = have[GEODE];
			produceOrig[pos][ORE] = produce[ORE]; produceOrig[pos][CLAY] = produce[CLAY];
			produceOrig[pos][OBSIDIAN] = produce[OBSIDIAN]; produceOrig[pos][GEODE] = produce[GEODE];
			minuteOrig[pos] = minute;
			recur(pos+1, n);
		}
	}

	public static void doit(int minuteOrig, String stritIn, int n, int haveSav[], int produceSav[])
	{
		String strit = new String(stritIn);
		have[ORE] = haveSav[ORE]; have[CLAY] = haveSav[CLAY];
		have[OBSIDIAN] = haveSav[OBSIDIAN]; have[GEODE] = haveSav[GEODE];

		produce[ORE] = produceSav[ORE]; produce[CLAY] = produceSav[CLAY];
		produce[OBSIDIAN] = produceSav[OBSIDIAN]; produce[GEODE] = produceSav[GEODE];
		int position = 0;
		char toBe = strit.charAt(position);
		int END = COMBOSLEN;

		{
			int haveCheck[] = new int[5], produceCheck[] = new int[5], havePOTCheck[] = new int[5];
			haveCheck[ORE] = haveSav[ORE]; haveCheck[CLAY] = haveSav[CLAY];
			haveCheck[OBSIDIAN] = haveSav[OBSIDIAN]; haveCheck[GEODE] = haveSav[GEODE];

			produceCheck[ORE] = produceSav[ORE]; produceCheck[CLAY] = produceSav[CLAY];
			produceCheck[OBSIDIAN] = produceSav[OBSIDIAN]; produceCheck[GEODE] = produceSav[GEODE];

			int positionCheck = position;
			int ENDCheck = 24;

			for (int minuCheck=minuteOrig; minuCheck <= 24; minuCheck++) {
				if (positionCheck < ENDCheck && minuCheck < 24) {
					havePOTCheck[GEODE]++;
					positionCheck++;
				}
				for (int i = 0; i < 4; i++) {//harvest
					produceCheck[i] += haveCheck[i];
				}
				for (int i = 0; i < 4; i++) {//move the POT over to have
					haveCheck[i] += havePOTCheck[i];
					havePOTCheck[i] = 0;
				}
			}
			if (produceCheck[GEODE] <= maxGeode) {minute=25; return;}
		}

		for (minute=minuteOrig; minute <= 24; minute++) {
			if (position < END && minute < 24) {
				switch(toBe) {
					case '0': //ORE
						if (produce[ORE] >= blue[n].costOreRobot[ORE]) {
							havePOT[ORE]++;
							produce[ORE] -= blue[n].costOreRobot[ORE];
							if (position + 1 < END) {
								++position;
								toBe = strit.charAt(position);
							} else {
								position++;
							}
						} 
						break;
					case '1': //CLAY
						if (produce[ORE] >= blue[n].costClayRobot[ORE]) {
							havePOT[CLAY]++;
							produce[ORE] -= blue[n].costClayRobot[ORE];
							if (position + 1 < END) {
								++position;
								toBe = strit.charAt(position);
							} else {
								position++;
							}
							//changed = 1;
						} 
						break;
					case '2': //OBSIDIAN
						if (produce[ORE] >= blue[n].costObsidianRobot[ORE] && 
								produce[CLAY] >= blue[n].costObsidianRobot[CLAY]) {
							havePOT[OBSIDIAN]++;
							produce[ORE] -= blue[n].costObsidianRobot[ORE];
							produce[CLAY] -= blue[n].costObsidianRobot[CLAY];
							if (position + 1 < END) {
								++position;
								toBe = strit.charAt(++position);
							} else {
								position++;
							}
								} 
						break;
					case '3': //GEODE
						if (produce[ORE] >= blue[n].costGeodeRobot[ORE] && 
								produce[OBSIDIAN] >= blue[n].costGeodeRobot[OBSIDIAN]) {
							havePOT[GEODE]++;
							produce[ORE] -= blue[n].costGeodeRobot[ORE];
							produce[OBSIDIAN] -= blue[n].costGeodeRobot[OBSIDIAN];
							if (position + 1 < END) {
								++position;
								toBe = strit.charAt(position);

							} else {
								position++;
							}
								} 
						break;
				}
			}
			for (int i = 0; i < 4; i++) {//harvest
				produce[i] += have[i]; 
			}
			for (int i = 0; i < 4; i++) {//move the POT over to have
				have[i] += havePOT[i];
				havePOT[i] = 0;
			}
			if (position == COMBOSLEN) {break;}
		}
		if (produce[GEODE] > maxGeode) {maxGeode = produce[GEODE];} 
	}



}


class Tuple<X,Y > {
	public X first;
	public Y second;

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

@SuppressWarnings("unchecked")
class TreTuple<X,Y, Z> {
	public X first;
	public Y second;
	public Z third;

	public TreTuple(Object o) {
		TreTuple tu2 = (TreTuple) o;
		this.first = (X)tu2.first;
		this.second = (Y)tu2.second;
		this.third = (Z)tu2.third;
	}
	public TreTuple(X first, Y second, Z third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}
	@Override
	public boolean equals(Object o) {
		TreTuple tu2 = (TreTuple) o;
		if (this == o) return true;
		if (!(o instanceof TreTuple)) return false;
		if (!this.first.equals(tu2.first)) {return false;}
		if (!this.second.equals(tu2.second)) {return false;}
		if (!this.third.equals(tu2.third)) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second, third);
	}

}

@SuppressWarnings("unchecked")
class QuadTuple<X,Y, Z, W> {
	public X first;
	public Y second;
	public Z third;
	public W fourth;

	public QuadTuple(X first, Y second, Z third, W fourth) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
	}
	@Override
	public boolean equals(Object o) {
		QuadTuple tu2 = (QuadTuple) o;
		if (this == o) return true;
		if (!(o instanceof QuadTuple)) return false;

		if (!first.equals(tu2.first)) {return false;}
		if (!second.equals(tu2.second)) {return false;}
		if (!third.equals(tu2.third)) {return false;}
		if (!fourth.equals(tu2.fourth)) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second, third, fourth);
	}

}

@SuppressWarnings("unchecked")
class CinqTuple<X,Y, Z, V, W> {
	public X first;
	public Y second;
	public Z third;
	public V fourth;
	public W fifth;

	public CinqTuple(X first, Y second, Z third, V fourth, W fifth) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
		this.fifth = fifth;
	}
	@Override
	public boolean equals(Object o) {
		CinqTuple tu2 = (CinqTuple) o;
		if (this == o) return true;
		if (!(o instanceof CinqTuple)) return false;

		if (!first.equals(tu2.first)) {return false;}
		if (!second.equals(tu2.second)) {return false;}
		if (!third.equals(tu2.third)) {return false;}
		if (!fourth.equals(tu2.fourth)) {return false;}
		if (!fifth.equals(tu2.fifth)) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second, third, fourth, fifth);
	}

}

