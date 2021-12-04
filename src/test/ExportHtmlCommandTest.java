package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JFileChooser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import model.DocumentManager;

class ExportHtmlCommandTest extends EnvironmentSetup {

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
											"reportTemplate", "letterTemplate"})
	void testExecute(String templateType) throws IOException {
		selectTemplateRadioButton(templateType);
		setUpMainWindow();
		
		DocumentManager documentManager = latexEditorController.getDocumentManager();
		String filename = simulateExportHtmlMenuItemBehaviour(templateType);
//		String documentContents = documentManager.getCurrentDocument().getContents();		
//		String exportedFileContents = Files.readString(Path.of(filename));
		File exportedFile = new File(filename);
		
		assertEquals(exportedFile.exists(), true);
		assertEquals(exportedFile.isDirectory(), false);
//		assertEquals(exportedFileContents, documentContents);
	}

	private String simulateExportHtmlMenuItemBehaviour(String templateType) {
		String filename = System.getProperty("user.dir") + "\\" + templateType + "_html_export_test.html";
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));		
		chooser.setSelectedFile(new File(filename));	
		chooser.approveSelection();
		latexEditorController.setFilename(filename);
		latexEditorController.enact("exportHtml");
		return filename;
	}
}
