package com.einstein.rain.util.dbconstruction;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.einstein.rain.entity.Entity;

public class FindDirectory {

	private static final File FOLDER = new File("F:\\Users\\Jacob\\workspace\\Rain");
	private static final String EXTENSION = ".*\\.pd";

	public List<String> listAllFilesWith(File folder) {

		List<String> paths = new ArrayList<String>();

		for (final File f : folder.listFiles()) {

			if (f.isFile()) {
				if (f.getName().matches(EXTENSION)) {
					paths.add(f.getName());
				}
			}
		}
		return paths;
	}
	public void listAllFiles(final File folder) {
		
		for (final File f : folder.listFiles()) {
			System.out.println(f.getName() + System.getProperty("user.dir"));
		}
	}
	public String getFullDirectory() {
		
		String path = System.getProperty("user.dir");
		return path;
	}
}
