package XmlDom;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.Document;
import org.junit.Test;

public class XmlDom4j  {
	
	@Test
	public void listFile()throws Exception{
      SAXReader r=new SAXReader();
      Document d=r.read(new File("src/XmlDom/Book.xml"));
      Element root=d.getRootElement();
      list(root);
	}
	public void list(Element e){
		System.out.println(e.getName());
		List<Element> list=e.elements();
		for(Element child:list){
			list(child);
		}
		
	}
	//获取特定结点内容、属性
	@Test
	public void test() throws Exception{
		SAXReader r=new SAXReader();
	    Document d=r.read(new File("src/XmlDom/Book.xml"));
	    
	    Element bookname=d.getRootElement().element("书").element("书名");
	    System.out.println(bookname.attributeValue("name"));
	   
	    String value=d.getRootElement().element("书").element("书名").getText();
	    System.out.println(value);
	}
	//添加标签
	@Test
	public void test2() throws Exception{
		SAXReader r=new SAXReader();
	    Document d=r.read(new File("src/XmlDom/Book.xml"));
	    
	    Element e=DocumentHelper.createElement("描述");
	    e.setText("是本好书");
	    
	    d.getRootElement().element("书").add(e);
//	       控制编码方式，以防乱码
//	    XMLWriter w=new XMLWriter(new OutputStreamWriter(
//	    		new FileOutputStream("src/XmlDom/Book.xml"),"UTF-8"));
	    OutputFormat format=OutputFormat.createPrettyPrint();
	    format.setEncoding("UTF-8");//格式化控制器
	    XMLWriter w=new XMLWriter(new FileOutputStream("src/XmlDom/Book.xml"),format);
	    w.write(d);
	    w.close();
	}
}
