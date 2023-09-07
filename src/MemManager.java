
public class MemManager {
	private int poolsize; 
	private byte[] memoryPool; 
	
	// Constructor. poolsize defines the size of the memory pool in bytes
	public MemManager(int poolsize) {
		this.poolsize = poolsize;  
		this.memoryPool = new byte[poolsize]; 
	}
	
	// Insert a record and return its position handle.
	// space contains the record to be inserted, of length size.
	public Handle insert(byte[] space, int size) {
		return null;
	}
	// Return the length of the record associated with theHandle
	public int length(Handle theHandle) { 
		return 0;
	}
	 
	// Free a block at the position specified by theHandle.
	// Merge adjacent free blocks.
	public void remove(Handle theHandle) { 
		
	}
	// Return the record with handle posHandle, up to size bytes, by
	// copying it into space.
	// Return the number of bytes actually copied into space.
	public int get(byte[] space, Handle theHandle, int size) { 
		return 0;
	}
	// Dump a printout of the freeblock list
	public void dump() { 
		
	}
}
