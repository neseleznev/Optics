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

    // Fields
    private final JTextField gField = new JTextField();
    private final JTextField dField = new JTextField();
    private final JTextField tOutField = new JTextField();
    private final JTextField tInField = new JTextField();
    private final JTextField twoWField = new JTextField();
    private final JTextField nField = new JTextField();

    // Results
    private final JLabel results1Step = new JLabel();
    private final JLabel results2Step = new JLabel();

    Gui(final Engine engine)
    {
        this.engine = engine;

        setLayout();
        setDefaultValues();

        // Init fields and listeners, very bad, I know
        initgField();
        initdField();
        inittOutField();
        inittInField();
        initTwoWField();
        initnField();

        Results results = engine.compute(params);
        displayResults(results);
    }

    void setLayout() {
        JFrame guiFrame = new JFrame();

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

        JLabel tOutLabel = new JLabel("t`");
        guiFrame.add(tOutLabel);
        guiFrame.add(tOutField);

        JLabel tInLabel = new JLabel("t");
        guiFrame.add(tInLabel);
        guiFrame.add(tInField);

        JLabel twoWLabel = new JLabel("2w");
        guiFrame.add(twoWLabel);
        guiFrame.add(twoWField);

        JLabel nLabel = new JLabel("n");
        guiFrame.add(nLabel);
        guiFrame.add(nField);

        final int labelsX = 10, labelsWidth = 20, height = 20;
        final int inputX = labelsX + labelsWidth, inputWidth = 60;
        gLabel.setBounds(labelsX, height, labelsWidth, height);
        gField.setBounds(inputX, height, inputWidth, height);

        dLabel.setBounds(labelsX, height*2, labelsWidth, height);
        dField.setBounds(inputX, height*2, inputWidth, height);

        tOutLabel.setBounds(labelsX, height*3, labelsWidth, height);
        tOutField.setBounds(inputX, height*3, inputWidth, height);

        tInLabel.setBounds(labelsX, height*4, labelsWidth, height);
        tInField.setBounds(inputX, height*4, inputWidth, height);

        twoWLabel.setBounds(labelsX, height*5, labelsWidth, height);
        twoWField.setBounds(inputX, height*5, inputWidth, height);

        nLabel.setBounds(labelsX, height*6, labelsWidth, height);
        nField.setBounds(inputX, height*6, inputWidth, height);


        // Tabs
        JTabbedPane tabbedPane = new JTabbedPane();

        JComponent panel1 = new JPanel();
        panel1.add(results1Step);
        tabbedPane.addTab("Шаг 1", panel1);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent panel2 = new JPanel();
        panel2.add(results2Step);
        tabbedPane.addTab("Шаг 2", panel2);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        guiFrame.add(tabbedPane);
        tabbedPane.setBounds(inputX + inputWidth + 10, 10, 250, 200);

        // Make sure the JFrame is visible
        // guiFrame.setBounds(0, 0, 300, 400);
        guiFrame.setVisible(true);
    }

    void setDefaultValues() {
        gField.setText("-10");
        dField.setText("0.005");
        tOutField.setText("0.025");
        tInField.setText("0");
        twoWField.setText("7");
        nField.setText("1.5163");
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
                    d = Double.parseDouble(dField.getText());
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

    void inittOutField() {
        try {
            params.settOut(Double.parseDouble(tOutField.getText()));
        }
        catch (NumberFormatException ignored) {
        }

        tOutField.getDocument().addDocumentListener(new DocumentListener() {
            void anyChange(DocumentEvent e) {
                Double tOut = null;
                try {
                    tOut = Double.parseDouble(tOutField.getText());
                }
                catch (NumberFormatException ignored) {
                }

                if (tOut == null) {
                    return;
                }
                params.settOut(tOut);
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

    void inittInField() {
        try {
            params.settIn(Double.parseDouble(tInField.getText()));
        }
        catch (NumberFormatException ignored) {
        }

        tInField.getDocument().addDocumentListener(new DocumentListener() {
            void anyChange(DocumentEvent e) {
                Double tIn = null;
                try {
                    tIn = Double.parseDouble(tInField.getText());
                }
                catch (NumberFormatException ignored) {
                }

                if (tIn == null) {
                    return;
                }
                params.settIn(tIn);
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

    void initTwoWField() {
        try {
            params.setTwoW(Double.parseDouble(twoWField.getText()));
        }
        catch (NumberFormatException ignored) {
        }

        twoWField.getDocument().addDocumentListener(new DocumentListener() {
            void anyChange(DocumentEvent e) {
                Double twoW = null;
                try {
                    twoW = Double.parseDouble(twoWField.getText());
                }
                catch (NumberFormatException ignored) {
                }

                if (twoW == null) {
                    return;
                }
                params.setTwoW(twoW);
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

    void initnField() {
        try {
            params.setN(Double.parseDouble(nField.getText()));
        }
        catch (NumberFormatException ignored) {
        }

        nField.getDocument().addDocumentListener(new DocumentListener() {
            void anyChange(DocumentEvent e) {
                Double n = null;
                try {
                    n = Double.parseDouble(nField.getText());
                }
                catch (NumberFormatException ignored) {
                }

                if (n == null) {
                    return;
                }
                params.setN(n);
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

        String res1 = String.format("<html><pre>" +
                "y0 = %s\t y1 = %s<br>" +
                "V0 = %s\t V1 = %s<br>" +
                "<br>" +
                "A1 = %s<br>" +
                "B1 = %s<br>" +
                "C1 = %s<br>" +
                "D1 = %s<br>" +
                "</pre></html>",
                fmt(results.y_0), fmt(results.y_1), fmt(results.V_0), fmt(results.V_1),
                fmt(results.A_1), fmt(results.B_1), fmt(results.C_1), fmt(results.D_1));
        results1Step.setText(res1);

        String res2 = String.format("<html><pre>" +
                        "Ф2 = %s\t y2 = %s<br>" +
                        "Ф2` = %s\t V2 = %s<br>" +
                        "<br>" +
                        "A2 = %s<br>" +
                        "B2 = %s<br>" +
                        "C2 = %s<br>" +
                        "D2 = %s<br>" +
                        "</pre></html>",
                fmt(results.F_2), fmt(results.y_2), fmt(results.F_2_ocul), fmt(results.V_2),
                fmt(results.A_2), fmt(results.B_2), fmt(results.C_2), fmt(results.D_2));
        results2Step.setText(res2);
    }
}
