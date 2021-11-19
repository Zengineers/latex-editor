package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.LatexEditorController;
import model.VersionsManager;
import view.LatexEditorView;
import view.MainWindow;

class EditCommandTest extends EnvironmentSetup {

	private String expectedContents;
	
	@BeforeEach
	void setUp() throws Exception {	
		setUpOpeningWindow();
		setUpChooseTemplate();
	}

	@AfterEach
	void tearDown() throws Exception {
		cleanUp();
		expectedContents = null;
	}

	@ParameterizedTest
	@CsvSource(value = {"bookTemplate-TEST_INPUT_TEXT_1",
										"letterTemplate-TEST_INPUT_TEXT_2",
										"reportTemplate-TEST_INPUT_TEXT_3",
										"articleTemplate-TEST_INPUT_TEXT_4",
										"emptyTemplate-TEST_INPUT_TEXT_5"}, 
							delimiter = '-')
	void testExecute(String templateType, String inputText) {
		selectTemplateRadioButton(templateType);
		setUpMainWindow();
		
		expectedContents = estimateExpectedContents(inputText);
		mainWindow.getEditorPane().setText(expectedContents);
		mainWindow.getMiSave().doClick();	
		
		String documentContents = latexEditorView.getCurrentDocument().getContents();
		String editorPaneContents = mainWindow.getEditorPane().getText();
		
		assertEquals(documentContents, expectedContents);
		assertEquals(editorPaneContents, expectedContents);
	}

	private String estimateExpectedContents(String inputText) {
		expectedContents = mainWindow.getEditorPane().getText();
		
		addInputText(inputText);
		removeRandomChar();
		return expectedContents;
	}

	private void removeRandomChar() {
		if (expectedContents.isEmpty())
			return;
		
		int caretPosition = getRandomNumber(0, expectedContents.length());
		String before = expectedContents.substring(0, caretPosition);
		String after = expectedContents.substring(caretPosition+1);

		expectedContents = before + after;
	}

	private void addInputText(String inputText) {
		int caretPosition = getRandomNumber(0, expectedContents.length());
		String before = expectedContents.substring(0, caretPosition);
		String after = expectedContents.substring(caretPosition);
		
		expectedContents =  before + "\n" + inputText + "\n" + after;
	}
	
	private int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
}
