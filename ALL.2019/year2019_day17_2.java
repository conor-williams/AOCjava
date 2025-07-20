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
//PrintStream originalOut = System.out;
//System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();

@SuppressWarnings("unchecked")
class year2019_day17_2 {
	//public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static String var_in = new String();
	//public static String var_in = "A,B,A,C,B,C,A,B,A,C\nR,6,L,10,R,8,R,8\nR,12,L,8,L,10\nR,12,L,10,R,6,L,10\nn\n";
	//public static String var_in = "A,B,B,C,B,C,A,B,A,C\nR,6,L,10,R,8,R,8\nR,12,L,8,L,10\nR,12,L,10,R,6,L,10\ny\n";
	public static int SZ = 120;
	public static char grid[][] = new char[SZ][SZ];
	public static int already[][] = new int[SZ][SZ];
	public static int sx = 0;
	public static int sy = 0;
	public static String gPath = new String();
	public static void main(String [] args) {
		out.println("		2019 Day17.2");
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
		Vector <Long> vi2 = new Vector<>();
		Vector <Long> viOrig = new Vector<>();
		for (int i = 0; i < blah.size(); i++) {
			Scanner scanner = new Scanner(blah.get(i));
			scanner.useDelimiter("[,]");
			int pos = 0;
			while (scanner.hasNext()) {
				String ne = scanner.next();
				viOrig.add(Long.parseLong(ne));
				if (pos != 0) {
					vi.add(Long.parseLong(ne));
				} else {
					vi.add((long)2);
					pos++;
				}
			}
		}

		for (int i = blah.size(); i < 10000; i++) {
			vi.add((long)0);
			viOrig.add((long)0);
		}
		vi2 = new Vector(vi);

		for (int yy = 0; yy < SZ; yy++) {
			for (int xx = 0; xx < SZ; xx++) {
				grid[yy][xx] = '.';
			}
		}
		//padit..

		long ans = 0;
		int times = -1;

		while (ans == 0) {
			times++;
			if (times == 0) {
				vi = new Vector(viOrig);
			} else {
				vi = new Vector(vi2);
			}
			out.println("waiting...");
			//Scanner scanner = new Scanner(System.in); scanner.nextLine();


			long outout = 0;
			long input = 2;
			int relBase = 0;
			int yy = 0;
			int xx = 0;
			int var_inPos = 0;
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
					input = (char)var_in.charAt(var_inPos);
					var_inPos++;

					vi.set(Math.toIntExact(firstParam), (long)input);
					jj++;
				} else if (opcode == 4) {
					outout = firstParam;
					/*
					   if (outout == 35) {out.print("#"); }
					   else if (outout == 46) {out.print(".");}
					   else if (outout == 10) {out.println();}
					   */

					/*
					   if (!(outout == 35 || outout == 46 || outout == 10)) {
					   out.print((char)outout);
					   }
					   */

					if (outout > 255) {ans = outout; /*out.println(outout);*/} 
					else if (yy < 120 && xx < 120) {

						if (outout == 35) {grid[yy][xx] = '#'; xx++;}
						else if (outout == 46) {grid[yy][xx] = '.'; xx++;}
						else if (outout == 10) {yy++;xx=0;}
						else { grid[yy][xx] = (char)(outout%256); xx++; }
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

			
			if (times == 0) {
				sx = 0; sy = 0; int dir = 0; int ex = 0; int ey = 0;
after:
				for (int yy3 = 1; yy3< 120-1; yy3++) {
					for (int xx3 = 1; xx3 < 120-1; xx3++) {
						if (grid[yy3][xx3] == '^') {
							dir = 0;
							sx = xx3; sy = yy3;
							break after;
						} else if (grid[yy3][xx3] == '>') {
							dir = 1;
							sx = xx3; sy = yy3;
							break after;
						} else if (grid[yy3][xx3] == 'v') {
							dir = 2;
							sx = xx3; sy = yy3;
							break after;
						} else if (grid[yy3][xx3] == '<') {
							dir = 3;
							sx = xx3; sy = yy3;
							break after;
						}

						//out.print(grid[yy3][xx3]);
					}
					//out.println();
				}
				out.print(sx); out.print(" "); out.println(sy);
after2:
				for (int yy3 = 1; yy3< 120-1; yy3++) {
					for (int xx3 = 1; xx3 < 120-1; xx3++) {
						int count = 0;
						if (grid[yy3][xx3] != '#') {continue;}
						if (grid[yy3-1][xx3] == '.') {
							count++;
						} 
						if (grid[yy3][xx3+1] == '.') {
							count++;
						}
						if (grid[yy3+1][xx3] == '.') {
							count++;
						}
						if (grid[yy3][xx3-1] == '.') {
							count++;
						}
						if (count == 3) {
							ex = xx3; ey= yy3;
							break after2;
						}
					}
				}
				grid[ey][ex] = '#';
				for (int xx4 = 0; xx4 < 85; xx4++) {
					if (xx4 % 10 == 0) {out.print("10        ");}
				}
				out.println();
				for (int yy4 = 0; yy4 < 85; yy4++) {
					for (int xx4 = 0; xx4 < 85; xx4++) {
						out.print(grid[yy4][xx4]);
					}
					out.println();
				}
				grid[sy][sx] = '#';
				out.print(ex); out.print(" "); out.println(ey);
				out.print("dir: "); out.println(dir);

				String bla = new String();
				next(sx, sy, ex, ey, dir, 'Q', bla);
				out.print("55 10"); out.println(grid[55][10]);

				{
					out.println(var_in);
					int count = 0;
					int first = 0;
					String pa = new String();
					for (int ii = 0; ii < gPath.length(); ii++) {
						if (gPath.charAt(ii) != 'S') {
							if (first != 0) {
								out.print(count+1); out.print(",");
								pa += String.valueOf(count+1) + ",";
							} else {
								first = 1;
							}
							out.print(gPath.charAt(ii)); out.print(",");
							pa += gPath.charAt(ii) + ",";
							count = 0;
						} else {
							count++;
						}
					}
					out.println(count+1);
					pa += String.valueOf(count+1);
					out.print("pa"); out.println(pa);
					pa += ",";
					//444; 333; 334; 343;
					int ar[] = new int[3];
					String paOrig = new String(pa);
after3:
					for (int end = 0; end < 3; end++) {
						for (int iii = 0; iii < end; iii++) {
							ar[iii] = 1;
						}
						for (int iii = end; iii < 3; iii++) {
							ar[iii] = 0;
						}
						int arOrig[] = Arrays.copyOf(ar, 3);

						do {
							pa = new String(paOrig);
							//for (int ii = 0; ii < 3; ii++) { out.print(ar[ii]); out.print(" "); } out.println();
							int paPos = 0;
							String parts[] = new String[3];
							parts[0] = new String();
							parts[1] = new String();
							parts[2] = new String();
							int partsPos = 0;
							for (int jjj = 0; jjj < 3; jjj++) {
								if (ar[jjj] == 1) {
									paPos = 0;
									for (int kk = 0; kk < 8; kk++) {
										paPos = pa.indexOf(",", paPos+1);
									}
									parts[partsPos] = new String(pa.substring(0, paPos));
									
									pa = pa.replaceAll(parts[partsPos]+",", "");
									out.println(pa);
									partsPos++;
								} else {
									paPos = 0;
									for (int kk = 0; kk < 6; kk++) {
										paPos = pa.indexOf(",", paPos+1);
									}
									parts[partsPos] = new String(pa.substring(0, paPos));
									pa = pa.replaceAll(parts[partsPos]+",", "");
									partsPos++;
								}
							}
							//out.println("The parts: "); for (int zz =0; zz < 3; zz++) { out.println(parts[zz]); } out.print("pa: "); out.println(pa);
							if (pa.indexOf("R") == -1 && pa.indexOf("L") == -1) {
								
								//out.println("yatzee");
								String pa2 = new String(paOrig);
								for (int ii = 0; ii < 3; ii++) {
									//out.print(ar[ii]); out.print(" ");
									pa2 = pa2.replaceAll(parts[ii], (char)('A'+ii)+"");
								}
								if (pa2.indexOf("R") == -1 && pa2.indexOf("L") == -1) {
									out.println("yatzee...");
									pa2 = pa2.substring(0, pa2.length()-1);
									for (int ii = 0; ii < 3; ii++) {
										pa2+= "\n";
										pa2+=parts[ii];
									}
									pa2+="\nn\n";
									var_in = new String(pa2);
									out.print("pa2: "); out.println(pa2);
									break after3;
								}

								
							}
							//Scanner scanner2 = new Scanner(System.in); scanner2.nextLine();
							nextPermutation(ar);
						} while (!Arrays.equals(ar, arOrig));
					}

					/*
					Pattern p5 = Pattern.compile("^(.{1,20})\1*(.{1,20})(?:\1|\2)*(.{1,20})(?:\1|\2|\3)*$");
					Matcher m5 = p5.matcher(pa);
					m5.find();
					out.println(m5.group(1));
					out.println(m5.group(2));
					out.println(m5.group(3));
					*/

				}

			}

		}
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(ans);
		out.println("");
	}
	public static void nextPermutation(int[] nums) {
                int i = nums.length - 2;
                while (i >= 0 && nums[i] >= nums[i + 1]) {
                        i--;
                }
                if (i >= 0) {
                        int j = nums.length - 1;
                        while (j >= 0 && nums[j] <= nums[i]) {
                                j--;
                        }
                        swap(nums, i, j);
                }
                reverse(nums, i + 1);
        }

        private static void swap(int[] nums, int i, int j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
        }

        private static void reverse(int[] nums, int start) {
                int end = nums.length - 1;
                while (start < end) {
                        swap(nums, start, end);
                        start++;
                        end--;
                }
        }


	public static void next(int x, int y, int ex, int ey, int dir, char turn, String tu2) {
		//out.print(x); out.print(" x<<>>>y "); out.println(y);
		//out.print("grid: "); out.println(grid[y][x]);
		if (x == ex && y == ey) {gPath = new String(tu2); out.print("end"); out.println(tu2); return;}
		if (grid[y][x] != '#') {return;}
		//if (grid[y][x] == '#') {out.print(turn);}
		if (already[y][x] == 0) {
			int goStrait = 0;
			//already[y][x] = 1;
			String tu = new String(tu2);
			int count = 0;
			if (y-1 >= 0 && grid[y-1][x] == '#') {
				count++;
			} 
			if (x+1 < 120 && grid[y][x+1] == '#') {
				count++;
			}
			if (y+1 < 120 && grid[y+1][x] == '#') {
				count++;
			}
			if (x-1 >= 0 && grid[y][x-1] == '#') {
				count++;
			}
			if (count == 4) {
				goStrait = 1;
			}

			if (dir == 0) {
				if (y-1 >= 0) {
					next(x, y-1, ex, ey, 0, 'S', tu+'S');
				}
				if (goStrait != 1) {
					if (x-1 >= 0) {
						next(x-1, y, ex, ey, 3, 'L', tu+'L');
					}
					if (x+1 < 120) {
						next(x+1, y, ex, ey, 1, 'R', tu+'R');
					}
				}
			} else if (dir == 1) {
				if (x+1 < 120) {
					next(x+1, y, ex, ey, 1, 'S', tu+'S');
				}
				if (goStrait != 1) {
					if (y-1 >= 0) {
						next(x, y-1, ex, ey, 0, 'L', tu+'L');
					}
					if (y+1 < 120) {
						next(x, y+1, ex, ey, 2, 'R', tu+'R');
					}
				}
			} else if (dir == 2) {
				if (y+1 < 120) {
					next(x, y+1, ex, ey, 2, 'S', tu+'S');
				}
				if (goStrait != 1) {
					if (x+1 < 120) {
						next(x+1, y, ex, ey, 1, 'L', tu+'L');
					}
					if (x-1 >= 0) {
						next(x-1, y, ex, ey, 3, 'R', tu+'R');
					}
				}
			} else if (dir == 3) {
				if (x-1 >= 0) {
					next(x-1, y, ex, ey, 3, 'S', tu+'S');
				}
				if (goStrait != 1) {
					if (y+1 < 120) {
						next(x, y+1, ex, ey, 2, 'L', tu+'L');
					}
					if (y-1 >= 0) {
						next(x, y-1, ex, ey, 0, 'R', tu+'R');
					}
				}
			}
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

