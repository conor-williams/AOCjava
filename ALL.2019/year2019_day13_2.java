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
// /java -Xmx2g year2019_day3.java *i1.txt


//System.setOut(originalOut);
//PrintStream originalOut = System.out;
//System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
class year2019_day13_2 {
	public static char [][] grid = new char[100][100];
	public static void main(String [] args) {
		out.println("		2019 Day13.2");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		Vector <Long> vi = new Vector<>();
		for (int i = 0; i < blah.size(); i++) {
			   Scanner scanner = new Scanner(blah.get(i));
			   scanner.useDelimiter("[,]");
			   while (scanner.hasNext()) {
			   	String ne = scanner.next();
			  	vi.add(Long.parseLong(ne));
			   }
		}
		vi.set(0, Long.valueOf(2));
		
		for (int i = blah.size(); i < 10000; i++) {
			vi.add((long)0);
		}

//padit..
		long outout = 0;
		long input = 2;
		int relBase = 0;
		int xx = 0;
		int yy = 0;
		char ch = ' ';
		int exywat = 0;
		int score = 0;
		int xxball = 0;
		int yyball = 0;
		int xxpaddle = 0;
		int yypaddle = 0;

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
				if (xxpaddle == xxball) {
					input = 0;
				} else if (xxpaddle > xxball) {
					input = -1;
				} else {
					input = 1;
				}

				vi.set(Math.toIntExact(firstParam), (long)input);
				jj++;
			} else if (opcode == 4) {
				outout = firstParam;
				if (exywat == 0) {
					xx = (int)outout;
					exywat++;
				} else if (exywat == 1) {
					yy = (int)outout;
					exywat++;
				} else if (exywat == 2) {
					exywat = 0;
					if (xx == -1 && yy == 0) {
						score = (int)outout;
					} else {
						switch ((int)outout) {
							case 0:
								ch = ' ';
								break;
							case 1:
								ch = '|';
								break;
							case 2:
								ch = '#';
								break;
							case 3:
								xxpaddle = xx;
								yypaddle = yy;
								ch = '-';
								break;
							case 4:
								xxball = xx;
								yyball = yy;
								ch = 'o';
								break;
							default:
								out.println("ERRtype");
								break;
						}
						grid[yy][xx] = ch;
					}
				}
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
		/*
		int count = 0;
		for (int y = 0; y < 26; y++) {
			for (int x = 0; x < 50; x++) {
				out.print(grid[y][x]);
				if (grid[y][x] == '#') {
					count++;
				}
			}
			out.println();
		}
		*/
		out.print("**j_ans: ");
		out.print(score);
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

