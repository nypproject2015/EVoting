package sg.edu.nyp.evoting.service;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.Color;
import java.awt.Transparency;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;

import com.lowagie.text.Font;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.chart.Bar3DChartBuilder;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.column.PercentageColumnBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.jasperreports.components.table.Column;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import sg.edu.nyp.evoting.beans.*;

 
public class VoteResultServlet extends HttpServlet{
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException{
		
		ElectionReport electionReport = new ElectionReport();
		System.out.println("1");
		electionReport.generateReport(request,response);
	}
}



class ElectionReport {
	public static final String ELECTION_REPORT_HEADER = "Election Results - 2012";
	
	void generateReport(HttpServletRequest request, HttpServletResponse response ) {
		
			/*
			List<ElectionReportItem> electionResults = new ArrayList<ElectionReportItem>();
			
			
			electionResults.add(new ElectionReportItem(1,"Marine Parade","PYT","John",1000));
			electionResults.add(new ElectionReportItem(1,"Marine Parade","WTR","Micheal",44000));
			electionResults.add(new ElectionReportItem(2,"Jurong","PYT","Hussain",38383));			
			electionResults.add(new ElectionReportItem(2,"Jurong","WTR","Manikam",50000));				
			electionResults.add(new ElectionReportItem(2,"Jurong","QER","Donny",4300));	
			*/
			try {
				String password1 = request.getParameter("password1");
				String password2 = request.getParameter("password2");
				String password3 = request.getParameter("password3");	
				
				System.out.println("2");
				List<ElectionReportItem> electionResults  = (new EVotingService(password1, password2, password3)).countVote();
				
				System.out.println("3"+electionResults);
				
				StyleBuilder columnTitleStyle  = DynamicReports.stl.style().bold()
													.setBorder(DynamicReports.stl.pen1Point())
													.setBackgroundColor(Color.ORANGE);

				JasperReportBuilder report = DynamicReports.report();//a new report
				
				System.out.println("4");
				
				//TextColumnBuilder<Integer> idColumn = Columns.column("Constituency", "id", DataTypes.integerType());
				TextColumnBuilder<String> constituencyName = Columns.column("Constituency", "constituencyName", DataTypes.stringType());
				System.out.println("41");
				TextColumnBuilder<String> partyName = Columns.column("Party", "partyName", DataTypes.stringType());
				System.out.println("42");
				//TextColumnBuilder<String> candidate = Columns.column("Candiate", "candidateName", DataTypes.stringType());
				TextColumnBuilder<Long> votes = Columns.column("Votes", "noofVotes", DataTypes.longType());			
				System.out.println("43");
				PercentageColumnBuilder  perCentVotes = Columns.percentageColumn("Vote %", votes);
			
				System.out.println("44");
				Bar3DChartBuilder itemChart = cht.bar3DChart().setTitle("Party-wise Results Summary")
												   .setCategory(constituencyName)
												   .setCategory(partyName)
												   .series(cht.serie(votes));

				System.out.println("45");
				report
				  .columns(
					  constituencyName, partyName, votes, perCentVotes
					  // Columns.column("Date", "date", DataTypes.dateType())
					  )
				  .title(//title of the report
					  Components.text(ELECTION_REPORT_HEADER)
					  .setHorizontalAlignment(HorizontalAlignment.CENTER))
					  .pageFooter(Components.pageXofY())//show page number on the page footer
					  .setColumnTitleStyle(columnTitleStyle) 
					  .groupBy(constituencyName)
					  .setDataSource(new JRBeanCollectionDataSource(electionResults))
					.summary(itemChart);
				System.out.println("46");
				try {
					//report.toPdf(servletOutputStream);
					report.toPdf(new FileOutputStream("c://results.pdf"));
					report.toPdf(response.getOutputStream());
					//report.show();
				   System.out.println("47");
					//export the report to a pdf file
					//report.toPdf(new FileOutputStream("c://reults.pdf"));
				} catch (Exception e) {
					e.printStackTrace();
				} 
			} catch (Exception e) {
				e.printStackTrace();
				try {
					response.getOutputStream().println("Count Vote failed due to an Exception:"+e.getStackTrace());
				} catch(Exception ex) {}
			} 	

	}
	
	/*
	public static void main(String args[]) {
		new ElectionReport().generateReport();
	}
	*/
}