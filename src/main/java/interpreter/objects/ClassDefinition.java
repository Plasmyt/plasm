package interpreter.objects;

import java.util.HashMap;
import java.util.Map;

public class ClassDefinition {
    private String className;
    private final Map<String, Object> fields;
    private final Map<String, FunctionDefinition> methods;

    public ClassDefinition(String className) {
        this.className = className;
        this.fields = new HashMap<>();
        this.methods = new HashMap<>();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Object getField(String fieldName) {
        return fields.get(fieldName);
    }

    public void setField(String fieldName, Object value) {
        fields.put(fieldName, value);
    }

    public FunctionDefinition getMethod(String methodName) {
        return methods.get(methodName);
    }

    public void setMethod(String methodName, FunctionDefinition method) {
        methods.put(methodName, method);
    }
}