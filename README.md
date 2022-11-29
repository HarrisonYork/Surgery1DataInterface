# TagTrackerDataInterface
Repository for display of data gained through use of the tag tracker device. Part of the Surgery 1, EGR101 project at Duke University.

Getting Started:

mapper.txt is a file within the "data" folder that stores customizable tag names with their unique tag ids. These ids can be found using the device and scanning each tool. Tags corresponding to residents or surgeons should have names including "surgeon" or "resident" so that they can be identified as user-worn tags. These keywords can be altered within the .setName() method of Scan.java. The default type is tool.

data.txt is a file that stores the record of scans in a surgery, also within "data" folder. The raw data collected by the device is the id and time scanned. Comparing subsequent scans will determine the "end" time for use of the tool, and the resulting time the tool was used. When running DataInterface.java, users can copy and paste the entirety of the SD card file written by the device into the text field within the interface home screen. This will automatically format the data.

After formatting, users can view the data in several different sorts:

  Chronological Sorting: lists each scan, sorted by start time

  Tag Sorting: lists each pair of start/end times, sorted by tag name

 
