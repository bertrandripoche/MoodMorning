package com.depuisletemps.moodmorning.model;

public enum Mood {
    SAD("#ffde3c50", "smileysad", .2f),
    BORED("#ff9b9b9b", "smileybored", .4f),
    REGULAR("#a5468ad9", "smileyregular", .6f),
    GLAD("#ffb8e986", "smileyglad", .8f),
    HAPPY("#fff9ec4f", "smileyhappy", 1f);

    private final String color;
    private final String fileName;
    private final float historyWidth;

    Mood(String color, String fileName, float historyWidth){
        this.color = color;
        this.fileName = fileName;
        this.historyWidth = historyWidth;
    }

    /**
     * Method returning the new mood to display after swiping up or down the main screen
     * @param direction "up" or "down"
     * @param currentMood current mood
     * @return the new mood to apply on the screen
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
}
