package controller.commands;

import javax.swing.JEditorPane;

import controller.LatexEditorController;
import model.Document;
import model.DocumentManager;
import model.VersionTrackingManager;

public class RollbackCommand implements Command {
	private VersionTrackingManager versionTrackingManager;
	private LatexEditorController latexEditorController;
	private DocumentManager documentManager;
	
	
	public RollbackCommand() {
		versionTrackingManager = VersionTrackingManager.getInstance();
		latexEditorController = LatexEditorController.getInstance();
		documentManager = DocumentManager.getInstance();
	}


	@Override
	public void execute() {
		versionTrackingManager.rollback();
		
		Document document = documentManager.getCurrentDocument();
		boolean isVersionTrackingEnabled = versionTrackingManager.isEnabled();
		boolean isRollbackSuccessful = versionTrackingManager.isRollbackSuccessful();

		if (isVersionTrackingEnabled && document != null && isRollbackSuccessful) {
			JEditorPane editorPane = latexEditorController.getEditorPane();
			editorPane.setText(document.getContents());
		}
	}

}
