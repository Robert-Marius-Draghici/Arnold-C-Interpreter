package treeNodes.instructionNodes;

public abstract class OperatorNode extends InstructionNode {

	public OperatorNode(String nodeName) {
		super(nodeName);

	}

	public abstract Integer executeInstruction(Integer operand1, Integer operand2);

}
