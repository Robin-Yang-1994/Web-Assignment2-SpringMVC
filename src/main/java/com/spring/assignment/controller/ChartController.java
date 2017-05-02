package com.spring.assignment.controller;
import java.io.*;

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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
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
        return chart; // return value
    }

    @RequestMapping(value = "/pie", method = RequestMethod.GET)  // displaying straight from controller without requiring a view
    public void drawPieChart(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("image/png"); // represent the pie chart as an image

        PieDataset data = createDataSet();  // instance of data

        JFreeChart chart = createChart(data, "Life of an Otaku"); // create chart with data and library

        try {
            ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, 1200, 600);
                                                                                                        // show image as PNG
                                                                                                        // fixed size chart
                                                                                                        // try catch for error if found
            response.getOutputStream().close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Autowired
    AnimeService animeService; // use anime service for anime information

    @RequestMapping(value = "/report", method = RequestMethod.GET)

    public String createReports() {

            JasperReportBuilder report = DynamicReports.report(); //a new report object

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

            return "redirect:/charts/reportDownload"; // return download method below
        }

    @RequestMapping(value = "/reportDownload", method = RequestMethod.GET)
    public String downloadFiles(HttpServletRequest request, HttpServletResponse response) {

        ServletContext context = request.getServletContext();

        File report = new File("report.pdf");  // set file to download
        FileInputStream input = null; // zero input and output
        OutputStream output = null;

        try { // try catch
            input = new FileInputStream(report);  // new file object for download

//            response.setContentLength((int) report.length());
            response.setContentType(context.getMimeType("report.pdf"));

            // file name and header value
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s",report.getName()); // file name
            response.setHeader(headerKey, headerValue); // set value

            output = response.getOutputStream(); // output file

            IOUtils.copy(input, output); // make copy of output file and push download

        } catch (Exception e) {  // error checking
            e.printStackTrace();
        } finally {
            try {
                if (null != input)
                    input.close();
                if (null != input)
                    output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return "redirect:/"; // return home view with file downloaded
    }

}


