import classes.SplitTextFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainProgram {
    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        SplitTextFile splitter = new SplitTextFile();
        while (true) splitter.execution(reader.readLine());
    }
}
