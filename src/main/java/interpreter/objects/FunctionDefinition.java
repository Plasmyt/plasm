package interpreter.objects;

import parser.ASTNode;

import java.util.List;

public class FunctionDefinition {
    private String functionName;
    private List<String> parameters;
    private ASTNode body;

    public FunctionDefinition(String functionName, List<String> parameters, ASTNode body) {
        this.functionName = functionName;
        this.parameters = parameters;
        this.body = body;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public ASTNode getBody() {
        return body;
    }

    public void setBody(ASTNode body) {
        this.body = body;
    }
}
