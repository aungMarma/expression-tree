import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ButtonActionListener implements ActionListener {
    private Gui gui;

    public ButtonActionListener(Gui gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        // not needed yet
    }
}