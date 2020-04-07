package Proxy;

public class Liyuchun implements Person {
   /* 
 * @see Proxy.Person#sing()
 */
public String  sing(String name ){
	   System.out.println("´º¸ç³ª "+name+" ÁË");
	   return "´º¸çĞ»Ğ»µã¸è£¡";
   }
public String dance(String name){
	System.out.println("´º¸çÌø "+name+" ÁË");
	 return "´º¸çĞ»Ğ»Ö¸Îè£¡";
}

}
