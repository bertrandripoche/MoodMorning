package com.depuisletemps.moodmorning.controller;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.depuisletemps.moodmorning.R;
import com.depuisletemps.moodmorning.model.Mood;
import com.depuisletemps.moodmorning.model.MoodStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class StatsActivity extends AppCompatActivity {
    private MoodDao mMoodDao = new MoodDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        // Pie chart creation
        PieChartView pieChartView = findViewById(R.id.activity_stats_pie_chart);
        List<SliceValue> pieData = new ArrayList<>();

        // Data aggregation to populate the chart
        Map<String, ?> allMoods = mMoodDao.getAllMoods(this);
        Map<Mood, Integer> moodsDataForPieChart = new HashMap<>();
        // We initialize every "mood data" to 0 occurrence
        for(Mood mood : Mood.values()) {
            moodsDataForPieChart.put(mood, 0);
        }

        for (Map.Entry<String, ?> entry : allMoods.entrySet()) {
            String record = (String) entry.getValue();
            MoodStore moodStore = mMoodDao.getMoodStoreFromRecord(record);

            assert moodStore != null;
            Mood mood = moodStore.getMood();
            int currentMoodOccurrences = moodsDataForPieChart.get(mood);
            moodsDataForPieChart.put(moodStore.getMood(), currentMoodOccurrences);
        }

        // From the data aggregated, we create each slice of "chart pie"
        for (Map.Entry<Mood, Integer> entry : moodsDataForPieChart.entrySet()) {
            Mood mood = entry.getKey();
            pieData.add(new SliceValue(entry.getValue(), Color.parseColor(mood.getColor())).setLabel(mood.toString() + " : " + entry.getValue()));
        }

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setHasCenterCircle(true).setCenterText1("Me and").setCenterCircleScale(.4f).setCenterText1FontSize(15).setCenterText2("my moods").setCenterText2FontSize(15);
        pieChartView.setPieChartData(pieChartData);

    }
}
