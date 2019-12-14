package GUI.State;
import javax.swing.*;
import org.knowm.xchart.*;
import org.knowm.xchart.style.PieStyler;
import org.knowm.xchart.style.Styler;

import java.awt.*;

public class State extends JFrame {
    private PieChart chart;
    public State(double backLog, double InProgress, double completed) {
        chart = new PieChartBuilder().width(800).height(600).title("State").theme(Styler.ChartTheme.GGPlot2).build();
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setAnnotationType(PieStyler.AnnotationType.LabelAndPercentage);
        chart.getStyler().setAnnotationDistance(1.15);
        chart.getStyler().setPlotContentSize(.7);
        chart.getStyler().setStartAngleInDegrees(90);

        chart.addSeries("backLog", backLog);
        chart.addSeries("In-Progress", InProgress);
        chart.addSeries("completed", completed);

        XChartPanel chartPane = new XChartPanel(chart);
        chartPane.setBounds(0,0,300,300);
        this.add(chartPane);
        this.setBounds(0,0,400,400);
        this.setVisible(true);
    }
}
