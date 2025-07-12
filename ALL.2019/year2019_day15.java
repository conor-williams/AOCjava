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
class year2019_day15 {
	public static int lenx = 50;
	public static int leny = 50;
	public static char [][] grid = new char[leny][lenx];
	public static int [][] already = new int[leny][lenx];
	public static int minPath = 99999;
	public static void main(String [] args) {
		out.println("		2019 Day15.1");
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
		
		for (int i = blah.size(); i < 10000; i++) {
			vi.add((long)0);
		}

//padit..
		for (int yy = 0; yy < leny; yy++) {
			for (int xx = 0; xx < lenx; xx++) {
				grid[yy][xx] = ' ';
			}
		}
		long outout = 0;
		long input = 1;
		int relBase = 0;
		int xx = lenx/2;
		int yy = leny/2;
		int timesOx = 0;
		int ex = 0;
		int ey = 0;
after:
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
				vi.set(Math.toIntExact(firstParam), (long)input);
				jj++;
			} else if (opcode == 4) {
				outout = firstParam;
				if (outout == 0) {
					switch ((int)input) {
						case 1:
							if (yy-1 == 0) {
								grid[yy-1][xx] = 'W';
							} else {
								grid[yy-1][xx] = '#';
							}
							input = 4;
							
							break;
						case 2:
							if (yy+1 == leny-1) {
								grid[yy+1][xx] = 'W';
							} else {
								grid[yy+1][xx] = '#';
							}
							input = 3;
							break;
						case 3:
							if (xx-1 == 0) {
								grid[yy][xx-1] = 'W';
							} else {
								grid[yy][xx-1] = '#';
							}
							input = 1;
							break;
						case 4:
							if (xx+1 == lenx-1) {
								grid[yy][xx+1] = 'W';
							} else {
								grid[yy][xx+1] = '#';
							}
							input = 2;
							break;
					}
					if (yy == 0 || xx == 0 || yy == leny-1 || xx == lenx-1) {
						grid[yy][xx] = 'W';
					}
				} else if (outout == 1) {
					switch ((int)input) {
						case 1:
							yy--;
							input = 3;
							break;
						case 2:
							yy++;
							input = 4;
							break;
						case 3:
							xx--;
							input = 2;
							break;
						case 4:
							xx++;
							input = 1;
							break;
					}
					grid[yy][xx] = '.';
					if (yy == 0 || xx == 0 || yy == leny-1 || xx == lenx-1) {
						grid[yy][xx] = 'W';
						switch ((int)input) {
							case 1:
								input = 2;
								break;
							case 2:
								input = 1;
								break;
							case 3:
								input = 4;
								break;
							case 4:
								input = 3;
								break;
						}
					}
				} else if (outout == 2) {
					switch ((int)input) {
						case 1:
							yy--;
							input = 3;
							break;
						case 2:
							yy++;
							input = 4;
							break;
						case 3:
							xx--;
							input = 2;
							break;
						case 4:
							xx++;
							input = 1;
							break;
					}
					grid[yy][xx] = 'O';
					
					//out.print("found the oxygen @"); out.print(xx); out.print(" "); out.println(yy);
					ex = xx;
					ey = yy;
					timesOx++;
					/*
					for (int yy1 = 0; yy1 < leny; yy1++) {
						for (int xx1 = 0; xx1 < lenx; xx1++) {
							if (xx1 == xx && yy1 == yy) {
								out.print("*");
							} else {
								out.print(grid[yy1][xx1]);
							}
						}
						out.println();
					}
					out.println();
					Scanner scanner = new Scanner(System.in); scanner.nextLine();
					*/
					if (timesOx == 4) {
						/*
						for (int yy1 = 0; yy1 < leny; yy1++) {
							for (int xx1 = 0; xx1 < lenx; xx1++) {
								if (xx1 == xx && yy1 == yy) {
									out.print("*");
								} else {
									out.print(grid[yy1][xx1]);
								}
							}
							out.println();
						}
						out.println();
						*/
						break after;
					}
				}
				/*
				int found = 0;
				for (int yy1 = 0; yy1 < leny; yy1++) {
					for (int xx1 = 0; xx1 < lenx; xx1++) {
						if (grid[yy1][xx1] == 'W' || grid[yy1][xx1] == 'O' ||
								grid[yy1][xx1] == '#' || 
								grid[yy1][xx1] == '.') {
							//ok
						} else {
							found = 1;
							break;
						}
					}
				}
				if (found == 0) {
					break after;
				}
				*/

				//Scanner scanner = new Scanner(System.in); scanner.nextLine();

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
		out.print(lenx/2); out.print(" "); out.println(leny/2);
		out.println(Math.abs((lenx/2)-ex) + Math.abs((leny/2)-ey));
		out.println(grid[leny/2][lenx/2]);
		out.println(grid[ey][ex]);
		*/
		next(lenx/2, leny/2, ex, ey, 0);

		out.print("**j_ans: ");
		out.print(minPath);
		out.println("");
		//for (int ii = 0; ii < vi.size(); ii++) {
		//	out.print(","); out.print(vi.get(ii));
		//}
		//out.println();
	}
	public static void next(int x, int y, int ex, int ey, int path) {
		if (grid[y][x] == ' ') {return;}
		if (grid[y][x] == '#') {return;}
		if (x == ex && y == ey) {
			/*out.println(path);*/
			if (path < minPath) {minPath = path;/* out.println(minPath);*/}
			return;
		}
		if (already[y][x] == 0 || path < already[y][x]) {
			already[y][x] = path;
			next(x, y-1, ex, ey, path+1);
			next(x+1, y, ex, ey, path+1);
			next(x, y+1, ex, ey, path+1);
			next(x-1, y, ex, ey, path+1);
		}
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

