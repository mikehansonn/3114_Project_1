public class Record {
    private int length; // Length of the byte array
    private int startPosition; // Starting position in the Memory Manager Array

    // Constructor
    public Record(int length, int startPosition) {
        this.length = length;
        this.startPosition = startPosition;
    }

    // Getters and Setters
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    
    @Override
    public String toString() {
        return "Record{" +
                "length=" + length +
                ", startPosition=" + startPosition +
                '}';
    }
}
