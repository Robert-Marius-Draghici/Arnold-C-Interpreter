package treeNodes.instructionNodes;

import treeVisitors.Visitor;

public class WhileNode extends InstructionNode {

	public WhileNode(String nodeName) {
		super(nodeName);
		
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
