package wcu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ReadFile {
	BufferedReader inputreader;
	private String path;

	public ReadFile(String file_path){
		path = file_path;
	}
	public String[] OpenFile() throws IOException{
		FileReader fr = new FileReader(path);
		BufferedReader textreader = new BufferedReader(fr);

		int numberoflines = readLines();
		String[] textData = new String[numberoflines];

		for (int i = 0; i<numberoflines; i++){
			textData[i] = textreader.readLine();

		}
		textreader.close();
		return textData;
	}

	int readLines() throws IOException {
		String line;
		int numberOflines = 0;

		FileReader file_to_read = new FileReader(path);
		BufferedReader br = new BufferedReader(file_to_read);

		while ((line = br.readLine()) != null){
			numberOflines ++;

		}
		br.close();
		return numberOflines;
	}
	
}
