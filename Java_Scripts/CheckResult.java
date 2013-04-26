import java.util.ArrayList;
import java.util.Hashtable;

import lib.LineReader;


public class CheckResult {

	public static boolean matchLabel(ArrayList<String> s, String gold){
		boolean flag = false;
		String[] golds = gold.split("\\.");
		for(String ss : s){
			boolean fflag = true;
			for(String gg : golds){
				if(gg.toLowerCase().equals("*")) continue;
				if(!ss.toLowerCase().contains(gg.toLowerCase())){
					fflag = false;
				}
			}
			if(fflag){
				flag = true;
				break;
			}
		}
		if(!flag){
			System.out.println(gold);
			for(String ss:s){
				System.out.println(ss);
			}
			System.out.println("=============================================");
		}
		
		return flag;
	}
	
	public static boolean matchLabelV(ArrayList<String> s, String gold){
		boolean flag = false;
		String[] golds = gold.split("\\.");
		for(String ss : s){
			boolean fflag = true;
			for(String gg : golds){
				if(gg.toLowerCase().equals("*")) continue;
				if((ss.toLowerCase().contains("pastp")&&gg.contains("psp"))||(ss.toLowerCase().contains("infinit")&&gg.contains("inf"))){
					fflag = true;
				}
			}
			if(fflag){
				flag = true;
				break;
			}
		}
		if(!flag){
			System.out.println(gold);
			for(String ss:s){
				System.out.println(ss);
			}
			System.out.println("=============================================");
		}
		
		return flag;
	}
	
	
	public static void main(String[] args) {
		Hashtable<String, Integer> oovt = new Hashtable<String, Integer>();
		
		LineReader goldfile = new LineReader("vcorpusbgold");
		LineReader systemfile = new LineReader("/Users/konglingpeng/Documents/NLP_Lab/OSX/vcorpusbout5");
		int correct = 0;
		int oov = 0;
		int total = 0;
		while(goldfile.hasNextLine()){
			String gline = goldfile.readNextLine();
			String gword = (gline.split("\t"))[0];
			String glabel = (gline.split("\t"))[1];
			ArrayList<String> snext = new ArrayList<String>();
			String sline = "";
			while(!(sline = systemfile.readNextLine().trim()).equals("")){
				snext.add(sline);
			}
			total++;
			if(snext.get(0).contains("?")){
				if(oovt.containsKey(gword)){
					oovt.put(gword, oovt.get(gword)+1);
				}else{
					oovt.put(gword,1);
				}
				oov++;
				continue;
			}
			if(matchLabelV(snext,glabel)){
				correct++;
			}
		}
		System.out.println(total+"\t"+(total-oov)+"\t"+correct+"\t"+oov);
		
		goldfile.closeAll();
		systemfile.closeAll();
		
		for(String key : oovt.keySet()){
			System.out.println(oovt.get(key)+"\t"+key);
		}
	}

}
