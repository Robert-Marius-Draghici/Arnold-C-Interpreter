package treeNodes.operandNodes;

import treeVisitors.Visitor;

public class StringNode extends OperandNode {

	public StringNode(String nodeName, String variableName, String variableValue) {
		super(nodeName, variableName, variableValue);
		
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
