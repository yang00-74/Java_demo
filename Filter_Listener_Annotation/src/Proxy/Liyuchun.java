package Proxy;

public class Liyuchun implements Person {
   /* 
 * @see Proxy.Person#sing()
 */
public String  sing(String name ){
	   System.out.println("���糪 "+name+" ��");
	   return "����лл��裡";
   }
public String dance(String name){
	System.out.println("������ "+name+" ��");
	 return "����ллָ�裡";
}

}
