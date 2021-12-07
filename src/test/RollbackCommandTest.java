package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
	@CsvSource(value = {"5-bookTemplate-volatile", "5-bookTemplate-stable",
										"5-letterTemplate-volatile", "5-letterTemplate-stable",
										"5-reportTemplate-volatile", "5-reportTemplate-stable",
										"5-articleTemplate-volatile", "5-articleTemplate-stable",
										"5-emptyTemplate-volatile", "5-emptyTemplate-stable"}, 
						delimiter = '-')
	void testExecute(String editsCount, String templateType, String strategyType) {
		selectTemplateRadioButton(templateType);
		setUpMainWindow();	
		clickStrategyMenuItem(strategyType);
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
