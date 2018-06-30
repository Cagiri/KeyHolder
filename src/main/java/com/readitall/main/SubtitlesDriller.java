package com.readitall.main;

import java.io.File;

import com.readitall.constants.ReaderConstants;

public class SubtitlesDriller {

	private final static String currentDir = System.getProperty("user.dir");
	static {
		String jvmArch = System.getProperty("sun.arch.data.model");
		if (jvmArch.equalsIgnoreCase(ReaderConstants.JVM_ARCH_X64)) {
			System.load(currentDir + "\\dll\\x64\\gsdll64.dll");
			System.load(currentDir + "\\dll\\x64\\liblept168.dll");
			System.load(currentDir + "\\dll\\x64\\libtesseract302.dll");
		} else {
			System.load(currentDir + "\\dll\\x86\\gsdll32.dll");
			System.load(currentDir + "\\dll\\x86\\liblept168.dll");
			System.load(currentDir + "\\dll\\x86\\libtesseract302.dll");
		}
	}
	
	public static void main(String[] args) {
		File movieFile = new File(args[1]);
		String createdFilesFolder = MovieCropper.cropMoviesToJpg(movieFile);
		if (!createdFilesFolder.isEmpty()) {
			ReadItAll.getSubtitlesFromPng(createdFilesFolder);	
		}
	}
}
