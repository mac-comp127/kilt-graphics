//package activityStarterCode.webBrowser;
//
//import javafx.application.Platform;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.concurrent.Worker;
//import javafx.embed.swing.JFXPanel;
//import javafx.scene.Scene;
//import javafx.scene.web.WebEngine;
//import javafx.scene.web.WebView;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.Scanner;
//
//
///**
// * Runs the a text-based user interface for a browser.
// * Created by bjackson
// */
//public class RunBrowser{
//
//    private JFrame window;
//    private JPanel contentPanel;
//    private Browser browser;
//    private WebEngine engine;
//    private Scanner scanner;
//
//    // Possible interaction options, could also use an enum
//    public static final String OPEN = "open";
//    public static final String LIST = "list";
//    public static final String NEW = "new";
//    public static final String SWITCH = "switch";
//    public static final String QUIT = "quit";
//    public static final String BACK = "back";
//    public static final String HISTORY = "history";
//
//    public RunBrowser(){
//        init();
//        run();
//    }
//
//    /**
//     * Initializes the instance variables.
//     */
//    public void init() {
//        browser = new Browser(engine);
//        scanner = new Scanner(System.in);
//        window = new JFrame("Web Browser");
//        contentPanel = new JPanel();
//        contentPanel.setPreferredSize(new Dimension(1200, 600));
//
//        JFXPanel jfxPanel = new JFXPanel();
//        Platform.runLater(() -> { // WebView javaFX components need to be managed by JavaFX
//            WebView webView = new WebView();
//            webView.setPrefWidth(1200);
//            webView.setPrefHeight(600);
//            engine = webView.getEngine();
//            //webView.getEngine().loadContent("<html> Hello World!</html>");
//            jfxPanel.setScene(new Scene(webView));
//            browser.setWebEngine(engine);
//
//            engine.getLoadWorker().workDoneProperty().addListener(new ChangeListener<Number>() {
//                @Override
//                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                    SwingUtilities.invokeLater( () -> {
//                        System.out.println("Loading (%): "+newValue.toString());
//                    });
//                }
//            });
//
//            engine.getLoadWorker().exceptionProperty().addListener(new ChangeListener<Throwable>() {
//                @Override
//                public void changed(ObservableValue<? extends Throwable> observable, Throwable oldValue, Throwable newValue) {
//                    if(engine.getLoadWorker().getState() == Worker.State.FAILED){
//                        SwingUtilities.invokeLater( () -> {
//                            System.out.println("Error: \n"+newValue.getMessage());
//                        });
//                    }
//                }
//            });
//
//        });
//        contentPanel.add(jfxPanel);
//        window.setContentPane(contentPanel);
//        window.pack();
//        window.setVisible(true);
//    }
//
//    /**
//     * Normal run method
//     */
//    public void run() {
//
//        while(true){
//            System.out.println("Enter command ("+NEW+", "+LIST+", "+SWITCH+", "+OPEN+", "+BACK+", "+HISTORY+", "+QUIT+"): ");
//            String command = scanner.nextLine();
//            if (command.equals(NEW)) {
//                browser.newTab(promptForURL());
//            } else if (command.equals(LIST)){
//                System.out.println(browser.list());
//            } else if (command.equals(SWITCH)){
//                System.out.println("Enter tab to display (from 0 to " + (browser.getNumTabs() - 1) + "): ");
//                int i = scanner.nextInt();
//                browser.switchTab(i);
//            } else if (command.equals(OPEN)){
//                browser.open(promptForURL());
//            } else if (command.equals(BACK)){
//                browser.navigateBack();
//            } else if (command.equals(HISTORY)){
//                System.out.println(browser.getHistoryString());
//            } else if (command.equals(QUIT)){
//                browser.quit();
//            }
//            System.out.println();
//        }
//    }
//
//    /**
//     * Askes the user to enter a url and returns it
//     * @return
//     */
//    private String promptForURL(){
//        System.out.println("Enter url: ");
//        return scanner.nextLine();
//    }
//
//    public static void main(String[] args){
//        RunBrowser prog = new RunBrowser();
//    }
//}
