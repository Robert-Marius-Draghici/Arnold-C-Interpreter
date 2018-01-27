package treeVisitors;

import treeNodes.instructionNodes.InstructionNode;
import treeNodes.operandNodes.OperandNode;

public interface RestrictedVisitor {
	public void visit(InstructionNode instructionNode);

	public void visit(OperandNode operandNode);
}
