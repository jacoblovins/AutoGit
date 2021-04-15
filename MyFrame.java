import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MyFrame extends JFrame implements ActionListener {

    JButton submitButton;
    JTextField textField1;
    JTextField textField2;
    JTextField textField3;

    MyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        textField1 = new JTextField();
        textField1.setPreferredSize(new Dimension(250, 40));
        textField2 = new JTextField();
        textField2.setPreferredSize(new Dimension(250, 40));
        textField3 = new JTextField();
        textField3.setPreferredSize(new Dimension(250, 40));

        this.add(submitButton);
        this.add(textField1);
        this.add(textField2);
        this.add(textField3);
        this.pack();
        this.setVisible(true);
    }

    Process p = null;

    @Override
    public void actionPerformed(ActionEvent e) {
        File dir = new File("/Users/jacoblovins/Desktop/" + textField1.getText());
        String[] stageCommand = { "git", "add", "-A" };
        String[] commitCommand = { "git", "commit", "-m", textField2.getText()};
        String[] pushCommand = { "git", "push", "origin", textField3.getText()};

        if (e.getSource() == submitButton) {
            System.out.println(dir);

            ProcessBuilder stage = new ProcessBuilder(stageCommand);
            stage.directory(new File("/Users/jacoblovins/Desktop/" + textField1.getText()));

            ProcessBuilder commit = new ProcessBuilder(commitCommand);
            commit.directory(new File("/Users/jacoblovins/Desktop/" + textField1.getText()));

            ProcessBuilder push = new ProcessBuilder(pushCommand);
            push.directory(new File("/Users/jacoblovins/Desktop/" + textField1.getText()));

            try {
                p = stage.start();
                p.waitFor();
                p = commit.start();
                p.waitFor();
                p = push.start();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        } 
        // else if (e.getSource() == pushButton) {
        //     ProcessBuilder push = new ProcessBuilder(pushCommand);
        //     push.directory(new File("/Users/jacoblovins/Desktop/" + textField1.getText()));

        //     try {
        //         p = push.start();
        //     } catch (IOException e1) {
        //         e1.printStackTrace();
        //     }
        // }
    }
}
