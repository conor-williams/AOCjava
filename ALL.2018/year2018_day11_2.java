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


//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

@SuppressWarnings("unchecked")
class year2018_day11_2 {
	//	        public static int maxPath = 0;
	public static int sx = 300;
	public static int sy = 300;
	public static int grid [][] = new int[sy][sx];
	//    public static int already [][] = new int[sy][sx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2018 Day11.2");
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

                PrintStream originalOut = System.out;
                System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));


		int gridSerialId = Integer.valueOf(blah.get(0));
		for (int yy = 0; yy < sy; yy++) {
			for (int xx = 0; xx < sx; xx++) {
				int x = xx+1;
				int y = yy+1;
				int rackid = x+10;
				int power = rackid * y;
				power += gridSerialId;
				power *= rackid;
				int digHun = 0;
				if (power > 99) {
					int dU = power % 100;
					int dT = power % 1000;
					digHun = (dT - dU)/100;
				}
				digHun -= 5;
				grid[yy][xx] = digHun;
			}
		}
		/*
		int sum1 = 0;
		for (int yy = 282 ; yy < 293; yy++) {
			for (int xx = 233; xx < 244; xx++) {
				out.print(grid[yy][xx]);
				sum1+=grid[yy][xx];
			}
			out.println();
		}
		out.println(sum1);
		*/
		//Scanner scanner1 = new Scanner(System.in); scanner1.nextLine();
		int max = 0;
		int yyy = 0;
		int xxx = 0;
		int gSize = 0;
		for (int yy = 0; yy < sy; yy++) {
			for (int xx = 0; xx < sx; xx++) {
				//have a 3 x 3;
				//mG = 4
				//y=4, x = 0, 1, 2, 3, 4;
				//x=4, y = 0, 1, 2, 3
				int mG = 0;
				int mGy = 300 - yy;
				int mGx = 300 - xx;

				if (mGy > mGx) {
					mG = mGx;
				} else {
					mG = mGy;
				}
				int tmptot = 0;// grid[yy][xx];
				//out.print(yy); out.print(" xx "); out.println(xx);
				//out.print("mG is "); out.println(mG); out.print("-----");
				int p = 0;
				for (int gS = 0; gS < mG; gS++) {
					if (gS == 0 && (yy == 281 && xx == 232)) {out.println("here10");
						out.println(tmptot);
						//p=1;
					}

					if (p == 1) {
						out.println(gS);
						out.println("around the box moving x bottom row");
					}
					for (int x1 = 0; x1 <= gS; x1++) {
						if (p == 1) {
							out.print("y: "); out.print(gS); out.print(" x "); out.println(x1);
						}
						tmptot+= grid[yy+gS][xx+x1];
					}
					if (p == 1) {
						out.println("----");
						out.println("down the side moving y");
					}
					for (int y1 = 0; y1 < gS; y1++) {
						if (p == 1) {
							out.print("y: "); out.println(y1); out.print(" x "); out.println(gS);
						}
						tmptot+= grid[y1+yy][gS+xx];
					}
					if (p==1) {
						out.println("------");
						//Scanner scanner = new Scanner(System.in); scanner.nextLine();
					}

					if (p == 1) {
						out.print("tmptot: "); out.println(tmptot);
					}
					if (tmptot > max) {
						max = tmptot; yyy = yy+1; xxx = xx+1; gSize = gS+1;
					}
				}
			}
		}
		out.println(max);
		//out.println(xxx); out.println(yyy);
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(xxx);
		out.print(",");
		out.print(yyy);
		out.print(",");
		out.print(gSize);
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

