package AnnotationDemo;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import javax.sql.DataSource;

public class DaoFactory {
	/**
	 * �����ԣ�set������ע��
	 * @return
	 */
    public static BookDao createDao(){
    	
    	BookDao dao =new BookDao();
    	
    	//��dao��ע�����ݿ����ӳ�
       //����dao����������
    	try{
    		//��ʡ���Ŀ�����bean
    		BeanInfo info=Introspector.getBeanInfo(dao.getClass(),Object.class);
    		
    		//��ȡbean����������
    		PropertyDescriptor pds[]=info.getPropertyDescriptors();
    		for(int i=0;pds!=null&i<pds.length;i++){
    			PropertyDescriptor pd=pds[i];
    			
    			Method setMethod=pd.getWriteMethod();//��ȡ���Զ�Ӧ��set����
    			
    			//��set������û��ע��
    			Inject inject= setMethod.getAnnotation(Inject.class);
    			if(inject==null){
    				continue;
    			}
    			//��ȡ��������ӳ�����
    			Class propertyType=pd.getPropertyType();
    			Object datasource=propertyType.newInstance();
    			//��ע�⣬��ȡ����Ϣ���������ӳ�
    			DataSource ds=(DataSource)createDateSourceByInject(inject, datasource);
    			
    			setMethod.invoke(dao, ds);//��dao������ע��ds����
    		}
    		
    	}catch(Exception e){
          throw new RuntimeException(e);
    	}
    	return dao;
    }
   //��ע����Ϣ�������ӳ�
	private static Object createDateSourceByInject(Inject inject,Object ds) {
		
		//��ȡע���������Լ�������set����������ķ����޳�
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
		return ds;
	}
}
