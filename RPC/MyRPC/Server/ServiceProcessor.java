package RPC.MyRPC.Server;

import RPC.MyRPC.Common.ServiceProtocol;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Ye.Yang
 * @date 19-8-8 上午10:52
 *
 * 服务端处理层,负责发布管理服务,并定位处理客户端的调用
 **/
public class ServiceProcessor {

    private static volatile ServiceProcessor instance;

    private ServiceProcessor() { }

    public static ServiceProcessor getInstance() {
        if (null == instance) {
            synchronized (ServiceProcessor.class) {
                if (null == instance) {
                    instance = new ServiceProcessor();
                }
            }
        }
        return instance;
    }

    private static final ConcurrentMap<String, Object> PROCESSOR_INSTANCE_MAP = new ConcurrentHashMap<String, Object>();


    public boolean publish(Class clazz, Object obj) {
        return PROCESSOR_INSTANCE_MAP.putIfAbsent(clazz.getName(), obj) != null;
    }

    public Object process(ServiceProtocol.ProtocolModel model) {
        try {
            Class clazz = Class.forName(model.getClazz());

            Class[] types = new Class[model.getArgTypes().length];
            for (int i = 0; i < types.length; i++) {
                types[i] = Class.forName(model.getArgTypes()[i]);
            }

            Method method = clazz.getMethod(model.getMethod(), types);

            Object obj = PROCESSOR_INSTANCE_MAP.get(model.getClazz());
            if (obj == null) {
                return null;
            }

            return method.invoke(obj, model.getArgs());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
