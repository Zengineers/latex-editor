package controller.commands;

import controller.LatexEditorController;
import model.Converter;
import model.Document;
import model.DocumentManager;

public class ImportHtmlCommand implements Command {
	private DocumentManager documentManager;
	private LatexEditorController latexEditorController;
	private Converter converter;

	
	public ImportHtmlCommand() {
		documentManager = DocumentManager.getInstance();
		latexEditorController = LatexEditorController.getInstance();
		converter = Converter.getInstance();
	}
	
	
	@Override
	public void execute() {
		String fileContents = latexEditorController.readFileContents();
		String latexContents = converter.convertHtmlToLatex(fileContents);
		String templateType = latexEditorController.findTemplateType(latexContents);
		
		latexEditorController.setTemplateType(templateType);
		documentManager.setCurrentDocument(new Document());
		documentManager.getCurrentDocument().setContents(latexContents);
	}

}
