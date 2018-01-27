package treeNodes.instructionNodes;

import treeVisitors.Visitor;

public class GreaterThanNode extends OperatorNode {

	public GreaterThanNode(String nodeName) {
		super(nodeName);

	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public Integer executeInstruction(Integer operand1, Integer operand2) {
		Integer result = 0;
		if (operand1 > operand2)
			result = 1;
		return result;
	}

}
