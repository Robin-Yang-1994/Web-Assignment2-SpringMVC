package com.spring.assignment.controller;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/charts")  // url defining
public class ChartController {

    private PieDataset createDataSet() {  // new data set

        DefaultPieDataset dpd = new DefaultPieDataset();  // data set object

        dpd.setValue("Anime and Manga", 55);  // set each value but adds up to hundred maximum
        dpd.setValue("Job/Work", 10);
        dpd.setValue("Social Life", 10);
        dpd.setValue("Food", 20);
        dpd.setValue("Others", 5);

        return dpd;  // return object
    }

    private JFreeChart createChart(PieDataset data, String pieChartTitle) {  // design and create pie chart

        // using chart library
        JFreeChart chart = ChartFactory.createPieChart(pieChartTitle, data,true, true, false);
        // new chart object with the title, data from dpd, with data representations and chart tips

        PiePlot plot = (PiePlot) chart.getPlot();
        // set how the pie should be shown with data
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;
    }

    @RequestMapping(value = "/pie", method = RequestMethod.GET)  // displaying straight from controller without requiring a view
    public void drawPieChart(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("image/png"); // represent the pie chart as an image format

        PieDataset data = createDataSet();  // instance of data

        JFreeChart chart = createChart(data, "Life of an Otaku");

        try {
            ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, 1200, 600);  // fixed size chart
                                                                                                        // try catch for error if found
            response.getOutputStream().close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}