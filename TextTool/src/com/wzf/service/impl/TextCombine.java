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
//�������ͽӿ�
import org.apache.commons.lang3.StringUtils;

import tool.text.interf.ToolTextInterface;
/**
 * �ϲ�ͬһ���ļ��е��ı��ļ�CombineText
 * ����������
 * @author wangzf
 * 2017��3��11�� ����1:41:54
 */
public class TextCombine implements ToolTextInterface{
	public class CombineTextParam{
		/**
		 * �������ļ���
		 */
		public File file;
		/**
		 * ��׺��ʽ
		 */
		public String[] suffix;
		/**
		 * ���Ŀ¼(Ĭ��fileͬ��Ŀ¼)
		 */
		public File destPath;

	}
	/**
	 * �ָ������
	 */
	private static String split_group=";";
	private static String split_suffix=",";

    /**
     * ƥ���ʽ��
     *        ִ���ļ���Ŀ¼;��׺��;���Ŀ¼(�ɿ�)
     *        �� c://test;txt,sql;d://a
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
				System.out.println(subFile.getName()+" ����ֽڣ�"+subByte.length);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("���ϲ�"+k+"���ļ�  ����ļ���"+outFileName);
	}

	/**
	 * ����ת��Ϊ�����ֶ�
	 * @param str
	 * @return null��ʾ�����ϸ�ʽ
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
	 * �жϺ�׺�Ƿ����
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
	 * ��ȡ�ļ����������
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
	 * ����ļ�
	 * #@return boolean true�ɹ� falseʧ��
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
