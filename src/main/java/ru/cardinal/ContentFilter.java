package ru.cardinal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.ParserProperties;
import ru.cardinal.filter_processors.DataProcessor;
import ru.cardinal.filter_processors.DoubleProcessor;
import ru.cardinal.filter_processors.IntegerProcessor;
import ru.cardinal.filter_processors.StringProcessor;

public class ContentFilter {

  @Argument(usage = "Input files to process", required = true, metaVar = "input files [in1.txt in2.txt]")
  private List<String> inputFiles = new ArrayList<>();
  @Option(name = "-o", aliases = "--output", usage = "Указывает путь к выходному файлу", metaVar = "[path]")
  private String outputPath = "";
  @Option(name = "-p", aliases = "--prefix", usage = "Префикс для выходных файлов", metaVar = "[filename prefix_]")
  private String prefix = "";
  @Option(name = "-a", aliases = "--append", usage = "Добавляет данные в существующие файлы")
  private boolean append;
  @Option(name = "-s", aliases = "--short", usage = "Выводит краткую статистику")
  private boolean shortStat;
  @Option(name = "-f", aliases = "--full", usage = "Выводит полную статистику")
  private boolean fullStat;
  @Option(name = "-h", aliases = "--help", usage = "Справка", help = true)
  private boolean help;

  private List<DataProcessor<?>> processors;

  public static void main(String[] args) {
    new ContentFilter().doMain(args);
  }

  private void doMain(String[] args) {
    ParserProperties properties = ParserProperties.defaults();
    properties.withOptionSorter(null);
    properties.withShowDefaults(false);
    CmdLineParser parser = new CmdLineParser(this, properties);
    try {
      parser.parseArgument(args);
      if (help) {
        parser.printUsage(System.out);
        return;
      }

      processors = Arrays.asList(
          new IntegerProcessor(outputPath, prefix, append, shortStat, fullStat),
          new DoubleProcessor(outputPath, prefix, append, shortStat, fullStat),
          new StringProcessor(outputPath, prefix, append, shortStat, fullStat)

      );
      processFiles(inputFiles);
      showStatistics();
    } catch (CmdLineException e) {
      System.err.println(e.getMessage());
      parser.printUsage(System.err);
      System.exit(1);
    }
  }


  /**
   * Обрабатывает список файлов.
   * Для каждого файла открывается поток чтения, и для каждой строки в файле
   * вызывается метод {@link DataProcessor#processLine(String)} для каждого обработчика,
   * зарегистрированного в списке {@link #processors}.
   *
   * @param fileNames Список имен файлов для обработки.
   */
  private void processFiles(List<String> fileNames) {
    for (String fileName : fileNames) {
      try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = br.readLine()) != null) {
          for (DataProcessor<?> processor : processors) {
            processor.processLine(line);
          }
        }
      } catch (IOException e) {
        System.err.println("Error reading file: " + fileName);
      }
    }
  }

  private void showStatistics() {
    String stat = fullStat ? "Полная статистика" : "Краткая статистика";
    System.out.println(stat);
    for (DataProcessor<?> processor : processors) {
      processor.printStatistics();
    }
  }
}
