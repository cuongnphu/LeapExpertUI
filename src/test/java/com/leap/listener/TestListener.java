package com.leap.listener;

import com.leap.helper.PdfHelper;
import com.leap.library.GenericLib;
import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TestListener implements ITestListener {

    File nxgReportDir;
    File sPdfReports;
    private String sdateTime;
    private int iTotalPassed = 0;
    private int iTotalFailed = 0;
    private int iTotalSkipped = 0;
    private int iTotalExecuted = 0;
    private ArrayList sTestName= new ArrayList<String>();
    private ArrayList sStatus= new ArrayList<String>();
    private ArrayList sDescription= new ArrayList<String>();

    /**
     * Impl Constructor
     * @throws IOException
     */
    public TestListener() throws IOException {
        // Setup format time
        Date date = new Date();
        SimpleDateFormat simpleDF = new SimpleDateFormat("dd-MM-yyyy_hh_mm_ss");
        sdateTime = simpleDF.format(date);

        // Setup directory report
        nxgReportDir = new File(System.getProperty("user.dir") + "//..//LeapX-Report//HTMLReports");
        sPdfReports = new File(System.getProperty("user.dir") + "//..//LeapX-Report//PDFReports");

        if(!nxgReportDir.exists()){
            FileUtils.forceMkdir(nxgReportDir);
        }

        if(!sPdfReports.exists()){
            FileUtils.forceMkdir(sPdfReports);
        }

        //Setting property of NXGReport
        System.setProperty("KIRWA.reporter.config", "KIRWA.properties");
    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        iTotalPassed++;
        sTestName.add(result.getName());
        String desc = result.getMethod().getDescription();
        if(desc != null){
            sDescription.add(desc);
        }else{
            sDescription.add(result.getName());
        }
        sStatus.add("PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        iTotalFailed++;
        sTestName.add(result.getName());
        String desc = result.getMethod().getDescription();
        if(desc != null){
            sDescription.add(desc);
        }else{
            sDescription.add(result.getName());
        }
        sStatus.add("FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        iTotalSkipped++;
        sTestName.add(result.getName());
        String desc = result.getMethod().getDescription();
        if(desc != null){
            sDescription.add(desc);
        }else{
            sDescription.add(result.getName());
        }
        sStatus.add("SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        try {
            //Copy image icon to report directory
            FileUtils.copyDirectory(new File("images"), new File(nxgReportDir.getAbsolutePath() + "//Images"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Copy testng-output report
        try {
            File testOuput = new File(System.getProperty("user.dir") + "//test-output");
            String sTestngReports= System.getProperty("user.dir") + "//..//LeapX-Report//TestNGReports//TestNG_" + sdateTime;
            FileUtils.copyDirectoryToDirectory(testOuput,new File(sTestngReports));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Generate Chart & pdf file
        try {
            File pdfReports = new File(sPdfReports+"//PDFReports"+sdateTime+".pdf");
            iTotalExecuted = iTotalFailed + iTotalSkipped + iTotalPassed;

            GenericLib.getPieChart(iTotalPassed,iTotalFailed,iTotalSkipped);
            GenericLib.getBarChart(iTotalPassed,iTotalFailed,iTotalSkipped);

            PdfHelper.toExecute(sTestName, sDescription, sStatus, iTotalPassed, iTotalFailed, iTotalSkipped, pdfReports);
        }catch (Exception e){
            System.out.println("There are problems in generate pdf: " + e.getMessage());
        }
    }
}
