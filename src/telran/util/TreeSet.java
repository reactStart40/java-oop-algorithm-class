package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> implements SortedSet<T>{
	private static class Node<T> {
		T obj;
		Node<T> parent;
		Node<T> left;
		Node<T> right;

		Node(T obj) {
			this.obj = obj;
		}

		void setNulls() {
			parent = null;
			left = null;
			right = null;
			obj = null;
		}

	}

	private Node<T> root;
	private Comparator<T> comp;
	private int size;

	public TreeSet(Comparator<T> comp) {
		this.comp = comp;
	}

	public TreeSet() {
		this((Comparator<T>) Comparator.naturalOrder());
	}

	private class TreeSetIterator implements Iterator<T> {
		Node<T> current;
		Node<T> prev;
		boolean flNext = false;

		TreeSetIterator() {
			current = root == null ? null : getLeast(root);
		}

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
			prev = current;
			current = getCurrent(current);
			flNext = true;
			return res;
		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			removeNode(prev);
			flNext = false;
		}
	}

	@Override
	public boolean add(T obj) {
		Node<T> node = new Node<>(obj);
		boolean res = false;
		if (size == 0) {
			root = node;
			res = true;
		} else {
			Node<T> parent = getParent(obj);
			if (parent != null) {
				res = true;
				node.parent = parent;
				if (comp.compare(obj, parent.obj) > 0) {
					parent.right = node;
				} else {
					parent.left = node;
				}
			}
		}
		if (res) {
			size++;
		}
		return res;
	}

	private Node<T> getCurrent(Node<T> current) {

		return current.right != null ? getLeast(current.right) : getGreaterParent(current);
	}

	private Node<T> getGreaterParent(Node<T> current) {
		while (current.parent != null && current == current.parent.right) {
			current = current.parent;
		}
		return current.parent;
	}

	private Node<T> getLeast(Node<T> node) {
		Node<T> current = node;
		while (current.left != null) {
			current = current.left;
		}
		return current;
	}

	private Node<T> getNodeParent(T obj) {
		Node<T> current = root;
		Node<T> parent = null;
		int compRes;
		while (current != null && (compRes = comp.compare(obj, current.obj)) != 0) {
			parent = current;
			current = compRes > 0 ? current.right : current.left;
		}
		return current == null ? parent : current;
	}

	private Node<T> getNode(T obj) {
		Node<T> node = getNodeParent(obj);
		Node<T> res = null;
		if (node != null && comp.compare(obj, node.obj) == 0) {
			res = node;
		}
		return res;

	}

	private Node<T> getParent(T obj) {
		Node<T> node = getNodeParent(obj);
		Node<T> res = null;
		if (node != null && comp.compare(obj, node.obj) != 0) {
			res = node;
		}
		return res;
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		Node<T> node = getNode(pattern);
		if (node != null) {
			removeNode(node);
			res = true;
		}

		return res;
	}

	private void removeNode(Node<T> node) {
		if (node.left != null && node.right != null) {
			removeJunction(node);
		} else {
			removeNonJunction(node);
		}
		size--;

	}

	private void removeJunction(Node<T> node) {
		Node<T> substitute = getMostNodeFrom(node.left);
		node.obj = substitute.obj;
		removeNonJunction(substitute);

	}

	private Node<T> getMostNodeFrom(Node<T> node) {
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}

	private void removeNonJunction(Node<T> node) {

		Node<T> parent = node.parent;
		Node<T> child = node.left == null ? node.right : node.left;
		if (parent == null) {
			root = child;
		} else {
			if (node == parent.left) {
				parent.left = child;
			} else {
				parent.right = child;
			}

		}
		if (child != null) {
			child.parent = parent;
		}
		node.setNulls();

	}

	@Override
	public boolean contains(T pattern) {

		return getNode(pattern) != null;
	}

	@Override
	public Iterator<T> iterator() {

		return new TreeSetIterator();
	}

	@Override
	public T first() {
		T res = null;
		if (root == null) {
			throw new NoSuchElementException();
		}
		res = getLeast(root).obj;
		return res;
	}

	@Override
	public T last() {
		T res = null;
		if (root == null) {
			throw new NoSuchElementException();
		}
		res = getMostNodeFrom(root).obj;
		return res;
	}

	@Override
	public T floor(T element) {

		return floorCeiling(element, true);
	}

	@Override
	public T ceiling(T element) {

		return floorCeiling(element, false);
	}

	private T floorCeiling(T pattern, boolean isFloor) {
		T res = null;
		int compRes = 0;
		Node<T> current = root;
		while (current != null && (compRes = comp.compare(pattern, current.obj)) != 0) {
			if ((compRes < 0 && !isFloor) || (compRes > 0 && isFloor)) {
				res = current.obj;
			}
			current = compRes < 0 ? current.left : current.right;
		}
		return current == null ? res : current.obj;

	}

}