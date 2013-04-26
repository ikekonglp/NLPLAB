import lib.LineReader;
import lib.LineWriter;


public class ExtractWords {
	
	public static void main(String[] args) {
		LineReader lr = new LineReader("/Users/konglingpeng/Documents/NLP_Lab/OSX/tigerout");
		LineWriter lw = new LineWriter("vcorpusa");
		LineWriter lw2 = new LineWriter("vcorpusagold");
		int count = 0;
		while(lr.hasNextLine()){
			String line = lr.readNextLine().trim();
			String[] strs = line.split("[\t ]+");
			if(strs.length<3) continue;
			if(strs[2].startsWith("VV")){
				System.out.println(strs[0]+"\t"+strs[2]+"\t"+strs[3]);
				count++;
				if(count<1000){
					lw.writeln(strs[0].trim());
					lw2.writeln(strs[0]+"\t"+strs[3]);
				}
			}
			
		}
		System.out.println(count);
		lr.closeAll();
		lw.closeAll();
		lw2.closeAll();
	}

}
