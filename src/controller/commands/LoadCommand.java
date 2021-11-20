package controller.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import controller.LatexEditorController;
import model.Document;
import model.DocumentManager;

public class LoadCommand implements Command {
	private DocumentManager documentManager;
	private LatexEditorController latexEditorController;
	
	
	public LoadCommand() {
		documentManager = DocumentManager.getInstance();
		latexEditorController = LatexEditorController.getInstance();
	}
	

	@Override
	public void execute() {
		String fileContents = readFileContents();
		String templateType = findTemplateType(fileContents);
		
		latexEditorController.setTemplateType(templateType);
		documentManager.setCurrentDocument(new Document());
		documentManager.getCurrentDocument().setContents(fileContents);
		
	}


	private String findTemplateType(String fileContents) {
		fileContents = fileContents.trim();
		if(fileContents.startsWith("\\documentclass[11pt,twocolumn,a4paper]{article}")) {
			return "articleTemplate";
		}
		if(fileContents.startsWith("\\documentclass[11pt,a4paper]{book}")) {
			return "bookTemplate";
		}
		if(fileContents.startsWith("\\documentclass[11pt,a4paper]{report}")) {
			return "reportTemplate";
		}
		if(fileContents.startsWith("\\documentclass{letter}")) {
			return "letterTemplate";
		}
		return "emptyTemplate";
	}


	private String readFileContents() {
		String fileContents = "";
		try {
			Scanner scanner = new Scanner(new FileInputStream(latexEditorController.getFilename()));
			while(scanner.hasNextLine()) {
				fileContents = fileContents + scanner.nextLine() + "\n";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fileContents;
	}

}
