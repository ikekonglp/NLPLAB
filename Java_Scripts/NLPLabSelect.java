import java.io.IOException;
import java.util.Scanner;

import lib.LineReader;
import lib.LineWriter;


public class NLPLabSelect {

	
	
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in); 
		String s = "";
		LineReader lr = new LineReader("/Users/konglingpeng/Documents/NLP_Lab/OSX/german.lexc");
		LineWriter lw = new LineWriter("outlexc");
		while(lr.hasNextLine()){
			String line = lr.readNextLine().trim();
			//boolean copyflag = true;
			if(line.startsWith("LEXICON") && !line.endsWith("SPinf") ){
				lw.writeln(line);
				System.out.println(line);
				//s=input.nextLine().trim();
				//if(s.equals("y")){
					// y means we want to check all words from the dictionary
					String[] strs = line.split("( +|\\t+)");
					String cname = strs[1].trim();
					System.err.println(cname);
					MyXMLReader2DOM.printWords(cname, lw);
					//copyflag = false;
				//}
				
			}else{
				lw.writeln(line);
				System.out.println(line);
			}
			//System.out.println(s);
		}
		lw.closeAll();
		lr.closeAll();
	}

}
