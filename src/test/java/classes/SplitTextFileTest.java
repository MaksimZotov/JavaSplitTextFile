package classes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SplitTextFileTest {

    @Test
    public void test1() {
        MainProgram.main("split -d -n 3 -o fff textFiles/align_in1.txt".split(" "));
    }
    /*
    SplitTextFile text = new SplitTextFile();

    String firstStr = "Для написания разных видов программ сейчас применяются разные языки программирования.\n"
            + "Например, в сфере мобильных программ сейчас правят бал языки Swift (мобильные устройства под управлением iOS)\n"
            + "и Java (устройства под управлением Android).\n" + "Системные программы, как правило, пишутся на языках C или {cpp}.\n"
            + "Эти же языки долгое время использовались и для создания встраиваемых программ,\n"
            + "но в последние годы в этой области набирает популярность язык Java.\n";
    String secondStr =  "Для написания web-клиентов часто используется JavaScript, а в простых случаях -- язык разметки страниц HTML.\n"
            + "Web-серверы используют опять-таки Java (в сложных случаях), а также Python и PHP (в более простых).\n"
            + "Наконец, простые desktop-программы сейчас могут быть написаны на самых разных языках,\n"
            + "и выбор во многом зависит от сложности программы, области её использования, предполагаемой операционной системы.\n"
            + "В первую очередь следует назвать языки Java, {cpp}, C#, Python, Visual Basic, Ruby, Swift.\n"
            + "Самым универсальным и одновременно самым распространённым языком программирования\n";
    String thirdStr = "на данный момент следует считать язык Java.\n"
            + "Java в широком смысле -- не только язык, но и платформа для выполнения программ\n"
            + "под самыми разными операционными системами и на разной аппаратуре.\n"
            + "Такая универсальность обеспечивается наличием виртуальной машины Java --\n"
            + "системной программы, интерпретирующей Java байт-код в машинные коды конкретного компьютера или системы.\n"
            + "Java также включает богатейший набор библиотек для разработки.\n";

    @Test
    public void test1() throws IOException {
        text.execute("split [-d] [-n 3] [-o fff] textFiles/align_in1.txt");
        assertTrue(new String(Files.readAllBytes(Paths.get("fff1"))).equals(firstStr));
        assertTrue(new String(Files.readAllBytes(Paths.get("fff2"))).equals(secondStr));
        assertTrue(new String(Files.readAllBytes(Paths.get("fff3"))).equals(thirdStr));
        new File("fff1").delete();
        new File("fff2").delete();
        new File("fff3").delete();
    }

    @Test
    public void test2() throws IOException {
        text.execute("split [-d] [-n 3] textFiles/align_in1.txt");
        assertTrue(new String(Files.readAllBytes(Paths.get("x1"))).equals(firstStr));
        assertTrue(new String(Files.readAllBytes(Paths.get("x2"))).equals(secondStr));
        assertTrue(new String(Files.readAllBytes(Paths.get("x3"))).equals(thirdStr));
        new File("x1").delete();
        new File("x2").delete();
        new File("x3").delete();
    }

    @Test
    public void test4() throws IOException {
        text.execute("split [-n 3] [-o fff] textFiles/align_in1.txt");
        assertTrue(new String(Files.readAllBytes(Paths.get("fffaa"))).equals(firstStr));
        assertTrue(new String(Files.readAllBytes(Paths.get("fffab"))).equals(secondStr));
        assertTrue(new String(Files.readAllBytes(Paths.get("fffac"))).equals(thirdStr));
        new File("fffaa").delete();
        new File("fffab").delete();
        new File("fffac").delete();
    }

    @Test
    public void test5() throws IOException {
        text.execute("split [-n 3] textFiles/align_in1.txt");
        assertTrue(new String(Files.readAllBytes(Paths.get("xaa"))).equals(firstStr));
        assertTrue(new String(Files.readAllBytes(Paths.get("xab"))).equals(secondStr));
        assertTrue(new String(Files.readAllBytes(Paths.get("xac"))).equals(thirdStr));
        new File("xaa").delete();
        new File("xab").delete();
        new File("xac").delete();
    }

    String firstStr2 = "Для написания разных видов программ сейчас применяются разные языки программирования.\n"
            + "Например, в сфере мобильных программ сейчас правят бал языки Swift (мобильные устройства под управлением iOS)\n"
            + "и Java (устройства под управлением Android).\n"
            + "Системные программы, как правило, пишутся на языках C или {cpp}.\n"
            + "Эти же языки долгое время использовались и для создания встраиваемых программ,\n"
            + "но в последние годы в этой области набирает популярность язык Java.\n"
            + "Для написания web-клиентов часто используется JavaScript, а в простых случаях -- язык разметки страниц HTML.\n";
    String secondStr2 = "Web-серверы используют опять-таки Java (в сложных случаях), а также Python и PHP (в более простых).\n"
            + "Наконец, простые desktop-программы сейчас могут быть написаны на самых разных языках,\n"
            + "и выбор во многом зависит от сложности программы, области её использования, предполагаемой операционной системы.\n"
            + "В первую очередь следует назвать языки Java, {cpp}, C#, Python, Visual Basic, Ruby, Swift.\n"
            + "Самым универсальным и одновременно самым распространённым языком программирования\n"
            + "на данный момент следует считать язык Java.\n"
            + "Java в широком смысле -- не только язык, но и платформа для выполнения программ\n";
    String thirdStr2 = "под самыми разными операционными системами и на разной аппаратуре.\n"
            + "Такая универсальность обеспечивается наличием виртуальной машины Java --\n"
            + "системной программы, интерпретирующей Java байт-код в машинные коды конкретного компьютера или системы.\n"
            + "Java также включает богатейший набор библиотек для разработки.\n";

    @Test
    public void test6() throws IOException {
        text.execute("split [-l 7] [-o -] textFiles/align_in1.txt");
        assertTrue(new String(Files.readAllBytes(Paths.get("textFiles/align_in1.txtaa"))).equals(firstStr2));
        assertTrue(new String(Files.readAllBytes(Paths.get("textFiles/align_in1.txtab"))).equals(secondStr2));
        assertTrue(new String(Files.readAllBytes(Paths.get("textFiles/align_in1.txtac"))).equals(thirdStr2));
        new File("textFiles/align_in1.txtaa").delete();
        new File("textFiles/align_in1.txtab").delete();
        new File("textFiles/align_in1.txtac").delete();
    }

    @Test
    public void test7() throws IOException {
        text.execute("split [-d] [-l 7] [-o fff] textFiles/align_in1.txt");
        assertTrue(new String(Files.readAllBytes(Paths.get("fff1"))).equals(firstStr2));
        assertTrue(new String(Files.readAllBytes(Paths.get("fff2"))).equals(secondStr2));
        assertTrue(new String(Files.readAllBytes(Paths.get("fff3"))).equals(thirdStr2));
        new File("fff1").delete();
        new File("fff2").delete();
        new File("fff3").delete();
    }

    @Test
    public void test8() throws IOException {
        text.execute("split [-d] [-l 7] textFiles/align_in1.txt");
        assertTrue(new String(Files.readAllBytes(Paths.get("x1"))).equals(firstStr2));
        assertTrue(new String(Files.readAllBytes(Paths.get("x2"))).equals(secondStr2));
        assertTrue(new String(Files.readAllBytes(Paths.get("x3"))).equals(thirdStr2));
        new File("x1").delete();
        new File("x2").delete();
        new File("x3").delete();
    }

    @Test
    public void test9() throws IOException {
        text.execute("split [-l 7] [-o fff] textFiles/align_in1.txt");
        assertTrue(new String(Files.readAllBytes(Paths.get("fffaa"))).equals(firstStr2));
        assertTrue(new String(Files.readAllBytes(Paths.get("fffab"))).equals(secondStr2));
        assertTrue(new String(Files.readAllBytes(Paths.get("fffac"))).equals(thirdStr2));
        new File("fffaa").delete();
        new File("fffab").delete();
        new File("fffac").delete();
    }

    @Test
    public void test10() throws IOException {
        text.execute("split [-l 7] textFiles/align_in1.txt");
        assertTrue(new String(Files.readAllBytes(Paths.get("xaa"))).equals(firstStr2));
        assertTrue(new String(Files.readAllBytes(Paths.get("xab"))).equals(secondStr2));
        assertTrue(new String(Files.readAllBytes(Paths.get("xac"))).equals(thirdStr2));
        new File("xaa").delete();
        new File("xab").delete();
        new File("xac").delete();
    }

    String firstStr3 = "Для написания разных видов программ сейчас применяются разные языки программирования.\r\n"
            + "Например, в сфере мобильных программ сейчас правят бал языки Swift (мобильные устройства под управлением iOS)\r\n"
            + "и Java (устройства под управлением Android).\r\n" + "Системные программы, как правило, пишутся на языках C или {cpp}.\r\n"
            + "Эти же языки долгое время использовались и для создания встраиваемых программ,\r\n"
            + "но в после";

    String secondStr3 = "дние годы в этой области набирает популярность язык Java.\r\n"
            + "Для написания web-клиентов часто используется JavaScript, а в простых случаях -- язык разметки страниц HTML.\r\n"
            + "Web-серверы используют опять-таки Java (в сложных случаях), а также Python и PHP (в более простых).\r\n"
            + "Наконец, простые desktop-программы сейчас могут быть написаны на самых разных языках,\r\n"
            + "и выбор во многом зависит от сложности прог";

    String thirdStr3 = "раммы, области её использования, предполагаемой операционной системы.\r\n"
            + "В первую очередь следует назвать языки Java, {cpp}, C#, Python, Visual Basic, Ruby, Swift.\r\n"
            + "Самым универсальным и одновременно самым распространённым языком программирования\r\n"
            + "на данный момент следует считать язык Java.\r\n"
            + "Java в широком смысле -- не только язык, но и платформа для выполнения программ\r\n"
            + "под самыми разными операцион";

    String fourthStr3 = "ными системами и на разной аппаратуре.\r\n"
            + "Такая универсальность обеспечивается наличием виртуальной машины Java --\r\n"
            + "системной программы, интерпретирующей Java байт-код в машинные коды конкретного компьютера или системы.\r\n"
            + "Java также включает богатейший набор библиотек для разработки.";

    @Test
    public void test11() throws IOException {
        text.execute("split [-d] [-c 400] [-o fff] textFiles/align_in1.txt");
        String str = new String(Files.readAllBytes(Paths.get("fff1")));
        assertTrue(new String(Files.readAllBytes(Paths.get("fff1"))).equals(firstStr3));
        assertTrue(new String(Files.readAllBytes(Paths.get("fff2"))).equals(secondStr3));
        assertTrue(new String(Files.readAllBytes(Paths.get("fff3"))).equals(thirdStr3));
        assertTrue(new String(Files.readAllBytes(Paths.get("fff4"))).equals(fourthStr3));
        new File("fff1").delete();
        new File("fff2").delete();
        new File("fff3").delete();
        new File("fff4").delete();
    }

    @Test
    public void test12() throws IOException {
        text.execute("split [-d] [-c 400] textFiles/align_in1.txt");
        assertTrue(new String(Files.readAllBytes(Paths.get("x1"))).equals(firstStr3));
        assertTrue(new String(Files.readAllBytes(Paths.get("x2"))).equals(secondStr3));
        assertTrue(new String(Files.readAllBytes(Paths.get("x3"))).equals(thirdStr3));
        assertTrue(new String(Files.readAllBytes(Paths.get("x4"))).equals(fourthStr3));
        new File("x1").delete();
        new File("x2").delete();
        new File("x3").delete();
        new File("x4").delete();
    }

    @Test
    public void test13() throws IOException {
        text.execute("split [-c 400] [-o fff] textFiles/align_in1.txt");
        assertTrue(new String(Files.readAllBytes(Paths.get("fffaa"))).equals(firstStr3));
        assertTrue(new String(Files.readAllBytes(Paths.get("fffab"))).equals(secondStr3));
        assertTrue(new String(Files.readAllBytes(Paths.get("fffac"))).equals(thirdStr3));
        assertTrue(new String(Files.readAllBytes(Paths.get("fffad"))).equals(fourthStr3));
        new File("fffaa").delete();
        new File("fffab").delete();
        new File("fffac").delete();
        new File("fffad").delete();
    }

    @Test
    public void test14() throws IOException {
        text.execute("split [-c 400] textFiles/align_in1.txt");
        assertTrue(new String(Files.readAllBytes(Paths.get("xaa"))).equals(firstStr3));
        assertTrue(new String(Files.readAllBytes(Paths.get("xab"))).equals(secondStr3));
        assertTrue(new String(Files.readAllBytes(Paths.get("xac"))).equals(thirdStr3));
        assertTrue(new String(Files.readAllBytes(Paths.get("xad"))).equals(fourthStr3));
        new File("xaa").delete();
        new File("xab").delete();
        new File("xac").delete();
        new File("xad").delete();
    }
     */
}