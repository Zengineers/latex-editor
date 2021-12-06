package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import javax.swing.JFileChooser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import model.DocumentManager;

class ImportHtmlCommandTest extends EnvironmentSetup {

	@BeforeEach
	void setUp() throws Exception {
		setUpOpeningWindow();
		setUpChooseTemplate();
		selectTemplateRadioButton("emptyTemplate");
		setUpMainWindow();	
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
		DocumentManager documentManager = latexEditorController.getDocumentManager();
		String filename = simulateImportHtmlMenuItemBehaviour(templateType);
		String htmlContents = latexEditorController.readFileContents();
		String expectedContents = latexEditorController.getConverter().convertHtmlToLatex(htmlContents);
		String documentContents = documentManager.getCurrentDocument().getContents();	
		String editorPaneContents = latexEditorController.getEditorPane().getText();
		File openedFile = new File(filename);
		
		assertEquals(openedFile.exists(), true);
		assertEquals(openedFile.isDirectory(), false);
		assertEquals(documentContents, expectedContents);
		assertEquals(editorPaneContents, expectedContents);
	}

	private String simulateImportHtmlMenuItemBehaviour(String templateType) {
		String filename = System.getProperty("user.dir") + "\\test_files\\html\\" + templateType + "_html_export_test.html";
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
		chooser.setSelectedFile(new File(filename));
		chooser.approveSelection();
		latexEditorController.setFilename(filename);
		latexEditorController.enact("importHtml");
		String contents = latexEditorController.getDocumentManager().getCurrentDocument().getContents();
		latexEditorController.getEditorPane().setText(contents);	
		return filename;
	}

}
