package AnnotationDemo;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.sql.DataSource;

public class DaoFactory2 {
	/**
	 * ���ֶ�ע��
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
   public static BookDao createDao(){
	   BookDao dao=new BookDao();
	   
	   Field field[]=dao.getClass().getDeclaredFields();//��ȡ�����ֶ�
	   for(int i=0;field!=null&&i<field.length;i++){
		   //�鿴�ֶ����Ƿ���ע��
		   Field f=field[i];
		   f.setAccessible(true);
		   Inject inject=f.getAnnotation(Inject.class);
		   if(inject==null){
			   continue;
		   }
		  try {
			DataSource ds= (DataSource) f.getType().newInstance();
			//��ע����Ϣ�������ӳ�
			inject2Datasource(inject,ds);
			
			f.set(dao, ds);
			
		} catch (Exception e) {			
			e.printStackTrace();
		} 		   
	   }
	return dao;
   }
    //ע����Ϣ�������ӳ�
	private static void inject2Datasource(Inject inject, DataSource ds) {
		
		Method method[]=inject.getClass().getMethods();
		for(Method m:method){
			String methodName=m.getName();//��ȡע���е���������
			
			//ͨ���������������Ƚ�ds�������������ע�����������Ƿ���ͬ
			PropertyDescriptor pd=null;
			try {			
			     pd=new PropertyDescriptor(methodName,ds.getClass());
			     
			     //�õ�ע�������Ե�ֵ
			     Object value=m.invoke(inject, null);
			     //��ֵ���뵽�����bean(ds)�Ķ�Ӧ������
			     pd.getWriteMethod().invoke(ds, value);
			     
			} catch (Exception e) {
				continue;
			}	
		}	
	}
}
