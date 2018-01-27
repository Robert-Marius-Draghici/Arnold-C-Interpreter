package treeNodes.operandNodes;

import treeNodes.ASTNode;
import treeVisitors.RestrictedVisitor;
import treeVisitors.Visitor;

public class OperandNode extends ASTNode {

	protected String variableName;
	protected String variableValue;

	public OperandNode(String nodeName) {
		super(nodeName);
	}

	public OperandNode(String nodeName, String variableName, String variableValue) {
		super(nodeName);
		this.variableName = variableName;
		this.variableValue = variableValue;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getVariableValue() {
		return variableValue;
	}

	public void setVariableValue(String variableValue) {
		this.variableValue = variableValue;
	}

	@Override
	public void accept(Visitor v) {
	
	}
	
	@Override
	public void accept(RestrictedVisitor v) {
		v.visit(this);
	}
	
}
