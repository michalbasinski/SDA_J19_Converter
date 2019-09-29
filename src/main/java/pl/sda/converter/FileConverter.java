package pl.sda.converter;

import pl.sda.converter.factories.FileReaderFactory;
import pl.sda.converter.factories.FileWriterFactory;

import java.util.List;
import java.util.Map;

public class FileConverter {

    public void convert(String sourceFilePath, String outputFilePath) {
        FileReaderFactory readerFactory = new FileReaderFactory();
        SDAFileReader reader = readerFactory.produce(sourceFilePath);
        List<Map<String, Object>> records = reader.read(sourceFilePath);

        FileWriterFactory writerFactory = new FileWriterFactory();
        SDAFileWriter writer = writerFactory.produce(outputFilePath);
        writer.write(records, outputFilePath);
    }

}
