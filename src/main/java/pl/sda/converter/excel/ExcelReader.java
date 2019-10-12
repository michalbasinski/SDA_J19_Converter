package pl.sda.converter.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pl.sda.converter.SDAFileReader;
import pl.sda.converter.exceptions.FileConverterException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ExcelReader implements SDAFileReader {

    @Override
    public List<Map<String, Object>> read(String filePath) {

        List<Map<String, Object>> result = new ArrayList<>();

        try {
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(fileInputStream);

            Sheet sheet = workbook.getSheetAt(0);

            // tworzenie wiersza nagłówkowego - start
            Row headerRow = sheet.getRow(0);
            List<String> headers = new ArrayList<>();
            Iterator<Cell> headerIterator = headerRow.iterator();
            while (headerIterator.hasNext()) {
                Cell cell = headerIterator.next();
                headers.add(cell.getStringCellValue());
            }
            // tworzenie wiersza nagłówkowego - koniec

            // tworzenie wierszy z danymi - start
            Iterator<Row> rowIterator = sheet.rowIterator();

            //ignorowanie pierwszego wiersza
            rowIterator.next();

            while (rowIterator.hasNext()) {
                Map<String, Object> record = new HashMap<>();

                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                int currentColumn = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    CellType cellType = cell.getCellType();

                    Object value = CellType.NUMERIC.equals(cellType) ?
                            cell.getNumericCellValue() : cell.getStringCellValue();

                    String currentColumnName = headers.get(currentColumn);
                    record.put(currentColumnName, value.toString());
                    currentColumn++;
                }
                result.add(record);
            }
            // tworzenie wierszy z danymi - koniec

        } catch (FileNotFoundException e) {
            throw new FileConverterException("Nie znaleziono pliku", e);
        } catch (IOException e) {
            throw new FileConverterException("Błąd podczas przetwarzania pliku", e);
        }

        return result;
    }

}
