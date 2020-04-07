package AnnotationDemo;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import javax.sql.DataSource;

public class DaoFactory {
	/**
	 * 从属性（set方法）注解
	 * @return
	 */
    public static BookDao createDao(){
    	
    	BookDao dao =new BookDao();
    	
    	//向dao中注入数据库连接池
       //解析dao的所有属性
    	try{
    		//内省获得目标类的bean
    		BeanInfo info=Introspector.getBeanInfo(dao.getClass(),Object.class);
    		
    		//获取bean上所有属性
    		PropertyDescriptor pds[]=info.getPropertyDescriptors();
    		for(int i=0;pds!=null&i<pds.length;i++){
    			PropertyDescriptor pd=pds[i];
    			
    			Method setMethod=pd.getWriteMethod();//获取属性对应的set方法
    			
    			//看set方法有没有注解
    			Inject inject= setMethod.getAnnotation(Inject.class);
    			if(inject==null){
    				continue;
    			}
    			//获取所需的连接池类型
    			Class propertyType=pd.getPropertyType();
    			Object datasource=propertyType.newInstance();
    			//有注解，获取其信息，创建连接池
    			DataSource ds=(DataSource)createDateSourceByInject(inject, datasource);
    			
    			setMethod.invoke(dao, ds);//往dao对象上注入ds对象
    		}
    		
    	}catch(Exception e){
          throw new RuntimeException(e);
    	}
    	return dao;
    }
   //用注解信息创建连接池
	private static Object createDateSourceByInject(Inject inject,Object ds) {
		
		//获取注解中属于自己的所有set方法，父类的方法剔除
		Method method[]=inject.getClass().getMethods();
		for(Method m:method){
			String methodName=m.getName();//获取注解中的属性名称
			
			//通过属性描述器，比较ds上所需的属性与注解中属性名是否相同
			PropertyDescriptor pd=null;
			try {
				
			     pd=new PropertyDescriptor(methodName,ds.getClass());
			     
			     //得到注解中属性的值
			     Object value=m.invoke(inject, null);
			     //将值填入到所需的bean(ds)的对应属性上
			     pd.getWriteMethod().invoke(ds, value);
			     
			} catch (Exception e) {
				continue;
			}	
		}	
		return ds;
	}
}
