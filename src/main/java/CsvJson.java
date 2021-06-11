import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class CsvJson {
    public static void csvJson(String file, String path) throws IOException {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        List<Employee> list = parseCSV(columnMapping, file);
        String json = listToJson(list);
        writeString(json, path);
    }

    public static List<Employee> parseCSV(String[] columnMapping, String filename) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(filename));
        ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(Employee.class);
        strategy.setColumnMapping(columnMapping);
        CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(reader)
                .withMappingStrategy(strategy)
                .build();
        return csv.parse();
    }

    public static String listToJson(List<Employee> list) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();
        return gson.toJson(list, listType);
    }

    public static void writeString(String json, String path) throws IOException {
        File file = new File(path);
        FileWriter toNewJson = new FileWriter(file, false);
        toNewJson.write(json);
        toNewJson.flush();
        toNewJson.close();
    }

}
