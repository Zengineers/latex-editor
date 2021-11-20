package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import resources.Strings;

class CreateCommandTest extends EnvironmentSetup {

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
	@ValueSource(strings = {"articleTemplate", "bookTemplate", 
											"reportTemplate", "letterTemplate", 
											"emptyTemplate"})
	void testExecute(String templateType) {
		selectTemplateRadioButton(templateType);
		setUpMainWindow();	
		
		String documentContents = latexEditorController.getDocumentManager().getCurrentDocument().getContents();	
		String templateContents = Strings.getTemplate(templateType);
		
		assertEquals(documentContents, templateContents);
	}

}
