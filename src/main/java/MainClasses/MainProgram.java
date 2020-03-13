package MainClasses;

import SplitClasses.SplitTextFile;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.io.IOException;

public class MainProgram{
    public static void main(String[] args) {
        SplitTextFile splitter = new SplitTextFile();
        CmdLineParser parser = new CmdLineParser(splitter);
        try {
            parser.parseArgument(args);
            splitter.execute();
        } catch (CmdLineException | IOException e) {
            e.printStackTrace();
        }
    }
}