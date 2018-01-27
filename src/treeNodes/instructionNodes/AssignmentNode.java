package treeNodes.instructionNodes;

import treeVisitors.Visitor;

public class AssignmentNode extends InstructionNode {

	public AssignmentNode(String nodeName) {
		super(nodeName);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
