package treeNodes;

import treeVisitors.RestrictedVisitor;
import treeVisitors.Visitor;

public abstract class ASTNode {
	protected String nodeName;

	public ASTNode(String nodeName) {
		super();
		this.nodeName = nodeName;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public abstract void accept(Visitor v);

	public abstract void accept(RestrictedVisitor printVisitor);

}
