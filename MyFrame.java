
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class MyFrame extends JFrame implements ActionListener {

	JLabel repoLabel;
    JTextField repoText;
    JLabel branchLabel;
    JTextField branchText;
    JLabel commitLabel;
    JTextField commitText;
    JButton submitButton;
    JLabel status;
    int count = 0;

    MyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setTitle("Git Automation");


        repoLabel = new JLabel("Repo Path:");
        repoText = new JTextField();
        repoText.setPreferredSize(new Dimension(250, 40));
        
        branchLabel = new JLabel("Branch:");
        branchText = new JTextField();
        branchText.setPreferredSize(new Dimension(250, 40));
        
        commitLabel = new JLabel("Commit Message:");
        commitText = new JTextField();
        commitText.setPreferredSize(new Dimension(250, 40));
        
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        
        status = new JLabel();

        this.add(repoLabel);
        this.add(repoText);
        this.add(branchLabel);
        this.add(branchText);
        this.add(commitLabel);
        this.add(commitText);
        this.add(submitButton);
        this.pack();
        this.setVisible(true);
    }

    Process p = null;

    @Override
    public void actionPerformed(ActionEvent e) {
        File dir = new File("/Users/jacoblovins/Desktop/" + repoText.getText());
        String[] stageCommand = { "git", "add", "-A" };
        String[] commitCommand = { "git", "commit", "-m", commitText.getText()};
        String[] pushCommand = { "git", "push", "origin", branchText.getText()};

        if (e.getSource() == submitButton) {
            System.out.println(dir);

            ProcessBuilder stage = new ProcessBuilder(stageCommand);
            stage.directory(new File("/Users/jacoblovins/Desktop/" + repoText.getText()));

            ProcessBuilder commit = new ProcessBuilder(commitCommand);
            commit.directory(new File("/Users/jacoblovins/Desktop/" + repoText.getText()));

            ProcessBuilder push = new ProcessBuilder(pushCommand);
            push.directory(new File("/Users/jacoblovins/Desktop/" + repoText.getText()));

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
            
            count++;
            status.setText("Changes Pushed: " + count);
            commitText.setText("");
            this.add(status);
            this.pack();
        } 
    }
}

