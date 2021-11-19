package controller;

import java.util.HashMap;

import controller.commands.AddLatexCommand;
import controller.commands.ChangeVersionsStrategyCommand;
import controller.commands.Command;
import controller.commands.CommandFactory;
import controller.commands.CreateCommand;
import controller.commands.DisableVersionsManagementCommand;
import controller.commands.EditCommand;
import controller.commands.EnableVersionsManagementCommand;
import controller.commands.LoadCommand;
import controller.commands.RollbackToPreviousVersionCommand;
import controller.commands.SaveCommand;
import model.VersionsManager;

public class LatexEditorController{
	
	private HashMap<String, Command> commands =  new HashMap<String, Command>();
	private String[] commandNames = { 
			"addLatex", "changeVersionsStrategy", 
			"create", "disableVersionsManagement",
			"edit", "enableVersionsManagement", 
			"load", "rollbackToPreviousVersion", 
			"save" 
	};
	
	public LatexEditorController(VersionsManager versionsManager) {
		CommandFactory commandFactory = new CommandFactory(versionsManager);
		
		for (String name : commandNames) {
			commands.put(name, commandFactory.createCommand(name));
		}
	}
	
	public void enact(String command) {
		commands.get(command).execute();
	}
}
