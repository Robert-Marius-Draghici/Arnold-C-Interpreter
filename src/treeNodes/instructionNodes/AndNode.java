package treeNodes.instructionNodes;

import treeVisitors.Visitor;

public class AndNode extends OperatorNode {

	public AndNode(String nodeName) {
		super(nodeName);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public Integer executeInstruction(Integer operand1, Integer operand2) {
		Integer result = 0;
		if (operand1 != 0 && operand2 != 0)
			result = 1;
		return result;
	}

}
