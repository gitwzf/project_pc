package com.wzf.excel;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
 
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.wzf.model.School;
 
 
/**
 * 
 * @author Hongten</br>
 * 
 *         �ο���ַ��http://hao0610.iteye.com/blog/1160678
 * 
 */
public class XlsMain {
 
    public static void main(String[] args) throws IOException {
        XlsMain xlsMain = new XlsMain();
//        XlsDto xls = null;
//        List<XlsDto> list = xlsMain.readXls();
//         
//        try {
//        	String[] titles=new String[]{"ѧ��","����","ѧԺ","�γ���","�ɼ�"};
//            XlsDto2Excel.xlsDto2Excel(titles,list,"d:/b.xls");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        for (int i = 0; i < list.size(); i++) {
//            xls = (XlsDto) list.get(i);
//            System.out.println(xls.getXh() + "    " + xls.getXm() + "    "
//                    + xls.getYxsmc() + "    " + xls.getKcm() + "    "
//                    + xls.getCj());
//        }
        List<School> array=xlsMain.readSchool();
        School s=null;
        for(int i=0;i<array.size();i++){
        	s=array.get(i);
        	System.out.println(i+s.getSname()+" "+s.getAddr()+" "+s.getChuanzhen()+" "+s.getYoubian()+" "+s.getFname()+s.getFduty()+s.getFmail()+s.getFtel());
        	
        }
    }
 
    private List<School> readSchool() throws IOException {
        InputStream is = new FileInputStream("d:/b.xls");
        XSSFWorkbook hssfWorkbook = new XSSFWorkbook(is);
        School school = null;
        List<School> list = new ArrayList<School>();
        // ѭ��������Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // ѭ����Row
            for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                school = new School();
                XSSFCell xh = hssfRow.getCell(0);
                if (xh == null) {
                    continue;
                }
                
                if(getValue(xh).matches("\\d+��\\S+")){
               school.setSname(getValue(xh).split("��")[1]);
              school.setAddr(getValue(hssfRow.getCell(2)).split("��")[1]);
               
               school.setChuanzhen(getValue(hssfSheet.getRow(rowNum+1).getCell(0)).split("��")[1]);
               school.setYoubian(getValue(hssfSheet.getRow(rowNum+1).getCell(2)).split("��")[1]);
               
               school.setFname(getValue(hssfSheet.getRow(rowNum+3).getCell(0)));
               school.setFduty(getValue(hssfSheet.getRow(rowNum+3).getCell(1)));
               school.setFtel(getValue(hssfSheet.getRow(rowNum+3).getCell(2)));
               school.setFmail(getValue(hssfSheet.getRow(rowNum+3).getCell(3)));
               list.add(school);
               rowNum=rowNum+3;
               }
                
               
            }
        }
        return list;
    }
    
    
    
    /**
     * ��ȡxls�ļ�����
     * 
     * @return List<XlsDto>����
     * @throws IOException
     *             ����/���(i/o)�쳣
     */
    private List<XlsDto> readXls() throws IOException {
        InputStream is = new FileInputStream("d:/a.xls");
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        XlsDto xlsDto = null;
        List<XlsDto> list = new ArrayList<XlsDto>();
        // ѭ��������Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // ѭ����Row
            for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                xlsDto = new XlsDto();
                // ѭ����Cell
                // 0ѧ�� 1���� 2ѧԺ 3�γ��� 4 �ɼ�
                // for (int cellNum = 0; cellNum <=4; cellNum++) {
                HSSFCell xh = hssfRow.getCell(0);
                if (xh == null) {
                    continue;
                }
                xlsDto.setXh(getValue(xh));
                HSSFCell xm = hssfRow.getCell(1);
                if (xm == null) {
                    continue;
                }
                xlsDto.setXm(getValue(xm));
                HSSFCell yxsmc = hssfRow.getCell(2);
                if (yxsmc == null) {
                    continue;
                }
                xlsDto.setYxsmc(getValue(yxsmc));
                HSSFCell kcm = hssfRow.getCell(3);
                if (kcm == null) {
                    continue;
                }
                xlsDto.setKcm(getValue(kcm));
                HSSFCell cj = hssfRow.getCell(4);
                if (cj == null) {
                    continue;
                }
                xlsDto.setCj(Float.parseFloat(getValue(cj)));
                list.add(xlsDto);
            }
        }
        return list;
    }
 
    /**
     * �õ�Excel���е�ֵ
     * 
     * @param hssfCell
     *            Excel�е�ÿһ������
     * @return Excel��ÿһ�������е�ֵ
     */
    @SuppressWarnings("static-access")
    private String getValue(Cell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            // ���ز������͵�ֵ
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            // ������ֵ���͵�ֵ
        	DecimalFormat df = new DecimalFormat("0"); 
        	return  df.format(hssfCell.getNumericCellValue()); 
        } else {
            // �����ַ������͵�ֵ
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
 
}