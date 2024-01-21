package my_GIU.listeners;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

public class JFramePrinter {
    JFrame jFrameText;
    StyledDocument doc;
    SimpleAttributeSet normal;


    public JFramePrinter(JFrame jFrameText, StyledDocument doc, SimpleAttributeSet normal) {
        this.jFrameText = jFrameText;
        this.doc = doc;
        this.normal = normal;
    }

    void clearText() {
        try {
            doc.remove(0, doc.getLength());
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }

    void printText(String text) {
        jFrameText.setVisible(true);
        try {
            doc.insertString(doc.getLength(), text, normal);
        } catch (BadLocationException evt) {
            throw new RuntimeException(evt);
        }
    }
}
