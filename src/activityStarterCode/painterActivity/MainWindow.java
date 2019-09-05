//package activityStarterCode.painterActivity;
//
//import comp127graphics.CanvasWindow;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
///**
// * This is the main GUI and application for a rudimentary paint application.
// *
// * This code illustrates the use of event listeners in Java swing and awt GUI components.
// *
// * There are two different Brushes, one that draws as the mouse is dragged and one that
// * erases the graphical objects underneath it.
// *
// * Created by bjackson on 10/5/2016.
// */
//public class MainWindow extends CanvasWindow implements MouseListener, MouseMotionListener, ActionListener {
//
//    private PaintBrush paintBrush;     // handles adding of elements under mouse pressed and dragged
//    private EraserBrush eraserBrush;   // handles erasing of elements under mouse pressed and dragged
//    private Brush currentBrush;        // holds which of the 2 above brushes is currently chosen by the user
//
//    private JRadioButton brushButton;   // toggles with eraserButton
//    private JRadioButton eraserButton;  // toggles with brushButton
//    private JButton clearButton;        // responsible for removing all elements from canvas
//
//    public MainWindow(){
//        super("Painter", 1000, 800);
//
//        // whole application CanvasWindow, which is a JPanel, will use BorderLayout
//        BorderLayout mainLayout = new BorderLayout();
//        setLayout(mainLayout);
//
//        // create the two Brush objects
//        paintBrush = new PaintBrush(PaintBrush.DEFAULT_BRUSH_RADIUS, PaintBrush.DEFAULT_BRUSH_COLOR, this);
//        eraserBrush = new EraserBrush(this);
//        currentBrush = paintBrush;    // start in paint mode
//
//        // The left side menu panel
//        JPanel menu = new JPanel();
//        menu.setPreferredSize(new Dimension(260, 0));
//        BoxLayout boxLayout = new BoxLayout(menu, BoxLayout.Y_AXIS);
//        menu.setLayout(boxLayout);
//
//        // The panel that lets the user set brush width and color (brush control)
//        BrushControlsPanel brushControlsPanel = new BrushControlsPanel(paintBrush);
//
//        // Add brush control panel to the left-hand menu panel
//        menu.add(brushControlsPanel);
//        // Add a blank bit of separator space to the left-hand menu panel
//        menu.add(Box.createRigidArea(new Dimension(0, 10)));
//
//        // Radio buttons to toggle which type of brush: paint or erase
//        brushButton = new JRadioButton("Paint Brush", true);
//        brushButton.addActionListener(this);  // see actionPerformed below
//        eraserButton = new JRadioButton("Eraser Brush");
//        eraserButton.addActionListener(this); // see actionPerformed below
//        // button group has the two radio buttons toggle together
//        ButtonGroup buttonGroup = new ButtonGroup();
//        buttonGroup.add(brushButton);
//        buttonGroup.add(eraserButton);
//
//        // small panel for the radio buttons
//        JPanel tools = new JPanel();
//        tools.setPreferredSize(new Dimension(0, 10));
//        tools.add(brushButton);
//        tools.add(eraserButton);
//        menu.add(tools);  // add radio buttons to the left menu
//
//        // button for clearing everything on the drawing canvas
//        clearButton = new JButton("Clear Artwork");
//        clearButton.addActionListener(this);  // see actionPerformed below
//        menu.add(clearButton);
//
//        // align elements in the menu from top to bottom
//        menu.add(Box.createVerticalGlue());
//
//        // place the menu JPanel on the left-hand side og the CanvasWindow,
//        // which is also a JPanel
//        add(menu, BorderLayout.WEST);
//
//        addMouseListener(this);  // see mousePressed below (others do nothing)
//        addMouseMotionListener(this);  // see mouseDragged below (mouseMoved does nothing)
//
//        revalidate();
//    }
//
//    ///////////////////////  mouse event listeners ///////////////////////////
//
//    /**
//     * depending on the current brush chosen, apply either the drawing or erasing technique
//     *
//     * @param e
//     */
//    public void mousePressed(MouseEvent e) {
//        // TODO: get the x and y location of this mouse event
//        //       and send them to the apply() method of the current brush
//
//    }
//
//    public void mouseReleased(MouseEvent e) {
//    }
//
//    public void mouseEntered(MouseEvent e) {
//    }
//
//    public void mouseExited(MouseEvent e) {
//    }
//
//    public void mouseClicked(MouseEvent e) {
//    }
//
//    /////////////////// mouse motion listeners   //////////////////////////////////
//    public void mouseMoved(MouseEvent e){
//    }
//
//    public void mouseDragged(MouseEvent e){
//        // TODO: get the x and y of the mouse event
//        //       and send them to the apply() method of the current brush
//
//    }
//
//    public static void main(String[] args){
//        MainWindow window = new MainWindow();
//    }
//
//    public void actionPerformed(ActionEvent e){
//        if (e.getSource() == brushButton){
//            currentBrush = paintBrush;
//        }
//        else if (e.getSource() == eraserButton){
//            currentBrush = eraserBrush;
//        }
//        else if (e.getSource() == clearButton){
//            removeAll();
//        }
//    }
//}
