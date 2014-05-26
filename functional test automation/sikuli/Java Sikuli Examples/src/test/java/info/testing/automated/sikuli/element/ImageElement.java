package info.testing.automated.sikuli.element;

import info.testing.automated.sikuli.entities.IImageElement;

/**
 * Author: Serhii Kuts
 */
public class ImageElement implements IImageElement {

    private String path;
    private float similarity;

    public ImageElement(final String path, final float similarity) {
        this.path = path;
        this.similarity = similarity;
    }

    public String getPath() {
        return path;
    }

    public float getSimilarity() {
        return similarity;
    }
}