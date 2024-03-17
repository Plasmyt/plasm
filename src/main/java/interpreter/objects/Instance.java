package interpreter.objects;

import java.util.HashMap;
import java.util.Map;

public class Instance {
    private final ClassDefinition classDefinition;
    private final Map<String, Object> fields = new HashMap<>();

    public Instance(ClassDefinition classDefinition) {
        this.classDefinition = classDefinition;
    }

    public Object getFieldValue(String fieldName) {
        if (fields.containsKey(fieldName)) {
            return fields.get(fieldName);
        } else {
            throw new RuntimeException("Field '" + fieldName + "' not found in instance of class '" + classDefinition.getClassName() + "'.");
        }
    }

    public void setFieldValue(String fieldName, Object value) {
        fields.put(fieldName, value);
    }

    @Override
    public String toString() {
        return "Instance of class: " + classDefinition.getClassName();
    }
}