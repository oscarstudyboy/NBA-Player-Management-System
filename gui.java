import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class gui extends JFrame  {
    public gui() {
        // Create a JLabel for the picture
        ImageIcon imageIcon = new ImageIcon("D:\\Java files\\nbaPlayer manager system\\src\\nba.png");
        JLabel imageLabel = new JLabel(imageIcon);

        // Create a panal for  each element
        JPanel panel = new JPanel();
        // set panels properties
        panel.setBounds(180, 50, 400, 450);
        JTextField tf = new JTextField(35);// accepts upto 35characters
        tf.setPreferredSize(new Dimension(35,40));
        JButton search = new JButton("Search");
        search.setPreferredSize(new Dimension(80,50));
        // have an action when pressing search button
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String searchText = tf.getText();
                // Search for the JFrame with the given search text
              try{
                    playerPage newFrame = new playerPage(searchText);
                    setVisible(false);
                    newFrame.setVisible(true);
                }catch (Exception errors){
                  System.out.println("error"+e);
              }

            }
        });


        panel.add(imageLabel);
        panel.add(tf, BorderLayout.CENTER);
        panel.add(search);
        // Add the JLabel to the JPanel


        // Add the JPanel to the JFrame
        add(panel);

        // Set the JFrame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Nba Player manager systerm");
        setSize(800, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setBackground(Color.WHITE);
        setVisible(true);
        repaint();
    }

    public static void main(String[] args)throws Exception {
        new gui();
    }
}
