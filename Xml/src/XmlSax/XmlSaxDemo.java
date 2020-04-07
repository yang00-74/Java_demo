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
 * sax����
 * @author Administrator
 *
 */
public class XmlSaxDemo {
     public static void main(String[] args) throws Exception {
    	 //1.��������������
		SAXParserFactory f=SAXParserFactory.newInstance();
		//2.����������
		SAXParser p=f.newSAXParser();
		//3.�õ�xml�ĵ���ȡ��
		XMLReader r=p.getXMLReader();
		//Ϊ��ȡ���������ݴ�����
//		r.setContentHandler(new ListHandler());
		BeanListHandler h=new BeanListHandler();
		r.setContentHandler(h);
		//4.���ö�ȡ������xml�ĵ�
		r.parse("src/XmlSax/Book.xml");
		System.out.println(h.getBooks().size());
	}
}
//��ȡ����xml�ĵ����ݵĴ�����
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
//��������ݷ�װ��javabean�Ĵ�����
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
		if(name.equals("��")){
			book=new Book();
		}
	}
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if(currentTag!=null &&currentTag.equals("����")){
			book.setName(new String(ch,start,length));
		}
		if(currentTag!=null &&currentTag.equals("�ۼ�")){
			book.setPrice(new String(ch,start,length));
		}
	}
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(qName.equals("��")){
			list.add(book);
		}
		currentTag=null;
	}	
	
}
