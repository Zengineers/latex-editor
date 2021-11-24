package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JFileChooser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import model.DocumentManager;

class SaveCommandTest extends EnvironmentSetup {

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
	void testExecute(String templateType) throws IOException {
		selectTemplateRadioButton(templateType);
		setUpMainWindow();		

		DocumentManager documentManager = latexEditorController.getDocumentManager();
		String filename = simulateSaveAsMenuItemBehaviour(templateType);	
		String documentContents = documentManager.getCurrentDocument().getContents();		
		String savedFileContents = Files.readString(Path.of(filename));
		File savedFile = new File(filename);
		
		assertEquals(savedFile.exists(), true);
		assertEquals(savedFile.isDirectory(), false);
		assertEquals(savedFileContents, documentContents);
		
	}

	private String simulateSaveAsMenuItemBehaviour(String templateType) {
		String filename = System.getProperty("user.dir") + "\\" + templateType + "TestFile.tex";
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));		
		chooser.setSelectedFile(new File(filename));	
		chooser.approveSelection();
		latexEditorController.setFilename(filename);
		latexEditorController.enact("save");
		return filename;
	}

}
