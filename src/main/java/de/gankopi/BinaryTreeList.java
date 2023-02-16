package de.gankopi;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BinaryTreeList<T extends Comparable<T>> {
	private Node listRoot = null;
	private Node treeRoot = null;
	
	public void add(T object) {
		Node newNode = new Node(object);

		if (this.listRoot == null) {
			this.listRoot = newNode;
			this.treeRoot = newNode;
			return;
		}

		Node parent = this.getParentTreeNode(newNode);
		if (parent == null) {
			return;
		}

		this.add(parent, newNode);
		this.rebalance();
	}

	private void add(Node parent, Node node) {
		Node previousSuccessor = null;

		if (parent.content.compareTo(node.content) < 0) {
			// update predecessors und successors in list: node -> parent
			if (parent.listSuccessor != null) {
				parent.listSuccessor.listPredecessor = node;
				node.listSuccessor = parent.listSuccessor;
			}
			node.listPredecessor = parent;
			parent.listSuccessor = node;

			// update successor of parent in tree
			previousSuccessor = parent.rightTreeSuccessor;
			parent.rightTreeSuccessor = node;
		}

		if (parent.content.compareTo(node.content) > 0) {
			// update predecessors und successors in list: parent -> node
			if (parent.listPredecessor != null) {
				parent.listPredecessor.listSuccessor = node;
				node.listPredecessor = parent.listPredecessor;
			}
			node.listSuccessor = parent;
			parent.listPredecessor = node;
			if (this.listRoot == parent) {
				this.listRoot = node;
			}

			// update successor of parent in tree
			previousSuccessor = parent.leftTreeSuccessor;
			parent.leftTreeSuccessor = node;
		}

		if (previousSuccessor == null) {
			return;
		}

		// update successor of node in tree
		if (node.content.compareTo(previousSuccessor.content) < 0) {
			node.rightTreeSuccessor = previousSuccessor;
		}

		if (parent.content.compareTo(node.content) > 0) {
			node.leftTreeSuccessor = previousSuccessor;
		}
	}

	private void rebalance() {
		// TODO
	}

	private Node getParentTreeNode(Node node) {
		if (node == null || node.content == null) {
			return null;
		}

		Node previousNode = null;
		Node nodeToCheck = this.treeRoot;

		while (nodeToCheck != null) {
			if (nodeToCheck.content.equals(node.content)) {
				return previousNode;
			}

			if (nodeToCheck.content.compareTo(node.content) < 0) {
				previousNode = nodeToCheck;
				nodeToCheck = nodeToCheck.rightTreeSuccessor;
				continue;
			}

			if (nodeToCheck.content.compareTo(node.content) > 0) {
				previousNode = nodeToCheck;
				nodeToCheck = nodeToCheck.leftTreeSuccessor;
				continue;
			}
		}

		return previousNode;
	}

	public String getTreeString() {
		StringBuilder sb = new StringBuilder();

		if (this.listRoot == null) {
			return "null";
		}

		this.getTreeString(sb, 0, this.treeRoot);

		return sb.toString();
	}

	private void getTreeString(StringBuilder sb, int indentationLevel, Node node) {
		sb.append(System.lineSeparator());
		for (int i = 0; i < indentationLevel; ++i) {
			sb.append("  ");
		}

		if (node == null) {
			sb.append("-");
			return;
		}

		sb.append(node.content);

		this.getTreeString(sb, indentationLevel + 1, node.leftTreeSuccessor);
		this.getTreeString(sb, indentationLevel + 1, node.rightTreeSuccessor);
	}

	@Override
	public String toString() {
		List<Node> nodes = new LinkedList<>();
		for (Node node = this.listRoot; node != null; node = node.listSuccessor) {
			nodes.add(node);
		}

		List<String> nodeString = nodes.stream()
			.map(node -> node.content.toString())
			.collect(Collectors.toList());

		return String.join(" -> ", nodeString);
	}

	private class Node {
		T content = null;
		Node listPredecessor = null;
		Node listSuccessor = null;
		Node leftTreeSuccessor = null;
		Node rightTreeSuccessor = null;


		Node(T content) {
			this.content = content;
		}
	}
}
