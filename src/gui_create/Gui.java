package gui_create;

import encrypt_decrypt_from_file.Main;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.Path;

import static brute_force.BruteForcedText.getBruteForcedText;

class Gui {

    private static Path path;
    private static int countAction;
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

        //создание меню бара
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("FILE");
        menuBar.add(fileMenu);

        JMenuItem openFile = new JMenuItem("Open");
        fileMenu.add(openFile);





        JButton encrypt = new JButton("Encrypt with key");

        JButton decrypt = new JButton("Decrypt with known key");

        JButton bruteForce = new JButton("BruteForce");

//        JLabel resetAlert = new JLabel("PLEASE RESET BEFORE ADDING NEW FILE");



        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        panel.add(encrypt);
        panel.add(decrypt);
        panel.add(bruteForce);

//        panel.add(resetAlert, SwingConstants.VERTICAL);
//        frame.add(resetAlert, SwingConstants.CENTER);
//        Dimension lableSize = resetAlert.getPreferredSize();


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
        panel.setVisible(false);


        JFileChooser fileChooser = new JFileChooser();
        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                int result = fileChooser.showOpenDialog(openFile);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    path = selectedFile.toPath();
                    panel.setVisible(true);
//                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                }
            }
        });



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
                southPanel.setVisible(true);
                encrypt.setSelected(true);
                decrypt.setSelected(false);

//                centerPanel.setVisible(true);

            }
        });

        decrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                southPanel.setVisible(true);
                encrypt.setSelected(false);
                decrypt.setSelected(true);

//                centerPanel.setVisible(true);

            }
        });

        bruteForce.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                southPanel.setVisible(false);

//                label.setVisible(false);
//                ok.setVisible(false);
//                reset.setVisible(true);
//                textField.setVisible(false);

                textField.setText("");

//                centerPanel.setVisible(true);
                if (countAction > 0) {
                    JOptionPane.showMessageDialog(frame.getComponent(0), "PLEASE OPEN NEW FILE BEFORE BRUTEFORCE!!!");
                }

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
                ((AbstractButton) event.getSource()).setEnabled(false);
                int key = 0;
                if (textField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame.getComponent(0), "Please enter key!");
                } else {
                    key = Integer.parseInt(textField.getText());
                }


                String text = "None";
                if (encrypt.isSelected()) {
                    text = Main.readFileAndEncrypt(path, key);
                }
                if (decrypt.isSelected()) {
                    text = "decrypt";
                }


                jFrameText.setVisible(true);
                try {
                    doc.insertString(doc.getLength(), text, normal);
                } catch (BadLocationException e) {
                    throw new RuntimeException(e);
                }

//                textPane.setText(text);
//                JOptionPane.showMessageDialog(frame.getComponent(0), "Hello World");

                countAction++;


                reset.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        ((AbstractButton) event.getSource()).setEnabled(true);
                        try {
                            doc.remove(0, doc.getLength());
                        } catch (BadLocationException ex) {
                            throw new RuntimeException(ex);
                        }

                        textField.setText("");
                    }
                });
            }
        });

//        encrypt.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                textField.setVisible(true);
//            }
//        });

//        JLabel label = new JLabel("Enter Text");
//        JTextField tf = new JTextField(10); // accepts upto 10 characters
//        JButton send = new JButton("Send");
//        JButton reset = new JButton("Reset");
//        panel.add(label); // Components Added using Flow Layout
//        panel.add(tf);
//        panel.add(send);
//        panel.add(reset);

        // Text Area at the Center
//        JTextArea ta = new JTextArea();

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, southPanel);
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
//        frame.getContentPane().add(BorderLayout.CENTER, centerPanel);

        jFrameText.getContentPane().add(BorderLayout.NORTH, headPanel);
//        jFrameText.getContentPane().add(BorderLayout.CENTER, textPanel);


        frame.setVisible(true);
//        textField.setVisible(true);
    }

}