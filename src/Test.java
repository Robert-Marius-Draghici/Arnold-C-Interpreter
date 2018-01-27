import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import abstractSyntaxTree.AbstractSyntaxTree;
import treeNodes.instructionNodes.MainNode;
import treeVisitors.InterpreterVisitor;
import treeVisitors.PrintVisitor;

public class Test {

	public static void main(String[] args) {
		BufferedReader bufferedReader = null;
		try {
			int delimiterPosition = args[0].indexOf('/');
			String filename = args[0].substring(delimiterPosition + 1);
			FileReader fileReader = new FileReader(args[0]);
			bufferedReader = new BufferedReader(fileReader);
			AbstractSyntaxTree.getInstance().createAST(bufferedReader);
			MainNode main = AbstractSyntaxTree.main;

			int pointPosition = filename.indexOf('.') + 1;
			String outputASTfilename = filename.substring(0, pointPosition).concat("ast");
			File outputASTfile = new File(outputASTfilename);
			System.setOut(new PrintStream(outputASTfile));
			PrintVisitor printVisitor = new PrintVisitor();
			main.accept(printVisitor);

			String outputInterpretfilename = filename.substring(0, pointPosition).concat("out");
			File outputInterpretfile = new File(outputInterpretfilename);
			System.setOut(new PrintStream(outputInterpretfile));
			InterpreterVisitor interpreterVisitor = new InterpreterVisitor();
			main.accept(interpreterVisitor);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
