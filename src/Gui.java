import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * Graphical user interface singleton class
 */
public class Gui {

    private Engine engine = null;
    private final Params params = new Params();

    // Fields
    private final JTextField xField = new JTextField();

    // Results
    private final JLabel resultLabel = new JLabel("Result:");

    Gui(final Engine engine)
    {
        // Engine & Params
        this.engine = engine;

        JFrame guiFrame = new JFrame();

        // Make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Optics");
        guiFrame.setSize(300, 250);

        // This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);

        // Init fields and listeners
        initXField();

        // TODO remove dummy values
        params.setG(-10);
        params.setD(0.005);
        params.settOut(0.025);
        params.settIn(0);
        params.setTwoW(7);
        params.setN(1.5163);
        engine.compute(params);
        // TODO remove this section ^^^


//        // Using Group Layout
//        GroupLayout layout = new GroupLayout(guiFrame);
//        guiFrame.setLayout(layout);


        // The JFrame uses the BorderLayout layout manager.
        guiFrame.add(xField, BorderLayout.NORTH);
        guiFrame.add(resultLabel, BorderLayout.AFTER_LAST_LINE);

        // Make sure the JFrame is visible
        guiFrame.setVisible(true);
    }

    /**
     * Listen for changes in the text, update params, launch compute
     */
    void initXField() {
        xField.getDocument().addDocumentListener(new DocumentListener() {
            void anyChange(DocumentEvent e) {
                Integer x = null;
                try {
                    x = Integer.parseInt(xField.getText());
                }
                catch (NumberFormatException ignored) {
                }

                if (x == null) {
                    return;
                }
                params.setX(x);
                Results results = engine.compute(params);
                setResults(results);
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

    private void setResults(Results results) {
        if (results.x0 == null) {
            resultLabel.setText("Ошибка (x0)");
        }

        String res = "y0 = " + results.y_0 + "\n" +
                "V_0 = " + results.V_0 + "\n" +
                "y1 = " + results.y_1 + "\n" +
                "V1 = " + results.V_1 + "\n" +
                "A1 = " + results.A_1 + "\n" +
                "B1 = " + results.B_1 + "\n" +
                "C1 = " + results.C_1 + "\n" +
                "D1 = " + results.D_1 + "\n";
        resultLabel.setText(res);
    }
}
