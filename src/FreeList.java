
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
    public FreeList(int maxPower) {
    	this.maxPower = maxPower;
        this.freeListArray = new Node[maxPower + 1];
        
        // Memory starts off as one large free block
        this.freeListArray[maxPower] = new Node(0, 1 << maxPower);
    }
    
        
    // change this to return a handle
    // Method to add a block back into the free list
    public void addBlock(int sizePower) {
    	 
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
    
    public void deallocateBlock(int sizePower, int startPosition) {
    	Node newNode = new Node(startPosition, 1 << sizePower);
        newNode.next = freeListArray[sizePower];
        freeListArray[sizePower] = newNode;
        
        // Check for a buddy to merge
        while (sizePower < maxPower) {
  
            int buddyStartPos = startPosition ^ (1 << sizePower);
            Node prev = null, curr = freeListArray[sizePower];
            
            while (curr != null) {
                if (curr.startPosition == buddyStartPos) {
                    if (prev == null) {
                        freeListArray[sizePower] = curr.next;
                    } else {
                        prev.next = curr.next;
                    }
                    
                    // Remove the original block as well
                    freeListArray[sizePower] = freeListArray[sizePower].next;

                    
                    // Merge the blocks
                    startPosition = Math.min(startPosition, buddyStartPos);
                    sizePower++;
                    
                    // Add the merged block back
                    newNode = new Node(startPosition, 1 << sizePower);
                    newNode.next = freeListArray[sizePower];
                    freeListArray[sizePower] = newNode;
                    
                    break;
                }
                prev = curr;
                curr = curr.next;
            }
            
            if (curr == null) {
                break;
            }
        }
    }
    
    public void doubleMemory() {
        // Increase the maxPower
        maxPower += 1;

        // Extend the freeListArray
        Node[] newFreeListArray = new Node[maxPower + 1];
        System.arraycopy(freeListArray, 0, newFreeListArray, 0, freeListArray.length);
        freeListArray = newFreeListArray;

        // Calculate the starting position of the new free block. It's the end of the old array.
        int newBlockStartPosition = 1 << (maxPower - 1);

        // Create a new Node for the new free block
        Node newBlock = new Node(newBlockStartPosition, 1 << maxPower);

        // Add this block to the free list array
        freeListArray[maxPower] = newBlock;
    }

    
    
}
