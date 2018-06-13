package rpc.connectiontools;

import java.io.Serializable;

public class RpcRequest implements Serializable {

    private static final long serialVersionUID = 363172382175902160L;
    private String className;
    private String methodName;
    private Object[] parmeters;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParmeters() {
        return parmeters;
    }

    public void setParmeters(Object[] parmeters) {
        this.parmeters = parmeters;
    }
}
