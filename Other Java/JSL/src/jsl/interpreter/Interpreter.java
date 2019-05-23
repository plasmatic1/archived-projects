package jsl.interpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Interpreter {
	
	private String code;

	public Interpreter() {
		// TODO Auto-generated constructor stub	
	}
	
	public String interpret(File file){
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(file));
			StringBuilder builder = new StringBuilder();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
