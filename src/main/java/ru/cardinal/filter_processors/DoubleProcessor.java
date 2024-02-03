package ru.cardinal.filter_processors;

public class DoubleProcessor extends DataProcessor<Double> {

  private final String fileName = "floats.txt";
  private int count;
  private double min = Double.MAX_VALUE;
  private double max = Double.MIN_VALUE;
  private double sum;

  public DoubleProcessor(String outputPath, String prefix, boolean append, boolean shortStat,
      boolean fullStat) {
    super(outputPath, prefix, append, shortStat, fullStat);
  }

  @Override
  public void processLine(String line) {
    if (isLineAsProcessed(line)) {
      return;
    }
    try {
      double value = Double.parseDouble(line);
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
    String filePath = "." + outputPath + "/" + prefix + fileName;
    String message = """
        %s
        Количество считанных вещественных чисел: %d
        """;
    System.out.printf(message, filePath, count);
    if (fullStat && count != 0) {
      String stat = """
          Минимальное вещественное число: %f
          Максимальное вещественное число: %f
          Сумма вещественных чисел: %f
          Среднее: %f
          """;
      System.out.printf(stat, min, max, sum, sum / count);
    }
  }
}
