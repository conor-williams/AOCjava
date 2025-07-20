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
// /java -Xmx2g year2019_day3.java *i1.txt


//System.setOut(originalOut);
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
class year2019_day17 {
	//public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static int SZ = 120;
	public static char grid[][] = new char[SZ][SZ];
	public static void main(String [] args) {
		out.println("		2019 Day17.1");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
		Vector <Long> vi = new Vector<>();
		for (int i = 0; i < blah.size(); i++) {
			   Scanner scanner = new Scanner(blah.get(i));
			   scanner.useDelimiter("[,]");
			   while (scanner.hasNext()) {
			   	String ne = scanner.next();
			  	vi.add(Long.parseLong(ne));
			   }
		}
		
		for (int i = blah.size(); i < 10000; i++) {
			vi.add((long)0);
		}

		for (int yy = 0; yy < SZ; yy++) {
			for (int xx = 0; xx < SZ; xx++) {
				grid[yy][xx] = '.';
			}
		}
//padit..
		long outout = 0;
		long input = 2;
		int relBase = 0;
		int yy = 0;
		int xx = 0;
		for (int jj = 0; jj < vi.size(); jj++) {
			String wholecode = String.format("%05d", vi.get(jj));
			//out.println(wholecode);
			int opcode = Integer.valueOf(wholecode.substring(3,5));

			Character firstMode = wholecode.charAt(2);
			Character secondMode = wholecode.charAt(1);
			Character thirdMode = wholecode.charAt(0);

			long firstParam = 0;
			long secondParam = 0;
			int thirdParam = 0;

			if (opcode == 1 || opcode == 2 || opcode == 5 || opcode == 6
					|| opcode == 7 || opcode == 8) {
				if (firstMode == '0') {
					firstParam = vi.get(Math.toIntExact(vi.get(jj+1)));
				} else if (firstMode == '1') {
					firstParam = vi.get(jj+1);
				} else if (firstMode == '2') {
					int relTmp = relBase + Math.toIntExact(vi.get(jj+1));
					firstParam = vi.get(relTmp);
				}

				if (secondMode == '0') {
					secondParam = vi.get(Math.toIntExact(vi.get(jj+2)));
				} else if (secondMode == '1') {
					secondParam = vi.get(jj+2);
				} else if (secondMode == '2') {
					int relTmp = relBase + Math.toIntExact(vi.get(jj+2));
					secondParam = vi.get(relTmp);
				}

				if (thirdMode == '0') {
					thirdParam = Math.toIntExact(vi.get(jj+3));
				} else if (thirdMode == '1') {
					thirdParam = jj+3;
				} else if (thirdMode == '2') {
					int relTmp = relBase + Math.toIntExact(vi.get(jj+3));
					thirdParam = relTmp;
					//thirdParam = vi.get(relTmp);
				}

			} else if (opcode == 4 || opcode == 9) {
				if (firstMode == '0') {
					firstParam = vi.get(Math.toIntExact(vi.get(jj+1)));
				} else if (firstMode == '1') {
					firstParam = vi.get(jj+1);
				} else if (firstMode == '2') {
					int relTmp = relBase + Math.toIntExact(vi.get(jj+1));
					firstParam = vi.get(relTmp);
				}

			} else if (opcode == 3 || opcode == 99) {
				if (firstMode == '0') {
					firstParam = Math.toIntExact(vi.get(jj+1));
				} else if (firstMode == '1') {
					firstParam = jj+3;
				} else if (firstMode == '2') {
					int relTmp = relBase + Math.toIntExact(vi.get(jj+1));
					firstParam = relTmp;
					//thirdParam = vi.get(relTmp);
				}

			} else {
				out.print("wholecode: "); out.println(wholecode);
				out.println("ERROR");
				Runtime.getRuntime().halt(0);
			}

			if (opcode == 1) {
				vi.set(thirdParam, firstParam+secondParam);
				jj+=3;
			} else if (opcode == 2) {
				vi.set(thirdParam, firstParam*secondParam);
				jj+=3;
			} else if (opcode == 9) {
				relBase += firstParam;
				//out.print("relBase: "); out.println(relBase);
				jj++;
			} else if (opcode == 3) {
				/*
				if (firstMode == '0') {
					vi.set(Math.toIntExact(vi.get(jj+1)), input);
				} else if (firstMode == '1') {
					out.println("whathere...");
				} else if (firstMode == '2') {
					vi.set(Math.toIntExact(vi.get(jj+1) + relBase), (long)input);
				}
				*/
				vi.set(Math.toIntExact(firstParam), (long)input);
				jj++;
			} else if (opcode == 4) {
				outout = firstParam;
				/*
				if (outout == 35) {out.print("#"); }
				else if (outout == 46) {out.print(".");}
				else if (outout == 10) {out.println();}
				*/

				if (outout == 35) {grid[yy][xx] = '#'; xx++;}
				else if (outout == 46) {grid[yy][xx] = '.'; xx++;}
				else if (outout == 10) {yy++;xx=0;}
				else { grid[yy][xx] = (char)(outout%256); xx++; }
				/*
					if (outout == (int) '^') {
						//out.println("up");
						grid[yy][xx] = '^'
					} else if (outout == (int) 'v') {
						out.println("down");
					} else if (outout == (int) '>') {
						out.println("right");
					} else if (outout == (int) '<') {
						out.println("left");
					}
					grid[yy][xx] = 'Q'; xx++;
				}
				*/

				//out.print("OUTOUT: "); out.println(outout);
				jj++;
			} else if (opcode == 5) {
				if (firstParam != 0) {
					jj = (int)(secondParam-(long)1);
				} else {
					jj+=(long)2;
				}
			} else if (opcode == 6) {
				if (firstParam == 0) {
					jj = (int)(secondParam-(long)1);
				} else {
					jj+=(long)2;
				}
			} else if (opcode == 7) {
				if (firstParam < secondParam) {
					vi.set(thirdParam, (long)1);
					//vi.set(Math.toIntExact(vi.get(jj+3)), (long)1);
				} else {
					vi.set(thirdParam, (long)0);
					//vi.set(Math.toIntExact(vi.get(jj+3)), (long)0);
				}
				jj+=3;
			} else if (opcode == 8) {
				if (firstParam == secondParam) {
					//vi.set(Math.toIntExact(vi.get(jj+3)), (long)1);
					vi.set((int)thirdParam, (long)1);
				} else {
					//vi.set(Math.toIntExact(vi.get(jj+3)), (long)0);
					vi.set((int)thirdParam, (long)0);

				}
				jj+=3;
			} else if (opcode == 99) {
				break;
			}
		}
		long tot = 0;
		for (int yy2 = 1; yy2 < SZ-1; yy2++) {
			for (int xx2 = 1; xx2 < SZ-1; xx2++) {
				out.print(grid[yy2][xx2]);
				if (grid[yy2-1][xx2] == '#' && grid[yy2][xx2+1] == '#' &&
						grid[yy2+1][xx2] == '#' && grid[yy2][xx2-1] == '#') {
					tot += yy2*xx2;

				}
			}
			out.println();
		}
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(tot);
		out.println("");
		//for (int ii = 0; ii < vi.size(); ii++) {
		//	out.print(","); out.print(vi.get(ii));
		//}
		//out.println();
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

