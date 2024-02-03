package ru.cardinal.filter_processors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;

public abstract class DataProcessor<T> {

  protected String outputPath;
  protected String prefix;
  protected boolean append;
  protected boolean shortStat;
  protected boolean fullStat;
  protected static final Set<String> processedLines = new HashSet<>();
  private boolean reWrite = true;

  public DataProcessor(String outputPath, String prefix, boolean append, boolean shortStat,
      boolean fullStat) {
    this.outputPath = outputPath;
    this.prefix = prefix;
    this.append = append;
    this.shortStat = shortStat;
    this.fullStat = fullStat;
  }

  public abstract void processLine(String line);

  public abstract void printStatistics();

  /**
   * Записывает строку в файл с указанным именем.
   * Если файл уже существует и параметр append установлен в false,
   * то файл будет перезаписан. В противном случае строка будет добавлена в конец файла.
   *
   * @param fileName Имя файла, в который будет произведена запись.
   * @param line Строка, которая будет записана в файл.
   */
  protected void writeToFile(String fileName, String line) {
    fileName = prefix + fileName;
    Path path = Paths.get(outputPath, fileName);
    try {
      checkAndCreateOutputDirectory();
      if (reWrite && !append) {
        Files.write(path, (line + "\n").getBytes(), StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING);
        reWrite = false;
      } else {
        Files.write(path, (line + "\n").getBytes(), StandardOpenOption.CREATE,
            StandardOpenOption.APPEND);
      }
    } catch (IOException e) {
      System.err.println("Error writing to file: " + fileName);
    }
  }

  protected boolean isLineAsProcessed(String line) {
    return processedLines.contains(line);
  }

  protected void markLinesAsProcessed(String line) {
    processedLines.add(line);
  }

  private void checkAndCreateOutputDirectory() {
    Path path = Paths.get(outputPath);
    if (!outputPath.isEmpty() && Files.notExists(path)) {
      try {
        Files.createDirectory(path);
      } catch (IOException e) {
        System.err.println("Error creating directory: " + outputPath);
      }
    }
  }

}
