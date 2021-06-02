package tools;

import javax.swing.*;
import java.awt.*;

public class FormConfigurator {
    public static void config (JFrame form, JPanel panel, String  title, int w, int h, int closeOperation){
        form.setContentPane(panel);
        form.setIconImage(new ImageIcon(FormConfigurator.class.getResource("/logo.png")).getImage());
        form.setMinimumSize(new Dimension(w, h));
        form.setDefaultCloseOperation(closeOperation);
        form.setTitle(title);
        form.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-w)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-h)/2);
    }
}
