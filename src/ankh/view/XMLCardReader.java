import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
 
public class XMLCardReader {
 
  public static void main(String argv[]) {
 
    try {
 
	File fXmlFile = new File("D:\\Coding Projects\\APP\\XMLCreation\\src\\xmlcreation\\SecretIdentityCards.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
	doc.getDocumentElement().normalize();
 
	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
 
	NodeList nList = doc.getElementsByTagName("item");
 
	System.out.println("----------------------------");
 
	for (int temp = 0; temp < nList.getLength(); temp++) {
 
		Node nNode = nList.item(temp);
 
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
 
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
			Element eElement = (Element) nNode;
 
			//System.out.println("Staff id : " + eElement.getAttribute("item"));
			System.out.println("card id " + eElement.getElementsByTagName("card_id").item(0).getTextContent());
			System.out.println("card name  " + eElement.getElementsByTagName("card_name").item(0).getTextContent());
			System.out.println("card description: " + eElement.getElementsByTagName("card_description").item(0).getTextContent());
			//System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
 
		}
	}
    } catch (Exception e) {
	e.printStackTrace();
    }
  }
 
}