package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import model.VersionTrackingManager;
import model.strategies.VersionTrackingStrategy;
import model.Document;

class ChangeVersionTrackingStrategyCommandTest extends EnvironmentSetup {

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
	void testExecute(String editsCount, String templateType, String fromStrategyType, String toStrategyType) {
		selectTemplateRadioButton(templateType);
		setUpMainWindow();	
		clickStrategyMenuItem(fromStrategyType);
		editDocumentVersions(Integer.parseInt(editsCount));
		
		VersionTrackingManager versionTrackingManager = latexEditorController.getVersionTrackingManager();
		List<Document> historyBeforeChange = getVersionHistory();
		boolean classInstanceEvaluation = evaluateClassInstance(versionTrackingManager.getStrategy(), fromStrategyType);
		
		assertEquals(versionTrackingManager.getStrategyType(), fromStrategyType);
		assertEquals(versionTrackingManager.isEnabled(), true);
		assertEquals(classInstanceEvaluation, true);
		
		clickStrategyMenuItem(toStrategyType);
		List<Document> historyAfterChange = getVersionHistory();
		classInstanceEvaluation = evaluateClassInstance(versionTrackingManager.getStrategy(), toStrategyType);
		
		assertEquals(versionTrackingManager.getStrategyType(), toStrategyType);
		assertEquals(versionTrackingManager.isEnabled(), true);
		assertEquals(classInstanceEvaluation, true);

		Iterator<Document> before = historyBeforeChange.iterator();
		Iterator<Document> after = historyAfterChange.iterator();
		while (before.hasNext() && after.hasNext()) {
			Document documentBeforeChange = before.next();
			Document documentAfterChange = after.next();
			assertEquals(documentBeforeChange.getVersionID(),documentAfterChange.getVersionID());		
			assertEquals(documentBeforeChange.getContents(), documentAfterChange.getContents());		
		}
		versionTrackingManager.getStrategy().clearHistory();
	}

	private List<Document> getVersionHistory() {
		VersionTrackingManager versionTrackingManager = latexEditorController.getVersionTrackingManager();
		VersionTrackingStrategy versionTrackingStrategy = versionTrackingManager.getStrategy();
		List<Document> history = versionTrackingStrategy.getEntireHistory();
		return history;
	}
	
	private void editDocumentVersions(int editsCount) {
		for (int e=0; e<editsCount; e++) {
			addTextToMainWindowEditorPane("TEST_INPUT_TEXT");
			mainWindow.getMiSave().doClick();
		}
	}
	
}
