package pl.gkuczera.files_counter;

import java.io.File;

import pl.gkuczera.files_counter.core.FilesCounter;

public class Application {
	
	public static void main(String[] args) {
		
		if (args.length < 1) {
			System.out.println("The path was not provided");
			return;
		}
		
		String providedDirectory = args[0];
		
		if (providedDirectory == null || providedDirectory.isEmpty()) {
			System.out.println("The provided path is empty or null");
			return;			
		}
		
		
		File directory = new File(providedDirectory); 
		
		if (!directory.isDirectory()) {
			System.out.format("The provided path is not a directory (%s)", directory.getAbsolutePath());
			return;						
		}
		
		long startTime = System.currentTimeMillis();
		
		FilesCounter counter = new FilesCounter(directory);
		counter.setLogging(true);
		counter.count();
		
		long endTime = System.currentTimeMillis();
		
		long executionTime = endTime - startTime;
		
		System.out.format("Execution time: %s (in seconds) %n", executionTime / 1000d);
	}
	
}
