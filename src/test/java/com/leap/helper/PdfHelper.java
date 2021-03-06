package com.leap.helper;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;

public class PdfHelper {

    private static final BaseColor GrayColor = null;
    public static String sdate;
    public static String path =  "D:/dvf.pdf";
    private static String FILE = path;
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD, BaseColor.BLACK);
    private static Font failFont = new Font(Font.FontFamily.TIMES_ROMAN, 9,Font.BOLD, BaseColor.RED);
    private static Font passFont = new Font(Font.FontFamily.TIMES_ROMAN, 9,Font.BOLD, new BaseColor(0, 153, 76));
    private static Font skipFont = new Font(Font.FontFamily.TIMES_ROMAN, 9,Font.BOLD, new BaseColor(204, 102, 0));
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);
    private static Font tableHeaderbold = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD, BaseColor.BLACK);
    private static Font tableCellText = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.NORMAL, BaseColor.BLACK);
    private static Font tableCellValue = new Font(Font.FontFamily.TIMES_ROMAN, 9,Font.NORMAL, BaseColor.BLACK);

    public static void toExecute(ArrayList sTestName, ArrayList sDescription, ArrayList sStatus , int iPassCount, int iFailCount, int iSkippedCount, File pdfReports)
    {
        PdfWriter writer = null;
        // String FILE = sTestngReports;
        try
        {
            Document document = new Document();
            writer = PdfWriter.getInstance(document, new FileOutputStream(pdfReports));
            document.open();
            addMetaData(document);
            addTitlePage(document, sTestName, sDescription, sStatus, iPassCount,iFailCount, iSkippedCount);
            document.close();
            writer.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void addMetaData(Document document)
    {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
        document.addHeader("Test", "Selenium");
    }

    private static void addTitlePage(Document document, ArrayList sTestName, ArrayList sDescription, ArrayList sStatus, int iPassCount, int iFailCount, int iSkipCount)throws DocumentException, MalformedURLException, IOException {
        PdfPTable tblheader = new PdfPTable(1);
        try
        {
            tblheader.setWidths(new int[]{24});
            tblheader.setTotalWidth(150);
            tblheader.setLockedWidth(true);
            tblheader.getDefaultCell().setFixedHeight(130);
            tblheader.getDefaultCell().setLeading(0, 1.1f);
            tblheader.getDefaultCell().setBorder(Rectangle.BOTTOM);
            tblheader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            tblheader.addCell("Image");
            //tblheader.writeSelectedRows(0, -1, 10, tblheader.getTotalHeight() + 590, writer.getDirectContent());
        }
        catch(Exception e)
        {

        }

        Paragraph preface = new Paragraph();
        Image AuvenirheaderImg = Image.getInstance(System.getProperty("user.dir")+"//images//LeapX.png");
        AuvenirheaderImg.scaleAbsolute(50f, 20f);
        AuvenirheaderImg.setAlignment(Element.ALIGN_TOP);

        Image NxgheaderImg = Image.getInstance(System.getProperty("user.dir")+"//images//NXGlogo.png");
        NxgheaderImg.scaleAbsolute(70f, 40f);
        NxgheaderImg.setAlignment(Element.ALIGN_RIGHT);

        // addEmptyLine(preface, 1);
        preface.add(new Paragraph("                           Automation Test Report".toUpperCase(), catFont));
        addEmptyLine(preface, 2);
        preface.add(new Paragraph("   Report generated by: " + "Automation Team" + ", " + new Date(), smallBold));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("   This report demontrates the status of automation execution results in number, percentage of Passed,"  		+ "     Failed and Skipped Test Cases.",smallBold));
        addEmptyLine(preface, 1);
        document.add(preface);

        Image image2 = Image.getInstance(System.getProperty("user.dir")+"//images//PieChart.png");
        image2.scaleAbsolute(120f, 120f);
        //image2.setAlignment(Element.ALIGN_LEFT);

        Image image3 = Image.getInstance(System.getProperty("user.dir")+"//images//BarChart.png");
        image3.scaleAbsolute(120f, 120f);

        addEmptyLine(preface, 8);
        PdfPTable table = new PdfPTable(2);

        table.setWidthPercentage(75);
        table.setWidths(new int[]{1, 1});
        table.setSpacingAfter(10);

        table.addCell(createImageCell(System.getProperty("user.dir")+"//images//PieChart.png"));
        table.addCell(createImageCell(System.getProperty("user.dir")+"//images//BarChart.png"));
        document.add(table);

        Paragraph prefaceThree = new Paragraph();
        prefaceThree.setSpacingBefore(2);
        createSummaryTable(prefaceThree,iPassCount, iFailCount, iSkipCount);
        addEmptyLine(prefaceThree, 2);
        document.add(prefaceThree);

        //preface.add(image2);
        //preface.add(image3);
        Paragraph prefaceTwo = new Paragraph();
        createTable(prefaceTwo,sTestName, sDescription,sStatus );
        document.add(prefaceTwo);
        document.newPage();
    }
    private static void createSummaryTable(Paragraph preface,int iPassCount, int iFailCount, int iSkipCount)throws DocumentException
    {
        int total= iPassCount+iFailCount+iSkipCount;
        PdfPTable table = new PdfPTable(2);

        table.setWidthPercentage(25);
        table.setWidths(new float[]{1,(float) 0.25});

        // table.setWidths(new int[]{1, 1});
        table.addCell(new PdfPCell(new Phrase("Test Summary",tableHeaderbold)));
        table.addCell("");

        table.addCell(new PdfPCell(new Phrase("Total Test cases",tableCellText)));
        table.addCell(String.valueOf(total));
        table.addCell(new PdfPCell(new Phrase("Passed",tableCellText)));
        table.addCell(String.valueOf(iPassCount));
        table.addCell(new PdfPCell(new Phrase("Failed",tableCellText)));
        table.addCell(String.valueOf(iFailCount));
        table.addCell(new PdfPCell(new Phrase("Skipped",tableCellText)));
        table.addCell(String.valueOf(iSkipCount));
        preface.add(table);
    }

    public static PdfPCell createImageCell(String path) throws DocumentException, IOException
    {
        Image img = Image.getInstance(path);
        PdfPCell cell = new PdfPCell(img, true);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setBorderWidth((float)0.05);
        return cell;
    }

    public static PdfPCell createStatusImage(String path) throws DocumentException, IOException
    {
        Image img = Image.getInstance(path);
        PdfPCell cell = new PdfPCell(img, true);
        // cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderWidth((float)0.25);
        cell.setBorderColor(BaseColor.BLACK);
        return cell;
    }

    private static void createTable(Paragraph preface,ArrayList sTestName, ArrayList sDescription, ArrayList sStatus)throws DocumentException, IOException
    {
        Font statusFont = null;
        String image =null;
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(95);
        table.setWidths(new float[]{(float)0.16,(float)0.90,(float)2.5,(float)0.30});
        PdfPCell c1 = new PdfPCell(new Phrase("No.",tableHeaderbold));
        c1.setVerticalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.GRAY);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Testcase",tableHeaderbold));
        c1.setVerticalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.GRAY);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Description",tableHeaderbold));
        c1.setVerticalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.GRAY);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Status",tableHeaderbold));
        c1.setVerticalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.GRAY);
        table.addCell(c1);
        table.setHeaderRows(1);
        int cnt = 0;
        for (int i =0; i<sTestName.size();i++)
        {
            if(sStatus.get(i).equals("Passed"))
            {
                statusFont = passFont;
                //image = System.getProperty("user.dir")+"\\src\\test\\resources\\images\\pass.png";
            }
            else if (sStatus.get(i).equals("Failed"))
            {
                statusFont = failFont;
                //image = System.getProperty("user.dir")+"\\src\\test\\resources\\images\\pass.png";

            }
            else
            {
                statusFont = skipFont;
                //image = System.getProperty("user.dir")+"\\src\\test\\resources\\images\\skip.png";
            }
            cnt=cnt+1;
            table.addCell(new PdfPCell(new Phrase(String.valueOf(cnt),tableCellValue)));
            table.addCell(new PdfPCell(new Phrase(sTestName.get(i).toString(),tableCellValue)));
            table.addCell(new PdfPCell(new Phrase(sDescription.get(i).toString(),tableCellValue)));
            table.addCell(new PdfPCell(new Phrase(sStatus.get(i).toString(),statusFont)));

        }
        preface.add(table);
    }

    private static void addEmptyLine(Paragraph paragraph, int number)
    {
        for (int i = 0; i < number; i++)
        {
            paragraph.add(new Paragraph(" "));
        }
    }

}
