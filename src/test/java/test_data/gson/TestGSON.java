package test_data.gson;

import com.google.gson.Gson;

public class TestGSON {

    public static void main(String[] args) {

        Gson gson = new Gson();

        //From JSON(String) to Object
        String jsonStr = "{\n" +
                "  \"name\" : \"ABC0\",\n" +
                "  \"age\": 20\n" +
                "}";

        User teo = gson.fromJson(jsonStr,User.class);

        teo.setName("Teo");
        teo.setAge("30");

        System.out.println(teo);


        //From Object to JSON
        String convertedJSON = gson.toJson(teo);
        System.out.println(convertedJSON);
    }

}
