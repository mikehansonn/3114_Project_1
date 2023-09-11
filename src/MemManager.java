
public class MemManager {
	private int poolsize; 
	private byte[] memoryPool;
	private FreeList freeList; 
	
	// Constructor. poolsize defines the size of the memory pool in bytes
	public MemManager(int poolsize) { 
		this.poolsize = poolsize;    
		this.memoryPool = new byte[poolsize]; 
		
		int sizePower = (int) (Math.log(poolsize) / Math.log(2));
		this.freeList = new FreeList(sizePower); 
		
	}
	
	public int getPoolsize() {
		return poolsize;
	}
	
	private void doubleSize() {
        byte[] newMemoryPool = new byte[poolsize * 2];
        poolsize *= 2;
        System.out.println("Memory pool expanded to " + poolsize + " bytes");
        System.arraycopy(memoryPool, 0, newMemoryPool, 0, memoryPool.length);
        memoryPool = newMemoryPool;
        freeList.doubleMemory();   
    }
	
	// Insert a record and return its position handle.
	// space contains the record to be inserted, of length size.
	public Handle insert(byte[] space, int size) {
		int sizePower = (int) Math.ceil(Math.log(size) / Math.log(2));
		
		
		int startPosition = freeList.addBlock(sizePower);
		
		System.out.println(startPosition);
		
		
		// fake while loop condition, place holder. 
		// might make a condition for the first block, and then a condtion for the negative -1
		while (startPosition == -1 ) { 
	        doubleSize();
	        freeList.doubleMemory();
	        startPosition = freeList.addBlock(sizePower);
	    }
		
		System.arraycopy(space, 0, memoryPool, startPosition, size);
		
		Handle handle = new Handle(size, startPosition);
		
		return handle;
	}
	
	
	// Return the length of the record associated with theHandle
	public int length(Handle theHandle) { 
		if (theHandle == null) {
	        return -1;  // Or any other value/error message to indicate that the handle is null
	    }
		
		return theHandle.getLength();
	}
	 
	
	// Free a block at the position specified by theHandle.
	// Merge adjacent free blocks.
	public void remove(Handle theHandle) { 
		
		int startPosition = theHandle.getStartPosition();
	    int size = theHandle.getLength();
	    int sizePower = (int) Math.ceil(Math.log(size) / Math.log(2));
	    
	    
	    // Reset the relevant portion of the memory pool to zero
	    for (int i = startPosition; i < startPosition + size; i++) {
	        memoryPool[i] = 0;
	    }
	    
	    
	    // Add the block to the free list
	    freeList.deallocateBlock(sizePower, startPosition);
			
	}
	
	
	// Return the record with handle posHandle, up to size bytes, by
	// copying it into space.
	// Return the number of bytes actually copied into space.
	public int get(byte[] space, Handle theHandle, int size) { 
		
		System.arraycopy(memoryPool, theHandle.getStartPosition(), space, 0, size);
		
		int totalBytes = space.length; 
		
		return totalBytes;
		
	}
	
	// Dump a printout of the freeblock list
	public void dump() { 
		System.out.println(freeList.toString());
	}
}
