import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class DataReader {
    private TreeMap<String, String> myTagNames;
    private ArrayList<String> output;

    //keeps track of number of occurences of each tag based on name
    private TreeMap<String, Integer> myScores;
    private ArrayList<String> scoreOutput;

    public ArrayList<Scan> myScans;
    
    public DataReader(String fName, TreeMap<String, String> myTagNames) throws FileNotFoundException{
        File f = new File(fName);
        this.myTagNames = myTagNames;
        output = new ArrayList<String>();

        myScores = new TreeMap<String, Integer>();
        scoreOutput = new ArrayList<String>();

        myScans = new ArrayList<Scan>();

        decoder(f);
    }

    public void decoder(File fileText) throws FileNotFoundException{
        Scanner scan = new Scanner(fileText);
        ArrayList<String> output = new ArrayList<String>();
        String myNextLine = scan.nextLine();
        boolean fullBreak = false;
        while(!myNextLine.equals("END")){
            String myLine = myNextLine;
            Scan myScan = new Scan();
            while(myLine.length()<=10){
                myLine=scan.nextLine();
                if(myLine.equals("END")){
                    fullBreak = true;
                }
                if(fullBreak){
                    scan.close();
                    break;
                }
            }
            if(fullBreak){
                scan.close();
                break;
            }
            String addThis = new String();
            
            if(myLine.contains("Card UID: ")){
                String[] uidd = myLine.split("UID: ");
                String[] fixedMaybe = uidd[1].split(" ", 2);
                String tagName = myTagNames.get(fixedMaybe[0]);
                if(tagName==null){
                    tagName = "UNEXPECTED UID";
                }
                myScan.setName(tagName);
                String uid = myLine.substring(10);
                myScan.setID(uid);
                
                addThis = "SCAN - TAG NAME: "+tagName+" // UID: "+uid;
                output.add(addThis);
                if(!myScores.containsKey(tagName)){
                    myScores.putIfAbsent(tagName, 1);
                }
                else if(myScores.containsKey(tagName)){
                    myScores.put(tagName, myScores.get(tagName)+1);
                }
            }
            myLine = scan.nextLine();
            if(myLine.substring(0,6).equals("TIME: ")){
                String myTime = myLine.substring(6);
                if(myTime==null){
                    myTime = " UNEXPECTED TIME";
                }
                addThis = "TIME SCANNED: "+myTime;
                String[] timeSplit = myTime.split(" ");
                String timeOnly = timeSplit[0];
                String dateOnly = timeSplit[1];
                myScan.setDateAndTime(myTime);
                myScan.setTime(timeOnly);
                myScan.setDate(dateOnly);
                output.add(addThis);
            }
            myScans.add(myScan);
            myNextLine = scan.nextLine();
        }
        scan.close();
        this.output = output;
    }

    public ArrayList<String> getFormatted(){
        return output;
    }

    public ArrayList<String> getScores(){
        Set<String> keys = myScores.keySet();
        for(String str: keys){
            int currentScore = myScores.get(str);
            String addThis = str+" SCAN COUNT: "+currentScore;
            scoreOutput.add(addThis);
        }

        return scoreOutput;
    }

    public ArrayList<Scan> getScans(){
        return myScans;
    }
}

