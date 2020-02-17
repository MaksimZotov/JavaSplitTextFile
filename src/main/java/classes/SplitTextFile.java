package classes;

import java.io.*;

public class SplitTextFile {
    private int size, numberOutputFile, previousNumberOutputFile, count;
    private boolean namesWithDigits, flagForOutputName;
    private enum SizeIn { LINES, SYMBOLS, AMOUNT_OF_FILES, DEFAULT }
    private SizeIn sizeInWhat = SizeIn.DEFAULT;
    private String outputName, curOutputName;
    private BufferedReader reader;
    private BufferedWriter writer;

    private String getOutputNameWithCharAtTheEnd() {
        String temp = outputName;
        if (numberOutputFile < 27) temp += "a" + (char) ((int) 'a' + numberOutputFile - 1);
        else temp += (char) ((int) 'a' + (numberOutputFile - 1) / 26) + "" +  (char) ((int) 'a' + (numberOutputFile - 1) % 26);
        return temp;
    }

    private void setSize(SizeIn sizeIn, String str, String regex) {
        if (sizeInWhat != SizeIn.DEFAULT) throw new IllegalArgumentException("Введено больше одного флага для размера");
        sizeInWhat = sizeIn;
        size = Integer.parseInt(str.split(regex)[1]);
    }

    private void writeToFile(String whatWrite) throws IOException {
        if (previousNumberOutputFile != numberOutputFile) {
            previousNumberOutputFile = numberOutputFile;
            writer = new BufferedWriter(new FileWriter(curOutputName));
        }
        writer.write(whatWrite);
        count--;
        if (count == 0) {
            writer.close();
            numberOutputFile++;
            curOutputName = (namesWithDigits) ? outputName + numberOutputFile : getOutputNameWithCharAtTheEnd();
            count = size;
        }
    }

    private void createOutputFiles(boolean sizeInSymbols) throws IOException {
        count = size;
        numberOutputFile = 1;
        previousNumberOutputFile = numberOutputFile;
        curOutputName = (namesWithDigits) ? outputName + "1" : outputName + "aa";
        writer = new BufferedWriter(new FileWriter(curOutputName));
        int c;
        String s;
        if (sizeInSymbols) while ((c = reader.read()) != -1) writeToFile("" + c);
        else while ((s = reader.readLine()) != null) writeToFile(s + "\n");
    }

    public void execution(String input) throws IOException {
        if (!input.matches("split *(\\[.*\\])+ *[a-zA-z/_0-9.]+")) throw new IllegalArgumentException("Некорректный ввод");
        String[] arrayStr = input.split("( *\\[)|(\\] *\\[)|(\\] *)");
        for (int i = 1; i < arrayStr.length - 1; i++) {
            if (arrayStr[i].matches("-d")) namesWithDigits = true;
            else if (arrayStr[i].matches("-o [a-zA-Z-]+")) {
                if (flagForOutputName) throw new IllegalArgumentException("Выходное имя дожно быть одно");
                flagForOutputName = true;
                String[] temp = arrayStr[i].split("-o ");
                outputName = temp[1];
            }
            else if (arrayStr[i].matches("-l [1-9][0-9]*")) setSize(SizeIn.LINES, arrayStr[i], "-l ");
            else if (arrayStr[i].matches("-c [1-9][0-9]*")) setSize(SizeIn.SYMBOLS, arrayStr[i], "-c ");
            else if (arrayStr[i].matches("-n [1-9][0-9]*")) setSize(SizeIn.AMOUNT_OF_FILES, arrayStr[i], "-n ");
            else throw new IllegalArgumentException("Некорректный ввод");
        }
        reader = new BufferedReader(new FileReader(arrayStr[arrayStr.length - 1]));
        count = 0;
        if (!flagForOutputName) outputName = "x";
        if (outputName.equals("-")) outputName = arrayStr[arrayStr.length - 1];
        if (sizeInWhat == SizeIn.LINES) createOutputFiles(false);
        else if (sizeInWhat == SizeIn.SYMBOLS) createOutputFiles(true);
        else if (sizeInWhat == SizeIn.AMOUNT_OF_FILES) {
            int amountOfLines = 0;
            while (reader.readLine() != null) amountOfLines++;
            reader.close();
            reader = new BufferedReader(new FileReader(arrayStr[arrayStr.length - 1]));
            size = (int) Math.ceil(((double) amountOfLines) / size);
            createOutputFiles(false);
        }
        writer.close();
        reader.close();
        namesWithDigits = false;
        flagForOutputName = false;
        sizeInWhat = SizeIn.DEFAULT;
    }

    public static void main(String[] args) throws IOException {
        SplitTextFile text = new SplitTextFile();
        text.execution("split [-d] [-n 3] [-o fff] textFiles/align_in1.txt");
    }
}
