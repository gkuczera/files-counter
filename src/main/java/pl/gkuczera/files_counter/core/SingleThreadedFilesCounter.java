package pl.gkuczera.files_counter.core;

import java.io.File;

public class SingleThreadedFilesCounter extends FilesCounter {
	
	public SingleThreadedFilesCounter(String rootDirectoryPath) {		
		super(rootDirectoryPath);
	}
	
	public SingleThreadedFilesCounter(File rootDirectory) {				
		super(rootDirectory);
	}	

	public void count() {
		long filesCount = count(rootDirectory);
		System.out.format("Files number in the %s directory: %s%n", rootDirectory.getAbsolutePath(), filesCount);				
	}	
	
	private long count(File directory) {
		long filesCount = 0;
		
		try {
			File[] files = directory.listFiles();					
			
			for (File file : files) {
				if (file.isDirectory()) {
					filesCount += count(file);
				} else {
					++filesCount;
				}
			}					
		} catch (Exception exception) {
			System.out.format("The files inside the %s directory could not be counted (%s)%n", directory.getAbsolutePath(), exception.getMessage());
		}
		
		return filesCount;
	}
}
