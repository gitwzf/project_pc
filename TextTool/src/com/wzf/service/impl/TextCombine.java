package com.wzf.service.impl;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
//公共包和接口
import org.apache.commons.lang3.StringUtils;

import tool.text.interf.ToolTextInterface;
/**
 * 合并同一个文件夹的文本文件CombineText
 * 功能描述：
 * @author wangzf
 * 2017年3月11日 下午1:41:54
 */
public class TextCombine implements ToolTextInterface{
	public class CombineTextParam{
		/**
		 * 待处理文件夹
		 */
		public File file;
		/**
		 * 后缀格式
		 */
		public String[] suffix;
		/**
		 * 输出目录(默认file同级目录)
		 */
		public File destPath;

	}
	/**
	 * 分割符定义
	 */
	private static String split_group=";";
	private static String split_suffix=",";

    /**
     * 匹配格式：
     *        执行文件夹目录;后缀列;输出目录(可空)
     *        如 c://test;txt,sql;d://a
     */
    private static String regExp="[^"+split_group+"]+"+split_group+"[a-zA-Z0-9]+("+split_suffix+"[a-zA-Z0-9]+)*("+split_group+"[^"+split_group+"]+)?";

    @Override
	public void doWork(String str){
		CombineTextParam param=toParam(str);
		if(param==null)
			return;
		File[] subFileList=param.file.listFiles();
		String outFileName=param.destPath+File.separator+new SimpleDateFormat("yyyy_MM_dd HH_mm_ss").format(new Date())+"CombineText."+param.suffix[0];
		File outFile=new File(outFileName);
		OutputStream oStream=null;
		try {
			oStream = new FileOutputStream(outFile);
		} catch(FileNotFoundException e1) {
			e1.printStackTrace();
		}
		int k=0;
		byte[] subByte;
		for(File subFile: subFileList){
			if(!isSuffixMatch(subFile.getName(),param.suffix))
				continue;
			try {
				subByte=getFileBuffer(subFile);
				oStream.write(subByte);
				k++;
				System.out.println(subFile.getName()+" 输出字节："+subByte.length);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("共合并"+k+"个文件  输出文件："+outFileName);
	}

	/**
	 * 输入转换为处理字段
	 * @param str
	 * @return null表示不符合格式
	 */
	private CombineTextParam toParam(String str){
		if(StringUtils.isBlank(str)
				|| !str.matches(regExp))
			return null;
		String[] params=str.split(split_group);
		CombineTextParam param=new CombineTextParam();
		param.file=new File(params[0]);
		if(!param.file.exists() || !param.file.isDirectory())
			return null;

		param.suffix=params[1].split(split_suffix);
		if(params.length==3){
			param.destPath=new File(params[2]);
			if(!param.destPath.exists()){
				param.destPath.mkdirs();
			}
		}else{
			param.destPath=param.file;
		}
		return param;
	}

	/**
	 * 判断后缀是否符合
	 */
	private boolean isSuffixMatch(String fileName,String[] suffixList){
		if(fileName.lastIndexOf('.')+1>fileName.length())
			return false;
		String suffix=fileName.substring(fileName.lastIndexOf('.')+1);

		for(String suf:suffixList){
			if(suffix.equals(suf))
				return true;
		}
		return false;
	}

	/**
	 * 获取文件输出数据流
	 */
	private byte[] getFileBuffer(File file){
		ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
		InputStream inputStream=null;
		try {
			inputStream=new FileInputStream(file);
			byte[] bb=new byte[1024];
			int len=0;
			while((len=inputStream.read(bb))>0){
				byteArrayOutputStream.write(bb, 0, len);
			}
			return byteArrayOutputStream.toByteArray();
		} catch(IOException e) {
			e.printStackTrace();
		}finally{
			try {
				inputStream.close();
				byteArrayOutputStream.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		return new byte[0];
	}

	/**
	 * 输出文件
	 * #@return boolean true成功 false失败
	 */
	private void outFile(String fileName,byte[] bytes){
		File file=new File(fileName);
		OutputStream oStream=null;
		try {
			oStream=new FileOutputStream(file);
			oStream.write(bytes);
		} catch(IOException e) {
			e.printStackTrace();
		}finally{
			try {
				oStream.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	public static void main(String[] args) {
		String input="C:\\Users\\WZF\\Desktop\\a;txt,sql";
		new TextCombine().doWork(input);
	}

}
