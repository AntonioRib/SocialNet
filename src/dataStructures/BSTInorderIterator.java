package dataStructures;
import dataStructures.exceptions.NoSuchElementException;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public class BSTInorderIterator<K, V> implements Iterator<Entry<K, V>> {
	

	private static final long serialVersionUID = 1L;
	
	private BSTNode<K, V> current;
	private Stack<BSTNode<K, V>> stack;
	
	public BSTInorderIterator(BSTNode<K, V> current) 
	{
		this.current = current;
		this.stack = new StackInList<BSTNode<K, V>>();
		rewind();
	}
	


	public boolean hasNext() 
	{
		return !stack.isEmpty();
	}

	public Entry<K, V> next() throws NoSuchElementException 
	{
		BSTNode<K, V> aux = stack.pop();
		if (aux == null)
			return null;
		if (aux.getRight() != null)					
			diveLeft(aux.getRight());
		return aux.getEntry();
	}

	public void rewind() 
	{
		stack = new StackInList<BSTNode<K, V>>();
		diveLeft(current);
	}
	
	private void diveLeft(BSTNode<K, V> aux) 
	{
		while (aux != null)
		{
			stack.push(aux);
			aux = aux.getLeft();
		}
	}

}
