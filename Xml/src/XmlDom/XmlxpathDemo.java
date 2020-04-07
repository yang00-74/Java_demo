package XmlDom;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class XmlxpathDemo {
    @Test
    public void test() throws Exception{
    	SAXReader r=new SAXReader();
    	Document d=r.read(new File("src/XmlDom/Book.xml"));
    	
    	Element e= (Element) d.selectSingleNode("//书名");
    	System.out.println(e.getText());
    	Element e1= (Element) d.selectNodes("//书名").get(1);
    	System.out.println(e1.getText());
    }
}
