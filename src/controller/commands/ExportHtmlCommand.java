package controller.commands;

import controller.LatexEditorController;
import model.Document;
import model.DocumentManager;
import model.HtmlConverter;

public class ExportHtmlCommand implements Command {
	private DocumentManager documentManager;
	private LatexEditorController latexEditorController;
	
	
	
	public ExportHtmlCommand() {
		documentManager = DocumentManager.getInstance();
		latexEditorController = LatexEditorController.getInstance();
	}
	
	
	@Override
	public void execute() {
		String contents = documentManager.getCurrentDocument().getContents();
		String htmlContents = HtmlConverter.convertLatexToHtml(contents);
		
		Document htmlDocument = documentManager.createDocument("emptyTemplate");
		htmlDocument.setContents(htmlContents);
		htmlDocument.save(latexEditorController.getFilename());
			
//		System.out.println(contents);
	}

}
