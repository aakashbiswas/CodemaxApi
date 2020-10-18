package CodeMaxApi.RestAssured;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentNG {
	public static ExtentReports er;
	 public static ExtentReports beforeTest() {
		  String path=System.getProperty("user.dir")+"\\reports\\index.html";
		  ExtentSparkReporter esr=new ExtentSparkReporter(path);
		  esr.config().setReportName("Api Test for CodeMax");
		  esr.config().setDocumentTitle("Api Test");
		  er=new ExtentReports();
		  er.attachReporter(esr);
		  er.setSystemInfo("Tester", "Aakash Biswas");
		  return er;
	  }
}
