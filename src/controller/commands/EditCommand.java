package controller.commands;

import controller.LatexEditorController;
import model.DocumentManager;
import model.VersionTrackingManager;

public class EditCommand implements Command {
	private LatexEditorController latexEditorController;
	private VersionTrackingManager  versionTrackingManager;
	private DocumentManager documentManager;
	
	
	public EditCommand() {
		latexEditorController = LatexEditorController.getInstance();
		versionTrackingManager = VersionTrackingManager.getInstance();
		documentManager = DocumentManager.getInstance();
	}


	@Override
	public void execute() {
		documentManager.setEditedContents(latexEditorController.getEditorPane().getText());
		documentManager.getCurrentDocument().setContents(documentManager.getEditedContents());
		if(versionTrackingManager.isEnabled()) {
			versionTrackingManager.putVersion(documentManager.getCurrentDocument());
			documentManager.getCurrentDocument().changeVersion();
		}
	}
	
}
