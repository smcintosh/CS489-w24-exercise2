package ca.uwaterloo.cs489.exercise2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Job {
  private File file;
  private int input;

  public Job(File file) throws IOException, FileNotFoundException {
    this.file = file;

    BufferedReader br = new BufferedReader(new FileReader(file));
    this.input = Integer.parseInt(br.readLine());
  }

  public int processJob() {
    int rtn = input;

    if (input == 0 || input == 1) {
      return 1;
    }

    for (int i = input-1; i > 1; i--) {
      rtn *= i;
    }

    return rtn;
  }

  public int getInput() { return this.input; }
}
