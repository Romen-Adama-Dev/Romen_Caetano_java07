import java.beans.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Employee<bean> {
    public Employee() {
        JFrame frame = new JFrame("First Frame");
        final JLabel label = new JLabel("Observing...");
        label.setFont(new Font("Dialog", Font.PLAIN, 18));
        frame.add(label);
        frame.getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 150);
        frame.setLocation(200, 200);
        frame.setVisible(true);

        MessageBean bean = new MessageBean();
        bean.addPropertyChangeListener(e ->     // lambda expression
                label.setText((String) e.getNewValue())
        );
        new Frame1(bean);
        new Frame2(bean);
    }

    private class Frame1 {
        private int clicks;

        Frame1(MessageBean bean) {
            JFrame frame = new JFrame("Second Frame");
            JLabel label = new JLabel("Click anywhere to test the interface");
            label.setFont(new Font("Dialog", Font.PLAIN, 18));
            frame.add(label);
            frame.getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(450, 150);
            frame.setLocation(600, 200);
            frame.setVisible(true);
            frame.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String data = "Click-count [" + ++clicks + "]";
                    bean.setValue(data);
                }
            });
        }
    }

    private class Frame2 {
        private String text;

        Frame2(MessageBean bean) {
            JFrame frame = new JFrame("Third Frame");
            JLabel label = new JLabel("This is to test the interface");
            label.setFont(new Font("Dialog", Font.PLAIN, 18));
            frame.add(label);
            frame.getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(450, 150);
            frame.setLocation(600, 200);
            frame.setVisible(true);
            frame.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e){
                    String keyPressed = "Key Pressed [" + text + "]";
                    bean.setValue(keyPressed);
                    JOptionPane.showMessageDialog(null, text,"Message", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }

    class MessageBean {
        private final PropertyChangeSupport support = new PropertyChangeSupport(this);
        private String value;

        public void addPropertyChangeListener(PropertyChangeListener listener) {
            support.addPropertyChangeListener(listener);
        }

        public void removePropertyChangeListener(PropertyChangeListener listener) {
            support.removePropertyChangeListener(listener);
        }

        public String getValue() {
            return value;
        }

        public void setValue(String newValue) {
            String oldValue = value;
            value = newValue;
            support.firePropertyChange("value", oldValue, newValue);
        }
    }
}