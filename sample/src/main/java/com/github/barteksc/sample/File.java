package com.github.barteksc.sample;

/**
 * Created by dell on 2017/8/15.
 */

public class File {
    private int id;
    private String name;
    private String size;
    private String type;
    private String time;
    private int lastPageView;
    private String fileURL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLastPageView() {
        return lastPageView;
    }

    public void setLastPageView(int lastPageView) {
        this.lastPageView = lastPageView;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }
}
