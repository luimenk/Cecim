package com.demo.utils;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;

public class WordCopyTableAfterTable {

    public static XmlCursor setCursorToNextStartToken(XmlObject object) {
        XmlCursor cursor = object.newCursor();
        cursor.toEndToken(); //Now we are at end of the XmlObject.
        //There always must be a next start token.
        while(cursor.hasNextToken() && cursor.toNextToken() != org.apache.xmlbeans.XmlCursor.TokenType.START);
        //Now we are at the next start token and can insert new things here.
        return cursor;
    }

    public static void removeCellValues(XWPFTableCell cell) {
        for (XWPFParagraph paragraph : cell.getParagraphs()) {
            for (int i = paragraph.getRuns().size() - 1; i >= 0; i--) {
                paragraph.removeRun(i);
            }
        }
    }
}
