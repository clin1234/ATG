import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI {
	
	// Data members
	JFrame window;
	Container con;
	JPanel startPanel; 

    public GUI() {
        
    	// Setting up the window
    	window = new JFrame();
    	
    	JLabel appTitle = new JLabel("Automated Exam Generator");
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
    	
    	JButton startButton = new JButton("Start Exam");
    	startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		// TODO: convert to either lambda or method reference
    	startButton.addActionListener(new ActionListener() {
    		@Override
			public void actionPerformed(ActionEvent e) {
    			// Start Exam
    		}
    	});
    	
    	startPanel = new JPanel();
    	startPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
    	startPanel.setPreferredSize(new Dimension(600,400));
    	startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.PAGE_AXIS));
    	startPanel.add(appTitle);
    	startPanel.add(Box.createVerticalStrut(50));
    	startPanel.add(appDescription);
    	startPanel.add(Box.createVerticalStrut(50));
    	startPanel.add(startButton);
    
   
    	window.getContentPane().add(startPanel);
    	window.setTitle("Automated Exam Generator");
    	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	window.pack();
    	window.setVisible(true);
    }
}
