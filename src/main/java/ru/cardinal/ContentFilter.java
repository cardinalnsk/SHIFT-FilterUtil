package ru.cardinal;

import java.util.Arrays;
import java.util.List;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import ru.cardinal.processors.DataProcessor;

public class ContentFilter {

  @Option(name = "-o", usage = "output path")
  private String outputPath = "";
  @Option(name = "-p", usage = "prefix for output files")
  private String prefix = "";
  @Option(name = "-a", usage = "append to existing files")
  private boolean append = false;
  @Option(name = "-s", usage = "short statistics")
  private boolean shortStat = false;
  @Option(name = "-f", usage = "full statistics")
  private boolean fullStat = false;

  private List<DataProcessor<?>> processors;

  public static void main(String[] args) {
    new ContentFilter().doMain(args);
  }

  private void doMain(String[] args) {
    CmdLineParser parser = new CmdLineParser(this);
    try {
      parser.parseArgument(args);
      processors = Arrays.asList(
          //todo add processors
      );
      processFiles(Arrays.copyOfRange(args, parser.getArguments().size(), args.length));
      printStatistics();
    } catch (CmdLineException e) {
      System.err.println(e.getMessage());
      parser.printUsage(System.err);
      System.exit(1);
    }
  }

  private void processFiles(String[] strings) {

  }

  private void printStatistics() {

  }
}
