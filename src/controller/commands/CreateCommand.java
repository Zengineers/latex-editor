package controller.commands;

import controller.LatexEditorController;
import model.Document;
import model.DocumentManager;

public class CreateCommand implements Command {
	private LatexEditorController latexEditorController;
	private DocumentManager documentManager;
	
	
	public CreateCommand() {
		latexEditorController = LatexEditorController.getInstance();
		documentManager = DocumentManager.getInstance();
	}

	
	@Override
	public void execute() {
		String templateType = latexEditorController.getTemplateType();
		Document document = documentManager.createDocument(templateType);
		documentManager.setCurrentDocument(document);
	}

}
