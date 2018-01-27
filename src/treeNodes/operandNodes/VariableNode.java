package treeNodes.operandNodes;

import treeVisitors.Visitor;

public class VariableNode extends OperandNode {

	public VariableNode(String nodeName, String variableName, String variableValue) {
		super(nodeName, variableName, variableValue);
		
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
