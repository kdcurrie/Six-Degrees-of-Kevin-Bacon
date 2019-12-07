import java.io.*;
import java.util.*;
import com.opencsv.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Assignment2 {

    public Assignment2(){
    }

    /**************
     * Read File
     **************/
    public void read(String path, Graph graph){
        try {
            //create csvreader and jsonparser
            FileReader fileReader = new FileReader(path);
            CSVReader csvReader = new CSVReader(fileReader);
            JSONParser jp = new JSONParser();


            //empty string array to store file contents
            String[] s;
            //read each line and put into string
            while((s = csvReader.readNext()) != null){
                try {

                    //creates graph containing actors and links
                    ArrayList<String> actors = new ArrayList<>();
                    JSONArray obj= (JSONArray) jp.parse(s[2]);

                    for(int i = 0;i < obj.size(); i++){
                        JSONObject temp = (JSONObject)obj.get(i);
                        String name = (String)temp.get("name");
                        actors.add(name);
                        for (Object o : obj) {
                            JSONObject tempJ = (JSONObject) o;
                            String nameJ = (String) tempJ.get("name");
                            if (!name.toLowerCase().equals(nameJ.toLowerCase())) {
                                graph.addEgde(name.toLowerCase(), nameJ.toLowerCase());
                            }

                        }

                    }


                }
                catch (Exception e){
                    continue;
                }

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    /*****************
     * Main Function
     *****************/
    public static void main(String[] args) {
        Assignment2 test = new Assignment2();
        Graph graph = new Graph();
        System.out.println("Processing file to Create Graph");
        test.read(args[0], graph);
        Scanner scan = new Scanner(System.in);
        System.out.print("Actor 1: ");
        String name1 = scan.nextLine().toLowerCase();
        System.out.print("Actor 2: ");
        String name2 = scan.nextLine().toLowerCase();
        graph.BFS(name1, name2);





    }
}



