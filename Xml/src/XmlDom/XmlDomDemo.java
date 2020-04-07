package XmlDom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**对文档进行dom解析
 * 1.创建工厂
 * 2.得到dom解析器
 * 3.解析xml文档，得到文档的document
 *
 */
public class XmlDomDemo {
      
	   @Test
	   public void read() throws Exception{
		  
	       DocumentBuilderFactory f=DocumentBuilderFactory.newInstance();
		   DocumentBuilder db=f.newDocumentBuilder();
		   Document d=db.parse("src/xmldemo/001.xml");
		   
		   NodeList list=d.getElementsByTagName("b");//返回对象
		   Node node=list.item(0);//读取标签<b>中的值
		   System.out.println(node.getTextContent());
		   
		   Element b1=(Element) d.getElementsByTagName("b1").item(0);//获取标签属性
		   System.out.println(b1.getAttribute("id"));
	   }
	   
	   @Test
	   //获取所有Node 节点对象
	   public void read2() throws Exception{
		  
	       DocumentBuilderFactory f=DocumentBuilderFactory.newInstance();
		   DocumentBuilder db=f.newDocumentBuilder();
		   Document d=db.parse("src/xmldemo/001.xml");
		   
		   Node root= d.getElementsByTagName("xml-body").item(0);
		   list(root);
		   
	   }

	private void list(Node node) {
		//只打印标签
		if(node instanceof Element){
		  System.out.println(node.getNodeName());
		  }
		 NodeList list=node.getChildNodes();
		 for(int i=0;i<list.getLength();i++){
			 Node child =list.item(i);
			 list(child);
		 }
	}
    
}
