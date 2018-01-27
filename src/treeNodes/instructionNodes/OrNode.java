package treeNodes.instructionNodes;

import treeVisitors.Visitor;

public class OrNode extends OperatorNode {

	public OrNode(String nodeName) {
		super(nodeName);
		
	}

	@Override
	public Integer executeInstruction(Integer operand1, Integer operand2) {
		Integer result = 0;
		if (operand1 == 0 && operand2 == 0)
			result = 0;
		else
			result = 1;
		return result;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
