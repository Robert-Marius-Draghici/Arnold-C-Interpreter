package treeVisitors;

import java.util.LinkedHashMap;

import treeNodes.ASTNode;
import treeNodes.instructionNodes.*;
import treeNodes.operandNodes.*;

public class InterpreterVisitor implements Visitor {

	private LinkedHashMap<String, Integer> variables;
	Integer operand1, operand2, result;

	public InterpreterVisitor() {
		variables = new LinkedHashMap<>();
		result = 0;
	}


	@Override
	public void visit(AssignmentNode assignmentNode) {
		OperandNode leftNode = (OperandNode) assignmentNode.getChildNodes().get(0);
		String variable = leftNode.getVariableName();
		Integer value = 0;
		if (variables.containsKey(variable))
			value = variables.get(variable);

		ASTNode rightNode = assignmentNode.getChildNodes().get(1);
		switch (rightNode.getNodeName()) {
			case "ConstantNode":
				value = Integer.parseInt(((OperandNode) rightNode).getVariableName());
				break;

			case "RvalNode":
				value = variables.get(((OperandNode) rightNode).getVariableName());
				break;

			default:
				rightNode.accept(this);
				value = result;
		}

		variables.put(variable, value);
	}

	@Override
	public void visit(BodyNode bodyNode) {
		for (ASTNode childNode : bodyNode.getChildNodes())
			childNode.accept(this);
	}

	@Override
	public void visit(DeclareNode declareNode) {
		OperandNode variableNode = (OperandNode) declareNode.getChildNodes().get(0);
		String variableName = variableNode.getVariableName();
		OperandNode valueNode = (OperandNode) declareNode.getChildNodes().get(1);
		Integer value = Integer.parseInt(valueNode.getVariableName());
		variables.put(variableName, value);
	}

	@Override
	public void visit(ElseBodyNode elseBodyNode) {
		for (ASTNode childNode : elseBodyNode.getChildNodes())
			childNode.accept(this);
	}

	@Override
	public void visit(IfBodyNode ifBodyNode) {
		for (ASTNode childNode : ifBodyNode.getChildNodes())
			childNode.accept(this);
	}

	@Override
	public void visit(IfNode ifNode) {
		OperandNode condition = (OperandNode) ifNode.getChildNodes().get(0);
		InstructionNode ifBody = (InstructionNode) ifNode.getChildNodes().get(1);
		InstructionNode elseBody = null;
		if (ifNode.getChildNodes().size() > 2)
			elseBody = (InstructionNode) ifNode.getChildNodes().get(2);
		if (variables.get(condition.getVariableName()) != 0)
			ifBody.accept(this);
		else {
			if (elseBody != null)
				elseBody.accept(this);
		}
	}

	@Override
	public void visit(MainNode mainNode) {
		for (ASTNode childNode : mainNode.getChildNodes())
			childNode.accept(this);
	}

	@Override
	public void visit(PrintNode printNode) {
		printNode.getChildNodes().get(0).accept(this);
	}

	@Override
	public void visit(WhileNode whileNode) {
		OperandNode condition = (OperandNode) whileNode.getChildNodes().get(0);
		InstructionNode body = (InstructionNode) whileNode.getChildNodes().get(1);
		while (variables.get(condition.getVariableName()) != 0)
			body.accept(this);
	}


	@Override
	public void visit(ConstantNode constantNode) {
		System.out.println(constantNode.getVariableValue());
	}

	@Override
	public void visit(StringNode stringNode) {
		System.out.println(stringNode.getVariableValue());
	}

	@Override
	public void visit(VariableNode variableNode) {
		System.out.println(variables.get(variableNode.getVariableName()));
	}

	@Override
	public void visit(OperatorNode operatorNode) {
		ASTNode leftNode = operatorNode.getChildNodes().get(0);
		OperandNode rightNode = (OperandNode) operatorNode.getChildNodes().get(1);
		switch (leftNode.getNodeName()) {
			case "ConstantNode":
				switch (rightNode.getNodeName()) {
					case "ConstantNode":
						operand1 = Integer.parseInt(((ConstantNode) leftNode).getVariableName());
						operand2 = Integer.parseInt(((ConstantNode) rightNode).getVariableName());
						result = operatorNode.executeInstruction(operand1, operand2);

						break;

					case "RvalNode":
						operand1 = Integer.parseInt(((ConstantNode) leftNode).getVariableName());
						operand2 = variables.get(((RvalNode) rightNode).getVariableName());
						result = operatorNode.executeInstruction(operand1, operand2);
						break;
				}
				break;

			case "RvalNode":
				switch (rightNode.getNodeName()) {
					case "ConstantNode":
						operand1 = variables.get(((RvalNode) leftNode).getVariableName());
						operand2 = Integer.parseInt(((ConstantNode) rightNode).getVariableName());
						result = operatorNode.executeInstruction(operand1, operand2);
						break;

					case "RvalNode":
						operand1 = variables.get(((RvalNode) leftNode).getVariableName());
						operand2 = variables.get(((RvalNode) rightNode).getVariableName());
						result = operatorNode.executeInstruction(operand1, operand2);
						break;
				}
				break;

			default:
				switch (rightNode.getNodeName()) {
					case "ConstantNode":
						leftNode.accept(this);
						operand2 = Integer.parseInt(((ConstantNode) rightNode).getVariableName());
						result = operatorNode.executeInstruction(result, operand2);
						break;

					case "RvalNode":
						leftNode.accept(this);
						operand2 = variables.get(((RvalNode) rightNode).getVariableName());
						result = operatorNode.executeInstruction(result, operand2);
						break;
				}
				break;
		}
	}

}
