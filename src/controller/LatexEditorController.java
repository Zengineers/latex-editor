package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JEditorPane;

import controller.commands.Command;
import controller.commands.CommandFactory;
import model.Converter;
import model.DocumentManager;
import model.VersionTrackingManager;

public class LatexEditorController{
	private static LatexEditorController instance = null;
	
	private DocumentManager documentManager;
	private VersionTrackingManager versionTrackingManager;
	private Converter converter;
	private JEditorPane editorPane;
	
	private String templateType;
	private String commandType;
	private String filename;

	private HashMap<String, Command> commands =  new HashMap<String, Command>();
	private String[] commandNames = { 
			"addLatex", "changeVersionTrackingStrategy", 
			"create", "disableVersionTracking",
			"edit", "enableVersionTracking", 
			"load", "rollback", 
			"save", "exportHtml", 
			"importHtml"
	};
	
	private LatexEditorController() {
		documentManager = DocumentManager.getInstance();
		versionTrackingManager = VersionTrackingManager.getInstance();
		converter = Converter.getInstance();
	}

	public void createCommands() {
		CommandFactory commandFactory = new CommandFactory();
		for (String name : commandNames) {
			commands.put(name, commandFactory.createCommand(name));
		}
	}

	public static LatexEditorController getInstance() {
		if (instance == null)
			instance = new LatexEditorController();
		return instance;
	}
	
	public void enact(String command) {
		commands.get(command).execute();
	}
	
	public VersionTrackingManager getVersionTrackingManager() {
		return versionTrackingManager;
	}
	
	public DocumentManager getDocumentManager() {
		return documentManager;
	}
	
	public Converter getConverter() {
		return converter;
	}
	
	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public JEditorPane getEditorPane() {
		return editorPane;
	}

	public void setEditorPane(JEditorPane editorPane) {
		this.editorPane = editorPane;
	}

	public String getCommandType() {
		return commandType;
	}

	public void setCommandType(String commandType) {
		this.commandType = commandType;
	}

	public String readFileContents() {
		String fileContents = "";
		try {
			Scanner scanner = new Scanner(new FileInputStream(filename));
			while(scanner.hasNextLine()) {
				fileContents = fileContents + scanner.nextLine() + "\n";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fileContents;
	}

	public String findTemplateType(String fileContents) {
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
	
}
