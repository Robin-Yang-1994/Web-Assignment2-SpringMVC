package com.spring.assignment.controller;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.spring.assignment.domain.User;
import com.spring.assignment.service.AnimeService;
import com.spring.assignment.service.UserService;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.exception.DRException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


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

    @Autowired
    AnimeService animeService;

    @RequestMapping(value = "/report", method = RequestMethod.GET)

    public String createReports() {

            JasperReportBuilder report = DynamicReports.report(); //a new report

            report.columns(
                            Columns.column("Anime Name", "animeName", DataTypes.stringType()),  // field name referring to DB fields
                            Columns.column("Genre", "genre", DataTypes.stringType()),
                            Columns.column("Description", "description", DataTypes.stringType()))

                    .title( // title of report
                            Components.text("Anime Infomration").setHorizontalAlignment(HorizontalAlignment.CENTER))

                    .pageFooter(Components.pageXofY()) //show page number on the page footer

                    .setDataSource(animeService.findAll()); // find data using anime service

            try { // try catch method for error finding

                report.toPdf(new FileOutputStream("report.pdf")); //export the report to a pdf file
            } catch (DRException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return "redirect:/";
        }


}