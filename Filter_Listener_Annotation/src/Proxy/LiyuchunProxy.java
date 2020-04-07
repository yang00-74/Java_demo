package Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LiyuchunProxy {
     
	public Person chunchun=new Liyuchun();
	
	public Person createProxy(){
		
		//��������Ĵ������  �������������װ���������������Ľӿڣ�����Ҫ�ɵ�����
		return (Person) Proxy.newProxyInstance(LiyuchunProxy.class.getClassLoader(), chunchun.getClass().getInterfaces(), new InvocationHandler() {				
					/**
					 * proxy:�Ѵ���Ķ��������ݽ���
					 * method:����ǰ���÷���
					 * args:���÷����Ĳ���
					 */			
			        @Override
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
						String methodname=method.getName();
						if(methodname.equals("sing")){
							System.out.println("��Ǯ10000");
							return method.invoke(chunchun, args);//ͨ�������ұ������������䷽��
						}else if(methodname.equals("dance")){
							System.out.println("��Ǯ15000");
							return method.invoke(chunchun, args);
						}else{
							System.out.println("���粻֧���������");
						}						
						return null;
					}
				});
	}
}
