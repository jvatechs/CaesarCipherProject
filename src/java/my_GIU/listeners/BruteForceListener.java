package my_GIU.listeners;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;

import static brute_force.BruteForcedText.getBruteForcedText;

public class BruteForceListener implements ActionListener {
//    JFileChooser fileChooser;
    JButton bruteForce;
    JPanel panel;
    JTextField textField;
    JFramePrinter jFramePrinter;
    JButton disableButton1;
    JButton disableButton2;
    private StyledDocument doc;
    private SimpleAttributeSet normal;
    private JFrame jFrameText;


    public BruteForceListener(JButton bruteForce, JPanel panel, JTextField textField) {
//        this.fileChooser = fileChooser;
        this.bruteForce = bruteForce;
        this.panel = panel;
        this.textField = textField;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.jFramePrinter = new JFramePrinter(jFrameText, doc, normal);

        jFramePrinter.clearText();

        Path path = null;

        JFileChooser newFileChooser = new JFileChooser();
        newFileChooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "/src/resources"));
        int result = newFileChooser.showOpenDialog(bruteForce);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = newFileChooser.getSelectedFile();
            path = selectedFile.toPath();
        }
        panel.setVisible(false);
        textField.setText("");

        String text = null;
        try {
           text = getBruteForcedText(path);
        } catch (NullPointerException exp) {
            JOptionPane.showMessageDialog(null, "Operation canceled.");
        }

        if (text != null) {
            jFramePrinter.printText(text);
        } else if (path != null){
            JOptionPane.showMessageDialog(null, "Результат выведения null, повторите с непустым файлом!");
        }



        ButtonSwitcher.disableAllButtons(disableButton1, disableButton2);
    }

    public void setStyledDoc(StyledDocument doc) {
        this.doc = doc;
    }

    public void setAttrSet(SimpleAttributeSet normal) {
        this.normal = normal;
    }

    public void setjFrameText(JFrame jFrameText) {
        this.jFrameText = jFrameText;
    }

    public void setDisableButton1(JButton disableButton1) {
        this.disableButton1 = disableButton1;
    }

    public void setDisableButton2(JButton disableButton2) {
        this.disableButton2 = disableButton2;
    }
}
