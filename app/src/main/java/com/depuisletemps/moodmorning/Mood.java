package com.depuisletemps.moodmorning;

public enum Mood {
    SAD("#ffde3c50", "smiley-sad"),
    BORED("#ff9b9b9b", "smiley-bored"),
    REGULAR("#a5468ad9", "smiley-regular"),
    GLAD("#ffb8e986", "smiley-glad"),
    HAPPY("#fff9ec4f", "smiley-happy");

    private String color;
    private String fileName;

    Mood(String color, String fileName){
        this.color = color;
        this.fileName = fileName;
    }

    public String getColor() {
        return color;
    }

    public String getFileName() {
        return fileName;
    }
}
