package listeners;

import javax.swing.*;

class ButtonSwitcher {
     static void disableAllButtons(JButton button1, JButton button2) {
        button1.setSelected(false);
        button2.setSelected(false);
    }
}
