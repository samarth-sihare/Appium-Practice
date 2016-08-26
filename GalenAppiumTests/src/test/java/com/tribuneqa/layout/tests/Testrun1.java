package com.tribuneqa.layout.tests;

/**
*This is just a test class to run tests
* 
*/

import java.io.IOException;

import org.testng.annotations.Test;

import com.tribuneqa.framework.properties.FrameworkProperties;
import com.tribuneqa.utilities.ConsolidatedHTMLReport;
import com.tribuneqa.utilities.FrameworkUtilities;

public class Testrun1 {
	
	
	FrameworkUtilities utilities = new FrameworkUtilities();
	ConsolidatedHTMLReport customReport = new ConsolidatedHTMLReport();
	

	@Test
	public void reportBuilderTest() throws IOException{
		customReport.buildCustomReport(FrameworkProperties.GALEN_HTML_REPORT_FOLDER);
	}
	

}
