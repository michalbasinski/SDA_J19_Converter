package pl.sda;

import pl.sda.converter.FileConverter;

public class App {
    public static void main(String[] args) {
        FileConverter converter = new FileConverter();
        String sourceFilePath = "/home/michal/SDA/Java19/Projekty/converter/src/main/resources/testOut.xlsx";
        String outputFilePath = "/home/michal/SDA/Java19/Projekty/converter/src/main/resources/testOut2.json";
        converter.convert(sourceFilePath, outputFilePath);
    }
}
