package service;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Part;
import repository.FileRepository;

public class FileService {
	public boolean saveFile(String imgURL, Part inputPart) {
		String inputFileName = getFileName(inputPart);
		String imgURI = getFilePath(imgURL);
		
		try {
			inputPart.write(imgURL + imgURI);
			System.out.println("FileService >> saveFile() >> success save file |inputName="+inputFileName+"|url="+imgURL+"|uri="+imgURI);
		} catch (IOException e) {
			System.out.println("FileService >> saveFile() >> [ERROR] : save fail !!");
			e.printStackTrace();
			return false;
		}
		
		new FileRepository().insertImage(imgURI);
		
		return true;
	}
	
	public boolean removeImg(String imgURL, String imgURI) {
		try {
			File oldImg = new File(imgURI);
			oldImg.delete();
		}catch(Exception e) {
			System.out.println("FileService >> removeImg() fail uri:"+imgURI);
			e.printStackTrace();
			return false;
		}
		System.out.println("FileService >> removeImg() success uri:"+imgURI);
		return true;
		
		new FileRepository().removeImage();
	}
	
	private String getFilePath(String imgURL) {
		String imgURIFormat = "board%d.png";
		for(int i = 0; i < 100; i++) {
			String imgURI = String.format(imgURIFormat, i);
			File file = new File(imgURL + imgURI);
			if(!file.exists()) {
				return imgURI;
			}
		}
		System.out.println("FileService >> saveFile()>>getFilePath() >> [EROOR] is already exists 100 same name files");
		return null;
	}
	private String getFileName(Part part) {
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename"))
	            return content.substring(content.indexOf("=") + 2, content.length() - 1);
	        }
	    return null;
	}
}