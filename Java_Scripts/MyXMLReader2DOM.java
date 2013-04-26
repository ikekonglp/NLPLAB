import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import lib.LineWriter;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class MyXMLReader2DOM {
	public static void printWords(String cname, LineWriter lw){
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();

			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();

			InputStreamReader in = new InputStreamReader(new FileInputStream(
					"lexicon.xml"), "utf-8");

			BufferedReader reader = new BufferedReader(in); // CHANGED

			InputSource input = new InputSource(reader);

			Element records = builder.parse(input).getDocumentElement();
			NodeList nl = records.getElementsByTagName("BaseStem");
			int count = 0;
			//PrintStream out = new PrintStream(System.out, true, "UTF-8");
			
			for (int i = 0; i < nl.getLength(); i++) {
				NodeList cnl = nl.item(i).getChildNodes();
				String stem = "";
				boolean flag = false;
				boolean neverprint = true;
				for (int j = 0; j < cnl.getLength(); j++) {
					Node cnode = cnl.item(j);
					if (cnode.getNodeName().equals("Stem")) {
						// System.out.print(cnode.getTextContent()+"\t");
						stem = cnode.getTextContent();
					}
					if (cnode.getNodeName().equals("InfClass")) {
						if (cnode.getTextContent().equals(cname)) {
							count++;
							flag = true;
						}

					}
					if (flag && neverprint) {
						stem = stem.replaceAll("ß", "ss");
						System.out.println(stem + "    "+cname+"SPinf;");
						lw.writeln(stem + "    "+cname+"SPinf;");
						neverprint = false;
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void printWords(String cname){
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();

			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();

			InputStreamReader in = new InputStreamReader(new FileInputStream(
					"lexicon.xml"), "utf-8");

			BufferedReader reader = new BufferedReader(in); // CHANGED

			InputSource input = new InputSource(reader);

			Element records = builder.parse(input).getDocumentElement();
			NodeList nl = records.getElementsByTagName("BaseStem");
			int count = 0;
			//PrintStream out = new PrintStream(System.out, true, "UTF-8");
			
			for (int i = 0; i < nl.getLength(); i++) {
				NodeList cnl = nl.item(i).getChildNodes();
				String stem = "";
				boolean flag = false;
				boolean neverprint = true;
				for (int j = 0; j < cnl.getLength(); j++) {
					Node cnode = cnl.item(j);
					if (cnode.getNodeName().equals("Stem")) {
						// System.out.print(cnode.getTextContent()+"\t");
						stem = cnode.getTextContent();
					}
					if (true) {
						if (cnode.getTextContent().equals(cname)) {
							count++;
							flag = true;
						}

					}
					if (flag && neverprint) {
						stem = stem.replaceAll("ß", "ss");
						System.out.println(stem+" #;");
						//lw.writeln(stem + "    "+cname+"SPinf;");
						neverprint = false;
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String arge[]) {
		printWords("NN");
	}
}