package controller.commands;

import controller.LatexEditorController;
import model.DocumentManager;

public class SaveCommand implements Command {
	private DocumentManager documentManager;
	private LatexEditorController latexEditorController;
	
	
	public SaveCommand() {
		documentManager = DocumentManager.getInstance();
		latexEditorController = LatexEditorController.getInstance();
	}
	
	
	@Override
	public void execute() {
		documentManager.getCurrentDocument().save(latexEditorController.getFilename());
	}

}
