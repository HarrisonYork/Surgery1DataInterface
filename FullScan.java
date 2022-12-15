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
        return color;//test
    }
}