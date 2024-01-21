import listeners.BruteForceListener;
import listeners.EncryptDecryptListener;
import listeners.OkayListener;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


class App {
    public static void main(String[] args) {

        //create main frame
        JFrame frame = new JFrame("Decryption Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);

        //additional frame create
        JFrame jFrameText = new JFrame("Get result");
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        jFrameText.setSize(size.width / 2, size.height / 2);
        jFrameText.setLayout(new BorderLayout());
        jFrameText.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel headPanel = new JPanel();
        JLabel textLabel = new JLabel("Your decrypted/encrypted text here");

        //set textPane on new JFrame
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        JScrollPane scrolledPane = new JScrollPane(textPane);

        //document styling
        StyledDocument doc = (StyledDocument) textPane.getDocument();
        SimpleAttributeSet normal = new SimpleAttributeSet();
        StyleConstants.setFontFamily(normal, "SansSerif");
        StyleConstants.setFontSize(normal, 16);

        headPanel.add(textLabel);
        jFrameText.add(headPanel);
        jFrameText.add(scrolledPane);


        JButton encrypt = new JButton("Encrypt with key");
        JButton decrypt = new JButton("Decrypt with known key");
        JButton bruteForce = new JButton("BruteForce");

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        panel.add(encrypt);
        panel.add(decrypt);
        panel.add(bruteForce);

        JPanel southPanel = new JPanel();
        JLabel label = new JLabel("Enter key:");
        JTextField textField = new JTextField(5);
        JButton ok = new JButton("OKAY");
        JButton reset = new JButton("RESET");

        southPanel.add(label);
        southPanel.add(textField);
        southPanel.add(ok);
        southPanel.add(reset);

        southPanel.setVisible(false);
        panel.setVisible(true);

        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // ignore, if this is not a number
                }
            }
        });

        //adding buttons' listeners
        EncryptDecryptListener encryptListener = new EncryptDecryptListener(encrypt, decrypt, southPanel);
        encrypt.addActionListener(encryptListener);
        // #1
        EncryptDecryptListener decryptListener = new EncryptDecryptListener(decrypt, encrypt, southPanel);
        decrypt.addActionListener(decryptListener);
        //#2
        BruteForceListener bFListener = new BruteForceListener(bruteForce, southPanel, textField);
        bFListener.setStyledDoc(doc);
        bFListener.setAttrSet(normal);
        bFListener.setjFrameText(jFrameText);
        bFListener.setDisableButton1(encrypt);
        bFListener.setDisableButton2(decrypt);
        bruteForce.addActionListener(bFListener);
        //#3
        OkayListener okayListener = new OkayListener(doc, normal, frame, jFrameText);
        okayListener.setReset(reset);
        okayListener.setTextField(textField);
        okayListener.setEncryptButton(encrypt);
        okayListener.setDecryptButton(decrypt);
        ok.addActionListener(okayListener);

        //Adding Components to the main frame.
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, southPanel);

        //to the additional frame
        jFrameText.getContentPane().add(BorderLayout.NORTH, headPanel);

        //set frame visible
        frame.setVisible(true);
    }
}