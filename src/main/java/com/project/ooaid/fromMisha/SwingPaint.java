package com.project.ooaid.fromMisha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingPaint {
/*
тут стартует отрисовка начального поля, да-да, я использовал свинг
 */
    private JButton clearBtn, backBtn;
    private DrawArea drawArea;
    private JFrame frame;
    private ActionListener actionListener = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearBtn) {
                drawArea.clear(2);
            } else if (e.getSource() == backBtn) {
                drawArea.back();
            }
        }
    };

    public void show() {
        frame = new JFrame("Swing Paint");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        drawArea = new DrawArea(frame);

        content.add(drawArea, BorderLayout.CENTER);

        JPanel controls = new JPanel();

        clearBtn = new JButton("Clear");
        clearBtn.addActionListener(actionListener);
        backBtn = new JButton("Back");
        backBtn.addActionListener(actionListener);

        controls.add(backBtn);
        controls.add(clearBtn);

        content.add(controls, BorderLayout.NORTH);

        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
