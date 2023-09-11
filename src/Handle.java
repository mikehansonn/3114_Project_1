
/**
 * The Handle to store in hashtable
 * 
 * @author matt02
 * @version 9/11/23
 */
public class Handle {
	private int length; // Length of the byte array
	private int startPosition; // Starting position in the Memory Manager Array

	/**
	 * Initial constructor for the handle
	 * 
	 * @param length        of the storage
	 * @param startPosition in the freelist
	 */
	public Handle(int length, int startPosition) {
		this.length = length;
		this.startPosition = startPosition;
	}

	/**
	 * getter for the length
	 * 
	 * @return the length value
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Setter for the length
	 * 
	 * @param length value to set length to
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * The start positon of the record
	 * 
	 * @return the start position
	 */
	public int getStartPosition() {
		return startPosition;
	}

	/**
	 * setter for startpositon
	 * 
	 * @param startPosition to set to
	 */
	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}

	/**
	 * Simple toString for the handle
	 * 
	 * @return return the toString result
	 */
	@Override
	public String toString() {
		return "Record{" + "length=" + length + ", startPosition=" + startPosition + '}';
	}
}
