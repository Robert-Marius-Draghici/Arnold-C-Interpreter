package treeNodes.instructionNodes;

import treeVisitors.Visitor;

public class DeclareNode extends InstructionNode {

	public DeclareNode(String nodeName) {
		super(nodeName);
		
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
