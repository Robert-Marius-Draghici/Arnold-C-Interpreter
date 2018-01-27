package treeNodes.instructionNodes;

import treeVisitors.PrintVisitor;
import treeVisitors.Visitor;

public class MainNode extends InstructionNode {

	public MainNode(String nodeName) {
		super(nodeName);
		
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	public void accept(PrintVisitor printVisitor) {
		printVisitor.visit(this);
	}

}
