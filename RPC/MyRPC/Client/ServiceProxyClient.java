package RPC.MyRPC.Client;

import RPC.MyRPC.Common.ServiceProtocol;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Ye.Yang
 * @date 19-8-8 上午9:51
 * <p>
 * 客户端服务代理,实际是将本地服务调用通过动态代理的方式将远程服务实现对象返回.
 **/
public class ServiceProxyClient {

    public static <T> T getInstance(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new ServiceProxy(clazz));
    }


    public static class ServiceProxy implements InvocationHandler {

        private Class clazz;

        public ServiceProxy(Class clazz) {
            this.clazz = clazz;
        }


        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

            //将远程调用的参数组装为model
            ServiceProtocol.ProtocolModel model = new ServiceProtocol.ProtocolModel();
            model.setClazz(clazz.getName());
            model.setMethod(method.getName());
            model.setArgs(objects);
            String[] argType = new String[method.getParameterTypes().length];
            for (int i = 0; i < argType.length; i++) {
                argType[i] = method.getParameterTypes()[i].getName();
            }
            model.setArgTypes(argType);

            //通过ClientRemoter发起远程调用, 返回远程服务对象
            Object object = ClientRemoter.getInstance().getDataRemote(model);

            return object;
        }
    }
}
