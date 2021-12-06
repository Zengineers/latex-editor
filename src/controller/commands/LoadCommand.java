package controller.commands;

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
		String fileContents = latexEditorController.readFileContents();
		String templateType = latexEditorController.findTemplateType(fileContents);
		
		latexEditorController.setTemplateType(templateType);
		documentManager.setCurrentDocument(new Document());
		documentManager.getCurrentDocument().setContents(fileContents);
	}

}
