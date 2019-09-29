package pl.sda.converter;

import java.util.List;
import java.util.Map;

public interface SDAFileWriter {
    void write(List<Map<String, Object>> records, String outputFilePath);
}
