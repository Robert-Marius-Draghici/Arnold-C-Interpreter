package treeNodes;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Stack;

import abstractSyntaxTree.InstructionChecker;
import treeNodes.instructionNodes.*;
import treeNodes.operandNodes.*;

public class NodeFactory {

	private static NodeFactory INSTANCE = null;
	private InstructionChecker checkerInstance;
	private LinkedHashMap<String, String> instructionMap;

	private NodeFactory() {
		checkerInstance = InstructionChecker.getInstance();
		instructionMap = checkerInstance.getInstructions();

	}

	public static NodeFactory getInstance() {
		if (INSTANCE == null)
			INSTANCE = new NodeFactory();
		return INSTANCE;
	}

	private void createAssignmentNode(InstructionNode parent, BufferedReader bufferedReader)
			throws IOException {
		String line = null;
		Stack<String> instructionStack = new Stack<String>();
		ASTNode assignmentParent = null;

		do {
			line = bufferedReader.readLine();
			line = line.trim();
			String instruction = checkerInstance.extractInstructionName(line);

			if (checkerInstance.checkInstruction(instruction)
					&& !checkerInstance.isEndOfInstruction(instruction)) {
				instructionStack.push(line);
			}

		} while (!line.equals("ENOUGH TALK"));

		while (!instructionStack.isEmpty()) {
			line = instructionStack.pop();
			String instruction = checkerInstance.extractInstructionName(line);
			String instructionArgument = checkerInstance.extractInstructionArgument(line,
					instruction);
			assignmentParent = createASTNode(parent, bufferedReader, instruction,
					instructionArgument);

			parent.getChildNodes().add(0, assignmentParent);
			if (instruction.equals("HERE IS MY INVITATION"))
				break;
			parent = (InstructionNode) assignmentParent;
		}
	}

	private void createIfBodyNode(InstructionNode ifNode, InstructionNode parent,
			BufferedReader bufferedReader) throws IOException {
		String line = null;

		do {
			line = bufferedReader.readLine();
			line = line.trim();
			String instruction = checkerInstance.extractInstructionName(line);
			String instructionArgument = checkerInstance.extractInstructionArgument(line,
					instruction);
			if (checkerInstance.checkInstruction(instruction)
					&& !checkerInstance.isEndOfInstruction(instruction)) {
				if (instruction.equals("BULLSHIT")) 
					parent = (InstructionNode) createASTNode(ifNode, bufferedReader, instruction,
							instructionArgument);
				else
					createASTNode(parent, bufferedReader, instruction, instructionArgument);
			}

		} while (!line.equals("YOU HAVE NO RESPECT FOR LOGIC"));
	}

	private void createBodyNode(ASTNode parent, BufferedReader bufferedReader) throws IOException {
		String line = null;

		do {
			line = bufferedReader.readLine();
			line = line.trim();

			String instruction = checkerInstance.extractInstructionName(line);
			String instructionArgument = checkerInstance.extractInstructionArgument(line,
					instruction);

			if (checkerInstance.checkInstruction(instruction)
					&& !checkerInstance.isEndOfInstruction(instruction)) {
				createASTNode((InstructionNode) parent, bufferedReader, instruction,
						instructionArgument);
			}

		} while (!line.equals("CHILL"));
	}

	private OperandNode createOperand(String instructionArgument, boolean isPrintInstruction) {
		OperandNode node = null;

		if (instructionArgument.matches("[0-9]+") || instructionArgument.equals("@NO PROBLEMO")
				|| instructionArgument.equals("@I LIED"))
			node = new ConstantNode("ConstantNode",
					checkerInstance.convertArgument(instructionArgument).toString(),
					checkerInstance.convertArgument(instructionArgument).toString());
		else if (!instructionArgument.contains(" ")) {
			if (isPrintInstruction)
				node = new VariableNode("VariableNode", instructionArgument, null);
			else
				node = new RvalNode("RvalNode", instructionArgument, null);
		} else {
			instructionArgument = instructionArgument.replace("\"", " ").trim();
			node = new StringNode("StringNode", instructionArgument, instructionArgument);
		}
		return node;
	}

	private InstructionNode createOperatorNode(String instruction) {
		switch (instruction) {
			case "GET UP":
				return new SumNode(instructionMap.get(instruction));

			case "GET DOWN":
				return new DifferenceNode(instructionMap.get(instruction));

			case "YOU'RE FIRED":
				return new MultiplicationNode(instructionMap.get(instruction));

			case "HE HAD TO SPLIT":
				return new DivisionNode(instructionMap.get(instruction));

			case "I LET HIM GO":
				return new ModuloNode(instructionMap.get(instruction));

			case "YOU ARE NOT YOU YOU ARE ME":
				return new EqualToNode(instructionMap.get(instruction));

			case "LET OFF SOME STEAM BENNET":
				return new GreaterThanNode(instructionMap.get(instruction));

			case "CONSIDER THAT A DIVORCE":
				return new OrNode(instructionMap.get(instruction));

			case "KNOCK KNOCK":
				return new AndNode(instructionMap.get(instruction));

		}
		return null;
	}

	public ASTNode createASTNode(InstructionNode parent, BufferedReader bufferedReader,
			String instruction, String instructionArgument) throws IOException {
		ASTNode newNode = null;
		String line = null;
		InstructionChecker checkerInstance = InstructionChecker.getInstance();

		LvalNode lvalNode;
		OperandNode node;
		switch (instruction) {
			case "HEY CHRISTMAS TREE":
				newNode = new DeclareNode(instructionMap.get(instruction));
				line = bufferedReader.readLine();
				String initialize = checkerInstance.extractInstructionName(line);
				String value = checkerInstance.extractInstructionArgument(line, initialize);
				lvalNode = new LvalNode("LvalNode", instructionArgument, value);
				((InstructionNode) newNode).getChildNodes().add(lvalNode);
				ConstantNode constantNode = new ConstantNode("ConstantNode",
						checkerInstance.convertArgument(value).toString(),
						checkerInstance.convertArgument(value).toString());
				((InstructionNode) newNode).getChildNodes().add(constantNode);
				parent.getChildNodes().add(newNode);
				break;

			case "GET TO THE CHOPPER":
				newNode = new AssignmentNode(instructionMap.get(instruction));
				lvalNode = new LvalNode("LvalNode", instructionArgument, null);

				createAssignmentNode((InstructionNode) newNode, bufferedReader);
				((InstructionNode) newNode).getChildNodes().add(0, lvalNode);

				parent.getChildNodes().add(newNode);
				break;

			case "GET UP":
			case "GET DOWN":
			case "YOU'RE FIRED":
			case "HE HAD TO SPLIT":
			case "I LET HIM GO":
			case "YOU ARE NOT YOU YOU ARE ME":
			case "LET OFF SOME STEAM BENNET":
			case "CONSIDER THAT A DIVORCE":
			case "KNOCK KNOCK":

				newNode = createOperatorNode(instruction);
				node = createOperand(instructionArgument, false);
				((InstructionNode) newNode).getChildNodes().add(node);
				break;

			case "HERE IS MY INVITATION":
				newNode = createOperand(instructionArgument, false);
				break;

			case "TALK TO THE HAND":
				newNode = new PrintNode(instructionMap.get(instruction));
				node = createOperand(instructionArgument, true);
				((InstructionNode) newNode).getChildNodes().add(node);
				parent.getChildNodes().add(newNode);
				break;

			case "BECAUSE I'M GOING TO SAY PLEASE":
				newNode = new IfNode(instructionMap.get(instruction));
				node = new ConditionNode("ConditionNode", instructionArgument, null);
				InstructionNode ifBodyNode = new IfBodyNode("IfBodyNode");
				((InstructionNode) newNode).getChildNodes().add(node);
				createIfBodyNode((InstructionNode) newNode, ifBodyNode, bufferedReader);
				((InstructionNode) newNode).getChildNodes().add(ifBodyNode);
				if(((InstructionNode) newNode).getChildNodes().size() == 3)
					Collections.swap(((InstructionNode) newNode).getChildNodes(), 1, 2);
				parent.getChildNodes().add(newNode);
				break;

			case "BULLSHIT":
				newNode = new ElseBodyNode("ElseBodyNode");
				parent.getChildNodes().add(newNode);					
				break;

			case "STICK AROUND":
				newNode = new WhileNode(instructionMap.get(instruction));
				node = new ConditionNode("ConditionNode", instructionArgument, null);
				InstructionNode bodyNode = new BodyNode("BodyNode");

				createBodyNode(bodyNode, bufferedReader);
				((InstructionNode) newNode).getChildNodes().add(node);
				((InstructionNode) newNode).getChildNodes().add(bodyNode);
				parent.getChildNodes().add(newNode);
				break;
		}
		return newNode;
	}
}
