package com.leap.library;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;



public class GenericLib {

    private static String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * Generate radom password
     * @param lenght
     * @return
     */
    public static String genPassword(int lenght, boolean isContainsUpperCase, boolean isContainsLowerCase, boolean isContainsDigit){
        Random r = new Random();
        while(true) {
            char[] password = new char[lenght];
            boolean hasUpper = false, hasLower = false, hasDigit = false, hasSpecial = false;
            for(int i=0; i<password.length; i++) {
                char ch = symbols.charAt(r.nextInt(symbols.length()));
                if(isContainsUpperCase && Character.isUpperCase(ch))
                    hasUpper = true;
                else if(isContainsLowerCase && Character.isLowerCase(ch))
                    hasLower = true;
                else if(isContainsDigit && Character.isDigit(ch))
                    hasDigit = true;
                password[i] = ch;
            }
            if(hasUpper && hasLower && hasDigit) {
                return new String(password);
            }
        }
    }

    /**
     * Parse Rgb to color to hex
     * @param rgb
     * @return
     */
    public static String parseRgbTohex(String rgb){
        String value = null;
        try {
            int indexOpen = rgb.indexOf("(");
            rgb = rgb.substring(indexOpen + 1, rgb.length() - 1);

            String[] temp = rgb.split(",");

            int r = Integer.parseInt(temp[0].trim());
            int g = Integer.parseInt(temp[1].trim());
            int b = Integer.parseInt(temp[2].trim());

            value = String.format("#%02x%02x%02x", r, g, b);
        } catch (Exception e) {
        }
        return value;
    }

    /**
     * Validate email address
     * @param email
     * @return
     */
    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public static void getPieChart(int iPassCount, int iFailCount, int iSkippedCount) {
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        pieDataset.setValue("FAIL", new Integer(iFailCount));
        pieDataset.setValue("SKIP", new Integer(iSkippedCount));
        pieDataset.setValue("PASS", new Integer(iPassCount));

        JFreeChart piechart = ChartFactory.createPieChart("Pie Chart", pieDataset, true, true, false);
        PiePlot plot = (PiePlot) piechart.getPlot();

        plot.setSectionPaint("FAIL", Color.RED);
        plot.setSectionPaint("SKIP", Color.ORANGE);
        plot.setSectionPaint("PASS", new Color(192 * 85 + 192 * 104 + 192 * 47));
        plot.setBackgroundPaint(new Color(192 * 65536 + 192 * 256 + 192));
        plot.setExplodePercent("FAIL", 0.05);
        plot.setSimpleLabels(true);
        plot.setSectionOutlinesVisible(true);

        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator("{0}: {1} ({2})", new DecimalFormat("0"),
                new DecimalFormat("0%"));
        plot.setLabelGenerator(gen);
        plot.setLabelFont(new Font("SansSerif", Font.BOLD, 12));
        try {
            ChartUtilities.saveChartAsJPEG(
                    new File(System.getProperty("user.dir") + "//images//PieChart.png"), piechart,
                    400, 400);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void getBarChart(int iPassCount, int iFailCount, int iSkippedCount) {
        final String series1 = "First";
        final String series2 = "Second";
        final String series3 = "Third";
        final String category1 = "Status";

        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        dataSet.addValue(iPassCount, series1, "Status");
        dataSet.addValue(iFailCount, series2, "Status");
        dataSet.addValue(iSkippedCount, series3, "Status");

        JFreeChart chart = ChartFactory.createBarChart("Bar Graph", "Execution Status", "Testcases", dataSet,
                PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot barplot = chart.getCategoryPlot();
        // barplot.setBackgroundPaint(paint);
        barplot.setBackgroundPaint(Color.white);
        barplot.setBackgroundPaint(new Color(192 * 65536 + 192 * 256 + 192));

        barplot.setDomainGridlinePaint(Color.white);

        NumberAxis rangeAxis = (NumberAxis) barplot.getRangeAxis();
        rangeAxis.setRange(0.0, 70.0);
        rangeAxis.setTickUnit(new NumberTickUnit(10));
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);

        final BarRenderer renderer = (BarRenderer) barplot.getRenderer();
        renderer.setDrawBarOutline(false);
        renderer.setMaximumBarWidth(0.20);

        // set up gradient paints for series...
        final GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, new Color(192 * 85 + 192 * 104 + 192 * 47), 0.0f, 0.0f,
                Color.lightGray);
        final GradientPaint gp1 = new GradientPaint(

                0.0f, 0.0f, Color.red, 0.0f, 0.0f, Color.lightGray);

        final GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.orange, 0.0f, 0.0f, Color.lightGray);
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);

        try {
            ChartUtilities.saveChartAsJPEG(
                    new File(System.getProperty("user.dir") + "//images//BarChart.png"), chart,
                    400, 400);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
