import java.io.*;
import java.util.*;

public class ScanDriver {
    public static void main(String[] args) {
        // ArrayList to store the scans
        ArrayList<Scan> scans = new ArrayList<>();
        ArrayList<FullScan> fullscans = new ArrayList<>();
        Map<String, String> nameMap = new HashMap<>();
        try {
            // Read the data.csv file and create the scans
            BufferedReader reader = new BufferedReader(new FileReader("data/data.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] items = line.split(", ");
                Scan currentScan = new Scan(items[0], items[1], items[2]);
                scans.add(currentScan);
            }
            reader.close();

            // Read the map.csv file and create the HashMap
            
            reader = new BufferedReader(new FileReader("data/map.csv"));
            while ((line = reader.readLine()) != null) {
                String[] items = line.split(", ");
                nameMap.put(items[0], items[1]);
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        
        // Set the value of the new parameter for each object
        for (Scan obj : scans) {
            String key = obj.getid();
            obj.setname(nameMap.get(key));
        }
    
        // Print the scans
        /*for (Scan obj : scans) {
            System.out.println("Name: " + obj.getname());
            System.out.println("ID:    " + obj.getid());
            System.out.println("Time: " + obj.gettime());
            System.out.println("Date: " + obj.getdate());
            System.out.println();
        }*/

        //fill the fullscan arraylist with scans of start and end times
        for(int i=0;i<scans.size();i++){
            for(int j=i+1;j<scans.size();j++){
                if(scans.get(i).read()==true&&scans.get(j).read()==true&&scans.get(i).getname().equals(scans.get(j).getname())){
                    Scan startScan = scans.get(i);
                    Scan endScan = scans.get(j);
                    FullScan currentFullScan = new FullScan(startScan.getid(), startScan.gettime(), endScan.gettime(), startScan.getdate(), endScan.getdate(), startScan.getname());
                    fullscans.add(currentFullScan);
                    startScan.setread(false);
                    endScan.setread(false);
                }
            }
        }

        // Print the fullscans
        /*for (FullScan fs : fullscans) {
            System.out.println("Name: " + fs.getname());
            System.out.println("ID: " + fs.getid());
            System.out.println("Time: " + fs.gettime());
            System.out.println("End Time: "+fs.getendtime());
            System.out.println("Date: " + fs.getdate());
            System.out.println("End Date: "+ fs.getenddate());
            System.out.println();
        }*/

        //timeline format using fullscans
        String timelineFormat = timelineFormat(fullscans);
        System.out.println(timelineFormat);
    }

    public static String timelineFormat(ArrayList<FullScan> fullscans){
        String output = new String();
        int size = fullscans.size();
        for(int i=0;i<size-1;i++){
            String[] starr = fullscans.get(i).gettime().split(":");
            String[] sdarr = fullscans.get(i).getdate().split("/");
            String startTime = "Date("+sdarr[2]+", "+(Integer.parseInt(sdarr[0])-1)+", "+sdarr[1]+", "+starr[0]+", "+starr[1]+", "+starr[2]+")";
            String[] etarr = fullscans.get(i).getendtime().split(":");
            String[] edarr = fullscans.get(i).getenddate().split("/");
            String endTime = "Date("+edarr[2]+", "+(Integer.parseInt(edarr[0])-1)+", "+edarr[1]+", "+etarr[0]+", "+etarr[1]+", "+etarr[2]+")";
            
            String current = "{\n"+
            "id: "+i+",\n"+
            "start: new "+startTime+",\n"+
            "end: new "+endTime+",\n"+
            "name: \""+fullscans.get(i).getname()+"\",\n"+
            "color: \""+fullscans.get(i).getcolor()+"\"\n},\n";
            output+=current;
        }
        String[] starr = fullscans.get(size-1).gettime().split(":");
        String[] sdarr = fullscans.get(size-1).getdate().split("/");
        String startTime = "Date("+sdarr[2]+", "+(Integer.parseInt(sdarr[0])-1)+", "+sdarr[1]+", "+starr[0]+", "+starr[1]+", "+starr[2]+")";
        String[] etarr = fullscans.get(size-1).getendtime().split(":");
        String[] edarr = fullscans.get(size-1).getenddate().split("/");
        String endTime = "Date("+edarr[2]+", "+(Integer.parseInt(edarr[0])-1)+", "+edarr[1]+", "+etarr[0]+", "+etarr[1]+", "+etarr[2]+")";
            
            String last = "{\n"+
            "id: "+(size-1)+",\n"+
            "start: new "+startTime+",\n"+
            "end: new "+endTime+",\n"+
            "name: \""+fullscans.get(size-1).getname()+"\",\n"+
            "color: \""+fullscans.get(size-1).getcolor()+"\"\n}";

        output += last;
        return output;
    }
}

// Class representing an object with three parameters
class Scan {
    private String id;
    private String time;
    private String date;
    private String name;
    private boolean read;

    public Scan(String id, String time, String date) {
        this.id = id;
        this.time = time;
        this.date = date;
        read=true;
    }

    // Getter and setter methods for the parameters
    public String getid() { return id; }
    public void setid(String id) { this.id = id; }
    public String gettime() { return time; }
    public void settime(String time) { this.time = time; }
    public String getdate() { return date; }
    public void setdate(String date) { this.date = date; }

    public boolean read(){
        return read;
    }
    public void setread(boolean read){
        this.read=read;
    }

    // Getter and setter methods for the new parameter
    public String getname() { return name; }
    public void setname(String name) { this.name = name; }
}

class FullScan {
    private String id;
    private String time;
    private String endtime;
    private String date;
    private String enddate;
    private String name;

    private String color;

    public FullScan(String id, String time, String endtime, String date, String enddate, String name) {
        this.id = id;
        this.time = time;
        this.endtime = endtime;
        this.date = date;
        this.enddate = enddate;
        this.name = name;
        color = "grey";
    }

    // Getter and setter methods for the parameters
    public String getid() { return id; }
    public void setid(String id) { this.id = id; }

    public String gettime() { return time; }
    public void settime(String time) { this.time = time; }

    public String getendtime() { return endtime; }
    public void setendtime(String endtime) { this.endtime = endtime; }

    public String getdate() { return date; }
    public void setdate(String date) { this.date = date; }

    public String getenddate() { return enddate; }
    public void setenddate(String enddate) { this.enddate = enddate; }

    // Getter and setter methods for the new parameter
    public String getname() { return name; }
    public void setname(String name) { this.name = name; }

    public String getcolor(){
        if(getname().contains("Surgeon")||getname().contains("surgeon")||getname().contains("resident")||getname().contains("Resident")){
            return "blue";
        }
        return color;
    }
}