/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.menu.components;

import com.virtualfactory.menu.components.ListBoxMessages_NewGame1;
import de.lessvoid.nifty.controls.ListBox.ListBoxViewConverter;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;

/**
 *
 * @author David
 */
public class MessagesViewConverter_NewGame1 implements ListBoxViewConverter<ListBoxMessages_NewGame1> {
    private static final String CHAT_LINE_INDEX = "#index";
    private static final String CHAT_LINE_DESCRIPTION = "#name";
    private static final String CHAT_LINE_ICON = "#icon";
    private static final String CHAT_LINE_CATEGORY = "#category";
    private static final String CHAT_LINE_ICON_STATUS = "#iconStatus";
    private static final String CHAT_LINE_ICON_STATUS2 = "#iconStatus2";
    private static final String CHAT_LINE_STATUS = "#status";
    private static final String CHAT_LINE_YOUR_BEST_SCORE = "#yourBestScore";
    private static final String CHAT_LINE_GAME_BEST_SCORE = "#gameBestScore";
    private static final String CHAT_LINE_ATTEMPTS_NUMBER = "#attemptsNumber";
    
    /**
     * Default constructor.
     */
    public MessagesViewConverter_NewGame1() {
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final void display(final Element listBoxItem, final ListBoxMessages_NewGame1 item) {
        final Element textIndex = listBoxItem.findElementByName(CHAT_LINE_INDEX);
        if (textIndex == null)   return;
        final TextRenderer textRendererIndex = textIndex.getRenderer(TextRenderer.class);
        final Element textD = listBoxItem.findElementByName(CHAT_LINE_DESCRIPTION);
        if (textD == null)   return;
        final TextRenderer textRendererD = textD.getRenderer(TextRenderer.class);
        final Element icon = listBoxItem.findElementByName(CHAT_LINE_ICON);
        final ImageRenderer iconRenderer = icon.getRenderer(ImageRenderer.class);
        final Element textC = listBoxItem.findElementByName(CHAT_LINE_CATEGORY);
        if (textC == null)   return;
        final TextRenderer textRendererC = textC.getRenderer(TextRenderer.class);
        final Element iconStatus = listBoxItem.findElementByName(CHAT_LINE_ICON_STATUS);
        final ImageRenderer iconStatusRenderer = iconStatus.getRenderer(ImageRenderer.class);
        final Element iconStatus2 = listBoxItem.findElementByName(CHAT_LINE_ICON_STATUS2);
        final ImageRenderer iconStatusRenderer2 = iconStatus2.getRenderer(ImageRenderer.class);
        final Element textStatus = listBoxItem.findElementByName(CHAT_LINE_STATUS);
        if (textStatus == null)   return;
        final TextRenderer textRendererStatus = textStatus.getRenderer(TextRenderer.class);
        
        final Element textYourBestScore = listBoxItem.findElementByName(CHAT_LINE_YOUR_BEST_SCORE);
        if (textYourBestScore == null)   return;
        final TextRenderer textRendererYourBestScore = textYourBestScore.getRenderer(TextRenderer.class);
        final Element textGameBestScore = listBoxItem.findElementByName(CHAT_LINE_GAME_BEST_SCORE);
        if (textGameBestScore == null)   return;
        final TextRenderer textRendererGameBestScore = textGameBestScore.getRenderer(TextRenderer.class);
        final Element textAttemptsNumber = listBoxItem.findElementByName(CHAT_LINE_ATTEMPTS_NUMBER);
        if (textAttemptsNumber == null)   return;
        final TextRenderer textRendererAttemptsNumber = textAttemptsNumber.getRenderer(TextRenderer.class);
        
        if (item != null) {
            textRendererIndex.setText(item.getTextIndex());
            textRendererD.setText(item.getTextName());
            iconRenderer.setImage(item.getIcon());
            textRendererC.setText(item.getTextCategory());
            iconStatusRenderer.setImage(item.getStatusIcon());
            iconStatusRenderer2.setImage(item.getStatusIcon2());
            textRendererStatus.setText(item.getStatus());
            textRendererYourBestScore.setText(item.getYourBestScore());
            textRendererGameBestScore.setText(item.getGameBestScore());
            textRendererAttemptsNumber.setText(String.valueOf(item.getAttemptsNumber()));
        } else {
            textRendererIndex.setText("");
            textRendererD.setText("");
            iconRenderer.setImage(null);
            textRendererC.setText("");
            iconStatusRenderer.setImage(null);
            iconStatusRenderer2.setImage(null);
            textRendererStatus.setText("");
            textRendererYourBestScore.setText("");
            textRendererGameBestScore.setText("");
            textRendererAttemptsNumber.setText("");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getWidth(final Element listBoxItem, final ListBoxMessages_NewGame1 item) {
        final Element textIndex = listBoxItem.findElementByName(CHAT_LINE_INDEX);
        if (textIndex == null)   return 0;
        final TextRenderer textRendererIndex = textIndex.getRenderer(TextRenderer.class);
        final Element textD = listBoxItem.findElementByName(CHAT_LINE_DESCRIPTION);
        if (textD == null)   return 0;
        final TextRenderer textRendererD = textD.getRenderer(TextRenderer.class);
        final Element icon = listBoxItem.findElementByName(CHAT_LINE_ICON);
        final ImageRenderer iconRenderer = icon.getRenderer(ImageRenderer.class);
        final Element textC = listBoxItem.findElementByName(CHAT_LINE_CATEGORY);
        if (textC == null)   return 0;
        final TextRenderer textRendererC = textC.getRenderer(TextRenderer.class);
        final Element iconStatus = listBoxItem.findElementByName(CHAT_LINE_ICON_STATUS);
        final ImageRenderer iconStatusRenderer = iconStatus.getRenderer(ImageRenderer.class);
        final Element iconStatus2 = listBoxItem.findElementByName(CHAT_LINE_ICON_STATUS2);
        final ImageRenderer iconStatusRenderer2 = iconStatus2.getRenderer(ImageRenderer.class);
        final Element textStatus = listBoxItem.findElementByName(CHAT_LINE_STATUS);
        if (textStatus == null)   return 0;
        final TextRenderer textRendererStatus = textStatus.getRenderer(TextRenderer.class);
        
        final Element textYourBestScore = listBoxItem.findElementByName(CHAT_LINE_YOUR_BEST_SCORE);
        if (textYourBestScore == null)   return 0;
        final TextRenderer textRendererYourBestScore = textYourBestScore.getRenderer(TextRenderer.class);
        final Element textGameBestScore = listBoxItem.findElementByName(CHAT_LINE_GAME_BEST_SCORE);
        if (textGameBestScore == null)   return 0;
        final TextRenderer textRendererGameBestScore = textGameBestScore.getRenderer(TextRenderer.class);
        final Element textAttemptsNumber = listBoxItem.findElementByName(CHAT_LINE_ATTEMPTS_NUMBER);
        if (textAttemptsNumber == null)   return 0;
        final TextRenderer textRendererAttemptsNumber = textAttemptsNumber.getRenderer(TextRenderer.class);
        
        return ((textRendererIndex.getFont() == null) ? 0 : textRendererIndex.getFont().getWidth(item.getTextIndex()))
                + ((textRendererD.getFont() == null) ? 0 : textRendererD.getFont().getWidth(item.getTextName()))
                + ((textRendererC.getFont() == null) ? 0 : textRendererC.getFont().getWidth(item.getTextCategory()))
                + ((textRendererStatus.getFont() == null) ? 0 : textRendererStatus.getFont().getWidth(item.getStatus()))
                + ((item.getIcon() == null) ? 0 : item.getIcon().getWidth())
                + ((item.getStatusIcon2() == null) ? 0 : item.getStatusIcon2().getWidth())
                + ((item.getStatusIcon() == null) ? 0 : item.getStatusIcon().getWidth())
                + ((textRendererYourBestScore.getFont() == null) ? 0 : textRendererYourBestScore.getFont().getWidth(item.getYourBestScore()))
                + ((textRendererGameBestScore.getFont() == null) ? 0 : textRendererGameBestScore.getFont().getWidth(item.getGameBestScore()))
                + ((textRendererAttemptsNumber.getFont() == null) ? 0 : textRendererAttemptsNumber.getFont().getWidth(String.valueOf(item.getAttemptsNumber())));
    }
}
