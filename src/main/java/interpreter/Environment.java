package interpreter;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    private final Map<String, Object> values = new HashMap<>();
    private final Environment enclosingEnvironment;

    public Environment() {
        this.enclosingEnvironment = null;
    }

    public Environment(Environment enclosingEnvironment) {
        this.enclosingEnvironment = enclosingEnvironment;
    }

    public void define(String name, Object value) {
        values.put(name, value);
    }

    public Object get(String name) {
        Object value = values.get(name);
        if (value == null && enclosingEnvironment != null) {
            return enclosingEnvironment.get(name);
        }
        return value;
    }

    public void assign(String name, Object value) {
        if (values.containsKey(name)) {
            values.put(name, value);
            return;
        }
        if (enclosingEnvironment != null) {
            enclosingEnvironment.assign(name, value);
            return;
        }
        throw new RuntimeException("Undefined variable: " + name);
    }
}