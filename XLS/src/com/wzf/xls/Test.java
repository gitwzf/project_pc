package com.wzf.xls;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Test {
	public static void main(String[] args) {
		try {
			Map<String,String> map=readXls();
			for(String key:map.keySet()){
			//	if(map.get(key).matches("\\S*"+str+"\\S*"))
				System.out.println(key+"  "+map.get(key));
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	 private static Map<String,String> readXls() throws IOException {
	        InputStream is = new Test().getClass()
	        .getResourceAsStream("/areaid_list.xls");
	    //    HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
	        Map<String,String> map = new HashMap<String, String>();
	        Workbook hssfWorkbook;
	        try{
	        	hssfWorkbook = new XSSFWorkbook(is);
	      
	        }catch(Exception e){
	        	hssfWorkbook = new HSSFWorkbook(is);
	        }
	        // ѭ��������Sheet
	           
	        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
	        	   
	            Sheet hssfSheet =  hssfWorkbook.getSheetAt(numSheet);
	            if (hssfSheet == null) {
	                continue;
	            }
	            // ѭ����Row
	            for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
	               Row hssfRow = hssfSheet.getRow(rowNum);
	                if (hssfRow == null) {
	                    continue;
	                }
	                 // ѭ����Cell
	                // for (int cellNum = 0; cellNum <=1; cellNum++) {
	                Cell xh = hssfRow.getCell(0);
	                if (xh == null) {
	                    continue;
	                }
	                Cell xm = hssfRow.getCell(1);
	                if (xm == null) {
	                    continue;
	                }
	                map.put(getValue(xh), getValue(xm));
	                
	            }
	        }
	        
	        return map;
	    }
	 
	    /**
	     * �õ�Excel���е�ֵ
	     * 
	     * @param hssfCell
	     *            Excel�е�ÿһ������
	     * @return Excel��ÿһ�������е�ֵ
	     */
	    @SuppressWarnings("static-access")
	    private static String getValue(Cell hssfCell) {
	        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
	            // ���ز������͵�ֵ
	            return String.valueOf(hssfCell.getBooleanCellValue());
	        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
	            // ������ֵ���͵�ֵ
	        	DecimalFormat df = new DecimalFormat("0"); 
	        	return  df.format(hssfCell.getNumericCellValue()); 
	        	
//	            return String.valueOf(hssfCell.getNumericCellValue());
	        } else {
	            // �����ַ������͵�ֵ
	            return String.valueOf(hssfCell.getStringCellValue());
	        }
	    }
}
