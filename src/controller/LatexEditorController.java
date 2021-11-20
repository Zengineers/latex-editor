package controller;

import java.util.HashMap;

import javax.swing.JEditorPane;

import controller.commands.Command;
import controller.commands.CommandFactory;
import model.DocumentManager;
import model.VersionTrackingManager;

public class LatexEditorController{
	private static LatexEditorController instance = null;
	
	private DocumentManager documentManager;
	private VersionTrackingManager versionTrackingManager;
	private JEditorPane editorPane;
	
	private String templateType;
	private String commandType;
	private String filename;
	
	private HashMap<String, Command> commands =  new HashMap<String, Command>();
	private String[] commandNames = { 
			"addLatex", "changeVersionsStrategy", 
			"create", "disableVersionsManagement",
			"edit", "enableVersionsManagement", 
			"load", "rollbackToPreviousVersion", 
			"save" 
	};
	
	private LatexEditorController() {
		documentManager = DocumentManager.getInstance();
		versionTrackingManager = VersionTrackingManager.getInstance();	
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

}
