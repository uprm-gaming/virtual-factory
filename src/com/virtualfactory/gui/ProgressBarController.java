/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.gui;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.tools.SizeValue;
import de.lessvoid.xml.xpp3.Attributes;
import com.virtualfactory.app.GameEngine;
import java.util.Properties;
/**
 *
 * @author David
 */
public class ProgressBarController implements Controller {
    private Element progressBarElement;
    private Element progressTextElement;

    @Override
    public void bind(
        final Nifty nifty,
        final Screen screenParam,
        final Element element,
        final Properties parameter,
        final Attributes controlDefinitionAttributes) {
      progressBarElement = element.findElementByName("imageInner");
      progressTextElement = element.findElementByName("textInner");
    }
    
    @Override
    public void init(final Properties parameter, final Attributes controlDefinitionAttributes) {
    }

    @Override
    public void onStartScreen() {
    }

    @Override
    public void onFocus(final boolean getFocus) {
    }

    @Override
    public boolean inputEvent(final NiftyInputEvent inputEvent) {
      return false;
    }

    public void setProgress(final float progressValue) {
      float progress = progressValue;
      if (progress < 0.0f) {
        progress = 0.0f;
      } else if (progress > 1.0f) {
        progress = 1.0f;
      }
      final int MIN_WIDTH = 32; 
      int pixelWidth = (int)(MIN_WIDTH + (progressBarElement.getParent().getWidth() - MIN_WIDTH) * progress);
      progressBarElement.setConstraintWidth(new SizeValue(pixelWidth + "px"));
      progressBarElement.getParent().layoutElements();

      String progressText = String.format("%3.0f%%", progress * 100);
      progressTextElement.getRenderer(TextRenderer.class).setText(progressText);
    }
}
