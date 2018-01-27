package treeNodes.instructionNodes;

import treeVisitors.Visitor;

public class IfBodyNode extends InstructionNode {

	public IfBodyNode(String nodeName) {
		super(nodeName);
		
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
