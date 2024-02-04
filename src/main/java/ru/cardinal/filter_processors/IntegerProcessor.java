package ru.cardinal.filter_processors;

public class IntegerProcessor extends DataProcessor<Integer> {

  private final String fileName = "integers.txt";
  private int count;
  private long min = Integer.MAX_VALUE;
  private long max = Integer.MIN_VALUE;
  private int sum;

  public IntegerProcessor(String outputPath, String prefix, boolean append, boolean shortStat,
      boolean fullStat) {
    super(outputPath, prefix, append, shortStat, fullStat);
  }

  @Override
  public void processLine(String line) {
    if (isLineAsProcessed(line)) {
      return;
    }
    try {
      long value = Long.parseLong(line);

      writeToFile(fileName, line);
      count++;
      min = Math.min(min, value);
      max = Math.max(max, value);
      sum += value;
      markLinesAsProcessed(line);
    } catch (NumberFormatException ignored) {
    }
  }


  @Override
  public void printStatistics() {
    boolean isWriteToFile = count != 0;
    String filePath = "." + outputPath + "/" + prefix + fileName;
    String message = """
        %s
        Количество считанных целых чисел: %d
        """;
    System.out.printf(message, isWriteToFile ? filePath : "", count);
    if (fullStat && count != 0) {
      String stat = """
          Минимальное целое число: %d
          Максимальное целое число: %d
          Сумма чисел: %d
          Среднее: %d
          """;
      System.out.printf(stat, min, max, sum, sum / count);
    }
  }
}
