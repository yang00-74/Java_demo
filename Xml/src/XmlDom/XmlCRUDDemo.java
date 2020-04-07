package XmlDom;

import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlCRUDDemo {
       @Test
       public void add() throws Exception{

	       DocumentBuilderFactory f=DocumentBuilderFactory.newInstance();
		   DocumentBuilder db=f.newDocumentBuilder();
		   Document d=db.parse("src/xmldemo/001.xml");
		   //创建节点
		   Element position =d.createElement("Position");
		   position.setTextContent("北美洲");
		   //挂载节点在标签b1中
		   Element e =(Element) d.getElementsByTagName("b1").item(0);
		   e.appendChild(position);
		   
		  // e.removeAttribute("id");//删除节点属性
		   
		   //更新后的内存写回xml文档
		   TransformerFactory tff=TransformerFactory.newInstance();
		   Transformer tf=tff.newTransformer();
		   tf.transform(new DOMSource(d), new StreamResult(
				   new FileOutputStream("src/xmldemo/001.xml")));
		   
       }
}
