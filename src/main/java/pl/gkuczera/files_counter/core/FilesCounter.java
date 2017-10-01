package pl.gkuczera.files_counter.core;

import java.io.File;
import java.util.concurrent.ForkJoinPool;

public abstract class FilesCounter {

	private boolean logging;

	protected File rootDirectory;

	public FilesCounter(String rootDirectoryPath) {
		initialize(rootDirectoryPath);
	}

	public FilesCounter(File rootDirectory) {
		initialize(rootDirectory);
	}
	
	public abstract void count();	

	public boolean isLogging() {
		return logging;
	}

	public void setLogging(boolean logging) {
		this.logging = logging;
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
