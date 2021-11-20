package controller.commands;

import model.Document;
import model.DocumentManager;
import model.VersionTrackingManager;
import view.LatexEditorView;

public class CreateCommand implements Command {
	private DocumentManager documentManager;
	private VersionTrackingManager versionsManager;
	private LatexEditorView latexEditorView;
	
	public CreateCommand(DocumentManager documentManager, VersionTrackingManager versionsManager, LatexEditorView latexEditorView) {
		super();
		this.documentManager = documentManager;
		this.versionsManager = versionsManager;
		this.latexEditorView = latexEditorView;
	}

	@Override
	public void execute() {
		String type = latexEditorView.getType();
		Document document = documentManager.createDocument(type);
		latexEditorView.setCurrentDocument(document);
	}

}
