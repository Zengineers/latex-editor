package controller.commands;

import javax.swing.JEditorPane;

import controller.LatexEditorController;
import model.DocumentManager;
import model.VersionTrackingManager;
import resources.Strings;

public class AddLatexCommand implements Command  {
	private LatexEditorController latexEditorController;
	
	
	public AddLatexCommand() {
		latexEditorController = LatexEditorController.getInstance();
	}


	@Override
	public void execute() {
		String commandType = latexEditorController.getCommandType();
		JEditorPane editorPane = latexEditorController.getEditorPane();
		int newCaretPosition = determineNewCaretPosition(editorPane, commandType);
		String newContents = determineNewContents(editorPane, commandType);

		latexEditorController.getDocumentManager().setEditedContents(editorPane.getText());
		
		editorPane.setText(newContents);
		editorPane.setCaretPosition(newCaretPosition);
	}

	private int determineNewCaretPosition(JEditorPane editorPane, String commandType) {
		int caretPosition = editorPane.getCaretPosition();
		int latexCommandLength = Strings.getLatexCommand(commandType).length();
		return caretPosition + latexCommandLength;
	}

	public String determineNewContents(JEditorPane editorPane, String commandType) {
		String contents = editorPane.getText();
		String before = contents.substring(0, editorPane.getCaretPosition());
		String after = contents.substring(editorPane.getCaretPosition());
		
		return before + Strings.getLatexCommand(commandType) + after;
	}
}
