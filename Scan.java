import java.io.File;
import java.io.FileNotFoundException;
//import java.util.HexFormat;
import java.util.Scanner;

public class Scan {
    //object should store data from two consecutive lines of sd card:
    //tag name, id, time and date of scan
    String timeDifference;
    String name;
    String realID;
    String id;
    String dateAndTime;
    String time;
        String hour;
        String minute;
        String second;
    String date;
        String month;
        String day;
        String year;

    String endtime;
        String endhour;
        String endminute;
        String endsecond;
    String enddate;
        String endmonth;
        String endday;
        String endyear;
    boolean readThis;
    String type;

    //map of names to ids
    //TreeMap<String, String> myMap;

    //may not need isTool
    boolean isTool;

    public Scan(){
        id="00000";
        time="00:00:00";
        date="00/00/0000";
        endtime="00:00:00";
        enddate="00/00/0000";
        realID="000";
        isTool=true;
        readThis = true;
        type = "tool";
    }

    public Scan(String fileName) throws FileNotFoundException{
        File f = new File(fileName);
        //name=null;
        id="00000";
        realID="000";
        time="00:00:00";
        date="00/00/0000";
        endtime="00:00:00";
        enddate="00/00/0000";
        isTool=true;
        readThis = true;
        type="tool";
        reader(f);
        //File m = new File(fileMap);
        /*myMap = new TreeMap<String, String>();
        mapper(m);*/
    }

    public void reader(File f) throws FileNotFoundException{
        Scanner scan = new Scanner("data/data.txt");
        //line should be of ID
        String myLine = scan.nextLine();
        boolean fullBreak = false;
        if(!myLine.equals("END")){
            while(myLine.length()<=5){
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
                return;
            }
            //goes to next ID
            while(!myLine.substring(0,10).equals("Card UID: ")){
                myLine=scan.nextLine();
            }
            id = myLine.substring(10);
            //goes to next time
            while(!myLine.substring(0,6).equals("TIME: ")){
                myLine=scan.nextLine();
            }
            //gets whole line
            String dateAndTime = myLine.substring(6);
            //splits at space to seperate date and time
            String[] splitdt = dateAndTime.split(" ");
            time = splitdt[0];
            date = splitdt[1];
            readThis = true;
        }
        scan.close();
    }

    public String getColor(){
        if(isTool){
            return "grey";
        }
        else return "red";
    }

    public void setName(String name){
        if(name.contains("Surgeon")||name.contains("surgeon")){
            isTool=false;
        }
        else if(name.contains("Tool")||name.contains("tool")){
            isTool = true;
        }
        this.name = name;
    }
    
    public void setID(String id){
        for(int i=0;i<id.length();i++){
            if(id.charAt(i)==' '){
                id=id.substring(0,i);
                break;
            }
        }
        realID=id;
        id=Integer.parseInt(id, 16)+"";
    }

    public String getRealID(){
        return realID;
    }

    public void setTime(String time){
        String[] timeArr = time.split(":");
        hour = timeArr[0];
        if(hour.length()<2){
            hour="0"+hour;
        }
        minute = timeArr[1];
        if(minute.length()<2){
            minute="0"+minute;
        }
        second = timeArr[2];
        if(second.length()<2){
            second="0"+second;
        }
        this.time=hour+":"+minute+":"+second;
    }

    public String getHour(){
        return hour;
    }

    public String getMinute(){
        return minute;
    }

    public String getSecond(){
        return second;
    }

    public String getType(){
        if(isTool){
            return "tool";
        }
        else return "surgeon";
    }

    public void setDate(String date){
        this.date = date;
        String[] dateArr = date.split("/");
        month = dateArr[0];
        day = dateArr[1];
        year = dateArr[2];
    }

    public String getMonth(){
        return month;
    }

    public String getDay(){
        return day;
    }

    public String getYear(){
        return year;
    }

    public void setEndTime(String etime){
        //this.endtime = etime;
        String[] etimeArr = etime.split(":");
        endhour = etimeArr[0];
        if(endhour.length()<2){
            endhour="0"+endhour;
        }
        endminute = etimeArr[1];
        if(endminute.length()<2){
            endminute="0"+endminute;
        }
        endsecond = etimeArr[2];
        if(endsecond.length()<2){
            endsecond="0"+endsecond;
        }
        this.endtime=endhour+":"+endminute+":"+endsecond;

    }

    public String getEndTime(){
        return endtime;
    }

    public String getEndDate(){
        return enddate;
    }

    public String getEndHour(){
        return endhour;
    }

    public String getEndMinute(){
        return endminute;
    }

    public String getEndSecond(){
        return endsecond;
    }

    public void setEndDate(String edate){
        this.enddate = edate;
        String[] edateArr = edate.split("/");
        endmonth = edateArr[0];
        endday = edateArr[1];
        endyear = edateArr[2];
    }

    public String getEndMonth(){
        return endmonth;
    }

    public String getEndDay(){
        return endday;
    }

    public String getEndYear(){
        return endyear;
    }

    public void setDateAndTime(String dateAndTime){
        this.dateAndTime = dateAndTime;
    }

    public String getName(){
        if(name.contains("Surgeon")||name.contains("surgeon")){
            isTool=false;
        }
        else if(name.contains("Tool")||name.contains("tool")){
            isTool = true;
        }
        return name.substring(0);
    }

    public String getID(){
        return id+"";
    }

    public String getTime(){
        return time;
    }

    public String getDate(){
        return date;
    }

    public void setRead(boolean b){
        readThis = b;
    }

    public boolean getRead(){
        return readThis;
    }

    public String toString(){
        String output = new String();
        output=name+"\n"
            +id+"\n"
            +hour+":"+minute+":"+second+"\n"
            +month+"/"+day+"/"+year+"\n"
            +endtime+"\n"
            +enddate;
        return output;
    }

    public String getTimeDiff(){
        Integer convertToSecStart = (Integer.parseInt(getHour())*60*60)+(Integer.parseInt(getMinute())*60)+(Integer.parseInt(getSecond()));
        Integer convertToSecEnd = (Integer.parseInt(getEndHour())*60*60)+(Integer.parseInt(getEndMinute())*60)+(Integer.parseInt(getEndSecond()));
        Integer difference = convertToSecEnd-convertToSecStart;
        Integer getDH = difference/60/60;
        Integer getDM = (difference/60)%60;
        Integer getDS = difference%60;
        this.timeDifference=getDH+":"+getDM+":"+getDS;
        return timeDifference;
    }
}
