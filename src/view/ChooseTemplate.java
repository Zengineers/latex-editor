package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import controller.LatexEditorController;

public class ChooseTemplate {

	/* Frame position and dimension  */
	private static final int FRAME_X = 100;
	private static final int FRAME_Y = 100;
	private static final int FRAME_WIDTH = 450;
	private static final int FRAME_HEIGHT = 300;
	
	/* Buttons' positions and dimensions */
	private static final int EXIT_BT_Y = 196;
	private static final int EXIT_BT_X = 46;
	private static final int CREATE_BT_Y = 196;
	private static final int CREATE_BT_X = 213;
	private static final int BT_HEIGHT = 25;
	private static final int BT_WIDTH = 97;
	
	/* Label position and dimensions */
	private static final int CHOOSE_LB_HEIGHT = 16;
	private static final int CHOOSE_LB_WIDTH = 332;
	private static final int CHOOSE_LB_Y = 13;
	private static final int CHOOSE_LB_X = 42;
	
	/* Radio buttons' positions and dimensions */
	private static final int ARTICLE_RBT_Y = 137;
	private static final int ARTICLE_RBT_X = 42;
	private static final int BOOK_RBT_Y = 51;
	private static final int BOOK_RBT_X = 42;
	private static final int LETTER_RBT_Y = 137;
	private static final int LETTER_RBT_X = 213;
	private static final int REPORT_RBT_Y = 51;
	private static final int REPORT_RBT_X = 213;
	private static final int RBT_WIDTH = 127;
	private static final int RBT_HEIGHT = 25;
	
	
	private JFrame frame;
	private String previousFrame;
	private LatexEditorController latexEditorController;
	private MainWindow mainWindow;
	private JLabel lbChooseTemplate;
	private ButtonGroup radioButtonGroup;
	private JRadioButton rbtBook;
	private JRadioButton rbtArticle;
	private JRadioButton rbtReport;
	private JRadioButton rbtLetter;
	private JButton btCreate;
	private JButton btBack;

	public LatexEditorController getLatexEditorController() { return latexEditorController; }	
	public JButton getBtCreate() { return btCreate; }
	public JRadioButton getRbtBook() { return rbtBook; }
	public JRadioButton getRbtArticle() { return rbtArticle; }
	public JRadioButton getRbtReport() { return rbtReport; }
	public JRadioButton getRbtLetter() { return rbtLetter; }	
	public MainWindow getMainWindow() { return mainWindow; }


	public ChooseTemplate(String previousFrame) {
		latexEditorController = LatexEditorController.getInstance();		
		this.previousFrame = previousFrame;
		initializeFrame();
		frame.setVisible(true);
	}

	private void initializeFrame() {
		frame = new JFrame();
		frame.setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Latex Editor");	

		addChooseTemplateLabel();
		radioButtonGroup = new ButtonGroup();
		rbtBook = addRadioButton("Book", BOOK_RBT_X, BOOK_RBT_Y, RBT_WIDTH, RBT_HEIGHT);
		rbtArticle = addRadioButton("Article", ARTICLE_RBT_X, ARTICLE_RBT_Y, RBT_WIDTH, RBT_HEIGHT);
		rbtReport = addRadioButton("Report", REPORT_RBT_X, REPORT_RBT_Y, RBT_WIDTH, RBT_HEIGHT);
		rbtLetter = addRadioButton("Letter", LETTER_RBT_X, LETTER_RBT_Y, RBT_WIDTH, RBT_HEIGHT);
		addCreateButton();
		addBackButton();
	}

	private void addChooseTemplateLabel() {
		lbChooseTemplate = new JLabel("Choose template. (Leave empty for blank document)");
		lbChooseTemplate.setBounds(CHOOSE_LB_X, CHOOSE_LB_Y, 
				CHOOSE_LB_WIDTH, CHOOSE_LB_HEIGHT);
		frame.getContentPane().add(lbChooseTemplate);
	}
	
	private JRadioButton addRadioButton(String name, int X, int Y, int WIDTH, int HEIGHT) {
		JRadioButton radioButton = new JRadioButton(name);
		radioButton.setBounds(X, Y, WIDTH, HEIGHT);
		radioButtonGroup.add(radioButton);
		frame.getContentPane().add(radioButton);
		return radioButton;
	}
	
	private void addCreateButton() {
		btCreate = new JButton("Create");
		btCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rbtBook.isSelected()) {
					latexEditorController.setTemplateType("bookTemplate");
				}
				else if(rbtReport.isSelected()) {
					latexEditorController.setTemplateType("reportTemplate");
				}
				else if(rbtArticle.isSelected()) {
					latexEditorController.setTemplateType("articleTemplate");
				}
				else if(rbtLetter.isSelected()) {
					latexEditorController.setTemplateType("letterTemplate");
				}
				else {
					latexEditorController.setTemplateType("emptyTemplate");
				}
				createMainWindow();
			}
		});
		btCreate.setBounds(CREATE_BT_X, CREATE_BT_Y, BT_WIDTH, BT_HEIGHT);
		frame.getContentPane().add(btCreate);
	}
	
	private void createMainWindow() {
		latexEditorController.enact("create");
		mainWindow = new MainWindow();
		frame.dispose();
	}
	
	private void addBackButton() {
		btBack = new JButton("Back");
		btBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(previousFrame.equals("main")) {
					mainWindow = new MainWindow();
					frame.dispose();
				}
				else {
					new OpeningWindow();
					frame.dispose();
				}
			}
		});
		btBack.setBounds(EXIT_BT_X, EXIT_BT_Y, BT_WIDTH, BT_HEIGHT);
		frame.getContentPane().add(btBack);
	}
	
	public void disposeFrame() {
		frame.dispose();
	}
	
}
