package treeNodes.instructionNodes;

import treeVisitors.Visitor;

public class ElseBodyNode extends InstructionNode {

	public ElseBodyNode(String nodeName) {
		super(nodeName);
		
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
