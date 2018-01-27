package treeNodes.instructionNodes;

import treeVisitors.Visitor;

public class ModuloNode extends OperatorNode {

	public ModuloNode(String nodeName) {
		super(nodeName);

	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public Integer executeInstruction(Integer operand1, Integer operand2) {
		return operand1 % operand2;
	}

}
