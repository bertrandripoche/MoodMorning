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

    public static Mood changeMood(String direction, Mood currentMood) {
       int i = currentMood.ordinal();
       if (direction.equalsIgnoreCase("up") && i != 4) i++;
       if (direction.equalsIgnoreCase("down") && i != 0) i--;
       Mood newMood = Mood.values()[i];
       return newMood;
    }

    /*public void updateMood(ImageView imgView, Mood mood) {
        imgView.setBackgroundColor(Color.parseColor(mood.getColor()));
        imgView.setTag(mood.getName());
        int resID = getResources().getIdentifier(mood.getFileName(), "drawable", MainActivity.getPackageName());
        imgView.setImageResource(resID);
    }*/

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
