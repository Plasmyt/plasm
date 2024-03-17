package interpreter.objects;

import java.util.HashMap;
import java.util.Map;

public class Function {
    private String functionName;
    private FunctionDefinition functionDefinition;
    private Map<String, Integer> localVariables;

    public Function(String functionName, FunctionDefinition functionDefinition) {
        this.functionName = functionName;
        this.functionDefinition = functionDefinition;
        this.localVariables = new HashMap<>();
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public FunctionDefinition getFunctionDefinition() {
        return functionDefinition;
    }

    public void setFunctionDefinition(FunctionDefinition functionDefinition) {
        this.functionDefinition = functionDefinition;
    }

    public Map<String, Integer> getLocalVariables() {
        return localVariables;
    }

    public void setLocalVariables(Map<String, Integer> localVariables) {
        this.localVariables = localVariables;
    }

    public void addLocalVariable(String variableName, int value) {
        localVariables.put(variableName, value);
    }

    public int getLocalVariableValue(String variableName) {
        return localVariables.getOrDefault(variableName, 0);
    }
}
