package FrontEnd.components;

import FrontEnd.pages.Page;
import FrontEnd.Client;

/**
 * Navigator that contains methods for changing frames
 */
public class PageNavigator {
    /**
     * The Page the user is currently on
     */
    private Page pageHolder;

    /**
     * Client used to communicate with the server
     */
    protected Client client;

    /**
     * Ctor that takes the client used and the starting page after login
     * @param page
     * @param client
     */
    public PageNavigator(Page page, Client client){
        pageHolder= page;
        this.client= client;
    }

    /**
     * Sets the current Page in pageHolder to
     */
    public void showPage(){
        pageHolder.setVisible(true);
    }

    /**
     * Used to change the page for the user
     * @param page
     */
    public void addPage(Page page){
        pageHolder = page;
        page.pack();
        page.setLocationRelativeTo(null);
    }

    /**
     * Gets the current page the user is on
     * @return
     */
    public Page getPageHolder() {
        return pageHolder;
    }

    /**
     * Gets the client used for communication
     * @return
     */
    public Client getClient(){ return client; }
}
