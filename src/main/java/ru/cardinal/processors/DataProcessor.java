package ru.cardinal.processors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public abstract class DataProcessor<T> {

  protected String outputPath;
  protected String prefix;
  protected boolean append;
  protected boolean shortStat;
  protected boolean fullStat;

  public DataProcessor(String outputPath, String prefix, boolean append, boolean shortStat,
      boolean fullStat) {
    this.outputPath = outputPath;
    this.prefix = prefix;
    this.append = append;
    this.shortStat = shortStat;
    this.fullStat = fullStat;
  }

  public abstract void processLine(String line);

  protected void writeToFile(String fileName, String line) {
    try {
      Files.writeString(Paths.get(outputPath, prefix + fileName), line + "\n",
          append ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
    } catch (IOException e) {
      System.err.println("Error writing to file: " + fileName);
    }
  }
}
