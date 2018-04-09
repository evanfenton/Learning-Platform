package FrontEnd.components;

import FrontEnd.pages.Page;
import FrontEnd.Client;

/**
 * Navigator that contains methods for professorGUI
 */
public class PageNavigator {
    private Page pageHolder;
    protected Client client;

    public PageNavigator(Page page, Client client){
        pageHolder= page;
        this.client= client;
    }

    public void showPage(){
        pageHolder.setVisible(true);
    }

    public void addPage(Page page){
        pageHolder = page;
        page.pack();
        page.setLocationRelativeTo(null);
    }

    public Page getPageHolder() {
        return pageHolder;
    }

    public Client getClient(){ return client; }
}
