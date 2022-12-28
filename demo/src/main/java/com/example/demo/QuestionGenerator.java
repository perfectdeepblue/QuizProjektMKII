package com.example.demo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import org.jsoup.Jsoup;
@Component
public class QuestionGenerator {

        public QuestionGenerator(){

        }

        public ArrayList<Question> generateQuestions(URL url) throws IOException, ParseException {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();

                int responseCode = conn.getResponseCode();
                System.out.print(responseCode);

                if (responseCode != 200) {
                        throw new RuntimeException("HttpResponseCode: " + responseCode);

                } else {

                        String inline = "";
                        Scanner scanner = new Scanner(url.openStream());

                        while (scanner.hasNext()) {
                                inline += scanner.nextLine();
                        }

                        scanner.close();

                        JSONParser parse = new JSONParser();
                        JSONObject data_obj = (JSONObject) parse.parse(inline);
                        //JSONObject obj = (JSONObject) data_obj.get("results");
                        JSONArray qArr = (JSONArray) data_obj.get("results");
                        ObjectMapper mapper = new ObjectMapper();
                        ArrayList<Question> qArray = new ArrayList<>();
                        for (int i = 0; i < qArr.size(); i++) {
                                Question q = mapper.readValue(qArr.get(i).toString(), Question.class);
                                //Jsoup
                                qArray.add(q);
                        }

                        return qArray;
                }
        }
}
