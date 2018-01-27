# Arnold-C-Interpreter
This is a Java implementation of an interpreter for the ArnoldC programming language. The entry point for the application is the class Test.java. The program takes as input a file that contains valid ArnoldC programs and generates the abstract syntax tree in the file <program_name>.ast, as well as an output file with the result of the interpretation <program_name>.out.

This application implements a restricted version of the language, which includes arithmetic instructions, if and while statements, printing instructions.

Usage example:
* compile: javac Test.java
* run: java Test <program_name>.ac

More information about the ArnoldC programming language can be found here https://github.com/lhartikk/ArnoldC/wiki/ArnoldC.
