import java.io.File;
import org.apache.commons.io.filefilter.DirectoryFileFilter;

public class Backup_main {

	public static void main(String[] args) {
		
		String user = "bhuzbonu";
		int num = 842;
		int nul = 0;
		javaxt.io.Directory input = new javaxt.io.Directory("C:/Users/" + user);
		javaxt.io.Directory output = new javaxt.io.Directory("E:/BHUM" + nul + num);
		String fileFilter = "*.json";
		
		if (num < 1000) {
			
			File theDir = new File("E:\\BHUM" + nul + num);
			
			if (!theDir.exists()) {
				
				System.out.println("Könyvtár Készítés:"  + theDir.getName());
				boolean result = false;
				
				try {
					theDir.mkdir();
					result = true;
					
				} catch (SecurityException se) {

				}
				if (result) {
					input.copyTo(output, true);
					int numThreads = 4;
					java.util.ArrayList<Thread> threads = new java.util.ArrayList<Thread>();
					
					for (int i = 0; i < numThreads; i++) {
						Thread thread = new Thread(new FileCopier(input, output));
						threads.add(thread);
						thread.start();
					}

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

								javaxt.io.File file = (javaxt.io.File) item;
								FileCopier.add(file);
								numFiles++;
							}
						} else { 
							break;
						}
					}

					FileCopier.done();

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

				}
			} else {
				System.out.println("Könyvtár Elkészítve");
			}
		} else {
			File theDir = new File("D:\\BHUM" + num);
			if (!theDir.exists()) {
				System.out.println("Könyvtár Készítés:" + theDir.getName());
				boolean result = false;
				try {
					theDir.mkdir();
					result = true;
				} catch (SecurityException se) {
					// handle it
				}
				if (result) {
					System.out.println("Könyvtár Elkészítve");
				}
			} else {
				System.out.println("A könyvtár már kész");
			}
		}

		int numThreads = 4;
		java.util.ArrayList<Thread> threads = new java.util.ArrayList<Thread>();
		
		for (int i = 0; i < numThreads; i++) {
			Thread thread = new Thread(new FileCopier(input, output));
			threads.add(thread);
			thread.start();
		}

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

					javaxt.io.File file = (javaxt.io.File) item;
					FileCopier.add(file);
					numFiles++;
				}
				
			} else { 
				break;
			}
		}
		
		FileCopier.done();

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


	}


}
