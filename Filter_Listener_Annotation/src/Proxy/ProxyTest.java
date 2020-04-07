package Proxy;

public class ProxyTest {
     public static void main(String[] args) {
		LiyuchunProxy p=new LiyuchunProxy();
		Person ps=p.createProxy();
		
		System.out.println(ps.sing("Ìý»°"));
		System.out.println(ps.dance("½ÖÎè"));
	}
}
