package test;

import javax.swing.JMenuItem;

import controller.LatexEditorController;
import view.ChooseTemplate;
import view.MainWindow;
import view.OpeningWindow;

public class EnvironmentSetup {

	protected static LatexEditorController latexEditorController;
	protected static OpeningWindow openingWindow;
	protected static ChooseTemplate chooseTemplate;
	protected static MainWindow mainWindow;
	
	protected static void setUpOpeningWindow() {
		openingWindow = new OpeningWindow();
		latexEditorController = openingWindow.getLatexEditorController();
	}
	
	protected static void setUpChooseTemplate() {
		openingWindow.getBtCreateNewDocument().doClick();
		chooseTemplate = openingWindow.getChooseTemplate();
		latexEditorController = chooseTemplate.getLatexEditorController();
	}
	
	protected static void setUpMainWindow() {
		chooseTemplate.getBtCreate().doClick();
		mainWindow = chooseTemplate.getMainWindow();
		latexEditorController = mainWindow.getLatexEditorController();
	}
	
	protected static void selectTemplateRadioButton(String templateType) {
		if (templateType.equals("bookTemplate")) {
			chooseTemplate.getRbtBook().setSelected(true);
		}
		else if (templateType.equals("reportTemplate")) {
			chooseTemplate.getRbtReport().setSelected(true);
		}
		else if (templateType.equals("articleTemplate")) {
			chooseTemplate.getRbtArticle().setSelected(true);
		}
		else if (templateType.equals("letterTemplate")) {
			chooseTemplate.getRbtLetter().setSelected(true);
		}
	}

	protected static void clickLatexCommandMenuItem(String latexCommandType) {
		JMenuItem miCommand = getLatexCommandMenuItem(latexCommandType);
		
		if (miCommand == null)
			return;
		
		if (miCommand.isEnabled())
			miCommand.doClick();
	}
	
	private static JMenuItem getLatexCommandMenuItem(String latexCommandType) {
		if (latexCommandType.equals("chapter")) {
			return mainWindow.getMiChapter();
		}
		else if (latexCommandType.equals("section")) {
			return mainWindow.getMiSection();
		}
		else if (latexCommandType.equals("subsection")) {
			return mainWindow.getMiSubsection();
		}
		else if (latexCommandType.equals("subsubsection")) {
			return mainWindow.getMiSubsubsection();
		}
		else if (latexCommandType.equals("itemize")) {
			return mainWindow.getMiItemizeList();
		}
		else if (latexCommandType.equals("enumerate")) {
			return mainWindow.getMiEnumerateList();
		}
		else if (latexCommandType.equals("table")) {
			return mainWindow.getMiTable();
		}
		else if (latexCommandType.equals("figure")) {
			return mainWindow.getMiFigure();
		}
		return null;
	}

	protected static void cleanUp() {
		if (openingWindow != null) openingWindow.disposeFrame();
		if (chooseTemplate != null) chooseTemplate.disposeFrame();
		if (mainWindow != null) mainWindow.disposeFrame();
		latexEditorController = null;
		openingWindow = null;
		chooseTemplate = null;
		mainWindow = null;
	}
	
}
