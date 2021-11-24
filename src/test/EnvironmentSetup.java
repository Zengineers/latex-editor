package test;

import javax.swing.JMenuItem;

import controller.LatexEditorController;
import model.strategies.StableStrategy;
import model.strategies.VersionTrackingStrategy;
import model.strategies.VolatileStrategy;
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
	
	protected static void clickStrategyMenuItem(String versionTrackingStrategy) {
		if (versionTrackingStrategy.equals("volatile")) {
			if (mainWindow.getCbmiVolatileStrategy().isEnabled())
				mainWindow.getCbmiVolatileStrategy().doClick();
		}
		else if (versionTrackingStrategy.equals("stable")) {
			if (mainWindow.getCbmiStableStrategy().isEnabled())
				mainWindow.getCbmiStableStrategy().doClick();
		}
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

	protected static boolean evaluateClassInstance(VersionTrackingStrategy versionTrackingStrategy, String strategyType) {
		if (strategyType.equals("volatile")) {
			return versionTrackingStrategy instanceof VolatileStrategy;
		}
		if (strategyType.equals("stable")) {
			return versionTrackingStrategy instanceof StableStrategy;
		}
		return false;
	}
	
	protected static String addTextToMainWindowEditorPane(String inputText) {
		String contents = mainWindow.getEditorPane().getText();
		int caretPosition = getRandomNumber(0, contents.length());
		String before = contents.substring(0, caretPosition);
		String after = contents.substring(caretPosition);
		
		contents = before + "\n" + inputText + "\n" + after;
		mainWindow.getEditorPane().setText(contents);
		return contents;
	}
	
	private static int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
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
