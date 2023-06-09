package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class LinkedList<T> implements List<T> {
	Node<T> head;
	Node<T> tail;
	int size;
private class LinkedListIterator implements Iterator<T> {
Node<T> current = head;
boolean flNext = false;
	@Override
	public boolean hasNext() {
		
		return current != null;
	}

	@Override
	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		T res = current.obj;
		current = current.next;
		flNext = true;
		return res;
	}
	@Override
	public void remove() {
		if (!flNext) {
			throw new IllegalStateException();
		}
		Node<T> removedNode = current != null ? current.prev : tail;
		removeNode(removedNode);
		flNext = false;
	}
	
	
}
	private static class Node<T> {
		T obj;
		Node<T> next;
		Node<T> prev;

		Node(T obj) {
			this.obj = obj;
		}
	}

	@Override
	public boolean add(T obj) {
		add(size, obj);
		return true;
	}

	@Override
	public int size() {

		return size;
	}

	

	
	@Override
	public void add(int index, T obj) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(index);
		}
		Node<T> node = new Node<>(obj);
		addNode(index, node);

	}

	@Override
	public T remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(index);
		}
		Node<T> node = getNode(index);
		removeNode(node);
		T res = node.obj;
		node.obj = null;
		return res;
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(index);
		}

		return getNode(index).obj;
	}

	@Override
	public void sort(Comparator<T> comp) {
		//1. call the method toArray
		//2. By applying Arrays.sort you sort the array from #1
		//3. Passing over all LinkedList nodes and setting references to objects (T)
		// in the appropriate order from #2
		T[] array = toArray();
	    Arrays.sort(array, comp);
	    Node<T>current = head;
	    int index = 0;
	    while(current != null) {
	    	current.obj = array[index++];
	    	current = current.next;
	    }

	}
	private T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[size];
	    Node<T> current = head;
	    int index = 0;
	    while(current != null) {
	    	array[index++] = current.obj;
	    	current = current.next;
	    }
	    return array;
	}
	@Override
	public int indexOf(Predicate<T> predicate) {
		int index = 0;
		Node<T> current = head;
		while (current != null && !predicate.test(current.obj)) {
			current = current.next;
			index++;
		}
		return current == null ? -1 : index;
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		int index = size - 1;
		Node<T> current = tail;
		while (current != null && !predicate.test(current.obj)) {
			current = current.prev;
			index--;
		}
		return current == null ? -1 : index;
	}

	

	private void addNode(int index, Node<T> node) {
		if (head == null) {
			head = tail = node;
		} else {
			if (index == 0) {
				addNodeHead(node);
			} else if (index == size) {
				addNodeTail(node);
			} else {
				addNodeMiddle(index, node);
			}
		}
		size++;
	}

	private void addNodeHead(Node<T> node) {
		node.next = head;
		head.prev = node;
		head = node;
	}

	private void addNodeTail(Node<T> node) {
		node.prev = tail;
		tail.next = node;
		tail = node;
	}

	private void addNodeMiddle(int index, Node<T> node) {
		Node<T> nodeA = getNode(index);
		Node<T> nodeBefore = nodeA.prev;
		node.prev = nodeBefore;
		node.next = nodeA;
		nodeBefore.next = node;
		nodeA.prev = node;

	}

	private Node<T> getNode(int index) {

		return index > size / 2 ? getNodeFromRight(index) : getNodeFromLeft(index);
	}

	private Node<T> getNodeFromLeft(int index) {
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	private Node<T> getNodeFromRight(int index) {
		Node<T> current = tail;
		for (int i = size - 1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	private void removeHead() {
		Node<T> newHead = head.next;
		if (newHead != null) {
			newHead.prev = null;
		}
		head.next = null;
		head = newHead;

	}

	private void removeTail() {
		Node<T> newTail = tail.prev;
		if (newTail != null) {
			newTail.next = null;
		}
		tail.prev = null;
		tail = newTail;
	}

	private void removeMiddle(Node<T> node) {
		Node<T> nodeBefore = node.prev;
		Node<T> nodeAfter = node.next;
		nodeBefore.next = nodeAfter;
		nodeAfter.prev = nodeBefore;
		node.next = node.prev = null;
	}

	private void removeNode(Node<T> node) {
		if (node == head) {
			removeHead();
		} else if (node == tail) {
			removeTail();
		} else {
			removeMiddle(node);
		}
		size--;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}
}