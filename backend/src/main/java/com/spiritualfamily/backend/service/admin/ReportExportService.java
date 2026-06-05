package com.spiritualfamily.backend.service.admin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class ReportExportService {

    private final AdminService adminService;

    public ReportExportService(AdminService adminService) {
        this.adminService = adminService;
    }

    public byte[] exportExcel() {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            var sheet = workbook.createSheet("Admin Report");
            var stats = adminService.getStats();

            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Spiritual Family Backend Report");

            Row usersRow = sheet.createRow(2);
            usersRow.createCell(0).setCellValue("Total Users");
            usersRow.createCell(1).setCellValue(stats.getTotalUsers());

            Row eventsRow = sheet.createRow(3);
            eventsRow.createCell(0).setCellValue("Total Events");
            eventsRow.createCell(1).setCellValue(stats.getTotalEvents());

            Row prayersRow = sheet.createRow(4);
            prayersRow.createCell(0).setCellValue("Total Prayers");
            prayersRow.createCell(1).setCellValue(stats.getTotalPrayers());

            Row announcementsRow = sheet.createRow(5);
            announcementsRow.createCell(0).setCellValue("Total Announcements");
            announcementsRow.createCell(1).setCellValue(stats.getTotalAnnouncements());

            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (IOException ex) {
            throw new RuntimeException("Failed to export Excel report", ex);
        }
    }

    public byte[] exportPdf() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();
            var stats = adminService.getStats();
            document.add(new Paragraph("Spiritual Family Backend Report"));
            document.add(new Paragraph("Total Users: " + stats.getTotalUsers()));
            document.add(new Paragraph("Total Events: " + stats.getTotalEvents()));
            document.add(new Paragraph("Total Prayers: " + stats.getTotalPrayers()));
            document.add(new Paragraph("Total Announcements: " + stats.getTotalAnnouncements()));
            document.close();
            return outputStream.toByteArray();
        } catch (DocumentException ex) {
            throw new RuntimeException("Failed to export PDF report", ex);
        }
    }
}