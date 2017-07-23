package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;


public class FtpMain {
	private static FTPClient ftp =new FTPClient();
	public static void main(String[] args) throws Exception {
		ftp.setDefaultPort(10021);
        ftp.connect("ftp.sinas3.com");
        FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        conf.setServerLanguageCode("zh_CN");
        ftp.configure(conf);
        ftp.login("13jyymn2kw", "3j2myl3y345mmkww53yy045h53zziiw3j25z235m");
//		login();
	upload("D:\\a.txt","xml/b.xml");
	}
	
	public static void login() throws Exception {
        ftp.setDefaultPort(10021);
        ftp.setControlEncoding("utf-8");  
        FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        conf.setServerLanguageCode("zh_CN");
        ftp.configure(conf);
        ftp.connect("ftp.sinas3.com");
        ftp.enterLocalPassiveMode();
        if (!ftp.login("13jyymn2kw", "3j2myl3y345mmkww53yy045h53zziiw3j25z235m")) {
            throw new Exception("FTP log error");
        }
        ftp.setFileType(ftp.BINARY_FILE_TYPE);
        ftp.setBufferSize(1024 * 16);
        ftp.setDefaultTimeout(300000);
        ftp.setDataTimeout(300000);
        ftp.changeWorkingDirectory("/");		
	}

	public static boolean upload(String localFilePath, String remoteFilePath) throws IOException {
		boolean state = false;
        File localFile = new File(localFilePath);
        if (!localFile.isFile() || localFile.length() == 0) {
            return state;
        }
        FileInputStream localIn = new FileInputStream(localFile);
        state = upload(localIn, remoteFilePath);
        localIn.close();
        return state;
	}
	
	public static boolean upload(InputStream localIn, String remoteFilePath) throws IOException {
		createDir(new File(remoteFilePath).getParent());
        boolean result = ftp.storeFile(remoteFilePath, localIn);
        return result;
	}
	
	public static void createDir(String dir) throws IOException {
		if (!isDirExist(dir)) {
            ftp.makeDirectory(dir);
        }
	}
	
		public static boolean isDirExist(String dir) {
			try {
	            int retCode = ftp.cwd(dir);
	            return FTPReply.isPositiveCompletion(retCode);
	        } catch (Exception e) {
	            return false;
	        }
		}

	
	
}
