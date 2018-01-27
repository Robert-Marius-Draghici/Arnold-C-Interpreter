package treeNodes.instructionNodes;

import treeVisitors.Visitor;

public class PrintNode extends InstructionNode {

	public PrintNode(String nodeName) {
		super(nodeName);

	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
