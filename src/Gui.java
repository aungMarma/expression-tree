import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

class Gui extends JFrame {
    private String valueText = "";
    private ExpressionTree expt = new ExpressionTree(); // to find node with point
    private MyPanel myPanel;
    protected JTextField expressionTextField = new JTextField("expression:");
    private JPanel buttomJPanel = new JPanel(); // to group buttons and valueTextField
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JRadioButton infixButton = new JRadioButton("Infix notation");
    private JRadioButton prefixButton = new JRadioButton("Prefix notation");
    private JRadioButton postfixButton = new JRadioButton("Postfix notation");
    private JTextField valueTextField = new JTextField("Value:");

    public Gui() {
        super();
    }

    public void setGUI() {
        // Configure the JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500); // width, height
        this.setLocation(200, 200); // x, y of the window

        this.expressionTextField.addActionListener(new ExpressionActionListener(this));
        this.add(expressionTextField, BorderLayout.NORTH);

        // infixButton is selected by default
        infixButton.setSelected(true);
        buttonGroup.add(infixButton);
        buttonGroup.add(prefixButton);
        buttonGroup.add(postfixButton);

        // listen to buttons
        infixButton.addActionListener(new ButtonActionListener(this));
        prefixButton.addActionListener(new ButtonActionListener(this));
        postfixButton.addActionListener(new ButtonActionListener(this));

        this.buttomJPanel.add(infixButton);
        this.buttomJPanel.add(prefixButton);
        this.buttomJPanel.add(postfixButton);
        this.buttomJPanel.add(valueTextField);
        this.add(this.buttomJPanel, BorderLayout.SOUTH);

        // add panel to contentPane
        myPanel = new MyPanel();
        // adding mouse event listener
        myPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valueText = expt.findValueOfNodeWithPoint(evt.getX(), evt.getY());
                setValueText(valueText); // method of Gui
            }
        });
        this.getContentPane().add(myPanel);
    }

    public void setValueText(String str) {
        this.valueTextField.setText(str);
    }

    public void display() {
        this.setGUI();
        this.setVisible(true);
    }

    public void takeExpressionString(String expressionString) {
        ExpConversion expConversion = new ExpConversion();
        String treeExp = expressionString; // in postfix format
        if (this.infixButton.isSelected()) {
            treeExp = expConversion.infixToPostfix(expressionString);
        } else if (this.prefixButton.isSelected()) {
            treeExp = expConversion.prefixToPostfix(expressionString);
        }

        if (treeExp == "Invalid Expression") {
            myPanel.repaint(); // draw nothing
        } else {
            expt.createTree(treeExp); // tree has been created
            myPanel.expt = expt; // myPanel has access to the expt, which gets the tree string, and draw
            myPanel.repaint(); // call paintComponent again
            // postfix string to test "123*+53*2-*";
            // infix string "(1+2*3)*(5*3-2)";
            // postfix string 23+456-+*71123*-+**
        }
    }

    public void showNothing() {
        myPanel.repaint();
    }
}