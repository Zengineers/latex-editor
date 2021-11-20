package controller.commands;

import view.LatexEditorView;

public class EditCommand implements Command {
	private LatexEditorView latexEditorView;
	
	
	public EditCommand( LatexEditorView latexEditorView) {
		super();
		this.latexEditorView = latexEditorView;
	}


	@Override
	public void execute() {
		latexEditorView.saveContents();
	}

}
