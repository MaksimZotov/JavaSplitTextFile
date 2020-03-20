package splitClasses;

import mainClasses.MainProgram;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SplitTextFileTest {

    // The first set of expected results

    private String firstExpectedResultsStr1 = "Для написания разных видов программ сейчас применяются разные языки программирования.\n"
            + "Например, в сфере мобильных программ сейчас правят бал языки Swift (мобильные устройства под управлением iOS)\n"
            + "и Java (устройства под управлением Android).\n"
            + "Системные программы, как правило, пишутся на языках C или {cpp}.\n"
            + "Эти же языки долгое время использовались и для создания встраиваемых программ,\n"
            + "но в последние годы в этой области набирает популярность язык Java.\n";
    private String firstExpectedResultsStr2 =  "Для написания web-клиентов часто используется JavaScript, а в простых случаях -- язык разметки страниц HTML.\n"
            + "Web-серверы используют опять-таки Java (в сложных случаях), а также Python и PHP (в более простых).\n"
            + "Наконец, простые desktop-программы сейчас могут быть написаны на самых разных языках,\n"
            + "и выбор во многом зависит от сложности программы, области её использования, предполагаемой операционной системы.\n"
            + "В первую очередь следует назвать языки Java, {cpp}, C#, Python, Visual Basic, Ruby, Swift.\n"
            + "Самым универсальным и одновременно самым распространённым языком программирования\n";
    private String firstExpectedResultsStr3 = "на данный момент следует считать язык Java.\n"
            + "Java в широком смысле -- не только язык, но и платформа для выполнения программ\n"
            + "под самыми разными операционными системами и на разной аппаратуре.\n"
            + "Такая универсальность обеспечивается наличием виртуальной машины Java --\n"
            + "системной программы, интерпретирующей Java байт-код в машинные коды конкретного компьютера или системы.\n"
            + "Java также включает богатейший набор библиотек для разработки.\n";

    private String[] firstExpectedResults = new String[] { firstExpectedResultsStr1, firstExpectedResultsStr2, firstExpectedResultsStr3 };

    // The second set of expected results

    private String secondExpectedResultsStr1 = "Для написания разных видов программ сейчас применяются разные языки программирования.\n"
            + "Например, в сфере мобильных программ сейчас правят бал языки Swift (мобильные устройства под управлением iOS)\n"
            + "и Java (устройства под управлением Android).\n"
            + "Системные программы, как правило, пишутся на языках C или {cpp}.\n"
            + "Эти же языки долгое время использовались и для создания встраиваемых программ,\n"
            + "но в последние годы в этой области набирает популярность язык Java.\n"
            + "Для написания web-клиентов часто используется JavaScript, а в простых случаях -- язык разметки страниц HTML.\n";
    private String secondExpectedResultsStr2 = "Web-серверы используют опять-таки Java (в сложных случаях), а также Python и PHP (в более простых).\n"
            + "Наконец, простые desktop-программы сейчас могут быть написаны на самых разных языках,\n"
            + "и выбор во многом зависит от сложности программы, области её использования, предполагаемой операционной системы.\n"
            + "В первую очередь следует назвать языки Java, {cpp}, C#, Python, Visual Basic, Ruby, Swift.\n"
            + "Самым универсальным и одновременно самым распространённым языком программирования\n"
            + "на данный момент следует считать язык Java.\n"
            + "Java в широком смысле -- не только язык, но и платформа для выполнения программ\n";
    private String secondExpectedResultsStr3 = "под самыми разными операционными системами и на разной аппаратуре.\n"
            + "Такая универсальность обеспечивается наличием виртуальной машины Java --\n"
            + "системной программы, интерпретирующей Java байт-код в машинные коды конкретного компьютера или системы.\n"
            + "Java также включает богатейший набор библиотек для разработки.\n";

    private String[] secondExpectedResults = new String[] { secondExpectedResultsStr1, secondExpectedResultsStr2, secondExpectedResultsStr3 };

    // The third set of expected results

    private String thirdExpectedResultsStr1 = "Для написания разных видов программ сейчас применяются разные языки программирования.\n"
            + "Например, в сфере мобильных программ сейчас правят бал языки Swift (мобильные устройства под управлением iOS)\n"
            + "и Java (устройства под управлением Android).\n"
            + "Системные программы, как правило, пишутся на языках C или {cpp}.\n"
            + "Эти же языки долгое время использовались и для создания встраиваемых программ,\n"
            + "но в последние ";

    private String thirdExpectedResultsStr2 = "годы в этой области набирает популярность язык Java.\n"
            + "Для написания web-клиентов часто используется JavaScript, а в простых случаях -- язык разметки страниц HTML.\n"
            + "Web-серверы используют опять-таки Java (в сложных случаях), а также Python и PHP (в более простых).\n"
            + "Наконец, простые desktop-программы сейчас могут быть написаны на самых разных языках,\n"
            + "и выбор во многом зависит от сложности программы, об";

    private String thirdExpectedResultsStr3 = "ласти её использования, предполагаемой операционной системы.\n"
            + "В первую очередь следует назвать языки Java, {cpp}, C#, Python, Visual Basic, Ruby, Swift.\n"
            + "Самым универсальным и одновременно самым распространённым языком программирования\n"
            + "на данный момент следует считать язык Java.\n"
            + "Java в широком смысле -- не только язык, но и платформа для выполнения программ\n"
            + "под самыми разными операционными системами";

    private String thirdExpectedResultsStr4 = " и на разной аппаратуре.\n"
            + "Такая универсальность обеспечивается наличием виртуальной машины Java --\n"
            + "системной программы, интерпретирующей Java байт-код в машинные коды конкретного компьютера или системы.\n"
            + "Java также включает богатейший набор библиотек для разработки.";

    private String[] thirdExpectedResults = new String[] { thirdExpectedResultsStr1, thirdExpectedResultsStr2, thirdExpectedResultsStr3, thirdExpectedResultsStr4 };



    private void test(String arguments, String[] pathsToFiles, String[] targets) throws IOException {
        MainProgram.main(arguments.split(" +"));
        if (pathsToFiles.length != targets.length)
            throw new IllegalArgumentException("The length of pathsToFiles must be equal to the length of targets");
        for (int i = 0; i < pathsToFiles.length; i++) {
            assertEquals(new String(Files.readAllBytes(Paths.get(pathsToFiles[i]))).replaceAll("\r", ""), targets[i]);
            new File(pathsToFiles[i]).delete();
        }
    }

    @Test
    void testAll() throws IOException {
        test("-d -n 3 -o fff textFiles/align_in1", new String[] { "fff1", "fff2", "fff3" }, firstExpectedResults);
        test("-d -n 3 textFiles/align_in1", new String[] { "x1", "x2", "x3" }, firstExpectedResults);
        test("-n 3 -o fff textFiles/align_in1", new String[] { "fffaa", "fffab", "fffac" }, firstExpectedResults);
        test("-n 3 textFiles/align_in1", new String[] { "xaa", "xab", "xac" }, firstExpectedResults);
        test("-d -n 3 textFiles/align_in1", new String[] { "x1", "x2", "x3" }, firstExpectedResults);

        test("-l 7 -o - textFiles/align_in1", new String[] {"textFiles/align_in1aa", "textFiles/align_in1ab", "textFiles/align_in1ac"}, secondExpectedResults);
        test("-d -l 7 -o fff textFiles/align_in1", new String[] { "fff1", "fff2", "fff3" }, secondExpectedResults);
        test("-d -l 7 textFiles/align_in1", new String[] { "x1", "x2", "x3" }, secondExpectedResults);
        test("-l 7 -o fff textFiles/align_in1", new String[] { "fffaa", "fffab", "fffac" }, secondExpectedResults);
        test("-l 7 textFiles/align_in1", new String[] { "xaa", "xab", "xac" }, secondExpectedResults);

        test("-d -c 400 -o fff textFiles/align_in1", new String[] { "fff1", "fff2", "fff3", "fff4" }, thirdExpectedResults);
        test("-d -c 400 textFiles/align_in1", new String[] { "x1", "x2", "x3", "x4" }, thirdExpectedResults);
        test("-c 400 -o fff textFiles/align_in1", new String[] { "fffaa", "fffab", "fffac", "fffad" }, thirdExpectedResults);
        test("-c 400 textFiles/align_in1", new String[] { "xaa", "xab", "xac", "xad" }, thirdExpectedResults);
    }
}