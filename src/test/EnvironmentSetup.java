package test;

import javax.swing.JMenuItem;

import view.ChooseTemplate;
import view.LatexEditorView;
import view.MainWindow;
import view.OpeningWindow;

public class EnvironmentSetup {

	protected static LatexEditorView latexEditorView;
	protected static OpeningWindow openingWindow;
	protected static ChooseTemplate chooseTemplate;
	protected static MainWindow mainWindow;
	
	protected static void setUpOpeningWindow() {
		openingWindow = new OpeningWindow();
		latexEditorView = openingWindow.getLatexEditorView();
	}
	
	protected static void setUpChooseTemplate() {
		openingWindow.getBtnCreateNewDocument().doClick();
		chooseTemplate = openingWindow.getChooseTemplate();
		latexEditorView = chooseTemplate.getLatexEditorView();
	}
	
	protected static void setUpMainWindow() {
		chooseTemplate.getBtnCreate().doClick();
		mainWindow = chooseTemplate.getMainWindow();
		latexEditorView = mainWindow.getLatexEditorView();
	}
	
	protected static void selectTemplateRadioButton(String templateType) {
		if (templateType.equals("bookTemplate")) {
			chooseTemplate.getBookRadioButton().setSelected(true);
		}
		else if (templateType.equals("reportTemplate")) {
			chooseTemplate.getReportRadioButton().setSelected(true);
		}
		else if (templateType.equals("articleTemplate")) {
			chooseTemplate.getArticleRadioButton().setSelected(true);
		}
		else if (templateType.equals("letterTemplate")) {
			chooseTemplate.getLetterRadioButon().setSelected(true);
		}
	}

	protected static void clickLatexCommandMenuItem(String latexCommandType) {
		if (!mainWindow.getMnCommands().isEnabled())
			return;

		JMenuItem mntmCommand = getLatexCommandMenuItem(latexCommandType);
		
		if (mntmCommand == null)
			return;
		
		if (mntmCommand.isEnabled())
			mntmCommand.doClick();
	}
	
	private static JMenuItem getLatexCommandMenuItem(String latexCommandType) {
		if (latexCommandType.equals("chapter")) {
			return mainWindow.getMntmChapter();
		}
		else if (latexCommandType.equals("section")) {
			return mainWindow.getMntmAddSection();
		}
		else if (latexCommandType.equals("subsection")) {
			return mainWindow.getMntmAddSubsection();
		}
		else if (latexCommandType.equals("subsubsection")) {
			return mainWindow.getMntmAddSubsubsection();
		}
		else if (latexCommandType.equals("itemize")) {
			return mainWindow.getMntmItemize();
		}
		else if (latexCommandType.equals("enumerate")) {
			return mainWindow.getMntmEnumerate();
		}
		else if (latexCommandType.equals("table")) {
			return mainWindow.getMntmTable();
		}
		else if (latexCommandType.equals("figure")) {
			return mainWindow.getMntmFigure();
		}
		return null;
	}

	protected static void cleanUp() {
		if (openingWindow != null) openingWindow.disposeFrame();
		if (chooseTemplate != null) chooseTemplate.disposeFrame();
		if (mainWindow != null) mainWindow.disposeFrame();
		latexEditorView = null;
		openingWindow = null;
		chooseTemplate = null;
		mainWindow = null;
	}
	
}
