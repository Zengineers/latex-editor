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
import org.junit.jupiter.params.provider.CsvSource;

import model.DocumentManager;
import view.MainWindow;

class LoadCommandTest extends EnvironmentSetup {

	@BeforeEach
	void setUp() throws Exception {
		setUpOpeningWindow();

	}

	@AfterEach
	void tearDown() throws Exception {
		cleanUp();
	}
	
	@ParameterizedTest
	@CsvSource(value = {"bookTemplate-OpeningWindow", "bookTemplate-MainWindow",
										"letterTemplate-OpeningWindow", "letterTemplate-MainWindow",
										"reportTemplate-OpeningWindow", "reportTemplate-MainWindow",
										"articleTemplate-OpeningWindow", "articleTemplate-MainWindow",
										"emptyTemplate-OpeningWindow", "emptyTemplate-MainWindow"},  
							delimiter = '-')
	void testExecute(String templateType, String view) throws IOException {
		if (view.equals("MainWindow")) {
			setUpChooseTemplate();
			selectTemplateRadioButton("emptyTemplate");
			setUpMainWindow();	
		}
		
		DocumentManager documentManager = latexEditorController.getDocumentManager();
		String filename = simulateOpenExistingDocumentBehaviour(templateType, view);
		String documentContents = documentManager.getCurrentDocument().getContents();		
		String openedFileContents = Files.readString(Path.of(filename));
		File openedFile = new File(filename);
		
		assertEquals(openedFile.exists(), true);
		assertEquals(openedFile.isDirectory(), false);
		assertEquals(openedFileContents, documentContents);
	}
	
	private String simulateOpenExistingDocumentBehaviour(String templateType, String view) {
		String filename = System.getProperty("user.dir") + "\\test_files\\tex\\" + templateType + "TestFile.tex";
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));		
		chooser.setSelectedFile(new File(filename));	
		chooser.approveSelection();
		latexEditorController.setFilename(filename);
		latexEditorController.enact("load");
		
		if (view.equals("MainWindow")) {
			String contents = latexEditorController.getDocumentManager().getCurrentDocument().getContents();
			latexEditorController.getEditorPane().setText(contents);	
		}
		else if (view.equals("OpeningWindow")) {
			mainWindow = new MainWindow();
			openingWindow.disposeFrame();
		}

		return filename;
		
	}
	
}
