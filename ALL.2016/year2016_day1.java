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

class year2016_day1 {
	public static void main(String [] args) {
		out.println("		2016 Day1.1");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		Pattern p = Pattern.compile("(L|R)(\\d+)");
	//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
	//	month= m.group(2);

		//BigInteger tot =  BigInteger.valueOf((long)0);
		int tot = 0;
		int dir = 0;
		int EW = 0;
		int NS = 0;
		int count = 0;
		for (int i = 0; i < blah.size(); i++) {
			Scanner scanner = new Scanner(blah.get(i));
			scanner.useDelimiter(", "); 
			while (scanner.hasNext()) {
				count++;
				String ne = scanner.next();
				if (ne.charAt(0) == 'L') {
					dir = ((dir-1) + 4)%4;
				} else if (ne.charAt(0) == 'R') {
					dir = (dir+1)%4;
				} else {
					Runtime.getRuntime().halt(0);
				}
				//out.println("ne is " + ne);
				//out.print(ne.charAt(1));
				//out.print(ne.charAt(1)-48);
				Matcher m = p.matcher(ne);
				m.find();
				//out.println(m.group(1));
				int  val=Integer.valueOf(m.group(2));
				switch (dir) {
					case 0:
						NS-=val;
						break;
					case 1:
						EW+=val;
						break;
					case 2:
						NS+=val;
						break;
					case 3:
						EW-=val;
						break;
					default:
						out.println("here4...\n");
						Runtime.getRuntime().halt(0);


				}
			}
		}
		tot += Math.abs(NS) + Math.abs(EW);
		out.print("**j_ans: ");
		out.println(tot);
					
	}
}
