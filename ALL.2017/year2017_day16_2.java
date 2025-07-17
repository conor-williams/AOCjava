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


//                        grid = Arrays.stream(gridTmp).map(char[]::clone).toArray(char[][]::new);
// pe.sort(Comparator.comparingInt((TreTuple t) -> (int)t.third).thenComparingInt((TreTuple t) -> (int)t.second).thenComparingInt((TreTuple t) -> (int)t.first));///tuples.sort(Comparator.comparingInt(tuple -> tuple[0]));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (var entry : mp.entrySet()) {
// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

@SuppressWarnings("unchecked")
class year2017_day16_2 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	//   public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	public static Vector <dance> dan = new Vector<>(); 
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static PrintStream originalOut;
	public static void main(String [] args) {
		out.println("		2017 Day16.2");
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
		originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
		/*
		   grid = new char[leny][lenx];
		   already = new int[leny][lenx];
		   for (int i = 0; i < blah.size();i++) {
		   grid[i] = blah.get(i).toCharArray();
		   }
		   */


		//	String firstpart = Pattern.quote("mul(");
		Pattern p1 = Pattern.compile("s(\\d+)");
		Pattern p2 = Pattern.compile("x(\\d+)/(\\d+)");
		Pattern p3 = Pattern.compile("p([a-z])/([a-z])");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		StringBuilder sb = new StringBuilder("abcdefghijklmnop");
		StringBuilder altersb = new StringBuilder(sb);
		//StringBuilder sb = new StringBuilder("abcde");
		int len = sb.length();

		int count = 0;
		for (int i = 0; i < blah.size(); i++) {
			Scanner scanner = new Scanner(blah.get(i));
			scanner.useDelimiter("[,]");
		
			while (scanner.hasNext()) {
				String ne = scanner.next();
				char ch1, ch2;
				int ind1, ind2, num1;
				dance danTmp = new dance();

				switch (ne.charAt(0)) {
					case 's':
						Matcher m1 = p1.matcher(ne);
						m1.find();
						num1 = Integer.valueOf(m1.group(1));
						danTmp.num1 = num1;
						danTmp.type = 's';
						dan.add(danTmp);
						break;
					case 'x':
						Matcher m2 = p2.matcher(ne);
						m2.find();
						ind1 = Integer.valueOf(m2.group(1));
						ind2 = Integer.valueOf(m2.group(2));
						danTmp.ind1 = ind1;
						danTmp.ind2 = ind2;
						danTmp.type = 'x';
						dan.add(danTmp);
						break;
					case 'p':
						Matcher m3 = p3.matcher(ne);
						m3.find();
						ch1 = m3.group(1).charAt(0);
						ch2 = m3.group(2).charAt(0);
						danTmp.ch1 = ch1;
						danTmp.ch2 = ch2;
						danTmp.type = 'p';
						dan.add(danTmp);
						break;
					default:
						out.println("ERR");
						break;
				}
				count++;

			}
		}
		//out.println(count); out.println(dan.size());
		int ind1, ind2;
		char ch1, ch2;
		int lendan = dan.size();
		/*
		dance danArray[] = new dance[dan.size()];
		for (int ii = 0; ii < dan.size(); ii++) {
			danArray[ii] = new dance();
		}
		*/
		
		Vector<String> vesbOrig = new Vector<>();
		int cycle = 0;
		String ans = new String("");
		for (int ii = 0; ; ii++) {
			if (vesbOrig.contains(sb.toString())) {
				cycle = ii;
				//out.println(cycle);
				ans = vesbOrig.get(1000000000 % cycle);
				//out.println(ans);
				break;
			}
			vesbOrig.add(sb.toString());
			for (int kkk = 0; kkk < lendan; kkk++) {
				switch (dan.get(kkk).type) {
					case 's':
						String move = sb.toString().substring(len-dan.get(kkk).num1);
						sb = new StringBuilder(sb.toString().substring(0, len-dan.get(kkk).num1));
						sb = new StringBuilder(move+sb);
						break;
					case 'x':
						ch1 = sb.charAt(dan.get(kkk).ind1);
						ch2 = sb.charAt(dan.get(kkk).ind2);

						sb.setCharAt(dan.get(kkk).ind1, ch2);
						sb.setCharAt(dan.get(kkk).ind2, ch1);
						break;
					case 'p':
						ind1 = sb.toString().indexOf(dan.get(kkk).ch1);
						ind2 = sb.toString().indexOf(dan.get(kkk).ch2);

						sb.setCharAt(ind1, dan.get(kkk).ch2);
						sb.setCharAt(ind2, dan.get(kkk).ch1);
						break;
				}
			}
		}

		
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(ans);
		out.println("");



		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		alternate(altersb);


	}
	public static void alternate(StringBuilder sb) {
		out.println(sb);
		//out.println(count); out.println(dan.size());
		int ind1, ind2;
		char ch1, ch2;
		int lendan = dan.size();
		int len = sb.length();
		
		/*
		dance danArray[] = new dance[dan.size()];
		for (int ii = 0; ii < dan.size(); ii++) {
			danArray[ii] = new dance();
		}
		*/
		
		int TIMES = 1000;
		StringBuilder sbOrig = new StringBuilder(sb);
		for (int ii = 0; ii < TIMES; ii++) {
			for (int kkk = 0; kkk < lendan; kkk++) {
				switch (dan.get(kkk).type) {
					case 's':
						String move = sb.toString().substring(len-dan.get(kkk).num1);
						sb = new StringBuilder(sb.toString().substring(0, len-dan.get(kkk).num1));
						sb = new StringBuilder(move+sb);
						break;
					case 'x':
						ch1 = sb.charAt(dan.get(kkk).ind1);
						ch2 = sb.charAt(dan.get(kkk).ind2);

						sb.setCharAt(dan.get(kkk).ind1, ch2);
						sb.setCharAt(dan.get(kkk).ind2, ch1);
						break;
					case 'p':
						/*
						ind1 = sb.toString().indexOf(dan.get(kkk).ch1);
						ind2 = sb.toString().indexOf(dan.get(kkk).ch2);

						sb.setCharAt(ind1, dan.get(kkk).ch2);
						sb.setCharAt(ind2, dan.get(kkk).ch1);
						*/
						break;
				}
			}
		}
		int sxchange[] = new int[16];
		for (char let = 'a'; let <= 'p'; let++) {
			for (int ii = 0; ii < 16; ii++) {
				if (sb.charAt(ii) == let) {
					sxchange[let-'a'] = ii;
				}
			}
		}
		/*
		for (int ii = 0; ii < 16; ii++) {
			out.print(sxchange[ii]); out.print(" ");
		}
		*/

	/*	
		out.println("jcobhadfnmpkglie");
                out.println("foanijpdclgmkebh");
		out.println("ebdkolcpiahgmjnf");
		out.println("pcmhjengokfdalib");
		out.println("gnalphmicjefdkbo");
		out.println("dbjkagfnimcephol");
		*/


		sb = new StringBuilder(sbOrig);
		
		Vector <Tuple<Character, Character>> ve = new Vector<>();
		for (int ii = 0; ii < TIMES; ii++) {
			for (int kkk = 0; kkk < lendan; kkk++) {
				 switch (dan.get(kkk).type) {
                                        case 'p':
                                                ind1 = sb.toString().indexOf(dan.get(kkk).ch1);
                                                ind2 = sb.toString().indexOf(dan.get(kkk).ch2);

                                                sb.setCharAt(ind1, dan.get(kkk).ch2);
                                                sb.setCharAt(ind2, dan.get(kkk).ch1);
                                                break;
                                }
			}
		}
		
		char sxchangePPP[] = new char[16];
		//out.println(sb);

		for (char let = 'a'; let <= 'p'; let++) {
			//sxchangePPP[let-'a'] = sb.charAt(let-'a');
			//out.println(sb.charAt(let-'a'));
			sxchangePPP[(int)(let-'a')] = sb.charAt(let-'a');
		}
		/*
		out.println("PPP");
		for (int ii = 0; ii < 16; ii++) {
			out.print(sxchangePPP[ii]); out.print(" ");
		}
		*/

		//out.println("combo building first 3");
		sb = new StringBuilder(sbOrig);
		out.println(sb);
		for (int ii = 0; ii < 1000000000 / TIMES; ii++) {
			if (ii % 500000 == 0) {out.println(ii);}
			//out.println(ii);
			StringBuilder sb2 = new StringBuilder(sb);
			for (int qq = 0; qq < 16; qq++) {
				ch1 = sb2.charAt(qq);
				sb.setCharAt(sxchange[qq], ch1);
			}
			sb2 = new StringBuilder(sb);
			//out.print("sb2: "); out.println(sb2);
			for (int qq = 0; qq < 16; qq++) {
                               ind1 = sb2.toString().indexOf((char)(qq+'a'));
                               ind2 = sb2.toString().indexOf(sxchangePPP[qq]);

			       //out.print((char)(qq+'a')); out.print(" -> "); out.println(sxchangePPP[qq]);
			       //out.print((char)(qq+'a')); out.print(" -> "); out.println(sxchangePPP[qq]);
			       //out.print(ind1); out.print(" -> "); out.println(ind2);
                               //sb.setCharAt(ind2, (char)(qq+'a'));
                               sb.setCharAt(ind1, sxchangePPP[qq]);
			//	ch1 = sb2.charAt(qq);
			//	sb.setCharAt(sxchangePPP[qq], ch1);
				//sb = new StringBuilder(sb);
			}
			//out.print("dodgy?: "); out.println(sb);
			//sb2 = new StringBuilder(sb);
			//out.println(sb);
		}
		
		///System.setOut(originalOut);
		out.print("alternate monoid ans: ");
		out.println(sb);
	}
	
	public static class dance {
		char ch1;
		char ch2;
		int num1;
		int ind1;
		int ind2;
		char type;
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

