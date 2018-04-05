package FrontEnd.components;

import FrontEnd.pages.Page;

public class PageNavigator {
    private Page pageHolder;

    public void showPage(){
        pageHolder.setVisible(true);
    }

    public void addPage(Page page){
        pageHolder = page;
        page.pack();
        page.setLocationRelativeTo(null);
    }

}
