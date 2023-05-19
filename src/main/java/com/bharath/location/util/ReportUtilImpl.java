package com.bharath.location.util;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.io.File;
import java.util.List;

public class ReportUtilImpl implements  ReportUtil {

    @Override
    public void generatePieChart(String path, List<Object[]> data) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Object[] object: data) {
            dataset.setValue(object[0].toString(), Double.parseDouble(object[1].toString()));
        }
        JFreeChart chart = ChartFactory.createPieChart3D("Location Type Report", dataset, true, true, true);
        try {
            ChartUtilities.saveChartAsJPEG(new File(path), chart, 300, 300);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
