
public class FreeList {
	private Node[] freeListArray; // Array of linked lists
	private int maxPower; // Maximum power of 2 for block sizes
	
	
	// Inner Node class to represent a block in the linked list
    private class Node {
        int startPosition; // Starting position of the block in the memory pool
        int size; // Size of the block
        Node next; // Pointer to the next Node

        public Node(int startPosition, int size) {
            this.startPosition = startPosition;
            this.size = size; 
            this.next = null;
        }
    }
    
    // Constructor initializes the array and adds the initial block
    public FreeList(int maxPower, int initialStartPosition) {
    	this.maxPower = maxPower;
        this.freeListArray = new Node[maxPower + 1];
        
        // Assuming memory starts off as one large free block
        this.freeListArray[maxPower] = new Node(initialStartPosition, 1 << maxPower);
    }
    
        
    
    // Method to add a block back into the free list
    public void addBlock(int sizePower, int startPosition) {
    	 
    	// Check if an appropriate block of memory exists in the free list at index k
        Node currNode = freeListArray[sizePower];
      
        // If a block of the appropriate size exists, allocate it
        if (currNode != null) {
            freeListArray[sizePower] = currNode.next;  // Remove head of list
            // Here, 'currNode' can be marked as allocated.
            return;
        }
        
        // If we didn't find a block of appropriate size,
        // then find the next larger block and split it
        for (int i = sizePower + 1; i <= maxPower; i++) {
            if (freeListArray[i] != null) {
            	
                // Remove the block from the free list
                Node largeBlock = freeListArray[i];
                freeListArray[i] = largeBlock.next;
      
                // Split the large block and add the split blocks back to the free list
                while (i > sizePower) {
                    i--;
                    int buddyStartPos = largeBlock.startPosition + (1 << i);
      
                    // Add buddy block back to the free list
                    Node buddyBlock = new Node(buddyStartPos, 1 << i);
                    buddyBlock.next = freeListArray[i];
                    freeListArray[i] = buddyBlock;
      
                    // Update the original block's size
                    largeBlock.size = 1 << i;
                }
      
                // Here, 'largeBlock' has now been resized to size 2^k and can be marked as allocated.
                return;
            }
        }
      
        // No blocks available to allocate
        System.out.println("Out of memory");
     
        
    }
    
    
    

}
