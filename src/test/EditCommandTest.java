package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
		
		expectedContents = addTextToMainWindowEditorPane(inputText);	
		mainWindow.getMiSave().doClick();	
		
		String documentContents = latexEditorController.getDocumentManager().getCurrentDocument().getContents();
		String editorPaneContents = mainWindow.getEditorPane().getText();
		
		assertEquals(documentContents, expectedContents);
		assertEquals(editorPaneContents, expectedContents);
	}

	
	
}
