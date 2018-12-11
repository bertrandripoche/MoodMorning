package com.depuisletemps.moodmorning.model;

public enum Mood {
    SAD("sad", "#ffde3c50", "smileysad", .2),
    BORED("bored", "#ff9b9b9b", "smileybored", .4),
    REGULAR("regular", "#a5468ad9", "smileyregular", .6),
    GLAD("glad", "#ffb8e986", "smileyglad", .8),
    HAPPY("happy", "#fff9ec4f", "smileyhappy", 1);

    private String name;
    private String color;
    private String fileName;
    private double historyWidth;

    Mood(String name, String color, String fileName, double historyWidth){
        this.name = name;
        this.color = color;
        this.fileName = fileName;
        this.historyWidth = historyWidth;
    }

    public String getColor() {
        return color;
    }

    public String getFileName() {
        return fileName;
    }

    public double getHistoryWidth() {
        return historyWidth;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
