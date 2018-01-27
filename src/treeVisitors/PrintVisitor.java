package treeVisitors;

import treeNodes.ASTNode;
import treeNodes.instructionNodes.InstructionNode;
import treeNodes.operandNodes.OperandNode;

public class PrintVisitor implements RestrictedVisitor {

	private Integer indentLevel;

	public PrintVisitor() {
		this.indentLevel = 0;
	}

	@Override
	public void visit(InstructionNode instructionNode) {
		for (int i = 0; i < indentLevel; i++)
			System.out.print("\t");
		
		System.out.println(instructionNode.getNodeName());
		
		for(ASTNode childNode : instructionNode.getChildNodes()) {
			indentLevel++;
			childNode.accept( this);
			indentLevel--;
		}
	}

	@Override
	public void visit(OperandNode operandNode) {
		for (int i = 0; i < indentLevel; i++)
			System.out.print("\t");
		
		System.out.println(operandNode.getNodeName() + " <" + operandNode.getVariableName() + ">");
	}

}
