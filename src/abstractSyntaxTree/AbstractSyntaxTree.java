package abstractSyntaxTree;

import java.io.BufferedReader; 
import java.io.IOException;

import treeNodes.NodeFactory;
import treeNodes.instructionNodes.MainNode;

public class AbstractSyntaxTree {

	private static AbstractSyntaxTree INSTANCE = null;
	public static MainNode main;

	private AbstractSyntaxTree() {
		main = new MainNode("MainNode");
	}

	public static AbstractSyntaxTree getInstance() {
		if (INSTANCE == null)
			INSTANCE = new AbstractSyntaxTree();
		return INSTANCE;
	}

	public void createAST(BufferedReader bufferedReader) throws IOException {
		InstructionChecker checkerInstance = InstructionChecker.getInstance();
		String line = null;
		line = bufferedReader.readLine();
		/*
		 * The first instruction of any ArnoldC program is IT'S SHOWTIME, which
		 * indicates the beginning of the main method. Here, we check if the
		 * first line of the file is IT'S SHOWTIME, and if it is not, we read
		 * the file line with line, until we find it. Also, we have to check if
		 * the line contains more than one space between words. (The instruction
		 * is still valid in this case).
		 */
		line = line.trim();
		line = line.replaceAll(" +", " ");
		while (!line.contains("IT'S SHOWTIME")) {
			line = bufferedReader.readLine();
			line = line.replaceAll(" +", " ");
		}

		do {
			line = bufferedReader.readLine();
			
			line = line.trim();
			line = line.replaceAll(" +", " ");
			
			String instruction = checkerInstance.extractInstructionName(line);
			String instructionArgument = checkerInstance.extractInstructionArgument(line, instruction);
			
			if (checkerInstance.checkInstruction(instruction)
					&& !checkerInstance.isEndOfInstruction(instruction)) {

				NodeFactory.getInstance().createASTNode(main, bufferedReader, instruction,
						instructionArgument);
			}
		} while (!line.equals("YOU HAVE BEEN TERMINATED"));

	}
}
