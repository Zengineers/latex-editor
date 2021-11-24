package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import model.VersionTrackingManager;

class EnableVersionTrackingCommandTest extends EnvironmentSetup {

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
	@CsvSource(value = {"bookTemplate-volatile", "bookTemplate-stable",
										"letterTemplate-volatile", "letterTemplate-stable",
										"reportTemplate-volatile", "reportTemplate-stable",
										"articleTemplate-volatile", "articleTemplate-stable",
										"emptyTemplate-volatile", "emptyTemplate-stable"}, 
							delimiter = '-')
	void testExecute(String templateType, String strategyType) {
		selectTemplateRadioButton(templateType);
		setUpMainWindow();
		clickStrategyMenuItem(strategyType);
		
		VersionTrackingManager versionTrackingManager = latexEditorController.getVersionTrackingManager();
		boolean classInstanceEvaluation = evaluateClassInstance(versionTrackingManager.getStrategy(), strategyType);
		
		assertEquals(versionTrackingManager.getStrategyType(), strategyType);
		assertEquals(versionTrackingManager.isEnabled(), true);
		assertEquals(classInstanceEvaluation, true);
	}
	
}
