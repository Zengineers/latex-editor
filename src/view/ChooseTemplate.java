package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class ChooseTemplate {

	private JFrame frame;
	private LatexEditorView latexEditorView;
	private String previous;
	
	private JButton btnCreate;
	private JRadioButton book;
	private JRadioButton article;
	private JRadioButton report;
	private JRadioButton letter;
	private MainWindow mainWindow;

	public LatexEditorView getLatexEditorView() {
		return latexEditorView;
	}
	
	public JButton getBtnCreate() {
		return btnCreate;
	}

	public JRadioButton getBookRadioButton() {
		return book;
	}

	public JRadioButton getArticleRadioButton() {
		return article;
	}

	public JRadioButton getReportRadioButton() {
		return report;
	}

	public JRadioButton getLetterRadioButon() {
		return letter;
	}
	
	public MainWindow getMainWindow() {
		return mainWindow;
	}

	/**
	 * Create the application.
	 * @param latexEditorView 
	 */
	public ChooseTemplate(LatexEditorView latexEditorView, String previous) {
		this.latexEditorView = latexEditorView;
		this.previous = previous;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void diselectRadioButtons(JRadioButton radioButton1, JRadioButton radioButton2, JRadioButton radioButton3,JRadioButton radioButton4) {
		if(radioButton1.isSelected()) {
			radioButton2.setSelected(false);
			radioButton3.setSelected(false);
			radioButton4.setSelected(false);
		}
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		book = new JRadioButton("Book");
		article = new JRadioButton("Article");
		report = new JRadioButton("Report");
		letter = new JRadioButton("Letter");
		
		book.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diselectRadioButtons(book, article, report, letter);
			}
		});
		book.setBounds(42, 51, 127, 25);
		frame.getContentPane().add(book);
		
		JLabel lblChooseTemplate = new JLabel("Choose template. (Leave empty for blank document)");
		lblChooseTemplate.setBounds(42, 13, 332, 16);
		frame.getContentPane().add(lblChooseTemplate);
		
		
		article.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diselectRadioButtons(article, book, report, letter);

			}
		});
		article.setBounds(42, 137, 127, 25);
		frame.getContentPane().add(article);
		
		
		report.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diselectRadioButtons(report, article, book, letter);
				
			}
		});
		report.setBounds(213, 51, 127, 25);
		frame.getContentPane().add(report);
		
		
		letter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diselectRadioButtons( letter, report, article, book);
			}
		});
		letter.setBounds(213, 137, 127, 25);
		frame.getContentPane().add(letter);
		
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(book.isSelected()) {
					latexEditorView.setType("bookTemplate");
				}
				else if(report.isSelected()) {
					latexEditorView.setType("reportTemplate");
				}
				else if(article.isSelected()) {
					latexEditorView.setType("articleTemplate");
				}
				else if(letter.isSelected()) {
					latexEditorView.setType("letterTemplate");
				}
				else {
					latexEditorView.setType("emptyTemplate");
				}

				latexEditorView.getController().enact("create");
				mainWindow = new MainWindow(latexEditorView);
				frame.dispose();
			}
		});
		btnCreate.setBounds(213, 196, 97, 25);
		frame.getContentPane().add(btnCreate);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(previous.equals("main")) {
					MainWindow mainWindow = new MainWindow(latexEditorView);
					frame.dispose();
				}
				else {
					OpeningWindow openingWindow = new OpeningWindow();
					frame.dispose();
				}
			}
		});
		btnBack.setBounds(46, 196, 97, 25);
		frame.getContentPane().add(btnBack);
	}
	
	public void disposeFrame() {
		frame.dispose();
	}
	
}
