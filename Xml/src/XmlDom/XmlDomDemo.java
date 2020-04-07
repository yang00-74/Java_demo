package XmlDom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**���ĵ�����dom����
 * 1.��������
 * 2.�õ�dom������
 * 3.����xml�ĵ����õ��ĵ���document
 *
 */
public class XmlDomDemo {
      
	   @Test
	   public void read() throws Exception{
		  
	       DocumentBuilderFactory f=DocumentBuilderFactory.newInstance();
		   DocumentBuilder db=f.newDocumentBuilder();
		   Document d=db.parse("src/xmldemo/001.xml");
		   
		   NodeList list=d.getElementsByTagName("b");//���ض���
		   Node node=list.item(0);//��ȡ��ǩ<b>�е�ֵ
		   System.out.println(node.getTextContent());
		   
		   Element b1=(Element) d.getElementsByTagName("b1").item(0);//��ȡ��ǩ����
		   System.out.println(b1.getAttribute("id"));
	   }
	   
	   @Test
	   //��ȡ����Node �ڵ����
	   public void read2() throws Exception{
		  
	       DocumentBuilderFactory f=DocumentBuilderFactory.newInstance();
		   DocumentBuilder db=f.newDocumentBuilder();
		   Document d=db.parse("src/xmldemo/001.xml");
		   
		   Node root= d.getElementsByTagName("xml-body").item(0);
		   list(root);
		   
	   }

	private void list(Node node) {
		//ֻ��ӡ��ǩ
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
