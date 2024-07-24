public class TrainLine {

    /** Points to first station in the train line */
    private Station head;

    /**
     * Points to the last station and facilitates O(1) performance when adding
     * a new station at the end of the line
     */
    private Station tail;

    /** Current number of stations in this object */
    private int numberOfStations;

    /** Default constructor - redundant but good to show intent */
    public TrainLine() {
        this.head = null;
        this.tail = null;
        this.numberOfStations = 0;
    } // default constructor

    /** Accessor for number of stations present in this trainline */
    public int getNumberOfStations() {
        return this.numberOfStations;
    } // method getNumberOfStations

    /**
     * Add a new station at the end of this trainline. The method creates
     * a new station object, first, with the given name. Then it checks to
     * if this line has a head station yet; if not, the new station becomes
     * the head station. If this line has a head station, the method begins
     * traversing this line from its head station, following the next points
     * untils it finds a station whose next is null. That station, by definition
     * is presently the last station in the line. The new station is added after
     * that last station.
     * 
     * @param name String with name of new station to add
     */
    public void addStation(String name) {
        // Create a new station object with the given name
        Station newStation = new Station(name);
        // Use addStion(Station) method
        this.addStation(newStation);
    } // method addStation

    /**
     * Add a new station at the end of this trainline. The method takes
     * a station object and it checks if this line has a head station yet;
     * if not, the new station becomes the head station. If this line has a
     * head station, the method places the new station after the last station
     * in the line and marks that new station the last station in the line.
     * 
     * @param station Station object to insert at teh end of the line
     */
    public void addStation(Station station) {
        // Check if this trainline has a head station yet or not
        if (this.head == null) {
            // There is no head station in this trainline. Make the
            // new station, just created, the head station and also
            // the tail station of the line and we are done.
            this.head = station;
            this.tail = station;
        } else {
            // The trainline has an existing head station. Therefore,
            // it also has a known last station (this.tail).
            this.tail.setNext(station); // add new station after tail station
            this.tail = station; // Designate newly added station as tail station
        }
        // Update station counter
        this.numberOfStations++;
    } // method addStation

    /**
     * Determines if the linked list contains a loop. A loop forms when
     * the last station (this.tail) points to another station in the
     * train line instead of pointing to null. An empty line (this.head==null)
     * is considered loop-free.
     * 
     * @return true if there is a loop, false otherwise
     */
    public boolean hasLoop() {
        boolean loopFound = false;
        // Perform the check only when the train line is not empty
        if (this.head != null) {
            // Create a fast and a slow cursor.
            Station fast = this.head;
            Station slow = this.head;
            while (!loopFound && fast.hasNext() && fast.getNext().hasNext()) {
                fast = fast.getNext().getNext();
                slow = slow.getNext();
                loopFound = (slow == fast);
            }
        }
        return loopFound;
    } // method hasLoop

    /**
     * Inserts a new station after an existing one.
     * 
     * @param existingStationName String with name of existing station that we
     *                            are adding a station after.
     * @param stationToAdd        String with name of new station to add.
     * @return true if insertion is successful, false if there is a problem.
     *         Potential problems inlude the presence of the station we are trying
     *         to add, the absence of the station we are trying to add after, and
     *         null/empty strings.
     */
    public boolean addAfter(String existingStationName, String stationToAdd) {
        boolean success = false;
        // Check if the station to add is already present in the TrainLine
        // object or if the supplied strings are null or empty.
        if (!this.contains(stationToAdd)
                && existingStationName != null && existingStationName.length() > 0
                && stationToAdd != null && stationToAdd.length() > 0) {
            // Traverse the TrainLine, looking for the existing station
            Station current = this.head;
            while (current != null) {
                // Check if the current station is the one we are looking for.
                // If the intended station is not found, we skill the if block,
                // the while-loop eventually ends, and we return the intial
                // value of success which is still false.
                if (current.getName().equals(existingStationName)) {
                    // Intended station found, time to get things going, first
                    // by creating the new station to insert.
                    Station newStation = new Station(stationToAdd);
                    // Make the new station point to where the existing station points
                    newStation.setNext(current.getNext());
                    // Make the existing station point to the new station
                    current.setNext(newStation);
                    // Update the return variable to indicate a successful insertion
                    success = true;
                }
            }
        }
        return success;
    } // method addAfter

    /**
     * Finds the position of a station with the specified name. If the station
     * is not present in the train line, the reported position is -1.
     * 
     * @param name String with name of station to look for in this train line.
     * @return int position of named station in train line or -1 if station
     *         is not present or the trainline is empty.
     */
    public int indexOf(String name) {
        // Return value
        int position = -1;
        // Make sure line is not empty first
        if (this.head != null) {
            // Counts the stations as we traverse the line.
            int counter = 0;
            // Begin traversing the line from start
            Station current = this.head;
            while (position < 0 && current != null) {
                // Update position if the current station matches the specified name
                position = (current.getName().equals(name)) ? counter : position;
                // Update counter
                counter++;
                // Advance station
                current = current.getNext();
            }
        }
        return position;
    } // method indexOf

    /**
     * Determines if a station with a specific name is present in this TrainLine. 
     * Method contains is essentially a wrapper method for indexOf. If a station 
     * is present in this trainline its indexOf will be > -1. Therefore, any
     * indexOf value > -1 indicates that the named station is contained in the
     * trainline.
     * 
     * @param stationName String with station name to search for
     * @return true if station found; false otherwise or if object has no stations.
     */
    public boolean contains(String stationName) {
        return this.indexOf(stationName) > -1;
    } // method contains

    /**
     * Accessor for this.head 
     * This accessor is necessary so that we can tell if other similar objects
     * have a null head or not. 
     * 
     * @return Station this.head
     */
    public Station getHead() {
        return this.head;
    } // method getHead

    /**
     * Accessor for this.tail 
     * 
     * @return Station this.tail
     */
    public Station getTail() {
        return this.tail;
    } // method getHead

    /**
     * Appends a trainline to the current trainline object
     * @param other Trainline to append to present object
     */
    public void append(TrainLine other) {
        // First make sure that the trainline we wish to append is not null or empty
        if (other != null && other.getHead() != null) {
            // OK, we have something to append, now how to append it?
            if (this.head == null) {
                // If this trainline is empty, we use the other trainline's head and tail
                this.head = other.getHead();
            } else {
                // otherwise, we point this.tail to other.head ae 
                this.tail.setNext(other.getHead());
            }
            // Either way it's the same tail
            this.tail = other.getTail();
        }
    } // method append

    //Write a method `String listStations` that returns a string with names of the stations

    public String listStations() { //the method name is linestations
        if (this.head == null) {
            System.out.print("The line is empty."); //display this message if it is completely empty aka set to null
        } else {
        StringBuilder sb = new StringBuilder();
        Station current = this.head; //destination is the first empty space avaible and add more until there is no more space
        
        while (current != null) { //while it is not empty add more stations
            sb.append(current.getName()); //Add each station's name to sb
            
            if (current.getNext() != null) {
                sb.append();  //this will go to the next station and add it to the string
            }
            current = current.getNext();
        }
    }
        return sb.toString(); //Return StringBuilder to a string.

    }
    //Write a method `boolean intersects(TrainLine other)` that returns `true` if the two lines cross each other and false otherwise
    public boolean intersects(TrainLine other) {
        if(this.head == null && other.head == null){
            return false;
        }
        Station current = this.head; //the traverse method we used earlier can be used here to find the tail
        while (current.getNext() != null) {
            current = current.getNext();
        } 
        this.tail = current;

        current = other.head; //using the same method to find the other trains tail
        while (current.getNext() != null) {
            current = current.getNext();
        }
        other.tail = current;

        if (this.tail == other.tail) { //this will see if they intersect by having the same stations
            return true;
        }
        return false; // if not return false
    }
    // Write a method `int CompareTo(TrainLine other)` that returns a negative value if the invoking `TrainLine` is smaller than the `other` one, zero when equal, and a positive number when greater. You can always assume that the invoking `TrainLine` is not null.
    public int compareTo(TrainLine other) {
        if (this.numberOfStations == other.numberOfStations) { //if they are equal set comparison value to 0
            compare = 0;
            if (compare != 0) { //if it is not zero the value will be positive or negative
            while (current != null && other != null) {
                int compare = current.getName().compareTo(other.getName());
                if(this.numberOfStations > other.numberOfStations){ //if greater return a positive value
                    return compare + 2;
                }else{
                    return compare - 1; //if not set the value to be negative
                }
            }
            return compare;
            }
        }
    }
} // class TrainLine

//I am thinking that if the comparisons are larger or smaller change the count to display positive or negative