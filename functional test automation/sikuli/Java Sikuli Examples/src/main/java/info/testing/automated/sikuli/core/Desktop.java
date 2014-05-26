package info.testing.automated.sikuli.core;

import info.testing.automated.sikuli.entities.IImageElement;
import org.sikuli.script.*;
import java.awt.*;

/**
 * Author: Serhii Kuts
 */
public class Desktop {

    private enum SikuliAction {
        CLICK,
        TYPE
    }

    private Region desktop;
    private boolean endStateReached;

    public Desktop() {
        desktop = new Region(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
    }

    public boolean clickAll(final IImageElement[] elements, final Integer timeout) {
        for (int i = 0; i < elements.length; i++) {
            onAppear(createImage(elements[i]), SikuliAction.CLICK,
                    (i == elements.length - 1));
        }

        boolean observeResult = observe(timeout);

        return endStateReached || observeResult;
    }

    public boolean type(final IImageElement element, final String text, final Integer timeout) {
        return onAppear(createImage(element), SikuliAction.TYPE, text).observe(timeout);
    }

    private Pattern createImage(final IImageElement element) {
        return new Pattern(element.getPath()).similar(element.getSimilarity());
    }

    private boolean observe(final Integer timeout) {
        return desktop.observe(timeout);
    }

    private Desktop onAppear(final Pattern image, final SikuliAction action, final String text) {
        return onAppear(image, action, text, true);
    }

    private Desktop onAppear(final Pattern image, final SikuliAction action, boolean stopObserver) {
        return onAppear(image, action, "", stopObserver);
    }

    private Desktop onAppear(final Pattern image, final SikuliAction action, final String text, final boolean stopObserver) {
        desktop.onAppear(image, new ObserverCallBack() {
            @Override
            public void appeared(ObserveEvent e) {
                switch (action) {
                    case CLICK:
                        e.getMatch().click();
                        break;
                    case TYPE:
                        e.getMatch().click();
                        e.getMatch().type(text);
                        break;
                }

                if (stopObserver) {
                    endStateReached = true;
                    desktop.stopObserver();
                }
            }
        });

        return this;
    }
}
