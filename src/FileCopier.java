public class FileCopier implements Runnable {
 
      private static java.util.List pool = new java.util.LinkedList();
      private javaxt.io.Directory output;
      private int pathLength = -1;
 
 
      public FileCopier(javaxt.io.Directory input, javaxt.io.Directory output) {
          pathLength = input.getPath().length();
          this.output = output;
      }
 
      public static void add(javaxt.io.File file) {
          synchronized (pool) {
             pool.add(pool.size(), file);
             pool.notify(); //pool.notifyAll();
          }
      }
 
      public static void done() {
          synchronized (pool) {
             pool.add(pool.size(), null);
             pool.notify(); //pool.notifyAll();
          }
      }
 
      public void run() {
 
          while (true) {
 
              javaxt.io.File file = null;
              
              synchronized (pool) {
                  while (pool.isEmpty()) {
                      try {
                          pool.wait();
                      }
                      catch (InterruptedException e) {
                          return;
                      }
                  }
                  
                  file = (javaxt.io.File) pool.get(0);
                  if (file!=null) pool.remove(0);
                  pool.notifyAll();
              }
 
              if (file!=null){
                  String relPath = file.getParentDirectory().getPath().substring(pathLength);
                  javaxt.io.File outputFile = new javaxt.io.File(output + relPath + file.getName());
                  file.copyTo(outputFile, true);
              }
              else{
                  return;
              }
          }
      }
  }