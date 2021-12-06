package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.awt.Robot;
import java.awt.AWTException;

import model.Document;

class RollbackCommandTest extends EnvironmentSetup {

	@BeforeEach
	void setUp() throws Exception {
		setUpOpeningWindow();
		setUpChooseTemplate();
	}

	@AfterEach
	void tearDown() throws Exception {
		cleanUp();
	}

	@ParameterizedTest
	@CsvSource(value = {"5-bookTemplate-volatile-stable", "5-bookTemplate-stable-volatile",
										"5-letterTemplate-volatile-stable", "5-letterTemplate-stable-volatile",
										"5-reportTemplate-volatile-stable", "5-reportTemplate-stable-volatile",
										"5-articleTemplate-volatile-stable", "5-articleTemplate-stable-volatile",
										"5-emptyTemplate-volatile-stable", "5-emptyTemplate-stable-volatile"}, 
						delimiter = '-')
	void testExecute(String editsCount, String templateType, String fromStrategyType) {
		selectTemplateRadioButton(templateType);
		setUpMainWindow();	
		clickStrategyMenuItem(fromStrategyType);
		editDocumentVersions(Integer.parseInt(editsCount));
		
		for (int i=0; i<Integer.parseInt(editsCount); i++) {
			Document previousDocument = latexEditorController.getVersionTrackingManager().getStrategy().getVersion();
			int previousDocumentVersionID = 0;
			if (previousDocument != null) {
				previousDocumentVersionID = Integer.parseInt(previousDocument.getVersionID());
			}
			
			mainWindow.getMiRollback().doClick();
			
			Document currentDocument = latexEditorController.getDocumentManager().getCurrentDocument();
			int currentDocumentVersionID = Integer.parseInt(currentDocument.getVersionID());
			
			assertEquals(previousDocumentVersionID, currentDocumentVersionID);
			assertEquals(latexEditorController.getEditorPane().getText(), currentDocument.getContents());
			
			if (previousDocument != null) {
				assertEquals(previousDocument.getContents(), currentDocument.getContents());
			}
			else {
				// TODO maybe assert dialog message
			}
		}
	}

	private void editDocumentVersions(int editsCount) {
		for (int e=0; e<editsCount; e++) {
			addTextToMainWindowEditorPane("TEST_INPUT_TEXT");
			mainWindow.getMiSave().doClick();
		}
	}
	
}
