package Utils;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static  ExtentReports getInstance() {
    	 if (extent == null) {
             ExtentSparkReporter spark = new ExtentSparkReporter("BlazeDemo.html");
             spark.config().setReportName("BlazeDemo Automation Report");
             spark.config().setDocumentTitle("Test Results");

             extent = new ExtentReports();
             extent.attachReporter(spark);
             extent.setSystemInfo("Tester", "Prajyoti Suryawanshi");
         }
        return extent;
     }
}

