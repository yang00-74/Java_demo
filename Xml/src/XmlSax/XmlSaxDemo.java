package XmlSax;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 * sax解析
 * @author Administrator
 *
 */
public class XmlSaxDemo {
     public static void main(String[] args) throws Exception {
    	 //1.创建解析器工厂
		SAXParserFactory f=SAXParserFactory.newInstance();
		//2.创建解析器
		SAXParser p=f.newSAXParser();
		//3.得到xml文档读取器
		XMLReader r=p.getXMLReader();
		//为读取器设置内容处理器
//		r.setContentHandler(new ListHandler());
		BeanListHandler h=new BeanListHandler();
		r.setContentHandler(h);
		//4.利用读取器解析xml文档
		r.parse("src/XmlSax/Book.xml");
		System.out.println(h.getBooks().size());
	}
}
//获取整个xml文档内容的处理器
class ListHandler extends DefaultHandler{
	@Override
	public void startElement(String uri, String localName, String name,
			Attributes atts) throws SAXException {
		System.out.println("<"+name+">");
		
	}

	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		System.out.println("</"+name+">");
		
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		System.out.println(new String(ch,start,length));
		
	}	
}
//把书的数据封装到javabean的处理器
class BeanListHandler extends DefaultHandler{
	private List list=new ArrayList();
	private Book book;
	private String currentTag;
	public List getBooks(){
		return list;
	}
	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		currentTag=name;
		if(name.equals("书")){
			book=new Book();
		}
	}
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if(currentTag!=null &&currentTag.equals("书名")){
			book.setName(new String(ch,start,length));
		}
		if(currentTag!=null &&currentTag.equals("售价")){
			book.setPrice(new String(ch,start,length));
		}
	}
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(qName.equals("书")){
			list.add(book);
		}
		currentTag=null;
	}	
	
}
