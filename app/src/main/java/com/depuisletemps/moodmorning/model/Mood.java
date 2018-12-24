package com.depuisletemps.moodmorning.model;

public enum Mood {
    SAD("sad", "#ffde3c50", "smileysad", .2f),
    BORED("bored", "#ff9b9b9b", "smileybored", .4f),
    REGULAR("regular", "#a5468ad9", "smileyregular", .6f),
    GLAD("glad", "#ffb8e986", "smileyglad", .8f),
    HAPPY("happy", "#fff9ec4f", "smileyhappy", 1f);

    private String name;
    private String color;
    private String fileName;
    private float historyWidth;

    Mood(String name, String color, String fileName, float historyWidth){
        this.name = name;
        this.color = color;
        this.fileName = fileName;
        this.historyWidth = historyWidth;
    }

    /**
     * Method returning the new mood to display after swiping up or down the main screen
     * @param direction : "up" or "down"
     * @param currentMood : current mood
     */
    public static Mood changeMood(Direction direction, Mood currentMood) {
       int i = currentMood.ordinal();
       if (direction == Direction.UP && i != 4) i++;
       if (direction == Direction.DOWN && i != 0) i--;
       return Mood.values()[i];
    }

    public String getColor() {
        return color;
    }

    public String getFileName() {
        return fileName;
    }

    public float getHistoryWidth() {
        return historyWidth;
    }

    public String getName() {
        return name;
    }
}
