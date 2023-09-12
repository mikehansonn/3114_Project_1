/**
 * This is a Memory Manager implementation for the project
 * 
 * @author mikehanson, matt02
 * @version 9/11/23
 * 
 */
public class MemManager {
    private int poolsize; 
    // the size of the memory pool
    private byte[] memoryPool; 
    // byte array the holds seminar information in byte form
    private FreeList freeList; 
    // freelist that determines free space in the byte array

    /**
     * Constructs a new MemManager with 
     * a specified memory pool size. It also
     * initializes the free list with 
     * the highest power of 2 equal to poolsize.
     *
     * @param poolsize the initial size of the memory pool in bytes
     */
    public MemManager(int poolsize) {
        this.poolsize = poolsize;
        this.memoryPool = new byte[poolsize];

        int sizePower = (int) (Math.log(poolsize) / Math.log(2));
        // finds highest power of 2 equal to poolsize
        this.freeList = new FreeList(sizePower);

    }

    /**
     * Gets the current size of the memory pool.
     *
     * @return the size of the memory pool in bytes
     */
    public int getPoolsize() {
        return poolsize;
    }

    /**
     * Doubles the size of the memory pool, 
     * copying existing data to the new, larger
     * pool.
     */
    private void doubleSize() {
        byte[] newMemoryPool = new byte[poolsize * 2];
        poolsize *= 2;
        System.out.println(
                "Memory pool expanded to " + poolsize + " bytes");
        System.arraycopy(
                memoryPool, 0, newMemoryPool, 0, memoryPool.length);
        memoryPool = newMemoryPool;
        freeList.doubleMemory();
    }

    /**
     * Inserts a new record into the memory pool, 
     * returning a handle to its
     * location.
     *
     * @param space the data to be inserted
     * @param size  the number of bytes to insert
     * @return a handle pointing to the record's location
     */
    public Handle insert(byte[] space, int size) {
        int sizePower = (int) Math.ceil(Math.log(size) / Math.log(2));

        int startPosition;

        // doubles pool size until the data to be added can fit
        // applies when the first element added is to large
        while ((1 << sizePower > poolsize)) {
            doubleSize();
        }

        startPosition = freeList.addBlock(sizePower);

        // if there is no block to fit the inserted data it will keep doubling
        // until there is space
        while (startPosition == -1) {
            doubleSize();
            startPosition = freeList.addBlock(sizePower);
        }

        // copies data into byte array
        System.arraycopy(space, 0, memoryPool, startPosition, size);

        Handle handle = new Handle(size, startPosition);

        return handle;
    }

    /**
     * Gets the length of the record associated with the given handle.
     *
     * @param theHandle the handle pointing to the record
     * @return the length of the record in bytes
     */
    public int length(Handle theHandle) {
        return theHandle.getLength();
    }

    /**
     * Removes the record associated 
     * with the given handle, resetting its bytes to
     * zero
     *
     * @param theHandle the handle pointing to the record to be removed
     */
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

    /**
     * Retrieves a record associated with the given handle, copying up to a
     * specified number of bytes into a provided array.
     *
     * @param space     the array to copy the data into
     * @param theHandle the handle pointing to the record to retrieve
     * @param size      the maximum number of bytes to retrieve
     * @return the actual number of bytes copied into the provided array
     */
    public int get(byte[] space, Handle theHandle, int size) {

        // copies bytes based off the handle into the space array
        System.arraycopy(
                memoryPool, theHandle.getStartPosition(), space, 0, size);

        int totalBytes = space.length;

        return totalBytes;

    }

    /**
     * Dumps a printout of the current state of the free list to the console.
     */
    public void dump() {
        System.out.print(freeList.toString());
    }

}