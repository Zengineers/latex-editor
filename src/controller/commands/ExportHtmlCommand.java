package controller.commands;

import controller.LatexEditorController;
import model.Converter;
import model.Document;
import model.DocumentManager;

public class ExportHtmlCommand implements Command {
	private DocumentManager documentManager;
	private LatexEditorController latexEditorController;
	private Converter converter;
	
	
	
	public ExportHtmlCommand() {
//		documentManager = DocumentManager.getInstance();
		latexEditorController = LatexEditorController.getInstance();
		documentManager = latexEditorController.getDocumentManager();
		converter = latexEditorController.getConverter();
//		converter = Converter.getInstance();
	}
	
	
	@Override
	public void execute() {
		String contents = documentManager.getCurrentDocument().getContents();
		String htmlContents = converter.convertLatexToHtml(contents);
		
		Document htmlDocument = documentManager.createDocument("emptyTemplate");
		htmlDocument.setContents(htmlContents);
		htmlDocument.save(latexEditorController.getFilename());
	}

}
