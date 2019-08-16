import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ExpressionActionListener implements ActionListener {
    private Gui gui;

    public ExpressionActionListener(Gui gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String text = evt.getActionCommand();
        String placeHolder = "expression:";
        if (text.contains(placeHolder) && text.length() > placeHolder.length()) {
            this.gui.takeExpressionString(text.substring(placeHolder.length()));
        } else {
            // invalid input
            this.gui.showNothing();
        }
    }
}