package treeNodes.instructionNodes;

import treeVisitors.Visitor;

public class IfNode extends InstructionNode {

	public IfNode(String nodeName) {
		super(nodeName);
		
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
