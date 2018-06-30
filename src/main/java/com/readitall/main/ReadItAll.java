package com.readitall.main;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import com.readitall.constants.ReaderConstants;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ReadItAll {
	
	private static final String subTitlesFolderPrefix = "C://moviecropper//tempText//";

	public static void main(String[] args) {
		File imageFile = new File("resource/samples/text.bmp");
		Tesseract instance = new Tesseract();
		instance.setDatapath("resource/tessdata");

		try {
			String result = instance.doOCR(imageFile);
			System.out.println(result);
		} catch (TesseractException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static boolean getSubtitlesFromPng (String folderPath) {
		
		File[] listOfFiles = getGrabbedFileList(folderPath);
		
		List<ReadedText> subtitleList = getTextList(listOfFiles);
		
		return createSubtitleFile(subtitleList);
	}

	private static List<ReadedText> getTextList(File[] listOfFiles) {
		List<ReadedText> resultList = new ArrayList<>();
		for (File file : listOfFiles) {
			ReadedText readedText = new ReadedText();
			readedText.setText(readSubtitlesFromPng(file));
			readedText.setFrameNumber(file.getName().substring(file.getName().indexOf("_")));
//			readedText.setTime(time); -> This should implement like framenumber / frame of movie
			if (!readedText.getText().isEmpty()) {
				resultList.add(readedText);	
			}
			
		}
		return resultList;
	}

	private static File[] getGrabbedFileList(String folderPath) {
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.contains(".png");
			}
		});
		return listOfFiles;
	}
	
	private static String readSubtitlesFromPng(File imageFile) {
		Tesseract instance = new Tesseract();
		instance.setDatapath("resource/tessdata");
		String result = "";
		try {
		    result = instance.doOCR(imageFile);
			System.out.println(result);
		} catch (TesseractException e) {
			System.err.println(e.getMessage());
		}
		
		return result;
	}
	
	private static boolean createSubtitleFile (List<ReadedText> subtitleList) {
		boolean completed = true;
		
		//Should be implemented...
		
		return completed;
	}

	public static class ReadedText {
		private String text;
		private String frameNumber;
		private String time;
		
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getFrameNumber() {
			return frameNumber;
		}
		public void setFrameNumber(String frameNumber) {
			this.frameNumber = frameNumber;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
	}
}
