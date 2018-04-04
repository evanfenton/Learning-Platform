package frontend.components;

import frontend.pages.Page;

import javax.swing.*;
import java.awt.*;

public class PageNavigator {
    private Page pageHolder;

    public void showPage(String page){
        pageHolder.setVisible(true);
    }

    public void addPage(Page page){
        pageHolder = page;
        page.pack();
        page.setLocationRelativeTo(null);
    }

}
