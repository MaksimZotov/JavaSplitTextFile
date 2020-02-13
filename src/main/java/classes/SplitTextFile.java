package classes;

import java.io.*;

public class SplitTextFile {
    private String inputName;
    private String outputName;
    private int sizeInLines;
    private int sizeInSymbols;
    private int amountOfOutputFiles;
    private boolean namesWithDigits;
    private boolean flagForOutputName;
    private enum sizeIn { LINES, SYMBOLS, AMOUNT_OF_FILES, DEFAULT }
    sizeIn sizeInWhat;


    public void setState(String input) {
        if (!input.matches("split *(\\[.*\\])+ *[a-zA-z/_0-9.]+"))
            throw new IllegalArgumentException("Некорректный ввод");
        flagForOutputName = false;
        sizeInWhat = sizeIn.DEFAULT;
        String[] arrayStr = input.split("( *\\[)|(\\] *\\[)|(\\] *)");
        for (int i = 1; i < arrayStr.length - 1; i++) {
            if (arrayStr[i].matches("-d"))
                namesWithDigits = true;
            else if (arrayStr[i].matches("-o [a-zA-Z-]+")) {
                if (flagForOutputName)
                    throw new IllegalArgumentException("Выходное имя дожно быть одно");
                flagForOutputName = true;
                String[] temp = arrayStr[i].split("-o ");
                outputName = temp[1];
            }
            else if (arrayStr[i].matches("-l [1-9][0-9]*")) {
                if (sizeInWhat != sizeIn.DEFAULT)
                    throw new IllegalArgumentException("Введено больше одного флага для размера");
                sizeInWhat = sizeIn.LINES;
                String[] temp = arrayStr[i].split("-l ");
                sizeInLines = Integer.parseInt(temp[1]);
            }
            else if (arrayStr[i].matches("-c [1-9][0-9]*")) {
                if (sizeInWhat != sizeIn.DEFAULT)
                    throw new IllegalArgumentException("Введено больше одного флага для размера");
                sizeInWhat = sizeIn.SYMBOLS;
                String[] temp = arrayStr[i].split("-c ");
                sizeInSymbols = Integer.parseInt(temp[1]);
            }
            else if (arrayStr[i].matches("-n [1-9][0-9]*")) {
                if (sizeInWhat != sizeIn.DEFAULT)
                    throw new IllegalArgumentException("Введено больше одного флага для размера");
                sizeInWhat = sizeIn.AMOUNT_OF_FILES;
                String[] temp = arrayStr[i].split("-n ");
                amountOfOutputFiles = Integer.parseInt(temp[1]);
            }
            else
                throw new IllegalArgumentException("Некорректный ввод");
        }
        inputName = arrayStr[arrayStr.length - 1];
    }

    public void doCommands() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputName));
            BufferedWriter writer;
            if (!flagForOutputName)
                outputName = "x";
            else if (outputName.equals("-"))
                outputName = inputName;
            if (sizeInWhat == sizeIn.LINES) {
                int countLines = sizeInLines;
                String curOutputName = (namesWithDigits) ? outputName + "1" : outputName + "aa";
                writer = new BufferedWriter(new FileWriter(curOutputName));
                String s;
                int numberOutputFile = 1;
                while ((s = reader.readLine()) != null) {
                    writer.write(s + "\n");
                    countLines--;
                    if (countLines == 0) {
                        writer.close();
                        numberOutputFile++;
                        curOutputName = (namesWithDigits) ? outputName + numberOutputFile : getOutputNameWithChar(outputName, numberOutputFile);
                        writer = new BufferedWriter(new FileWriter(curOutputName));
                        countLines = sizeInLines;
                    }
                }
                writer.close();
            }
            else if (sizeInWhat == sizeIn.SYMBOLS) {
                int countSymbols = sizeInSymbols;
                String curOutputName = (namesWithDigits) ? outputName + "1" : outputName + "aa";
                writer = new BufferedWriter(new FileWriter(curOutputName));
                int c;
                int numberOutputFile = 1;
                while ((c = reader.read()) != -1) {
                    writer.write(c);
                    countSymbols--;
                    if (countSymbols == 0) {
                        writer.close();
                        numberOutputFile++;
                        curOutputName = (namesWithDigits) ? outputName + numberOutputFile : getOutputNameWithChar(outputName, numberOutputFile);
                        writer = new BufferedWriter(new FileWriter(curOutputName));
                        countSymbols = sizeInSymbols;
                    }
                }
                writer.close();
            }
            else if (sizeInWhat == sizeIn.AMOUNT_OF_FILES) {
                int amountOfLines = 0;
                String s;
                while ((s = reader.readLine()) != null)
                    amountOfLines++;
                int sizeInLines = (int) Math.ceil(((double) amountOfLines) / amountOfOutputFiles);
                int countLines = sizeInLines;
                reader.close();
                reader = new BufferedReader(new FileReader(inputName));
                String curOutputName = (namesWithDigits) ? outputName + "1" : outputName + "aa";
                writer = new BufferedWriter(new FileWriter(curOutputName));
                int numberOutputFile = 1;
                while ((s = reader.readLine()) != null) {
                    writer.write(s + "\n");
                    countLines--;
                    if (countLines == 0) {
                        writer.close();
                        numberOutputFile++;
                        curOutputName = (namesWithDigits) ? outputName + numberOutputFile : getOutputNameWithChar(outputName, numberOutputFile);
                        writer = new BufferedWriter(new FileWriter(curOutputName));
                        countLines = sizeInLines;
                    }
                }
                writer.close();
            }
            reader.close();
        }
        catch (IOException e) {
            e.fillInStackTrace();
        }
    }
    private String getOutputNameWithChar(String outputName, int numberOfFile) {
        if (numberOfFile < 27)
            outputName += "a" + (char) ((int) 'a' + numberOfFile - 1);
        else {
            outputName += (char) ((int) 'a' + (numberOfFile - 1) / 26);
            outputName += (char) ((int) 'a' + (numberOfFile - 1) % 26);
        }
        return outputName;
    }

    public static void main(String[] args) {
        int numberOfFile = 27;
        String outputName = "x";
        char t1 = (char) ((int) 'a' + (numberOfFile - 1) / 26);
        char t2 = (char) ((int) 'a' + (numberOfFile - 1) % 26);
        outputName += (char) ((int) 'a' + (numberOfFile - 1) / 26);
        outputName += (char) ((int) 'a' + (numberOfFile - 1) % 26);
        SplitTextFile text = new SplitTextFile();
        text.setState("split [-c 25] textFiles/align_in1.txt");
        text.doCommands();
    }
}
