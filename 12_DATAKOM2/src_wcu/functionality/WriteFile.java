package functionality;

import java.io.*;

public class WriteFile {
	BufferedReader inputreader;
	BufferedWriter writer;

	public void writeLog(String date, String Opr_nummer, int varenummer,
			double netto) {
		String filename = "log.txt";

		try {
			FileWriter output = new FileWriter(filename);

			output.write("dato " + date + "\n "+",");
			output.write("oprNr " + Opr_nummer + "\n "+",");
			output.write("varenr " + varenummer + "\n "+",");
			output.write("netto " + netto + "\n "+",");

			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("Filen kunne ikke oprettes: " + filename);
		} catch (IOException e) {
			System.out.println("Der opstod fejl under skrivning til: "
					+ filename);
		}

	}

	public void readLog() {
		String filename = "log.txt";
		try {
			FileReader input = new FileReader(filename);

			while (input.ready())
				System.out.print((char) input.read());

			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("Filen findes ikke: " + filename);
		} catch (IOException e) {
			System.out
					.println("Der opstod fejl under læsning fra: " + filename);
		}
	}

	public void writeStore() {
		if (new File("data.text/test").exists()) {
			System.out.println("Filen eksisterer allerede");
		} else {
			File outputfile = new File("data.text/test");
			try {
				writer = new BufferedWriter(new FileWriter(outputfile));
				writer.write("Pille1");
				((BufferedWriter) writer).newLine();
				
				writer.close();
				System.out.println("Filen blev skrevet");
			} catch (IOException e) {
				System.out
						.println("Der skete en fejl under skrivningen af filen");
			}

		}

	}

	public String readStore(int varenummer) {

		try {
			File inputfile = new File("data.text/test");
			inputreader = new BufferedReader(new FileReader(inputfile));
			LineNumberReader ln = new LineNumberReader(inputreader);
			ln.setLineNumber(varenummer);
			
			if (ln.getLineNumber() <= 10) {
				if (varenummer == 0) {
					String x = ln.readLine();
					System.out.println(x);
					return x;
				}
				for (int i = 0; i + 1 <= varenummer; i++) {
					ln.readLine();
					if (i + 1 == varenummer) {
						String x = ln.readLine();
						System.out.println(x);
						return x;
					}

				}

			}
			else {
				System.out.println("Du skulle have valgt en vare nummer mellem 0 og 9");
			}


			inputreader.close();

		} catch (FileNotFoundException ex) {
			System.out.println("Filen blev ikke fundet");
		} catch (Exception ex) {
			System.out.println("Noget gik galt under læsningen af filen");
		}
		return null;
	}
	public void opdaterDatabase(){
//		IOController oc = new IOController();
		WriteFile wf = new WriteFile();
		
		System.out.println();
	}

}
