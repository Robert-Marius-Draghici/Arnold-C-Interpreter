package treeVisitors;

import treeNodes.instructionNodes.*;
import treeNodes.operandNodes.*;

public interface Visitor {

	void visit(AssignmentNode assignmentNode);

	void visit(BodyNode bodyNode);

	void visit(DeclareNode declareNode);

	void visit(ElseBodyNode elseBodyNode);

	void visit(IfBodyNode ifBodyNode);

	void visit(IfNode ifNode);

	void visit(MainNode mainNode);

	void visit(OperatorNode operatorNode);

	void visit(PrintNode printNode);

	void visit(WhileNode whileNode);

	void visit(ConstantNode constantNode);

	void visit(StringNode stringNode);

	void visit(VariableNode variableNode);

}
