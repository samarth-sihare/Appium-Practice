package com.tribuneqa.utilities;

import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class ConsolidatedHTMLReport {
	
	@SuppressWarnings("unchecked")
	public static void jsonCreatorForReports(String commonReportsFolder, String customReportResources) throws IOException {
		
		JSONObject testsObj = new JSONObject();
		JSONArray testsArray = new JSONArray();
		
		
		File[] directories = new File(commonReportsFolder).listFiles(new FileFilter() {
		    @Override
		    public boolean accept(File file) {
		        return file.isDirectory();
		    }
		});
//		System.out.println(Arrays.toString(directories));
		if (directories != null){	
			for (File directory : directories ) {
				File report = new File(directory.getPath() + "\\report.html");
				
				if (report.exists()){
//					System.out.println(directory.getName().toString());
//					System.out.println(report.lastModified());
					
					JSONObject testsdataObj = new JSONObject();
					
					testsdataObj.put("name", directory.getName());
					testsdataObj.put("executedAt", report.lastModified());
					testsdataObj.put("statistic", "");
					
					testsArray.add(testsdataObj);
					
				}
				
			}
			testsObj.put("tests", testsArray);
			
			try (FileWriter jsfile = new FileWriter(customReportResources + "/tests.js")) {
				jsfile.write("var reportData = ");
				jsfile.append(testsObj.toJSONString());
				jsfile.append(";");
			}
			catch (Exception e){
				System.out.println("Can not create common report ----\n" + e.toString());
			}

		}
	}
	
	
    private static final String[] resources = new String[]{
            "galen-report.js",
            "handlebars-v2.0.0.js",
            "jquery-1.11.2.min.js",
            "report.css",
            "tablesorter.css",
            "tablesorter.js"
    };
	
	public void buildCustomReport(String galenHTMLReportsFolder) throws IOException {
		
		try{
			new File(galenHTMLReportsFolder + "/CustomReportResources").mkdirs();
			
			File reportsFiledest = new File(galenHTMLReportsFolder);
			File reportsFileSrc = new File("src/test/resources/report.html");
			
			//Copy all files other than Reports.html (i.e. listed in resources)
			for (String resourceName : resources) {
				File source = new File("src/test/resources/" + resourceName);
				File dest = new File(galenHTMLReportsFolder + "/CustomReportResources");
				
				FileUtils.copyFileToDirectory(source, dest, false);
			}
			
			//Create js file to feed reports.html file (do not get confused with name of method)
			jsonCreatorForReports(galenHTMLReportsFolder, galenHTMLReportsFolder + "/CustomReportResources");
			
			
			//Copy just report.html file to html reports folder
			FileUtils.copyFileToDirectory(reportsFileSrc, reportsFiledest, false);
			
		}
		catch(Exception ex){
			System.out.println("Can not create common report ----\n" + ex.toString());
		}
				
	}

}
