package controller.commands;

import model.VersionsManager;
import view.LatexEditorView;

public class AddLatexCommand implements Command  {
	private LatexEditorView latexEditorView;
	
	
	
	public AddLatexCommand(LatexEditorView latexEditorView) {
		super();
		this.latexEditorView = latexEditorView;
	}


	@Override
	public void execute() {
		latexEditorView.saveContents();
	}

}
