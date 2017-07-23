package com.unit.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import com.unit.model.ReMethodObj;


public class FtpUnit {
	private  FTPClient ftp=new FTPClient();

	public FTPClient getFtp() {
		return ftp;
	}

	public void setFtp(FTPClient ftp) {
		this.ftp = ftp;
	}

	public ReMethodObj login(String host,String username,String password,int port){
		try{
		ftp.connect(host,port);
	    ftp.login(username, password);
		}catch(Exception ex){
		return new ReMethodObj(-1, "����ʧ�ܣ�"+ex.getMessage());
		}
		return new ReMethodObj(1,this);
	}
	
	public ReMethodObj login(String host,String username,String password,int port,String path){
		if(path!=null && !"".equals(path)){
			try {
				ftp.changeWorkingDirectory(path);
			} catch (IOException e) {
				return new ReMethodObj(-1, "����/�����쳣��"+e.getMessage());
			}
		}
		login(host,username,password,port);
		return new ReMethodObj(1,this);
	}
	
	/**
	 * ftp������·�����磺a/b.txt 
	 * @return 1�ɹ� -1ʧ��
	 */
	public ReMethodObj downFile(String parFileName,String remoteFilePath,File locFile){
		try {
			ftp.retrieveFile(parFileName+File.separator+remoteFilePath, new FileOutputStream(locFile));
		} catch (FileNotFoundException e) {
			return new ReMethodObj(-1, "�ļ�δ�ҵ���"+e.getMessage());
		} catch (IOException e) {
			return new ReMethodObj(-1, "����/�����쳣��"+e.getMessage());
		}
		return new ReMethodObj(1,this);
	}
	
	/**
	 * ftp������·�����磺a/b.txt 
	 * @return 1�ɹ� -1ʧ��
	 */
	public ReMethodObj upFile(String parFileName,String remoteFilePath,File locFile){
		try {
			ftp.storeFile(parFileName+File.separator+remoteFilePath, new FileInputStream(locFile));
		} catch (FileNotFoundException e) {
			return new ReMethodObj(-1, "�ļ�δ�ҵ���"+e.getMessage());
		} catch (IOException e) {
			return new ReMethodObj(-1, "����/�����쳣��"+e.getMessage());
		}
		return new ReMethodObj(1,this);
	}


	public static void main(String[] args) {
		FtpUnit ftpunit=new FtpUnit();
		//TODO ��ftp�˺��ٲ���
		//ftpunit.loginLocPas(host, username, password, 10021);
		//ftpunit.downFile(parFileName, remoteFilePath, locFile);
	}
	
}
