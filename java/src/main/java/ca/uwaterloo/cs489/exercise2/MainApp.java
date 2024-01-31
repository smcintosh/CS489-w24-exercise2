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
        File f = entry.toFile();
        Job job = new Job(f);
        logger.info(String.format("Job %d yields %d", job.getInput(), job.processJob()));
        if (f.delete()) {
          logger.info("Deleted the file: " + f.getName());
        } else {
          logger.info("Failed to delete the file.");
        }
      }

      File dir_file = dir.toFile();
      if (dir_file.delete()) {
        logger.info("Deleted the directory: " + dir_file.getName());
      } else {
        logger.info("Failed to delete the directory.");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
