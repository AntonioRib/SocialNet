package dataStructures;
                                        
/**
 * @author Antonio Ribeiro, n�41674 / Francisco Godinho, n�41611
 */
public class AVLTree<K extends Comparable<K>, V> 
    extends AdvancedBSTree<K,V> 
{                                                                   

    static final long serialVersionUID = 0L;


    // If there is an entry in the dictionary whose key is the specified key,
    // replaces its value by the specified value and returns the old value;
    // otherwise, inserts the entry (key, value) and returns null.
    public V insert( K key, V value )
    {                                                                   
        // creates an empty stack of path steps to get to a node.
    	Stack<PathStep<K,V>> path = new StackInList<PathStep<K,V>>();
    	// searches for the node, filling the stack with the path steps taken to find it.
        BSTNode<K,V> node = this.findNode(key, path);
        // if the node was found
        if ( node == null )
        {
        	// creates the new node and links it to the main tree in the last step of the stack.
            AVLNode<K,V> newLeaf = new AVLNode<K,V>(key, value);
            this.linkSubtree(newLeaf, path.top());
            currentSize++;
            // reorganizes the tree in order for it to keep its balance.
            this.reorganizeIns(path);
            return null;   
        }
        // node wasn't found
        else 
        {
        	// simply changes the old node value to the new one.
            V oldValue = node.getValue();
            node.setValue(value);
            return oldValue;
        }
    }


    // Every ancestor of the new leaf is stored in the stack, 
    // which is not empty.
    protected void reorganizeIns( Stack<PathStep<K,V>> path )               
    {                                                                   
        boolean grew = true;
        PathStep<K,V> lastStep = path.pop();
        AVLNode<K,V> parent = (AVLNode<K,V>) lastStep.parent;
        while ( grew && parent != null )
        {
            if ( lastStep.isLeftChild )
                // parent's left subtree has grown.
                switch ( parent.getBalance() )
                {
                    case 'L': 
                        this.rebalanceInsLeft(parent, path);
                        grew = false;
                        break;
                    case 'E': 
                        parent.setBalance('L');
                        break;
                    case 'R': 
                        parent.setBalance('E');
                        grew = false;
                        break;
                }
            else
                // parent's right subtree has grown.
                switch ( parent.getBalance() )
                {
                    case 'L': 
                        parent.setBalance('E');
                        grew = false;
                        break;
                    case 'E': 
                        parent.setBalance('R');
                        break;
                    case 'R': 
                        this.rebalanceInsRight(parent, path);
                        grew = false;
                        break;
                }
            lastStep = path.pop();
            parent = (AVLNode<K,V>) lastStep.parent;
        } 
    }


    // Every ancestor of node is stored in the stack, which is not empty.
    // height( node.getLeft() ) - height( node.getRight() ) = 2.
    protected void rebalanceInsLeft( AVLNode<K,V> node, 
        Stack<PathStep<K,V>> path )
    {
        AVLNode<K,V> leftChild = (AVLNode<K,V>) node.getLeft();
        switch ( leftChild.getBalance() )
        {
            case 'L': 
                this.rotateLeft1L(node, leftChild, path);
                break;
         // case 'E': 
         //     Impossible.
            case 'R': 
                this.rotateLeft2(node, leftChild, path);
                break;
        }
    }

        
    // Every ancestor of node is stored in the stack, which is not empty.
    // height( node.getRight() ) - height( node.getLeft() ) = 2.
    protected void rebalanceInsRight( AVLNode<K,V> node, 
        Stack<PathStep<K,V>> path )
    {
        AVLNode<K,V> rightChild = (AVLNode<K,V>) node.getRight();
        switch ( rightChild.getBalance() )
        {
            case 'L': 
                this.rotateRight2(node, rightChild, path);
                break;
         // case 'E': 
         //     Impossible.
            case 'R': 
                this.rotateRight1R(node, rightChild, path);
                break;
        }
    }
               

    // If there is an entry in the dictionary whose key is the specified key,
    // removes it from the dictionary and returns its value;
    // otherwise, returns null.
    public V remove( K key )
     {                                                                   
        Stack<PathStep<K,V>> path = new StackInList<PathStep<K,V>>();
        BSTNode<K,V> node = this.findNode(key, path);
        if ( node == null )
            return null;
        else
        {
            V oldValue = node.getValue();
            if ( node.getLeft() == null )
                // The left subtree is empty.
                this.linkSubtree(node.getRight(), path.top());
            else if ( node.getRight() == null )
                // The right subtree is empty.
                this.linkSubtree(node.getLeft(), path.top());
            else
            {
                // Node has 2 children. Replace the node's entry with
                // the 'minEntry' of the right subtree.
                path.push( new PathStep<K,V>(node, false) );
                BSTNode<K,V> minNode = this.minNode(node.getRight(), path);
                node.setEntry( minNode.getEntry() );
                // Remove the 'minEntry' of the right subtree.
                this.linkSubtree(minNode.getRight(), path.top());
            }
            currentSize--;
            this.reorganizeRem(path);
            return oldValue;
        }
    }
    
    


    // Every ancestor of the removed node is stored in the stack, 
    // which is not empty.
    protected void reorganizeRem( Stack<PathStep<K,V>> path )               
    {                                                                   
    	 boolean shrunk = true;
         PathStep<K,V> lastStep = path.pop();
         AVLNode<K,V> parent = (AVLNode<K,V>) lastStep.parent;
         while ( shrunk && parent != null )
         {
             if ( lastStep.isLeftChild )
                 // parent's left subtree has shrinked.
                 switch ( parent.getBalance() )
                 {
                     case 'L': 
                    	 parent.setBalance('E');
                         break;
                     case 'E': 
                         parent.setBalance('R');
                         shrunk = false;
                         break;
                     case 'R': 
                    	 this.rebalanceRemLeft(parent, path);
                         shrunk = false;
                         break;
                 }
             else
                 // parent's right subtree has shrinked.
                 switch ( parent.getBalance() )
                 {
                     case 'L': 
                    	 this.rebalanceRemRight(parent, path);
                         shrunk = false;
                         break;
                     case 'E': 
                         parent.setBalance('L');
                         shrunk = false;
                         break;
                     case 'R': 
                    	 parent.setBalance('E');
                         break;
                 }
             lastStep = path.pop();
             parent = (AVLNode<K,V>) lastStep.parent;
         } 
    }
    
    // Every ancestor of node is stored in the stack, which is not empty.
    // height( node.getLeft() ) - height( node.getRight() ) = 2.
    protected void rebalanceRemLeft( AVLNode<K,V> node, 
        Stack<PathStep<K,V>> path )
    {
        AVLNode<K,V> rightChild = (AVLNode<K,V>) node.getRight();
        switch ( rightChild.getBalance() )
        {
            case 'L': 
                this.rotateRight2(node, rightChild, path);
                break;
            case 'E':
            	this.rotateRight1E(node, rightChild, path);
            	break;
            case 'R': 
                this.rotateRight1R(node, rightChild, path);
                break;
        }
    }

        
    // Every ancestor of node is stored in the stack, which is not empty.
    // height( node.getRight() ) - height( node.getLeft() ) = 2.
    protected void rebalanceRemRight( AVLNode<K,V> node, 
        Stack<PathStep<K,V>> path )
    {
        AVLNode<K,V> leftChild = (AVLNode<K,V>) node.getLeft();
        switch ( leftChild.getBalance() )
        {
            case 'L': 
                this.rotateLeft2(node, leftChild, path);
                break;
            case 'E':
            	this.rotateLeft1E(node, leftChild, path);
            	break;
            case 'R': 
                this.rotateLeft1L(node, leftChild, path);
                break;
        }
    }


    // Performs a single left rotation rooted at theRoot,
    // when the balance factor of its leftChild is 'L'.
    //
    // Every ancestor of theRoot is stored in the stack, which is not empty.
    // height( node.getLeft() ) - height( node.getRight() ) = 2.
    protected void rotateLeft1L( AVLNode<K,V> theRoot, AVLNode<K,V> leftChild, 
        Stack<PathStep<K,V>> path )
    {
        theRoot.setBalance('E');
        leftChild.setBalance('E');
        this.rotateLeft(theRoot, leftChild, path);
    }


    // Performs a single left rotation rooted at theRoot,
    // when the balance factor of its leftChild is 'E'.
    //
    // Every ancestor of theRoot is stored in the stack, which is not empty.
    // height( node.getLeft() ) - height( node.getRight() ) = 2.
    protected void rotateLeft1E( AVLNode<K,V> theRoot, AVLNode<K,V> leftChild, 
        Stack<PathStep<K,V>> path )
    {
     // theRoot.setBalance('L');
        leftChild.setBalance('R');
        this.rotateLeft(theRoot, leftChild, path);
    }


    // Performs a single right rotation rooted at theRoot,
    // when the balance factor of its rightChild is 'R'.
    //
    // Every ancestor of theRoot is stored in the stack, which is not empty.
    // height( node.getRight() ) - height( node.getLeft() ) = 2.
    protected void rotateRight1R( AVLNode<K,V> theRoot, 
        AVLNode<K,V> rightChild, Stack<PathStep<K,V>> path )
    {
        theRoot.setBalance('E');
        rightChild.setBalance('E');
        this.rotateRight(theRoot, rightChild, path);
    }


    // Performs a single right rotation rooted at theRoot,
    // when the balance factor of its rightChild is 'E'.
    //
    // Every ancestor of theRoot is stored in the stack, which is not empty.
    // height( node.getRight() ) - height( node.getLeft() ) = 2.
    protected void rotateRight1E( AVLNode<K,V> theRoot, 
        AVLNode<K,V> rightChild, Stack<PathStep<K,V>> path )
    {
     // theRoot.setBalance('R');
        rightChild.setBalance('L');
        this.rotateRight(theRoot, rightChild, path);
    }


    // Performs a double left rotation rooted at theRoot.
    //
    // Every ancestor of theRoot is stored in the stack, which is not empty.
    // height( node.getLeft() ) - height( node.getRight() ) = 2.
    protected void rotateLeft2( AVLNode<K,V> theRoot, AVLNode<K,V> leftChild, 
        Stack<PathStep<K,V>> path )
    {
        AVLNode<K,V> rightGrandchild = (AVLNode<K,V>) leftChild.getRight();
        switch ( rightGrandchild.getBalance() )
        {
            case 'L': 
                leftChild.setBalance('E');
                theRoot.setBalance('R');
                break;
            case 'E': 
                leftChild.setBalance('E');
                theRoot.setBalance('E');
                break;
            case 'R': 
                leftChild.setBalance('L');
                theRoot.setBalance('E');
                break;
        }
        rightGrandchild.setBalance('E');
        this.rotateLeft(theRoot, leftChild, rightGrandchild, path);
    }


    // Performs a double right rotation rooted at theRoot.
    //
    // Every ancestor of theRoot is stored in the stack, which is not empty.
    // height( node.getRight() ) - height( node.getLeft() ) = 2.
    protected void rotateRight2( AVLNode<K,V> theRoot, 
        AVLNode<K,V> rightChild, Stack<PathStep<K,V>> path )
    {
        AVLNode<K,V> leftGrandchild = (AVLNode<K,V>) rightChild.getLeft();
        switch ( leftGrandchild.getBalance() )
        {
            case 'L': 
                theRoot.setBalance('E');
                rightChild.setBalance('R');
                break;
            case 'E': 
                theRoot.setBalance('E');
                rightChild.setBalance('E');
                break;
            case 'R': 
                theRoot.setBalance('L');
                rightChild.setBalance('E');
                break;
        }
        leftGrandchild.setBalance('E');
        this.rotateRight(theRoot, rightChild, leftGrandchild, path);
    }

}
