package com.wzf.excel;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
 
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
 
 
public class XlsDto2Excel {
 
    /**
     * 
     * @param xls
     *            XlsDtoʵ�����һ������
     * @throws Exception
     *             �ڵ���Excel�Ĺ������׳��쳣
     */
    public static void xlsDto2Excel(String[] titles,List<XlsDto> xls,String outpath) throws Exception {
        // ����Excel�ĵ�
        HSSFWorkbook hwb = new HSSFWorkbook();
        XlsDto xlsDto = null;
        // sheet ��Ӧһ������ҳ
        HSSFSheet sheet = hwb.createSheet("a");
        HSSFRow firstrow = sheet.createRow(0); // �±�Ϊ0���п�ʼ
        HSSFCell[] firstcell = new HSSFCell[titles.length];
        
        for (int j = 0; j < titles.length; j++) {
            firstcell[j] = firstrow.createCell(j);
            firstcell[j].setCellValue(new HSSFRichTextString(titles[j]));
        }
        for (int i = 0; i < xls.size(); i++) {
            // ����һ��
            HSSFRow row = sheet.createRow(i + 1);
            // �õ�Ҫ�����ÿһ����¼
            xlsDto = xls.get(i);
            for (int colu = 0; colu <= 4; colu++) {
                // ��һ����ѭ��
                HSSFCell xh = row.createCell(0);
                xh.setCellValue(xlsDto.getXh());
                HSSFCell xm = row.createCell(1);
                xm.setCellValue(xlsDto.getXm());
                HSSFCell yxsmc = row.createCell(2);
                yxsmc.setCellValue(xlsDto.getYxsmc());
                HSSFCell kcm = row.createCell(3);
                kcm.setCellValue(xlsDto.getKcm());
                HSSFCell cj = row.createCell(4);
                cj.setCellValue(xlsDto.getCj());
            }
        }
        // �����ļ��������׼��������ӱ��
        OutputStream out = new FileOutputStream(outpath);
        hwb.write(out);
        out.close();
        System.out.println("���ݿ⵼���ɹ�");
    }
 
}