package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import controller.LatexEditorController;

public class OpeningWindow {

	/* Frame position and dimension  */
	private static final int FRAME_X = 100;
	private static final int FRAME_Y = 100;
	private static final int FRAME_WIDTH = 450;
	private static final int FRAME_HEIGHT = 300;
	
	/* Buttons' positions and dimensions */
	private static final int CREATE_BT_X = 89;
	private static final int CREATE_BT_Y = 26;
	private static final int OPEN_BT_X = 89;
	private static final int OPEN_BT_Y = 92;
	private static final int BT_WIDTH = 278;
	private static final int BT_HEIGHT = 36;
	
	/* Exit button position and dimension */
	private static final int EXIT_X = 99;
	private static final int EXIT_Y = 169;
	private static final int EXIT_WIDTH = 268;
	private static final int EXIT_HEIGHT = 25;
	
	
	private JFrame frame;
	private LatexEditorController latexEditorController;
	private ChooseTemplate chooseTemplate;
	private JButton btCreateNewDocument;
	private JButton btOpenExistingDocument;
	private JButton btnExit;
	
	public LatexEditorController getLatexEditorController() { return latexEditorController; }	
	public JButton getBtCreateNewDocument() { return btCreateNewDocument; }
	public ChooseTemplate getChooseTemplate() { return chooseTemplate; }


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpeningWindow openingWindow = new OpeningWindow();
					openingWindow.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public OpeningWindow() {
		initializeController();
		initializeFrame();
		frame.setVisible(true);
	}
	
	private void initializeController() {
		latexEditorController = LatexEditorController.getInstance();
		latexEditorController.createCommands();
	}

	private void initializeFrame() {
		frame = new JFrame();
		frame.setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Latex Editor");	
		
		addCreateNewDocumentButton();
		addOpenExistingDocumentButton();
		addExitButton();
	}
	
	private void addCreateNewDocumentButton() {
		btCreateNewDocument = new JButton("Create New Document");
		btCreateNewDocument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseTemplate = new ChooseTemplate("opening");
				frame.dispose();
			}
		});
		btCreateNewDocument.setBounds(CREATE_BT_X, CREATE_BT_Y, BT_WIDTH, BT_HEIGHT);
		frame.getContentPane().add(btCreateNewDocument);
	}
	
	private void addOpenExistingDocumentButton() {
		btOpenExistingDocument = new JButton("Open Existing Document");
		btOpenExistingDocument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser(System.getProperty("user.dir"));
				int option = filechooser.showOpenDialog(null);
				if(option == JFileChooser.APPROVE_OPTION) {
					String filename = filechooser.getSelectedFile().toString();
					latexEditorController.setFilename(filename);
					latexEditorController.enact("load");
					new MainWindow();
					frame.dispose();
				}
			}
		});
		btOpenExistingDocument.setBounds(OPEN_BT_X, OPEN_BT_Y, BT_WIDTH, BT_HEIGHT);
		frame.getContentPane().add(btOpenExistingDocument);
	}
	
	private void addExitButton() {
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(EXIT_X, EXIT_Y, EXIT_WIDTH, EXIT_HEIGHT);
		frame.getContentPane().add(btnExit);
	}
	
	public void disposeFrame() {
		frame.dispose();
	}
	
}
