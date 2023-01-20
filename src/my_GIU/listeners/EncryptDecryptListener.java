package my_GIU.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;

public class EncryptDecryptListener implements ActionListener {
    static Path path;
    private  JButton targetButton;
    private  JButton otherButton;
    private  JPanel panel;


    public EncryptDecryptListener(JButton targetButton, JButton otherButton, JPanel panel) {
        this.targetButton = targetButton;
        this.otherButton = otherButton;
        this.panel = panel;
    }


    //    public Path getPath() {
//        return path;
//    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser newFileChooser = new JFileChooser();
        newFileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int result = newFileChooser.showOpenDialog(targetButton);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = newFileChooser.getSelectedFile();
            path = selectedFile.toPath();
        }

        panel.setVisible(true);
        otherButton.setSelected(false);
        targetButton.setSelected(true);
    }

}
