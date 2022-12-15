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