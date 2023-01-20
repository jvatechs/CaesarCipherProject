package my_GIU.listeners;

import encrypt_decrypt_from_file.Main;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Path;

public class OkayListener implements ActionListener {
    JTextField textField;
    JButton encryptButton;
    JButton decryptButton;
    JButton reset;


    private final StyledDocument doc;
    private final SimpleAttributeSet normal;
    private final JFrame mainFrame;
    private final JFrame jFrameText;



    public OkayListener(StyledDocument doc, SimpleAttributeSet normal, JFrame mainFrame, JFrame jFrameText) {
        this.doc = doc;
        this.normal = normal;
        this.mainFrame = mainFrame;
        this.jFrameText = jFrameText;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        ((AbstractButton) event.getSource()).setEnabled(false); //feature for blocking OKAY

        try {
            doc.remove(0, doc.getLength());
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }

        int key = 0;
        if (textField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame.getComponent(0), "Please enter key!");
        } else {
            key = Integer.parseInt(textField.getText());
        }

        String text = null;
        Path filePath;

        if (encryptButton.isSelected() ) {
            try {
                filePath = EncryptDecryptListener.path;
                 text = Main.readFileAndEncrypt(filePath, key);
                 encryptButton.setSelected(false);
            } catch (StringIndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(mainFrame.getComponent(0), "Введите действительный ключ от 0 до 41 (НЕВКЛЮЧИТЕЛЬНО) !");
                textField.setText("");
            }
        }else if(decryptButton.isSelected()) {
            try {

                filePath = EncryptDecryptListener.path;
                 text = Main.readFileAndDecrypt(filePath, key);
                 decryptButton.setSelected(false);
            } catch (StringIndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(mainFrame.getComponent(0), "Введите действительный ключ от 0 до 41 (НЕВКЛЮЧИТЕЛЬНО) !");
                textField.setText("");
            }
        }

        if (text != null) {
            jFrameText.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(mainFrame.getComponent(0), "Результат выведения null, повторите с непустым файлом!");
        }

        try {
            doc.insertString(doc.getLength(), text, normal);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }

        //RESET button
        reset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ((AbstractButton) event.getSource()).setEnabled(true); // feature for unblocking OKAY button
                textField.setText("");
            }
        });
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }

    public void setEncryptButton(JButton encryptButton) {
        this.encryptButton = encryptButton;
    }

    public void setDecryptButton(JButton decryptButton) {
        this.decryptButton = decryptButton;
    }

    public void setReset(JButton reset) {
        this.reset = reset;
    }
}
