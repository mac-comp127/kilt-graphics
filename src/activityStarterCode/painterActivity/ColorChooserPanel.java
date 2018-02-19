//package activityStarterCode.painterActivity;
//
//import javax.swing.*;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;
//import java.awt.*;
//
///**
// * A panel that contains the color choosing GUI elements.
// *
// * Created by bjackson on 10/5/2016.
// */
//public class ColorChooserPanel extends JPanel {
//
//    private JPanel controls;
//    private JPanel colorPanel;
//    private JLabel rLabel, gLabel, bLabel;
//    private JSpinner rSpinner, gSpinner, bSpinner;
//    private PaintBrush paintBrush;
//
//
//    public ColorChooserPanel(PaintBrush paintBrush){
//
//        //setPreferredSize(new Dimension(260, 0));
//        this.paintBrush = paintBrush;
//        controls = new JPanel();
//        BoxLayout layout = new BoxLayout(controls, BoxLayout.Y_AXIS);
//        controls.setLayout(layout);
//
//        SpinnerNumberModel rModel = new SpinnerNumberModel(0, 0, 255, 1);
//        rSpinner = new JSpinner(rModel);
//        SpinnerNumberModel gModel = new SpinnerNumberModel(0, 0, 255, 1);
//        gSpinner = new JSpinner(gModel);
//        SpinnerNumberModel bModel = new SpinnerNumberModel(0, 0, 255, 1);
//        bSpinner = new JSpinner(bModel);
//
//        SpinnerListener spinnerListener = new SpinnerListener();
//        rSpinner.addChangeListener(spinnerListener);
//        gSpinner.addChangeListener(spinnerListener);
//        bSpinner.addChangeListener(spinnerListener);
//
//
//        rLabel = new JLabel("Red:");
//        rLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
//        gLabel = new JLabel("Green:");
//        gLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
//        bLabel = new JLabel("Blue:");
//        bLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
//
//        controls.add(rLabel);
//        controls.add(rSpinner);
//        controls.add(Box.createRigidArea (new Dimension (0, 20)));
//        controls.add(gLabel);
//        controls.add(gSpinner);
//        controls.add(Box.createRigidArea (new Dimension (0, 20)));
//        controls.add(bLabel);
//        controls.add(bSpinner);
//
//        colorPanel = new JPanel();
//        colorPanel.setPreferredSize(new Dimension (100, 100));
//        colorPanel.setBackground(new Color(0, 0, 0));
//
//        add(controls);
//        add(colorPanel);
//    }
//
//    private class SpinnerListener implements ChangeListener{
//
//        public void stateChanged(ChangeEvent e){
//            int red = (Integer)rSpinner.getValue();
//            int green = (Integer)gSpinner.getValue();
//            int blue = (Integer)bSpinner.getValue();
//            Color color = new Color(red, green, blue);
//            paintBrush.setBrushColor(color);
//            colorPanel.setBackground(color);
//        }
//    }
//}
