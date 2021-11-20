package controller.commands;

import model.VersionTrackingManager;
import view.LatexEditorView;

public class LoadCommand implements Command {
	private LatexEditorView latexEditorView;
	
	public LoadCommand(LatexEditorView latexEditorView) {
		super();
		this.latexEditorView = latexEditorView;
	}

//	public VersionsManager getVersionsManager() {
//		return versionsManager;
//	}
//
//	public void setVersionsManager(VersionsManager versionsManager) {
//		this.versionsManager = versionsManager;
//	}

	@Override
	public void execute() {
		latexEditorView.loadFromFile();
	}

}
