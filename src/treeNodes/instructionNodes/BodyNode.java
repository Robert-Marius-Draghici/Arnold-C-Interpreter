package treeNodes.instructionNodes;

import treeVisitors.Visitor;

public class BodyNode extends InstructionNode {

	public BodyNode(String nodeName) {
		super(nodeName);		
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
