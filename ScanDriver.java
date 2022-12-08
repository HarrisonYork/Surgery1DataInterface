import java.io.*;
import java.util.*;

public class ScanDriver {
    public static void main(String[] args) {
        // ArrayList to store the objects
        ArrayList<MyObject> objects = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        try {
            // Read the data.csv file and create the objects
            BufferedReader reader = new BufferedReader(new FileReader("data/data.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] items = line.split(",");
                MyObject obj = new MyObject(items[0], items[1], items[2]);
                objects.add(obj);
            }
            reader.close();

            // Read the map.csv file and create the HashMap
            
            reader = new BufferedReader(new FileReader("data/map.csv"));
            while ((line = reader.readLine()) != null) {
                String[] items = line.split(",");
                map.put(items[0], items[1]);
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
            // Set the value of the new parameter for each object
            for (MyObject obj : objects) {
                String key = obj.getid();
                obj.setname(map.get(key));
            }
    
            // Print the objects
            for (MyObject obj : objects) {
                System.out.println("Name: " + obj.getName());
                System.out.println("ID:    " + obj.getid());
                System.out.println("Time: " + obj.gettime());
                System.out.println("Date: " + obj.getdate());
                System.out.println();
            }
        }
    }

// Class representing an object with three parameters
class MyObject {
    private String id;
    private String time;
    private String date;
    private String name;

    public MyObject(String id, String time, String date) {
        this.id = id;
        this.time = time;
        this.date = date;
    }

    // Getter and setter methods for the parameters
    public String getid() { return id; }
    public void setid(String id) { this.id = id; }
    public String gettime() { return time; }
    public void settime(String time) { this.time = time; }
    public String getdate() { return date; }
    public void setdate(String date) { this.date = date; }

    // Getter and setter methods for the new parameter
    public String getName() { return name; }
    public void setname(String name) { this.name = name; }
}