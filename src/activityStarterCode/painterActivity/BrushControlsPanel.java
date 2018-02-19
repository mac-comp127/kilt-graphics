//package activityStarterCode.painterActivity;
//
//import javax.swing.*;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;
//import java.awt.*;
//
///**
// * A panel that lets the user set up the size and color of the paint brush.
// *
// * Created by bjackson on 10/5/2016.
// */
//public class BrushControlsPanel extends JPanel {
//
//    private ColorChooserPanel colorChooserPanel;
//    private PaintBrush paintBrush;
//    private JSpinner radiusSpinner;
//    private JLabel radiusLabel;
//
//
//    public BrushControlsPanel(PaintBrush paintBrush){
//        this.paintBrush = paintBrush;
//        colorChooserPanel = new ColorChooserPanel(paintBrush);
//
//        SpinnerNumberModel model = new SpinnerNumberModel(paintBrush.getBrushRadius(), 1, 500, 1);
//        radiusSpinner = new JSpinner(model);
//        radiusSpinner.addChangeListener(new RadiusChangeListener());
//        radiusLabel = new JLabel("PaintBrush Radius:");
//
//        JPanel controls = new JPanel();
//        controls.setMaximumSize(new Dimension(200, 25));
//        BoxLayout controlsLayout = new BoxLayout(controls, BoxLayout.X_AXIS);
//        controls.setLayout(controlsLayout);
//        controls.add(radiusLabel);
//        controls.add(Box.createRigidArea(new Dimension(10, 0)));
//        controls.add(radiusSpinner);
//
//        BoxLayout panelLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
//        setLayout(panelLayout);
//        add(Box.createVerticalGlue());
//        add(controls);
//        add(Box.createRigidArea(new Dimension(0, 10)));
//        add(colorChooserPanel);
//        //add(Box.createVerticalGlue());
//
//        revalidate();
//    }
//
//    private class RadiusChangeListener implements ChangeListener {
//
//        public void stateChanged(ChangeEvent e){
//            paintBrush.setBrushRadius((Double)radiusSpinner.getValue());
//        }
//    }
//}
