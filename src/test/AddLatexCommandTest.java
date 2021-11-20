package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import resources.Strings;

class AddLatexCommandTest extends EnvironmentSetup {

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
	@CsvSource(value = {"bookTemplate-chapter", "bookTemplate-section", "bookTemplate-subsection", "bookTemplate-subsubsection",
										"bookTemplate-itemize", "bookTemplate-enumerate", "bookTemplate-table", "bookTemplate-figure",
										
										"articleTemplate-chapter", "articleTemplate-section", "articleTemplate-subsection", "articleTemplate-subsubsection",
										"articleTemplate-itemize", "articleTemplate-enumerate", "articleTemplate-table", "articleTemplate-figure",
										
										"reportTemplate-chapter", "reportTemplate-section", "reportTemplate-subsection", "reportTemplate-subsubsection",
										"reportTemplate-itemize", "reportTemplate-enumerate", "reportTemplate-table", "reportTemplate-figure",
										
										"letterTemplate-chapter", "letterTemplate-section", "letterTemplate-subsection", "letterTemplate-subsubsection",
										"letterTemplate-itemize", "letterTemplate-enumerate", "letterTemplate-table", "letterTemplate-figure",
										
										"emptyTemplate-chapter", "emptyTemplate-section", "emptyTemplate-subsection", "emptyTemplate-subsubsection",
										"emptyTemplate-itemize", "emptyTemplate-enumerate", "emptyTemplate-table", "emptyTemplate-figure",}, 
							delimiter = '-')
	void testExecute(String templateType, String latexCommandType) {
		selectTemplateRadioButton(templateType);
		setUpMainWindow();
		
		expectedContents = estimateExpectedContents(templateType, latexCommandType);
		clickLatexCommandMenuItem(latexCommandType);
		mainWindow.getMiSave().doClick();
		
		String documentContents = latexEditorController.getDocumentManager().getCurrentDocument().getContents();
		String editorPaneContents = mainWindow.getEditorPane().getText();
		
		assertEquals(documentContents, expectedContents);
		assertEquals(editorPaneContents, expectedContents);
	}

	private String estimateExpectedContents(String templateType, String latexCommandType) {
		boolean isLetterTemplate = templateType.equals("letterTemplate");
		boolean isArticleTemplate = templateType.equals("articleTemplate");
		boolean isChapterCommand = latexCommandType.equals("chapter");
		boolean commandNotSupportedByTemplate = isLetterTemplate || (isArticleTemplate && isChapterCommand);
		
		if (commandNotSupportedByTemplate)
			return mainWindow.getEditorPane().getText();
		
		int caretPosition = mainWindow.getEditorPane().getCaretPosition();
		String contents = mainWindow.getEditorPane().getText();
		String before = contents.substring(0, caretPosition);
		String after = contents.substring(caretPosition);
		
		return before + Strings.getLatexCommand(latexCommandType) + after;
	}

}
