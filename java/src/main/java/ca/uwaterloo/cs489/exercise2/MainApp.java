package ca.uwaterloo.cs489.exercise2;

import java.io.BufferedReader;
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

        try {
            Path dir = getDirectory();
            DirectoryStream<Path> ds = Files.newDirectoryStream(dir);

            // Iterate over all of the files in the directory, creating a job for each
            for (Path entry : ds) {
                Job job = new Job(entry.toFile());
                logger.info(String.format("Job %d yields %d", job.getInput(), job.processJob()));

                // Remove the job file immediately after processing
                try {
                    Files.delete(entry);
                    logger.info(String.format("Removed file: %s", entry));
                } catch (IOException e) {
                    logger.error(String.format("Failed to remove file: %s", entry), e);
                }
            }

            // Remove the directory after all jobs have been processed
            try {
                Files.delete(dir);
                logger.info(String.format("Removed directory: %s", dir));
            } catch (IOException e) {
                logger.error(String.format("Failed to remove directory: %s", dir), e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
