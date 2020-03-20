package splitClasses;

import java.io.*;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

public class SplitTextFile {
    private int numberOutputFile, previousNumberOutputFile, count;
    private String curOutputName;
    private BufferedReader reader;
    private BufferedWriter writer;
    @Option(name = "-o") private String outputName = "x";
    @Option(name = "-d") private boolean namesWithDigits;
    @Option(name = "-l") private int sizeInLines = -1;
    @Option(name = "-c") private int sizeInSymbols = -1;
    @Option(name = "-n") private int sizeInAmountOfFiles = -1;
    @Argument(required = true) String inputName;

    private String getOutputNameWithCharAtTheEnd() {
        String temp = outputName;
        if (numberOutputFile < 27) temp += "a" + (char) ((int) 'a' + numberOutputFile - 1);
        else temp += (char) ((int) 'a' + (numberOutputFile - 1) / 26) + "" +  (char) ((int) 'a' + (numberOutputFile - 1) % 26);
        return temp;
    }

    private void writeToFile(String whatWrite, int size) throws IOException {
        if (previousNumberOutputFile != numberOutputFile) {
            previousNumberOutputFile = numberOutputFile;
            writer = new BufferedWriter(new FileWriter(curOutputName));
        }
        writer.write(whatWrite);
        if (!whatWrite.equals("\r")) count--;
        if (count == 0) {
            writer.close();
            numberOutputFile++;
            curOutputName = (namesWithDigits) ? outputName + numberOutputFile : getOutputNameWithCharAtTheEnd();
            count = size;
        }
    }

    private void createOutputFiles(boolean sizeInSymbols, int size) throws IOException {
        count = size;
        numberOutputFile = 1;
        previousNumberOutputFile = numberOutputFile;
        curOutputName = (namesWithDigits) ? outputName + "1" : outputName + "aa";
        writer = new BufferedWriter(new FileWriter(curOutputName));
        int c;
        String s;
        if (sizeInSymbols) while ((c = reader.read()) != -1) writeToFile("" + (char) c, size);
        else while ((s = reader.readLine()) != null) writeToFile(s + "\n", size);
    }

    public void execute() throws IOException {
        reader = new BufferedReader(new FileReader(inputName));
        count = 0;
        if (outputName.equals("-")) outputName = inputName;
        if (sizeInLines != -1) createOutputFiles(false, sizeInLines);
        else if (sizeInSymbols != -1) createOutputFiles(true, sizeInSymbols);
        else if (sizeInAmountOfFiles != -1) {
            int amountOfLines = 0;
            while (reader.readLine() != null) amountOfLines++;
            reader.close();
            reader = new BufferedReader(new FileReader(inputName));
            sizeInLines = (int) Math.ceil(((double) amountOfLines) / sizeInAmountOfFiles);
            createOutputFiles(false, sizeInLines);
        }
        else createOutputFiles(false, 100);
        writer.close();
        reader.close();
        namesWithDigits = false;
    }
}
