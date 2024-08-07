
//use assignment #8 as a reference
class Station { //use assignment 8 trainline and station to implement the code
    String name; //the string will store the name of the station
    Station next; //will allow the next station to be called something different can be used in an array or linked list to tie them together.

    Station(String name) {
        this.name = name; //assigns the name attribute from the station object constructor that will be created
        this.next = null; //the next attribute will be null which means that a new station will not tie together with the old one in the array or list.
    }
}

class TrainLine {
    
    Station head; //point to the first station of each line

    TrainLine() {
        head = null; //the train line will be empty by default
    }

    void addStation(String name) { 
         // Create a new station object with the given name
         Station newStation = new Station(name);
         // Use addStion(Station) method
         this.addStation(newStation);
    }

    String reverseListStations() {
        if (head == null) { //if the head is null then we will return and display the line is empty.
            return "The line is empty.";
        }

        Station previous = null; //start at null and store the previous node in the list during the reverse stage.
        Station current = head; //start with the head and this is the currecnt node that is being selected.
        Station next = null; //the next node in the list will be set to null.

        while (current != null) {
            next = current.next; // the next node will not point to the previous node and that will be current.
            current.next = previous;
            previous = current; //the prevoius pointer will point to the current node and now becomes the previous node
            current = next; //next node will now be the current node
        }
      
        while (previous != null) { //while it is not null
            reversedStations.append(previous.name).append("\n"); //reverse the trainline and adds the names on a new line
            previous = previous.next; //moves forward in the reverse list
        }



public class TestTrainLine {
    public static void main(String[] args) {
        TrainLine lincolnService = new TrainLine();
        lincolnService.addStation("Chicago");
        lincolnService.addStation("Summit");
        lincolnService.addStation("Joliet");
        lincolnService.addStation("Dwight");
        lincolnService.addStation("Pontiac");
        lincolnService.addStation("Bloomington");

        lincolnService.addStation("Lincoln");
        lincolnService.addStation("Springfield");
        lincolnService.addStation("Carlinville");
        lincolnService.addStation("Alton");
        lincolnService.addStation("SaintLouis");

        StringBuilder reversedStations = new StringBuilder(); //creates an empty list that will store multiple strings
String reversedList = lincolnService.reverseListStations(reversedStations); //will reverse the stations in lincoln service starting with saint louis
System.out.println(reversedList); //print out the list
    }
}
    }
}

//I used Assignment 8 as a reference to make a small testing code to reverse the lincoln service list without for loops, or restricted methods. Something similar to what we did in learning train line assignments to see if the recursion would work.