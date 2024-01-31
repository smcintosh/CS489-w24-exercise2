package ca.uwaterloo.cs489.exercise2;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileNotFoundException;

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
        // getting the file handle directory from the job to ensure it is a regular file
        File jobFile = job.getFile();
        String jobFilePath = jobFile.getPath();
        jobFile.delete();
        logger.info(String.format("Removed job %d from %s\n", job.getInput(), jobFilePath));
      }

      Files.delete(dir);
      logger.info(String.format("Removed job directory: %s\n", dir));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
