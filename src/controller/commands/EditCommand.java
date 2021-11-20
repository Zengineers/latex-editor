package controller.commands;

import model.VersionTrackingManager;
import view.LatexEditorView;

public class EditCommand implements Command {
	private LatexEditorView latexEditorView;
	private VersionTrackingManager  versionTrackingManager;
	
	
	public EditCommand( LatexEditorView latexEditorView) {
		super();
		this.latexEditorView = latexEditorView;
	}


	@Override
	public void execute() {
		//latexEditorView.saveContents();
		if(versionTrackingManager.isEnabled()) {
			versionTrackingManager.putVersion(currentDocument);
			currentDocument.changeVersion();
		}
		currentDocument.setContents(text);
	}

	
}
