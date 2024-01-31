package ca.uwaterloo.cs489.exercise2;

import java.io.*;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MainApp {

  private static Path getDirectory() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    System.out.print("Enter the path to the directory: ");
    return Paths.get(br.readLine());
  }

  public static void main(String[] args) {
    final Logger logger = LogManager.getLogger(MainApp.class.getName());

    // Open the dir
    try {
      Path dir = getDirectory();
      DirectoryStream<Path> ds = Files.newDirectoryStream(dir);

      // Iterate over all of the files in the directory, creating a job for each
      for (Path entry : ds) {
        Job job = new Job(entry.toFile());
        logger.info(String.format("Job %d yields %d\n", job.getInput(), job.processJob()));
        // Delete job file right after it's been processed
        job.deleteJobFile();
        logger.info(String.format("Job %d's file with path: %s has been deleted \n", job.getInput(), entry));
      }
      // https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html
      // Delete given directory
      Files.delete(dir);
      logger.info(String.format("Successfully removed directory: %s", dir));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
