package ru.cardinal.filter_processors;

public class StringProcessor extends DataProcessor<String> {

  private final String fileName = "strings.txt";
  private int count;
  private int min = Integer.MAX_VALUE;
  private int max;

  public StringProcessor(String outputPath, String prefix, boolean append, boolean shortStat,
      boolean fullStat) {
    super(outputPath, prefix, append, shortStat, fullStat);
  }

  @Override
  public void processLine(String line) {
    if (isLineAsProcessed(line)) {
      return;
    }
    writeToFile(fileName, line);
    count++;
    min = Math.min(min, line.length());
    max = Math.max(max, line.length());
    markLinesAsProcessed(line);
  }

  @Override
  public void printStatistics() {
    boolean isWriteToFile = count != 0;
    String filePath = "." + outputPath + "/" + prefix + fileName;
    String message = """
        %s
        Количество считанных строк: %d
        """;
    System.out.printf(message, isWriteToFile ? filePath : "", count);
    if (fullStat && count != 0) {
      String stat = """
          Длинна минимальной строки: %s
          Длинна максимальной строки: %s
          """;
      System.out.printf(stat, min, max);
    }
  }
}
