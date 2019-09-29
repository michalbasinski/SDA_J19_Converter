package pl.sda.converter.factories;

import pl.sda.converter.SDAFileWriter;
import pl.sda.converter.csv.CSVWriter;
import pl.sda.converter.json.JsonWriter;

public class FileWriterFactory {
    public SDAFileWriter produce(String filePath) {
        if (filePath.endsWith(".csv")) {
            return new CSVWriter();
        }
        if (filePath.endsWith(".json")) {
            return new JsonWriter();
        }
        return null;
    }
}
