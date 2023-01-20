package my_GIU;

import my_GIU.listeners.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.file.Path;


class Gui {

    private static Path filePath;
    JFileChooser fileChooser = new JFileChooser();

    public static void main(String[] args) {
        JTextField filename = new JTextField(), dir = new JTextField();

        //создание фрейма
        JFrame frame = new JFrame("Decryption Frame");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(400, 500);

        //создание дополнительного фрейма
        JFrame jFrameText = new JFrame("Get result");
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        jFrameText.setSize(size.width / 2, size.height / 2);
        jFrameText.setLayout(new BorderLayout());
        jFrameText.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JPanel headPanel = new JPanel();
        JLabel textLabel = new JLabel("Your decrypted/encrypted text here");

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        JScrollPane scrolledPane = new JScrollPane(textPane);

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


        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Only text files", "txt", "text");
        fileChooser.setFileFilter(filter);

        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // игнор, если не номер
                }
            }
        });


        EncryptDecryptListener encryptListener = new EncryptDecryptListener(encrypt, decrypt, southPanel);
        encrypt.addActionListener(encryptListener);

        EncryptDecryptListener decryptListener = new EncryptDecryptListener(decrypt, encrypt, southPanel);
        decrypt.addActionListener(decryptListener);

        BruteForceListener bFListener = new BruteForceListener(fileChooser, bruteForce, southPanel, textField);
        bFListener.setStyledDoc(doc);
        bFListener.setAttrSet(normal);
        bFListener.setjFrameText(jFrameText);
        bFListener.setDisableButton1(encrypt);
        bFListener.setDisableButton2(decrypt);
        bruteForce.addActionListener(bFListener);

        OkayListener okayListener = new OkayListener(doc, normal, frame, jFrameText);
        okayListener.setReset(reset);
        okayListener.setTextField(textField);
        okayListener.setEncryptButton(encrypt);
        okayListener.setDecryptButton(decrypt);
        ok.addActionListener(okayListener);


        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, southPanel);

        jFrameText.getContentPane().add(BorderLayout.NORTH, headPanel);

        frame.setVisible(true);
    }
}