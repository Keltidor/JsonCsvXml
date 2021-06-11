import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    public static void jsonParser(String file) throws IOException, ParseException {
        String json = readString(file);
        List<Employee> list = jsonToList(json);
        System.out.println(list);
    }

    public static String readString(String file) throws IOException {
        File f = new File(file);
        BufferedReader buf = new BufferedReader(new FileReader(f));
        return buf.readLine();
    }

    public static List<Employee> jsonToList(String json) throws ParseException {
        List<Employee> list = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(json);
        for (Object o : array) {
            Employee employee = gson.fromJson(String.valueOf(o), Employee.class);
            list.add(employee);
        }
        return list;
    }
}
