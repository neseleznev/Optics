import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

/**
 * Graphical user interface singleton class
 */
public class Gui {

    private Engine engine = null;
    private final Params params = new Params();

    private JFrame guiFrame;

    // Fields
    private final JTextField gField = new JTextField();
    private final JTextField dField = new JTextField();

    // Results
    private final JLabel results1Step = new JLabel();
    private final JLabel results2Step = new JLabel();

    Gui(final Engine engine)
    {
        // Engine & Params
        this.engine = engine;

        setLayout();
        setDefaults();

        // Init fields and listeners
        initgField();
        initdField();

        // TODO move dummy values to setDefaults after Labels creation
        params.settOut(0.025);
        params.settIn(0);
        params.setTwoW(7);
        params.setN(1.5163);
        // TODO remove this section ^^^

        Results results = engine.compute(params);
        displayResults(results);
    }

    void setLayout() {
        guiFrame = new JFrame();

        // Make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Optics");
        guiFrame.setSize(400, 300);

        // This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);

        Container contentPane = guiFrame.getContentPane();
        contentPane.setLayout(null);

        // Input section
        JLabel gLabel = new JLabel("Г");
        guiFrame.add(gLabel);
        guiFrame.add(gField);

        JLabel dLabel = new JLabel("D`");
        guiFrame.add(dLabel);
        guiFrame.add(dField);

        // etc...

        final int labelsX = 10, labelsWidth = 20, height = 20;
        final int inputX = labelsX + labelsWidth, inputWidth = 60;
        gLabel.setBounds(labelsX, height, labelsWidth, height);
        gField.setBounds(inputX, height, inputWidth, height);

        dLabel.setBounds(labelsX, height*2, labelsWidth, height);
        dField.setBounds(inputX, height*2, inputWidth, height);

        // etc...


        JTabbedPane tabbedPane = new JTabbedPane();

        JComponent panel1 = new JPanel(); //makeTextPanel("Panel #1");
        panel1.add(results1Step);
        tabbedPane.addTab("Шаг 1", panel1);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent panel2 = new JPanel(); //makeTextPanel("Panel #2");
        panel2.add(new JLabel("Panel #2"));
        tabbedPane.addTab("Шаг 2", panel2);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

//        JComponent panel3 = makeTextPanel("Panel #3");
//        tabbedPane.addTab("Tab 3", icon, panel3,
//                "Still does nothing");
//        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
//
//        JComponent panel4 = makeTextPanel(
//                "Panel #4 (has a preferred size of 410 x 50).");
//        panel4.setPreferredSize(new Dimension(410, 50));
//        tabbedPane.addTab("Tab 4", icon, panel4,
//                "Does nothing at all");
//        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
        guiFrame.add(tabbedPane);
        tabbedPane.setBounds(inputX + inputWidth + 10, 10, 250, 200);

        // Make sure the JFrame is visible
        // guiFrame.setBounds(0, 0, 300, 400);
        guiFrame.setVisible(true);
    }

    void setDefaults() {
        gField.setText((new Double(-10)).toString());
        dField.setText((new Double(0.005)).toString());
//        params.settOut(0.025);
//        params.settIn(0);
//        params.setTwoW(7);
//        params.setN(1.5163);
    }
    /**
     * Listen for changes in the text, update params, launch computation
     */
    void initgField() {
        try {
            params.setG(Double.parseDouble(gField.getText()));
        }
        catch (NumberFormatException ignored) {
        }

        gField.getDocument().addDocumentListener(new DocumentListener() {
            void anyChange(DocumentEvent e) {
                Double g = null;
                try {
                    g = Double.parseDouble(gField.getText());
                }
                catch (NumberFormatException ignored) {
                }

                if (g == null) {
                    return;
                }
                params.setG(g);
                Results results = engine.compute(params);
                displayResults(results);
            }
            public void changedUpdate(DocumentEvent e) {
                anyChange(e);
            }
            public void removeUpdate(DocumentEvent e) {
                anyChange(e);
            }
            public void insertUpdate(DocumentEvent e) {
                anyChange(e);
            }
        });
    }

    void initdField() {
        try {
            params.setD(Double.parseDouble(dField.getText()));
        }
        catch (NumberFormatException ignored) {
        }

        dField.getDocument().addDocumentListener(new DocumentListener() {
            void anyChange(DocumentEvent e) {
                Double d = null;
                try {
                    d = Double.parseDouble(gField.getText());
                }
                catch (NumberFormatException ignored) {
                }

                if (d == null) {
                    return;
                }
                params.setD(d);
                Results results = engine.compute(params);
                displayResults(results);
            }
            public void changedUpdate(DocumentEvent e) {
                anyChange(e);
            }
            public void removeUpdate(DocumentEvent e) {
                anyChange(e);
            }
            public void insertUpdate(DocumentEvent e) {
                anyChange(e);
            }
        });
    }

    public static String fmt(double d)
    {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s", new DecimalFormat("#.#####").format(d));
    }

    private void displayResults(Results results) {
        if (results == null) {
            results1Step.setText("Ошибка (results == null)");
            return;
        }
        JLabel step1 = new JLabel("<html>First line<br>Second line</html>");

        String res = String.format("<html><pre>" +
                "y0 = %s\t y1 = %s<br>" +
                "V0 = %s\t V1 = %s<br>" +
                "A1 = %s<br>" +
                "B1 = %s<br>" +
                "C1 = %s<br>" +
                "D1 = %s<br>" +
                "</pre></html>",
                fmt(results.y_0), fmt(results.y_1), fmt(results.V_0), fmt(results.V_1),
                fmt(results.A_1), fmt(results.B_1), fmt(results.C_1), fmt(results.D_1));
        results1Step.setText(res);
    }
}
