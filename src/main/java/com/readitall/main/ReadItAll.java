package com.readitall.main;

import java.io.File;

import com.readitall.constants.ReaderConstants;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ReadItAll {

	final static String currentDir = System.getProperty("user.dir");
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
}
