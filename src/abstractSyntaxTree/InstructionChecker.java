package abstractSyntaxTree;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstructionChecker {

	private LinkedHashMap<String, String> instructions;
	private LinkedHashMap<String, String> endInstruction;
	private static InstructionChecker INSTANCE = null;

	public static InstructionChecker getInstance() {
		if (INSTANCE == null)
			INSTANCE = new InstructionChecker();
		return INSTANCE;
	}

	private InstructionChecker() {
		instructions = new LinkedHashMap<>();
		instructions.put("@I LIED", "0");
		instructions.put("@NO PROBLEMO", "1");
		instructions.put("BECAUSE I'M GOING TO SAY PLEASE", "IfNode");
		instructions.put("BULLSHIT", "ElseBodyNode");
		instructions.put("YOU HAVE NO RESPECT FOR LOGIC", "EndIf");
		instructions.put("STICK AROUND", "WhileNode");
		instructions.put("CHILL", "EndWhile");
		instructions.put("GET UP", "SumNode");
		instructions.put("GET DOWN", "DifferenceNode");
		instructions.put("YOU'RE FIRED", "MultiplicationNode");
		instructions.put("HE HAD TO SPLIT", "DivisionNode");
		instructions.put("I LET HIM GO", "ModuloNode");
		instructions.put("YOU ARE NOT YOU YOU ARE ME", "EqualToNode");
		instructions.put("LET OFF SOME STEAM BENNET", "GreaterThanNode");
		instructions.put("CONSIDER THAT A DIVORCE", "OrNode");
		instructions.put("KNOCK KNOCK", "AndNode");
		instructions.put("HEY CHRISTMAS TREE", "DeclareNode");
		instructions.put("YOU SET US UP", "SetInitialValue");
		instructions.put("IT'S SHOWTIME", "MainNode");
		instructions.put("YOU HAVE BEEN TERMINATED", "EndMain");
		instructions.put("TALK TO THE HAND", "PrintNode");
		instructions.put("GET TO THE CHOPPER", "AssignmentNode");
		instructions.put("HERE IS MY INVITATION", "SetValue");
		instructions.put("ENOUGH TALK", "EndAssignment");

		endInstruction = new LinkedHashMap<>();
		endInstruction.put("YOU HAVE NO RESPECT FOR LOGIC", "EndIf");
		endInstruction.put("CHILL", "EndWhile");
		endInstruction.put("YOU HAVE BEEN TERMINATED", "EndMain");
		endInstruction.put("ENOUGH TALK", "EndAssignment");
	}

	public LinkedHashMap<String, String> getInstructions() {
		return instructions;
	}

	public boolean checkInstruction(String instruction) {
		return instructions.containsKey(instruction);
	}

	public boolean isEndOfInstruction(String instruction) {
		return endInstruction.containsKey(instruction);
	}

	public String extractInstructionName(String line) {
		line = line.trim();
		line = line.replaceAll(" +", " ");
		Pattern upperCase = Pattern.compile("\\b[A-Z' ]{2,}\\b");
		Matcher matcher = upperCase.matcher(line);
		List<String> results = new ArrayList<String>();

		while (matcher.find()) {
			results.add(matcher.group());
		}

		if (results.isEmpty())
			return null;
		
		String instruction = results.get(0);
		instruction = instruction.trim();
		return instruction;
	}

	public String extractInstructionArgument(String line, String instruction) {
		if (instruction == null)
			return null;

		String instructionArgument = line.substring(instruction.length());
		instructionArgument = instructionArgument.trim();
		return instructionArgument;
	}
	
	public Integer convertArgument(String argument) {
		switch(argument) {
			case "@I LIED":				
			case "@NO PROBLEMO":
				return Integer.parseInt(instructions.get(argument));
			default:
				return Integer.parseInt(argument);
		}
	}

}
