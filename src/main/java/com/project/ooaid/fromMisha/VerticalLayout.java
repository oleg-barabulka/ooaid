package com.project.ooaid.fromMisha;

import java.awt.*;

public class VerticalLayout implements LayoutManager
{
    /*
    вспомогательный класс для вертикального вывода
     */
    private Dimension size = new Dimension();

    public void addLayoutComponent   (String name, Component comp) {}
    public void removeLayoutComponent(Component comp) {}

    public Dimension minimumLayoutSize(Container c) {
        return calculateBestSize(c);
    }

    public Dimension preferredLayoutSize(Container c) {
        return calculateBestSize(c);
    }

    public void layoutContainer(Container container)
    {

        Component list[] = container.getComponents();
        int currentY = 5;
        for (int i = 0; i < list.length; i++) {

            Dimension pref = list[i].getPreferredSize();

            list[i].setBounds(0, currentY, pref.width, pref.height);

            currentY += 5;

            currentY += pref.height;
        }
    }

    private Dimension calculateBestSize(Container c)
    {

        Component[] list = c.getComponents();
        int maxWidth = 0;
        for (int i = 0; i < list.length; i++) {
            int width = list[i].getWidth();

            if ( width > maxWidth )
                maxWidth = width;
        }

        size.width = maxWidth;

        int height = 0;
        for (int i = 0; i < list.length; i++) {
            height += 5;
            height += list[i].getHeight();
        }
        size.height = height;
        return size;
    }
}