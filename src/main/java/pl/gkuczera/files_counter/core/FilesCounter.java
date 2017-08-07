package pl.gkuczera.files_counter.core;

import java.io.File;

public class FilesCounter {
	
	private boolean logging;
	
	private File rootDirectory;

	public FilesCounter(String rootDirectoryPath) {		
		initialize(rootDirectoryPath);
	}
	
	public FilesCounter(File rootDirectory) {				
		initialize(rootDirectory);
	}	
	
	public boolean isLogging() {
		return logging;
	}

	public void setLogging(boolean logging) {
		this.logging = logging;
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
	
	private void initialize(String rootDirectoryPath) {		
		if (rootDirectoryPath == null || rootDirectoryPath.isEmpty()) {
			throw new IllegalArgumentException("The provided argument is not a valid directory path");
		}
		
		initialize(new File(rootDirectoryPath));
	}		
	
	private void initialize(File rootDirectory) {
		if (rootDirectory == null || !rootDirectory.isDirectory()) {
			throw new IllegalArgumentException("The provided argument is not a valid directory");
		}
		
		this.rootDirectory = rootDirectory;
	}
}
