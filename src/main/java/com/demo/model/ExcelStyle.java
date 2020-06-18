package com.demo.model;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ExcelStyle {
    private Workbook wb;
    private XSSFSheet sheet;
    public ExcelStyle(Workbook wb, XSSFSheet sheet) {
        this.wb = wb;
        this.sheet = sheet;
    }
    public CellStyle titleStyle() {
        CellStyle style=wb.createCellStyle();
        Font font = sheet.getWorkbook ().createFont ();
        font.setFontName ( "Arial Black" );
        font.setColor(IndexedColors.BLACK.getIndex());
        font.setFontHeight((short)(16*20));
        style.setFont(font);
        return style;
    }

    public CellStyle subjectStyle(){
        CellStyle style=wb.createCellStyle();
        Font font = sheet.getWorkbook ().createFont ();
        font.setFontName ( "Arial" );
        font.setColor(IndexedColors.BLACK.getIndex());
        font.setFontHeight((short)(14*20));
        style.setFont(font);
        return style;
    }

    public CellStyle dateStyle() {
        CellStyle style=wb.createCellStyle();
        Font font = sheet.getWorkbook ().createFont ();
        font.setFontName ( "Arial" );
        font.setColor(IndexedColors.RED.getIndex());
        style.setFont(font);
        return style;
    }

    public CellStyle tableCellStyle(){
        CellStyle style=wb.createCellStyle();
        style.setWrapText(true);
        return style;
    }

    public CellStyle boldStyle(){
        CellStyle style=wb.createCellStyle();
        Font font = sheet.getWorkbook ().createFont ();
        font.setBold(true);
        style.setWrapText(true);
        style.setFont(font);
        return style;
    }




    public Workbook getWb() {
        return wb;
    }

    public void setWb(Workbook wb) {
        this.wb = wb;
    }

    public XSSFSheet getSheet() {
        return sheet;
    }

    public void setSheet(XSSFSheet sheet) {
        this.sheet = sheet;
    }
}
