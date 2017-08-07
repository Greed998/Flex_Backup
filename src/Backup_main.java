import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;

public class Backup_main {

	public static void main(String[] args) {
		String user = "bhucsapu";
		int num = 842;
		int nul = 0;
		javaxt.io.Directory input = new javaxt.io.Directory("C:/Users/" + user);
		javaxt.io.Directory output = new javaxt.io.Directory("D:/BHUM" + nul + num);
		String fileFilter = "*.json";
		if (num < 1000) {
			File theDir = new File("D:\\BHUM" + nul + num);
			if (!theDir.exists()) {
				System.out.println("creating directory: " + theDir.getName());
				boolean result = false;
				try {
					theDir.mkdir();
					result = true;
				} catch (SecurityException se) {
					// handle it
				}
				if (result) {
					System.out.println("DIR created");
					
					input.copyTo(output, true); // true to overwrite any existing files
					// Spawn threads
					int numThreads = 4;
					java.util.ArrayList<Thread> threads = new java.util.ArrayList<Thread>();
					for (int i = 0; i < numThreads; i++) {
						Thread thread = new Thread(new FileCopier(input, output));
						threads.add(thread);
						thread.start();
					}

					// Initiate search
					int numFiles = 0;
					java.util.List results = input.getChildren(true, fileFilter, false);
					while (true) {
						Object item;
						synchronized (results) {
							while (results.isEmpty()) {
								try {
									results.wait();
								} catch (InterruptedException e) {
									break;
								}
							}
							item = results.remove(0);
							results.notifyAll();
						}

						if (item != null) {

							if (item instanceof javaxt.io.File) {

								// Add file to the file copier
								javaxt.io.File file = (javaxt.io.File) item;
								FileCopier.add(file);
								numFiles++;
							}
						} else { // item is null. This is our queue that the search is done!
							break;
						}
					}

					// Notify threads that we are done adding files to the queue
					FileCopier.done();
					// Wait for threads to complete
					while (true) {
						try {
							for (Thread thread : threads) {
								thread.join();
							}
							break;
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					threads.clear();

					System.out.println("Copy complete!");

				}
			} else {
				System.out.println("Dir already Created");
			}
		} else {
			File theDir = new File("D:\\BHUM" + num);
			if (!theDir.exists()) {
				System.out.println("creating directory: " + theDir.getName());
				boolean result = false;
				try {
					theDir.mkdir();
					result = true;
				} catch (SecurityException se) {
					// handle it
				}
				if (result) {
					System.out.println("DIR created");
				}
			} else {
				System.out.println("Dir already Created");
			}
		}

		int numThreads = 4;
		java.util.ArrayList<Thread> threads = new java.util.ArrayList<Thread>();
		for (int i = 0; i < numThreads; i++) {
			Thread thread = new Thread(new FileCopier(input, output));
			threads.add(thread);
			thread.start();
		}

		// Initiate search
		int numFiles = 0;
		java.util.List results = input.getChildren(true, fileFilter, false);
		while (true) {
			Object item;
			synchronized (results) {
				while (results.isEmpty()) {
					try {
						results.wait();
					} catch (InterruptedException e) {
						break;
					}
				}
				item = results.remove(0);
				results.notifyAll();
			}

			if (item != null) {

				if (item instanceof javaxt.io.File) {

					// Add file to the file copier
					javaxt.io.File file = (javaxt.io.File) item;
					FileCopier.add(file);
					numFiles++;
				}
			} else { // item is null. This is our queue that the search is done!
				break;
			}
		}

		// Notify threads that we are done adding files to the queue
		FileCopier.done();
		// Wait for threads to complete
		while (true) {
			try {
				for (Thread thread : threads) {
					thread.join();
				}
				break;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		threads.clear();

		System.out.println("Copy complete!");

	}


}
