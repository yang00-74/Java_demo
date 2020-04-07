package Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LiyuchunProxy {
     
	public Person chunchun=new Liyuchun();
	
	public Person createProxy(){
		
		//创建对象的代理对象  参数：代理的类装载器，被代理的类的接口，代理要干的事情
		return (Person) Proxy.newProxyInstance(LiyuchunProxy.class.getClassLoader(), chunchun.getClass().getInterfaces(), new InvocationHandler() {				
					/**
					 * proxy:把代理的对象自身传递进来
					 * method:代表当前调用方法
					 * args:调用方法的参数
					 */			
			        @Override
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
						String methodname=method.getName();
						if(methodname.equals("sing")){
							System.out.println("付钱10000");
							return method.invoke(chunchun, args);//通过反射找被代理对象调用其方法
						}else if(methodname.equals("dance")){
							System.out.println("付钱15000");
							return method.invoke(chunchun, args);
						}else{
							System.out.println("春哥不支持这个服务");
						}						
						return null;
					}
				});
	}
}
