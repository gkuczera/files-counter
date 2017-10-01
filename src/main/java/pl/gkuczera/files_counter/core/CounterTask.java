package pl.gkuczera.files_counter.core;

import java.io.File;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class CounterTask extends RecursiveAction {
	
	private File directory;
	
	private static LongAdder filesCount;
	
	static {
		filesCount = new LongAdder();
	}
	
	public CounterTask(File directory) {
		this.directory = directory;
	}
	
	public static long getFilesCount() {
		return filesCount.sum();
	}

	@Override
	protected void compute() {
		long currentDirectoryFilesCount = 0L;
		
		try {
			File[] files = directory.listFiles();							
			
			for (File file : files) {
				if (file.isDirectory()) {
					invokeAll(new CounterTask(file));		
				} else {					
					++currentDirectoryFilesCount;
				}
			}					
		} catch (Exception exception) {
			System.out.format("The files inside the %s directory could not be counted (%s)%n", directory.getAbsolutePath(), exception.getMessage());
		}
		
		filesCount.add(currentDirectoryFilesCount);
		
		//System.out.format("Thread is finishing... Files count: %s%n", filesCount.get());		
	}

}
