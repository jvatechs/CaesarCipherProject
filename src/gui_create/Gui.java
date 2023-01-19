package gui_create;

import encrypt_decrypt_from_file.Main;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.Path;

import static brute_force.BruteForcedText.getBruteForcedText;
import static encrypt_decrypt_basic.Common.getAlphabet;

class Gui {

    private static Path path;
    public static void main(String[] args) {
        JTextField filename = new JTextField(), dir = new JTextField();

        //создание фрейма
        JFrame frame = new JFrame("Decryption Frame");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(400, 500);

        //создание дополнительного фрейма
        JFrame jFrameText = new JFrame("Get result");
        Dimension size = Toolkit. getDefaultToolkit(). getScreenSize();
        jFrameText.setSize(size.width / 2, size.height / 2);
        jFrameText.setLayout(new BorderLayout());
        jFrameText.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JPanel headPanel = new JPanel();
        JLabel textLabel = new JLabel("Your decrypted/encrypted text here");

//        JPanel textPanel = new JPanel();
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
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // игнор, если не номер
                }
            }
        });


        encrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                int result = fileChooser.showOpenDialog(encrypt);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    path = selectedFile.toPath();
                }
                southPanel.setVisible(true);
                encrypt.setSelected(true);
                decrypt.setSelected(false);
            }
        });

        decrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                int result = fileChooser.showOpenDialog(decrypt);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    path = selectedFile.toPath();
                }

                southPanel.setVisible(true);
                encrypt.setSelected(false);
                decrypt.setSelected(true);

            }
        });

        bruteForce.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    doc.remove(0, doc.getLength());
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }

                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                int result = fileChooser.showOpenDialog(bruteForce);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    path = selectedFile.toPath();
                }

                southPanel.setVisible(false);

                textField.setText("");


                String text = getBruteForcedText(path);

                jFrameText.setVisible(true);
                try {
                    doc.insertString(doc.getLength(), text, normal);
                } catch (BadLocationException evt) {
                    throw new RuntimeException(evt);
                }



                encrypt.setSelected(false);
                decrypt.setSelected(false);
            }
        });


        ok.addActionListener(new ActionListener() {
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
                    JOptionPane.showMessageDialog(frame.getComponent(0), "Please enter key!");
                }
                else {
                    key = Integer.parseInt(textField.getText());
//                    try {
//
//                    } catch (StringIndexOutOfBoundsException e) {
//                        JOptionPane.showMessageDialog(frame.getComponent(0), "Введите действительный ключ от 0 до 41 (НЕВКЛЮЧИТЕЛЬНО) !");
//                        textField.setText("");
//                    }
                }


                String text = null;
                if (encrypt.isSelected()) {

                    try {
                        text = Main.readFileAndEncrypt(path, key);
                    } catch (StringIndexOutOfBoundsException e) {
                        JOptionPane.showMessageDialog(frame.getComponent(0), "Введите действительный ключ от 0 до 41 (НЕВКЛЮЧИТЕЛЬНО) !");
                        textField.setText("");
                    }
                }
                if (decrypt.isSelected()) {
                    text = Main.readFileAndDecrypt(path, key);
                }


                if (text != null) {
                    jFrameText.setVisible(true);
                }
                try {
                    doc.insertString(doc.getLength(), text, normal);
                } catch (BadLocationException e) {
                    throw new RuntimeException(e);
                }

                reset.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        ((AbstractButton) event.getSource()).setEnabled(true); // feature for unblocking OKAY button

                        textField.setText("");
                    }
                });
            }
        });


        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, southPanel);

        jFrameText.getContentPane().add(BorderLayout.NORTH, headPanel);

        frame.setVisible(true);
    }

}