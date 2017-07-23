package Main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;


public class FtpMain {
	private static FTPClient ftp =new FTPClient();
	public static void main(String[] args) throws Exception {
        ftp.connect("ftp.sinas3.com",10021);
        ftp.enterLocalPassiveMode();
        ftp.login("13jyymn2kw", "3j2myl3y345mmkww53yy045h53zziiw3j25z235m");

       ftp.retrieveFile("xml/keyword.xml", new FileOutputStream("d:/a.txt"));
	}
	
	
}
