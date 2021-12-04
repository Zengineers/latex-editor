package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import controller.LatexEditorController;

public class MainWindow {

	/* Frame position and dimension */
	private static final int FRAME_X = 100;
	private static final int FRAME_Y = 100;
	private static final int FRAME_WIDTH = 823;
	private static final int FRAME_HEIGHT = 566;
	
	/* Menu bar position and dimension */
	private static final int MENU_BAR_X = 0;
	private static final int MENU_BAR_Y = 0;
	private static final int MENU_BAR_WIDTH = 805;
	private static final int MENU_BAR_HEIGHT = 26;
	
	/* Scroll pane position and dimension */
	private static final int SCROLL_PANE_X = 10;
	private static final int SCROLL_PANE_Y = 39;
	private static final int SCROLL_PANE_WIDTH = 783;
	private static final int SCROLL_PANE_HEIGHT = 467;
	
	
	private JFrame frame;
	private JEditorPane editorPane;
	private LatexEditorController latexEditorController;
	private JMenuBar menuBar;
	private JMenu mnEdit;
	private JMenuItem miRollback;
	private JMenu mnFile;
	private JMenuItem miNewFile;
	private JMenuItem miSave;
	private JMenuItem miLoadFile;
	private JMenuItem miSaveAs;
	private JFileChooser fileChooser;
	private JMenu mnImport;
	private JMenuItem miImportHtml;
	private JMenu mnExport;
	private JMenuItem miExportHtml;
	private JMenuItem miExit;
	private JMenu mnCommands;
	private JMenuItem miChapter;
	private JMenuItem miSection;
	private JMenuItem miSubsection;
	private JMenuItem miSubsubsection;
	private JMenuItem miItemizeList;
	private JMenuItem miEnumerateList;
	private JMenuItem miTable;
	private JMenuItem miFigure;
	private JMenu mnDocument;
	private JMenu mnEnableVersionTracking;
	private JCheckBoxMenuItem cbmiVolatileStrategy;
	private JCheckBoxMenuItem cbmiStableStrategy;
	private JMenuItem miDisableVersionTracking;
	
	public JEditorPane getEditorPane() { return editorPane; }
	public LatexEditorController getLatexEditorController() { return latexEditorController; }	
	public JMenu getMnCommands() { return mnCommands; }	
	public JMenuItem getMiSave() { return miSave; }
	public JMenuItem getMiChapter() { return miChapter; }
	public JMenuItem getMiSection() { return miSection; }
	public JMenuItem getMiSubsection() { return miSubsection; }
	public JMenuItem getMiSubsubsection() { return miSubsubsection; }
	public JMenuItem getMiItemizeList() { return miItemizeList; }
	public JMenuItem getMiEnumerateList() { return miEnumerateList; }
	public JMenuItem getMiTable() { return miTable; }
	public JMenuItem getMiFigure() { return miFigure; }
	public JCheckBoxMenuItem getCbmiVolatileStrategy() { return cbmiVolatileStrategy;	}
	public JCheckBoxMenuItem getCbmiStableStrategy() { return cbmiStableStrategy; }
	public JMenuItem getMiDisableVersionTracking() { return miDisableVersionTracking; }
	public JMenuItem getMiRollback() { return miRollback; }
	public JMenuItem getMiSaveAs() { return miSaveAs; }
	public JFileChooser getFileChooser() { return fileChooser; }
	
	
	public MainWindow() {
		latexEditorController = LatexEditorController.getInstance();
		initializeFrame();
		frame.setVisible(true);
	}

	private void initializeFrame() {
		frame = new JFrame();
		frame.setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Latex Editor");
		
		addMenuBarToFrame();
		addEditorPaneToFrame();
	}
	
	private void addEditorPaneToFrame() {
		editorPane = new JEditorPane();
		String contents = latexEditorController.getDocumentManager().getCurrentDocument().getContents();
		editorPane.setText(contents);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(SCROLL_PANE_X, SCROLL_PANE_Y, SCROLL_PANE_WIDTH, SCROLL_PANE_HEIGHT);
		scrollPane.setViewportView(editorPane);
		
		latexEditorController.setEditorPane(editorPane);
		frame.getContentPane().add(scrollPane);
	}
	
	private void addDisableVersionTrackingMenuItemToDocumentMenu() {
		miDisableVersionTracking = new JMenuItem("Disable Version Tracking");
		miDisableVersionTracking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				latexEditorController.enact("disableVersionTracking");
				updateCheckBoxes("disable");
			}
		});
		mnDocument.add(miDisableVersionTracking);
	}
	
	private void updateCheckBoxes(String command) {
		if (command.equals("disable")) {
			cbmiVolatileStrategy.setSelected(false);
			cbmiVolatileStrategy.setEnabled(true);
			cbmiStableStrategy.setSelected(false);
			cbmiStableStrategy.setEnabled(true);
		}
		else if(command.equals("volatile")) {
			cbmiStableStrategy.setSelected(false);
			cbmiVolatileStrategy.setEnabled(false);
			cbmiStableStrategy.setEnabled(true);
		}
		else if(command.equals("stable")) {
			cbmiVolatileStrategy.setSelected(false);
			cbmiStableStrategy.setEnabled(false);
			cbmiVolatileStrategy.setEnabled(true);
		}
	}
	
	private JCheckBoxMenuItem addStrategyCheckBoxToEnableVersionTrackingMenu(String checkBoxText, String strategyType) {
		JCheckBoxMenuItem checkBox = new JCheckBoxMenuItem(checkBoxText);
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				latexEditorController.getVersionTrackingManager().setStrategyType(strategyType);
				if(latexEditorController.getVersionTrackingManager().isEnabled() == false) {
					latexEditorController.enact("enableVersionTracking");
				}
				else {
					latexEditorController.enact("changeVersionTrackingStrategy");
				}
				updateCheckBoxes(strategyType);
			}
		});
		mnEnableVersionTracking.add(checkBox);
		return checkBox;
		
	}
	
	private void addEnableVersionTrackingMenuToDocumentMenu() {
		mnEnableVersionTracking = new JMenu("Enable Version Tracking");
		mnDocument.add(mnEnableVersionTracking);
		
		cbmiVolatileStrategy = addStrategyCheckBoxToEnableVersionTrackingMenu("Volatile Strategy", "volatile");
		cbmiStableStrategy = addStrategyCheckBoxToEnableVersionTrackingMenu("Stable Strategy", "stable");
	}
	
	private void addDocumentMenuToMenuBar() {
		mnDocument = new JMenu("Document");
		menuBar.add(mnDocument);
		
		addEnableVersionTrackingMenuToDocumentMenu();
		addDisableVersionTrackingMenuItemToDocumentMenu();
	}
	
	private void addRollbackMenuItemToEditMenu() {
		miRollback = new JMenuItem("Rollback");
		miRollback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				latexEditorController.enact("rollback");
			}
		});
		mnEdit.add(miRollback);
	}
	
	private void setDisabledCommandsMenuItems() {
		if(latexEditorController.getTemplateType().equals("letterTemplate")) {
			miChapter.setEnabled(false);
			miSection.setEnabled(false);
			miSubsection.setEnabled(false);
			miSubsubsection.setEnabled(false);
			miItemizeList.setEnabled(false);
			miEnumerateList.setEnabled(false);
			miTable.setEnabled(false);
			miFigure.setEnabled(false);
		}
		if(latexEditorController.getTemplateType().equals("articleTemplate")) {
			miChapter.setEnabled(false);
		}
	}
	
	private JMenuItem addCommandMenuItemToCommandsMenu(String text, String command) {
		JMenuItem menuItem = new JMenuItem(text);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				latexEditorController.setCommandType(command);
				latexEditorController.enact("addLatex");
			}
		});
		mnCommands.add(menuItem);
		return menuItem;
	}
	
	private void addCommandsMenuToMenuBar() {
		mnCommands = new JMenu("Commands");
		menuBar.add(mnCommands);
		
		miChapter = addCommandMenuItemToCommandsMenu("Chapter", "chapter");
		mnCommands.addSeparator();
		miSection = addCommandMenuItemToCommandsMenu("Section", "section");
		miSubsection = addCommandMenuItemToCommandsMenu("Subsection", "subsection");
		miSubsubsection = addCommandMenuItemToCommandsMenu("Subsubsection", "subsubsection");
		mnCommands.addSeparator();
		miItemizeList = addCommandMenuItemToCommandsMenu("Itemize List", "itemize");
		miEnumerateList = addCommandMenuItemToCommandsMenu("Enumerate List", "enumerate");
		mnCommands.addSeparator();
		miTable = addCommandMenuItemToCommandsMenu("Table", "table");
		miFigure = addCommandMenuItemToCommandsMenu("Figure", "figure");
		
		setDisabledCommandsMenuItems();
	}
	
	private void addExitMenuItemToFileMenu() {
		miExit = new JMenuItem("Exit");
		miExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				System.exit(0);
			}
		});
		mnFile.add(miExit);
	}
	
	private void addHtmlMenuItemToExportMenu() {
		miExportHtml = new JMenuItem("HTML");
		miExportHtml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser(System.getProperty("user.dir"));
				int option = filechooser.showSaveDialog(null);
				if (option == JFileChooser.APPROVE_OPTION) {
					String filename = filechooser.getSelectedFile().toString();
					if (filename.endsWith(".html") == false) {
						filename += ".html";
					}
					latexEditorController.setFilename(filename);
					latexEditorController.enact("exportHtml");
				}
			}
		});
		mnExport.add(miExportHtml);
	}
	
	private void addExportMenuToFileMenu() {
		mnExport = new JMenu("Export");
		mnFile.add(mnExport);
		addHtmlMenuItemToExportMenu();
	}
	
	private void addHtmlMenuItemToImportMenu() {
		miImportHtml = new JMenuItem("HTML");
		miImportHtml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser(System.getProperty("user.dir"));
				int option = filechooser.showOpenDialog(null);
				if (option == JFileChooser.APPROVE_OPTION) {
					String filename = filechooser.getSelectedFile().toString();
					System.out.println(filename);
				}
			}
		});
		mnImport.add(miImportHtml);
	}
	
	private void addImportMenuToFileMenu() {
		mnImport = new JMenu("Import");
		mnFile.add(mnImport);
		addHtmlMenuItemToImportMenu();
	}

	private void addSaveAsMenuItemToFileMenu() {
		miSaveAs = new JMenuItem("Save As...");
		miSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileChooser = new JFileChooser(System.getProperty("user.dir"));
				int option = fileChooser.showSaveDialog(null);
				if(option == JFileChooser.APPROVE_OPTION) {
					String filename = fileChooser.getSelectedFile().toString();
					if(filename.endsWith(".tex") == false) {
						filename = filename+".tex";
					}
					latexEditorController.setFilename(filename);
					latexEditorController.enact("save");
				}
			}
		});
		mnFile.add(miSaveAs);
	}
	
	private void addOpenMenuItemToFileMenu() {
		miLoadFile = new JMenuItem("Open...");
		miLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser(System.getProperty("user.dir"));
				int option = filechooser.showOpenDialog(null);
				if(option == JFileChooser.APPROVE_OPTION) {
					String filename = filechooser.getSelectedFile().toString();
					latexEditorController.setFilename(filename);
					latexEditorController.enact("load");
					setDisabledCommandsMenuItems();
					String contents = latexEditorController.getDocumentManager().getCurrentDocument().getContents();
					editorPane.setText(contents);
				}
			}
		});
		mnFile.add(miLoadFile);
	}
	
	private void addSaveMenuItemToFileMenu() {
		miSave = new JMenuItem("Save");
		miSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				latexEditorController.enact("edit");
			}
		});
		mnFile.add(miSave);
	}
	
	private void addNewFileMenuItemToFileMenu() {
		miNewFile = new JMenuItem("New");
		miNewFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ChooseTemplate("main");
				frame.dispose();
			}
		});
		mnFile.add(miNewFile);
	}
	
	private void addFileMenuToMenuBar() {
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		addNewFileMenuItemToFileMenu();
		addOpenMenuItemToFileMenu();
		mnFile.addSeparator();
		addSaveMenuItemToFileMenu();
		addSaveAsMenuItemToFileMenu();
		mnFile.addSeparator();
		addImportMenuToFileMenu();
		addExportMenuToFileMenu();
		mnFile.addSeparator();
		addExitMenuItemToFileMenu();
	}

	private void addEditMenuToMenuBar() {
		mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		addRollbackMenuItemToEditMenu();
	}
	
	private void addMenuBarToFrame() {
		menuBar = new JMenuBar();
		menuBar.setBounds(MENU_BAR_X, MENU_BAR_Y, 
				MENU_BAR_WIDTH, MENU_BAR_HEIGHT);
		frame.getContentPane().add(menuBar);
		
		addFileMenuToMenuBar();
		addEditMenuToMenuBar();
		addCommandsMenuToMenuBar();
		addDocumentMenuToMenuBar();
	}

	public void disposeFrame() {
		frame.dispose();
	}

}
