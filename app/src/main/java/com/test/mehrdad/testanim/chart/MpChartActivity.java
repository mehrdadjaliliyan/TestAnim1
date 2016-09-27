package com.test.mehrdad.testanim.chart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.test.mehrdad.testanim.R;

import java.util.ArrayList;

public class MpChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp_chart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        m1();
        m2();
    }

    private void m1() {

        BarChart chart;
        ArrayList<BarEntry> barEntries;
        ArrayList<String> BarEntryLabels;
        BarDataSet barDataSet;
        BarData data;


        chart = (BarChart) findViewById(R.id.chart1);

        chart.setDescription("");

        // enable touch gestures
        chart.setTouchEnabled(true);
        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);


        barEntries = new ArrayList<>();
        BarEntryLabels = new ArrayList<>();

        barEntries.add(new BarEntry(2f, 0));
        barEntries.add(new BarEntry(4f, 1));
        barEntries.add(new BarEntry(6f, 2));
        barEntries.add(new BarEntry(8f, 3));
        barEntries.add(new BarEntry(7f, 4));
        barEntries.add(new BarEntry(3f, 5));

        BarEntryLabels.add("January");
        BarEntryLabels.add("February");
        BarEntryLabels.add("March");
        BarEntryLabels.add("April");
        BarEntryLabels.add("May");
        BarEntryLabels.add("June");

        barDataSet = new BarDataSet(barEntries, "Projects");

        data = new BarData(BarEntryLabels, barDataSet);
        /////////////////////////////////
        LimitLine llXAxis = new LimitLine(4);
        llXAxis.setLineWidth(4f);
        llXAxis.setLineColor(Color.RED);
        llXAxis.enableDashedLine(10f, 10f, 0f);
        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        llXAxis.setTextSize(10f);

        XAxis xAxis = chart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.addLimitLine(llXAxis); // add x-axis limit line

        YAxis leftAxis = chart.getAxisLeft();
        // leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.addLimitLine(llXAxis); // add x-axis limit line
        //--
        /////////////////////////////

        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        chart.setData(data);

        chart.animateY(3000);
    }

    private void m2() {
        LineChart lineChart = (LineChart) findViewById(R.id.chart2);

        int Low = 10;
        int High = 100;

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry((int) (Math.random() * (High - Low)) + Low, 0));
        entries.add(new Entry((int) (Math.random() * (High - Low)) + Low, 1));
        entries.add(new Entry((int) (Math.random() * (High - Low)) + Low, 2));
        entries.add(new Entry((int) (Math.random() * (High - Low)) + Low, 3));
        entries.add(new Entry((int) (Math.random() * (High - Low)) + Low, 4));
        entries.add(new Entry((int) (Math.random() * (High - Low)) + Low, 5));


        LineDataSet dataset = new LineDataSet(entries, "# of Calls");
        ArrayList<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");

        LineData data = new LineData(labels, dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        dataset.setDrawCubic(true);
        dataset.setDrawFilled(true);
        lineChart.setData(data);
        lineChart.animateY(3000);


        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, int i, Highlight highlight) {

                if (entry != null){
                    Toast.makeText(getBaseContext(),"val = " + entry.getVal() , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });

    }


}
