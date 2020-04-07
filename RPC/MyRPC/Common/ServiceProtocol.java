package RPC.MyRPC.Common;

import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.JSONFunctions;

import java.io.Serializable;

/**
 * @author Ye.Yang
 * @date 19-8-7 下午5:54
 *
 * 协议层 对传输通信的远程调用请求接口和方法参数等数据按照规定的格式进行组装编码和解码
 **/
public class ServiceProtocol {

    private static volatile ServiceProtocol instance;

    private ServiceProtocol() { }

    public static ServiceProtocol getInstance() {
        if (null == instance) {
            synchronized (ServiceProtocol.class) {
                if (null == instance) {
                    instance = new ServiceProtocol();
                }
            }
        }
        return instance;
    }

    /**
     * 参数传递的模型
     */
    public static class ProtocolModel implements Serializable {
        private static final long serialVersionUID = 1L;
        private String clazz;
        private String method;
        private String[] argTypes;
        private Object[] args;

        public String getClazz() {
            return clazz;
        }

        public void setClazz(String clazz) {
            this.clazz = clazz;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String[] getArgTypes() {
            return argTypes;
        }

        public void setArgTypes(String[] argTypes) {
            this.argTypes = argTypes;
        }

        public Object[] getArgs() {
            return args;
        }

        public void setArgs(Object[] args) {
            this.args = args;
        }
    }
}
