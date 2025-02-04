package com.unoapp.uno.ui.screens;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;

import com.unoapp.uno.Application;
import com.unoapp.uno.Application.ScreenObject;
import com.unoapp.uno.abstracts.MouseClickListener;
import com.unoapp.uno.abstracts.onClickListener;
import com.unoapp.uno.ui.components.GenericMenuScreen;
import com.unoapp.uno.ui.components.RoundedRectangle;
import com.unoapp.uno.ui.components.ScaledBackground;
import com.unoapp.uno.ui.components.SmoothText;
import com.unoapp.uno.ui.components.TransparentPanel;
import com.unoapp.uno.utils.Constants;
import com.unoapp.uno.utils.Constants.Screens;

/**
 * Title screen 
 * Opens on start of program
 */
public class TitleScreen extends GenericMenuScreen {

    public TitleScreen() {
        init();
    }

    private void init() {
        ScaledBackground background = new ScaledBackground(Constants.getAsset("mainBg.png"), xSize, ySize);
        getContentPane().add(background);

        TransparentPanel mainPanel = new TransparentPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setPreferredSize(new Dimension(MAX_COMPONENT_X, MAX_COMPONENT_Y));

        TransparentPanel buttonPanel = new TransparentPanel();

        // Play button
        buttonPanel.add(createPanel("Play", Constants.getAsset("playIcon.png"), 178, 124,
                () -> Application.changeScreen(new ScreenObject(this, Screens.PLAYER_SELECT))));
        buttonPanel.add(Box.createHorizontalStrut(60));

        // Settings button
        buttonPanel.add(createPanel("Settings", Constants.getAsset("gear.png"), 123, 123,
                () -> Application.changeScreen(new ScreenObject(this, Screens.EXIT))));
        buttonPanel.add(Box.createHorizontalStrut(60));

        // Quit button
        buttonPanel.add(createPanel("Quit", Constants.getAsset("power.png"), 144, 144, () -> System.exit(0)));

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(Box.createVerticalGlue());

        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue());

        background.add(mainPanel);

        pack();
    }

    /**
     * Creates panel with button and text for respective operation
     * @param labelText label of button
     * @param iconSrc src of icon
     * @param iconWidth width of icon
     * @param iconHeight height of icon
     * @return panel with label and icon
     */
    private TransparentPanel createPanel(String labelText, String iconSrc, int iconWidth, int iconHeight,
            onClickListener listener) {
        TransparentPanel mainPanel = new TransparentPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.addMouseListener(new MouseClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listener.onClick();
            }
        });

        int rectSize = (MAX_COMPONENT_X / 4) - (60);

        TransparentPanel textPanel = new TransparentPanel();
        SmoothText text = new SmoothText(labelText, Constants.getColor(Constants.Color.RED, false),
                Constants.getProximaInstance(26));
        textPanel.add(text);
        textPanel.add(Box.createVerticalStrut(rectSize / 3));

        RoundedRectangle playButton = new RoundedRectangle(rectSize, rectSize, 80, new BorderLayout());
        TransparentPanel label = new TransparentPanel();
        label.add(new ScaledBackground(iconSrc, iconWidth, iconHeight, new FlowLayout()));
        playButton.add(Box.createVerticalStrut(rectSize / 4), BorderLayout.NORTH);
        playButton.add(label, BorderLayout.CENTER);
        playButton.add(textPanel, BorderLayout.SOUTH);

        mainPanel.add(playButton);

        return mainPanel;
    }
}