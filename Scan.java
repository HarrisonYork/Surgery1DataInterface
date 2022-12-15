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
