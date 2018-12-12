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

    public static Mood changeMood(String direction, String mood) {
       Mood currentMood = Mood.valueOf(mood.toUpperCase());
       int i = currentMood.ordinal();
       if (direction.equalsIgnoreCase("up") && i != 4) i++;
       if (direction.equalsIgnoreCase("down") && i != 0) i--;
       Mood newMood = Mood.values()[i];
       return newMood;
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
