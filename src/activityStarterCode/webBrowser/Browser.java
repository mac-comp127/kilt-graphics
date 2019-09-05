//package activityStarterCode.webBrowser;
//
//import javafx.application.Platform;
//import javafx.scene.web.WebEngine;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * A browser maintains a set of tabs.
// * @author shilad, Bret
// */
//public class Browser {
//	private List<Tab> tabs = new ArrayList<Tab>();
//	private int displayedIndex = -1;
//
//	private WebEngine webEngine;
//
//	/**
//	 * Default constructor.
//	 */
//	public Browser(WebEngine webEngine) {
//		this.webEngine = webEngine;
//	}
//
//	/**
//	 * Sets the webengine used to load the html webpages
//	 * @param engine
//	 */
//	public void setWebEngine(WebEngine engine){
//		this.webEngine = engine;
//	}
//
//	/**
//	 * Adds a new tab and makes it the current tab.
//	 * @param tab
//	 */
//	public void addTab(Tab tab) {
//		this.tabs.add(tab);
//		displayedIndex = this.tabs.size() - 1;
//        loadURL(tab.getUrl());
//	}
//
//	/**
//	 * Makes the current tab the i'th tab (i is 0-indexed).
//	 * @param i
//	 */
//	public void setDisplayedTab(int i) {
//		displayedIndex = i;
//		Tab t = getDisplayedTab();
//		loadURL(t.getUrl());
//	}
//
//	/**
//	 * Returns the current tab.
//	 * @return
//	 */
//	public Tab getDisplayedTab() {
//		return getTab(displayedIndex);
//	}
//
//	/**
//	 * Returns the index of the current tab.
//	 * @return
//	 */
//	public int getDisplayedTabIndex() {
//		return displayedIndex;
//	}
//
//	/**
//	 * Returns the i'th tab (i is 0-indexed).
//	 * @param i
//	 * @return
//	 */
//	public Tab getTab(int i) {
//		return tabs.get(i);
//	}
//
//	/**
//	 * Returns the number of tabs for the browser.
//	 * @return
//	 */
//	public int getNumTabs() {
//		return tabs.size();
//	}
//
//
//	/**
//	 * Loads a url in the WebView
//	 * @param url
//	 */
//	private void loadURL(final String url){
//        if (webEngine != null) {
//            Platform.runLater(() -> {
//                String tmp = toURL(url);
//                if (tmp == null) {
//                    tmp = toURL("http://" + url);
//                }
//                webEngine.load(tmp);
//            });
//        }
//	}
//
//	/**
//	 * Checks that url is well-formed.
//	 * @param url to check
//	 * @return modified url
//	 */
//	private String toURL(String url){
//		try {
//			return new URL(url).toExternalForm();
//		} catch (MalformedURLException exception){
//			return null;
//		}
//	}
//
//    /**
//     * Creates a new tab
//     * @param url initial page
//     */
//	public void newTab(String url){
//		Tab t = new Tab();
//		t.setUrl(url);
//		addTab(t);
//	}
//
//    /**
//     * Sets the current webpage for the currently displayed tab.
//     * @param url initial page
//     */
//	public void open(String url){
//        if (displayedIndex != -1) {
//            getDisplayedTab().setUrl(url);
//            loadURL(url);
//        }
//	}
//
//    /**
//     * Returns a string containing information about which tabs are open and their current page.
//     * @return string representing current state of all the tabs.
//     */
//	public String list() {
//		StringBuilder list = new StringBuilder();
//		list.append("==================\nAll tabs:\n");
//		for(int i=0; i < getNumTabs(); i++){
//			Tab t = getTab(i);
//			list.append("\tTab " + i + ": " + t.getUrl()+"\n");
//		}
//		list.append("==================");
//		return list.toString();
//	}
//
//    /**
//     * Switches current browser tab to a specific tab
//     * @param i index to switch to
//     */
//	public void switchTab(int i) {
//		if(i >=0 && i < getNumTabs()) {
//			setDisplayedTab(i);
//		}
//	}
//
//    /**
//     * Gets a string holding the current navigation history for the currently displayed tab.
//     * @return string representing the tab's history
//     */
//	public String getHistoryString(){
//        if (displayedIndex != -1) {
//            StringBuilder sb = new StringBuilder();
//            sb.append("------ History for tab ");
//            sb.append(getDisplayedTabIndex());
//            sb.append(" ------\n");
//            sb.append(getDisplayedTab().getHistoryString());
//            return sb.toString();
//        }
//        return "Please navigate to an initial page before viewing the history.";
//    }
//
//    /**
//     * Navigates the browser back to the last url stored in the current tab's history
//     */
//    public void navigateBack(){
//        if (displayedIndex != -1) {
//            String url = getDisplayedTab().navigateBack();
//            if (url != null){
//                loadURL(url);
//            }
//        }
//    }
//
//    /**
//     * Closes the browser by quitting the program.
//     */
//	public void quit() {
//		System.exit(0);
//	}
//
//    /**
//     * Returns a string representation of this browser.
//     * @return description of the tabs contained in the browser
//     */
//	@Override
//	public String toString(){
//		return "A browser object with tabs:\n"+list();
//	}
//}
