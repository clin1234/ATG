//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartFrame;
//import org.jfree.data.general.DefaultPieDataset;
//import org.jfree.data.general.PieDataset;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI {
	
	// Data members
	JFrame window;
	Exam exam;
	String userName;

	// Start screen data members
	JPanel appInfoPane; 
	JPanel buttonPane;
	JLabel appTitle;
	JLabel appDescription;
	JButton startButton;
	
	// Exam screen data members
	JPanel examPane;
	JPanel choiceButtonPane;
	JLabel questionLabel;
	JRadioButton option1, option2, option3, option4;
	ButtonGroup optionsGroup;
	JButton nextQuestion;
	
	// Results screen data members
	JPanel resultsPane;
	JLabel userScore;
	JLabel numOfTestTakers;
	JLabel avgScoreLabel;
	
	
    public GUI() {
        
    	// Setting up the window
    	window = new JFrame();
    	
    	// Lay out the label and application description from top to bottom
    	appInfoPane = new JPanel();
    	appInfoPane.setLayout(new BoxLayout(appInfoPane, BoxLayout.PAGE_AXIS));
    	appInfoPane.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
    	appInfoPane.setPreferredSize(new Dimension(600,400));
    	
    	appTitle = new JLabel("Automated Exam Generator");
    	appTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    	appTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
    	
    	JLabel appDescription = new JLabel();
    	appDescription.setHorizontalAlignment(SwingConstants.CENTER);
    	appDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
    	appDescription.setText("<html>AEG for short, the Automated Exam Generator<br>"
    			+ "simulates a real online exam of 25 questions. You will be<br>"
    			+ "presented with questions from different subjects (History,<br>"
    			+ "Math, Art, Science, Geography), and in different types.<br>"
    			+ "Select the best suitable answer for each question and<br>"
    			+ "proceed to the next question. Your results will be saved<br>"
    			+ "and displayed at the end. Good luck!</html>");
    	
    	
    	
    	appInfoPane.add(appTitle);
    	appInfoPane.add(Box.createVerticalStrut(70));
    	appInfoPane.add(appDescription);
    
    	// Lay out name field box and start button from left to right
    	buttonPane = new JPanel();
    	buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
    	buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 50));
    	
    	JLabel nameLabel = new JLabel("Your Name: ");
    	JTextField nameField = new JTextField();
    	
    	JButton startButton = new JButton("Start Exam");
    	startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
   
		// TODO: convert to either lambda or method reference
    	startButton.addActionListener(new ActionListener() {
    		@Override
			public void actionPerformed(ActionEvent e) {
    			// Start Exam
    			userName = nameField.getText().toString();
    			createExamScreen(userName);
    		}
    	});
    	buttonPane.add(nameLabel);
    	buttonPane.add(nameField);
    	buttonPane.add(startButton);
    	
    	
    	
    	
    	// Putting it together
    	window.getContentPane().add(appInfoPane, BorderLayout.CENTER);
    	window.getContentPane().add(buttonPane, BorderLayout.PAGE_END);
    	//window.getRootPane().setDefaultButton(startButton);
    	window.setTitle("Automated Exam Generator");
    	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	window.pack();
    	window.setVisible(true);
    }
    

    public void createExamScreen(String userName) {
    	// Disable start screen panes
    	appInfoPane.setVisible(false);
    	buttonPane.setVisible(false);
    	
    	// Laying out the question
    	examPane = new JPanel();
    	examPane.setLayout(new BoxLayout(examPane, BoxLayout.PAGE_AXIS));
    	examPane.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
    	
    	questionLabel = new JLabel("Q: Question would go here, but we have to refactor the code in Exam 1st.");
    	//Exam exam = new Exam(userName, LocalDate.now().toString());

    	// Laying out the options with radio buttons
    	choiceButtonPane = new JPanel();
    	choiceButtonPane.setLayout(new BoxLayout(choiceButtonPane, BoxLayout.Y_AXIS));
    	choiceButtonPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 50, 50));
    	
    	// Initializing radio buttons
    	option1 = new JRadioButton();
    	option2 = new JRadioButton();
    	option3 = new JRadioButton();
    	option4 = new JRadioButton();
    	
    	// Grouping radio buttons together
    	optionsGroup = new ButtonGroup();
    	optionsGroup.add(option1);
    	optionsGroup.add(option2);
    	optionsGroup.add(option3);
    	optionsGroup.add(option4);
    	
    	choiceButtonPane.add(option1);
    	choiceButtonPane.add(option2);
    	choiceButtonPane.add(option3);
    	choiceButtonPane.add(option4);
    	
    	
    	nextQuestion = new JButton("Next");
    	nextQuestion.addActionListener(new ActionListener() {
    		@Override
			public void actionPerformed(ActionEvent e) {
    			// If not in the last question, present the following Q
    			// Else, submit exam and skip to results screen
    			createResultsScreen();
    			
    		}
    	});
    	
    	// Putting it together
    	examPane.add(questionLabel);
    	examPane.add(choiceButtonPane);
    	examPane.add(nextQuestion);
    	window.getContentPane().add(examPane);
    	
    	
    	
    }
    
    public void createResultsScreen() {
    	// Disable exam screen panes
    	examPane.setVisible(false);
    	choiceButtonPane.setVisible(false);
    	
    	// Laying out the user's results
    	resultsPane = new JPanel();
    	resultsPane.setLayout(new BoxLayout(resultsPane, BoxLayout.PAGE_AXIS));
    	resultsPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 50, 50));
    	
    	userScore = new JLabel(userName + ": **getUserScore()**/" + Exam.getMAX_SCORE());
    	numOfTestTakers = new JLabel("Number of test takers: **getNumOfTestTakers()**");
    	avgScoreLabel = new JLabel("Average score per subject: **getAvgScorePerSubject()**");

    	// Putting it all together
    	resultsPane.add(userScore);
    	resultsPane.add(numOfTestTakers);
    	resultsPane.add(avgScoreLabel);
    	window.getContentPane().add(resultsPane);
    	
    	displayGraph();
    }

	public void displayGraph() {
		var ds = new DefaultPieDataset<String>();
		ds.setValue("Math", 4.0);
		ds.setValue("Geopragy", 5.7);
		var c = ChartFactory.createPieChart("Stats", ds, true, true, false);
		var frame = new ChartFrame("Lol", c);
		frame.setVisible(true);
	}
}
