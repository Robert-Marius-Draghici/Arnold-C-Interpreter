package treeNodes.instructionNodes;

import java.util.ArrayList;   

import treeNodes.ASTNode;
import treeVisitors.RestrictedVisitor;

public abstract class InstructionNode extends ASTNode {
	
	protected ArrayList<ASTNode> childNodes;

	public InstructionNode(String nodeName) {
		super(nodeName);
		this.childNodes = new ArrayList<>();
	}

	public ArrayList<ASTNode> getChildNodes() {
		return childNodes;
	}

	public void setChildNodes(ArrayList<ASTNode> childNodes) {
		this.childNodes = childNodes;
	}
	
	@Override
	public void accept(RestrictedVisitor v) {
		v.visit(this);
	}

}
