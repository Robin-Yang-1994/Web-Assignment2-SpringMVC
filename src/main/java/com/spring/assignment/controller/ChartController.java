package com.spring.assignment.controller;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/charts")
public class ChartController {

    private PieDataset createDataSet() {

        DefaultPieDataset dpd = new DefaultPieDataset();
        dpd.setValue("Anime and Manga", 55);
        dpd.setValue("Job/Work", 10);
        dpd.setValue("Social Life", 10);
        dpd.setValue("Food", 20);
        dpd.setValue("Others", 5);
        return dpd;
    }

    private JFreeChart createChart(PieDataset data, String pieChartTitle) {

        JFreeChart chart = ChartFactory.createPieChart3D(pieChartTitle, data,true, true, false);
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;
    }

    @RequestMapping(value = "/pie", method = RequestMethod.GET)
    public void drawPieChart(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("image/png");
        PieDataset data = createDataSet();

        JFreeChart chart = createChart(data, "Life of an Otaku");

        try {
            ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, 1200, 600);

            response.getOutputStream().close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}