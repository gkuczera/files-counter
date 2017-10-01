package pl.gkuczera.files_counter.core;

import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class MultiThreadedFilesCounter extends FilesCounter {

	public MultiThreadedFilesCounter(String rootDirectoryPath) {		
		super(rootDirectoryPath);
	}
	
	public MultiThreadedFilesCounter(File rootDirectory) {				
		super(rootDirectory);
	}	
	
	public void count() {
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(new CounterTask(rootDirectory));
				
		System.out.format("Files number in the %s directory: %s%n", rootDirectory.getAbsolutePath(), CounterTask.getFilesCount());		
	}
}
