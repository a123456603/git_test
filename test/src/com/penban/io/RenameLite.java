package com.penban.io;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

public class RenameLite {

	public static void main(String[] args) {

		renameZip();
		
	}
	
	public static void renamVedio() {
		String path = "D:\\Youku Files\\transcode\\";
		String fielterName = "_1.mp4";
		String subffix = "mp3";
		renameLiteMp3(path, fielterName, subffix);
	}
	
	
	public static void renameZip() {

		String path = "D:\\Youku Files\\transcode\\【压缩】\\";
		String fielterName = "lite.mp3";
		String subffix = "mp3";
		renameLiteMp3(path, fielterName, subffix);
	}

	public static void renameLiteMp3(String path, String fielterName, String subffix) {
		
		
		File fileLite = new File(path);
		MyFileFilter fileFilter = new MyFileFilter(fielterName);
		for (File file : fileLite.listFiles(fileFilter)) {
			if (file.isFile()) {
				String filter = "lite";
				String destpath = file.getPath();
				destpath = destpath.substring(0, destpath.indexOf(filter)) + subffix;
				System.out.println(destpath);
				File dest = new File(destpath);
				file.renameTo(dest);
			}
			if (file.isDirectory()) {
				renameLiteMp3(file.getPath(), fielterName, subffix);
			}
		}
	}

	static class MyFileNameFilter implements FilenameFilter {
		
		String fielterName;
		
		public MyFileNameFilter(String fielterName) {
			this.fielterName = fielterName;
		}

		@Override
		public boolean accept(File dir, String name) {

			return name.indexOf("lite") >= 0 ? true : false;
		}

	}

	static class MyFileFilter implements FileFilter {

		String fielterName;
		
		public MyFileFilter(String fielterName) {
			this.fielterName = fielterName;
		}
		
		@Override
		public boolean accept(File file) {
			return file.getName().endsWith(fielterName);
		}

	}

}
