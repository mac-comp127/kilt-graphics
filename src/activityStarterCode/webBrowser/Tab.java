package activityStarterCode.webBrowser;

import java.util.Deque;

/**
 * Browser Tab that displays a single web page at a time and keeps track of the navigation history.
 * .
 * @author shilad, Bret
 */
public class Tab {
	public static final int MAX_HISTORY_SIZE = 2;
	private String url; // Current url
	private Deque<String> history; // Previous urls in the navigation history.

    /**
     * Constructor to initialize instance variables
     */
	public Tab() {
		url = null;
        //TODO: Initialize history variable
	}

    /**
     * Getter
     * @return the url of the currently displayed page
     */
	public String getUrl() {
		return url;
	}

    /**
     * Sets the currently displayed page and updates the history
     * @param url to display
     */
	public void setUrl(String url) {
        if (this.url != null){
            updateHistory(this.url);
        }
		this.url = url;
	}

    /**
     * Adds the url to the front of the history deque. The history maintains a maximum size, so if the history
     * exceeds this size this method removes the url at the rear of the deque.
     * @param url to add to history
     */
	private void updateHistory(String url){
        //TODO: Add the url to the front of the deque
        // if the history is bigger than the maximum size then remove from the end.
	}

    /**
     * Sets the currently displayed url to be the most recent url in the history deque.
     * @return the new url.
     */
    public String navigateBack(){
        //TODO: Set the tab's current url to be the first url in the history deque
        // return the new current url or null if there is nothing in the history.
        return null;
    }

    /**
     * Returns a string containing the current url history for this tab.
     * @return history
     */
	public String getHistoryString(){
		StringBuilder sb = new StringBuilder();
        //TODO: append each url in the history to the stringbuilder object to return a string containing the entire history.


		return sb.toString();
	}

    /**
     * String representation of the tab.
     * @return
     */
	@Override
	public String toString() {
		return "Tab object with url: "+url;
	}

    /**
     * Tests whether two tab objects are equal. We assume they are the same tab if they are currently displaying the same url
     * and their histories are the same.
     * @param o
     * @return
     */
	@Override
	public boolean equals(Object o){
		if (o == null || !(o instanceof Tab)){
			return false;
		}
		Tab t = (Tab)o;

		return url.equals(t.getUrl()) && history.equals(t.history);
	}
}
