class Station {
    String name;
    Station next;

    Station(String name) {
        this.name = name;
        this.next = null;
    }
}

class TrainLine {
    Station head;

    TrainLine() {
        head = null;
    }

    void addStation(String name) {
        // ... (same as previous implementation)
    }

    String reverseListStations() {
        if (head == null) {
            return "The line is empty.";
        }

        Station previous = null;
        Station current = head;
        Station next = null;

        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        StringBuilder reversedStations = new StringBuilder();
        while (previous != null) {
            reversedStations.append(previous.name).append("\n");
            previous = previous.next;
        }

        return reversedStations.toString();
    }
}