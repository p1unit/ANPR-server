
package com.anpr.server.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Word {

    @SerializedName("boundingBox")
    @Expose
    private String boundingBox;
    @SerializedName("text")
    @Expose
    private String text;

    public String getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(String boundingBox) {
        this.boundingBox = boundingBox;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Word{" +
                "boundingBox='" + boundingBox + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
