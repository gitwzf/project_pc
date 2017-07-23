package com.wzf.xls;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.wzf.dbconn.Dbcon;
import com.wzf.model.AskItems;
import com.wzf.model.AskPaper;
import com.wzf.model.AskQuestion;
import com.wzf.model.AskResult;
import com.wzf.model.AskSuit;
import com.wzf.model.Askpage;
import com.wzf.pubvari.Variable;


public class AskMain {
	public static void main(String[] args) {
		try {
			readXls();
			
			System.out.println("suit="+Variable.asksuit.getTitle());
			System.out.println("--------------------");
			for(Askpage askpage:Variable.array_askpage){
				System.out.println("问卷："+askpage.getAskpaper().getTitle()+"  "+askpage.getAskpaper().getBegTim());
				System.out.println("result.....");
				for(AskResult askresults:askpage.getAskresults()){
					System.out.println(askresults.getTitle()+"  "+askresults.getDowngoal());
				}
				System.out.println("..question");
				for(AskQuestion askquestion:askpage.getAskpaper().getAskquestin()){
					System.out.println(askquestion.getQuestion());
					for(AskItems askitems:askquestion.getAskitems()){
						System.out.println(askitems.getContent()+"  "+askitems.getCore());
					}
					
				}
			}
			//OK
			Dbcon db=new Dbcon("dkforplat","112.124.65.68", "3306", "root8", "123456789");
			db.addAsk();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	 private static void readXls() throws IOException {
		 InputStream is = new FileInputStream("d:/ffqy.xlsx");
	    //    HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
	        Workbook hssfWorkbook;
	        try{
	        	hssfWorkbook = new XSSFWorkbook(is);
	      
	        }catch(Exception e){
	        	hssfWorkbook = new HSSFWorkbook(is);
	        }
	        // 循环工作表Sheet
	        ArrayList<Askpage> askpage=new ArrayList<Askpage>();
	        AskSuit asksuit=new AskSuit();
	        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
	        	   
	            Sheet hssfSheet =  hssfWorkbook.getSheetAt(numSheet);
	            if (hssfSheet == null) {
	                continue;
	            }
	            
	            // 循环行Row
	           
	            ArrayList<AskQuestion> askquestin=null;
	            ArrayList<AskResult> askresults=null;
	            ArrayList<AskItems> askitems=null;
	           
	            Askpage askpage0=null;
	            AskQuestion askquestin0=null;
	            AskResult askresults0=null;
	            AskItems askitems0=null;
	            AskPaper askpaper0=null;
	          //第一列
	            int col=0;
	             for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
	               Row hssfRow = hssfSheet.getRow(rowNum);
	                if (hssfRow == null) {
	                    continue;
	                }
	                 // 循环列Cell
	                // for (int cellNum = 0; cellNum <=1; cellNum++) {
	                Cell xh = hssfRow.getCell(col);
	                System.out.println("row="+rowNum+" xh0="+hssfRow.getCell(col)+"  xh1="+hssfRow.getCell(col+1));
	                if (xh == null) {
	                    continue;
	                }
	               
	                //suit
	                if(getValue(xh).equals("游戏名")){
	                	hssfRow = hssfSheet.getRow(++rowNum);
	                //	asksuit=new AskSuit();
	                	asksuit.setTitle(getValue(hssfRow.getCell(col)));
	                //	askpage=new ArrayList<Askpage>();
	                }else
	                //askpaper
	                	if(getValue(xh).equals("问卷名")){
	                		hssfRow = hssfSheet.getRow(++rowNum);
	                		 askpage0=new Askpage();
	                		       askpaper0=new AskPaper(getValue(hssfRow.getCell(col)), getValue(hssfRow.getCell(col+1)),getValue(hssfRow.getCell(col+3)), getValue(hssfRow.getCell(col+4)), getValue(hssfRow.getCell(col+2)), getValue(hssfRow.getCell(col+5)), getValue(hssfRow.getCell(col+7)), getValue(hssfRow.getCell(col+6)));
	                                askpage0.setAskpaper(askpaper0);
	                                askpage.add(askpage0);
	                                askresults=new ArrayList<AskResult>();
	                                askquestin=new ArrayList<AskQuestion>();
	                                askpaper0.setAskquestin(askquestin);
	                	}else
	                  //askresult
	                		if(getValue(xh).equals("结果分析")){
	                			hssfRow = hssfSheet.getRow(++rowNum);
	                			while(!getValue(hssfRow.getCell(col)).equals("题目")&&!getValue(hssfRow.getCell(col)).equals("")){
	                				if(getValue(hssfRow.getCell(col))!=null&&!getValue(hssfRow.getCell(col)).equals("")){
	                				askresults0=new AskResult(getValue(hssfRow.getCell(col+1)), getValue(hssfRow.getCell(col+2)),getValue(hssfRow.getCell(col)), getValue(hssfRow.getCell(col+6)), getValue(hssfRow.getCell(col+7)));
	                				askresults.add(askresults0);
	                				hssfRow = hssfSheet.getRow(++rowNum);
	                				}
	                			}
	                			askpage0.setAskresults(askresults);
	                			rowNum--;
	                		}else
	                //question
	                			if(getValue(xh).equals("题目")){
	                				hssfRow = hssfSheet.getRow(++rowNum);
	                				askquestin0=new AskQuestion(getValue(hssfRow.getCell(col+7)), getValue(hssfRow.getCell(col)));
	                				askitems=new ArrayList<AskItems>();
	                				askquestin.add(askquestin0);
	                			}else
	                   //items
	                				if(getValue(xh).equals("选项")){
	                					hssfRow = hssfSheet.getRow(++rowNum);
	    	                			while(hssfRow!=null&&!getValue(hssfRow.getCell(col)).equals("题目")&&!getValue(hssfRow.getCell(col)).equals("")){
	    	                				if(getValue(hssfRow.getCell(col))!=null&&!getValue(hssfRow.getCell(col)).equals("")){
	    	                				askitems0=new AskItems(getValue(hssfRow.getCell(col)), getValue(hssfRow.getCell(col+1)), getValue(hssfRow.getCell(col+7)));
	    	                				askitems.add(askitems0);
	    	                				hssfRow = hssfSheet.getRow(++rowNum);
	    	                				}
	    	                				
	    	                			}
	    	                			askquestin0.setAskitems(askitems);
	    	                				rowNum--;
	                				}
	                
	            }
	             if(numSheet==0){
	            	 Variable.asksuit=asksuit;
	            	 if(Variable.asksuit!=null)
	            	 System.out.println(Variable.asksuit.getTitle());
	            	 Variable.array_askpage=askpage;
	            	 
	             }
	             
	        }
	        
	    }
	 
	    /**
	     * 得到Excel表中的值
	     * 
	     * @param hssfCell
	     *            Excel中的每一个格子
	     * @return Excel中每一个格子中的值
	     */
	    @SuppressWarnings("static-access")
	    private static String getValue(Cell hssfCell) {
	    	if(hssfCell==null)return "";
	        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
	            // 返回布尔类型的值
	            return String.valueOf(hssfCell.getBooleanCellValue());
	        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
	            // 返回数值类型的值
	        	if(hssfCell.toString().indexOf('-')>0){
	        		DateFormat df=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	        		return df.format(hssfCell.getDateCellValue());
	        	}
	        	else{
	        	DecimalFormat df = new DecimalFormat("0"); 
	        	return  df.format(hssfCell.getNumericCellValue()); 
	        	}
//	            return String.valueOf(hssfCell.getNumericCellValue());
	        } else
	        
	        {
	            // 返回字符串类型的值
	            return String.valueOf(hssfCell.getStringCellValue());
	        }
	    }
}
