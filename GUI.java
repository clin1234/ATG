
import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*; 

public class GUI implements ActionListener{

    // DATA MEMBERS
    JFrame frame;

    public GUI() {
        // Creating desktop window
        frame = new JFrame();

        // Creating the container within the window to sotre components
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        panel.setBorder(BorderFactory.createEmptyBorder(200, 100, 200, 100));

        // Text describing type of application
        JLabel label = new JLabel("This is the Automated Test Generator, where you will be tested on a series of questions on multiple subjects. Are you ready?");
        panel.add(label);
        
         // Start Button
         JButton startButton = new JButton("Start");
         c.fill = GridBagConstraints.HORIZONTAL;
         c.weightx = 0.5;
         c.gridx = 2;
         c.gridy = 0;
         panel.add(startButton, c);
         startButton.addActionListener(this);
        
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Automatic Test Generator");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
    }
}
