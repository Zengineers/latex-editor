package controller.commands;

import model.VersionsManager;
import view.LatexEditorView;

public class SaveCommand implements Command {
	private LatexEditorView latexEditorView;
	
	public SaveCommand(LatexEditorView latexEditorView) {
		this.latexEditorView = latexEditorView;
	}
	@Override
	public void execute() {
		latexEditorView.saveToFile();
	}

}
