package com.readitall.main;

import java.io.File;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.OpenCVFrameConverter;

public class MovieCropper {

	private final static String fileLoc = "C://moviecropper//grabbedFiles//";

	public static String cropMoviesToJpg(File movFile) {

		FFmpegFrameGrabber grabber = null;
		String filePath="";
		try {
			grabber = FFmpegFrameGrabber.createDefault(movFile);
			grabber.start();

			OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
			int frame_count = grabber.getLengthInFrames();
			for (int i = 0; i < frame_count; i++) {
				Frame grabbedFrame = grabber.grabImage();
				IplImage grabbedImage = converter.convert(grabbedFrame);
				filePath = fileLoc + "//" + movFile.getName() + "//" + movFile.getName() + "_" + grabber.getFrameNumber();
				opencv_core.cvSave(filePath, grabbedImage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (grabber!=null) {
				try {
					grabber.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
			
		}

		return filePath;
	}
}
