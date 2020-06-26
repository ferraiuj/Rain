package com.einstein.rain.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.einstein.rain.level.Level;
import com.einstein.rain.level.Node;
import com.einstein.rain.level.tile.Tile;

public class Vector2i {

	public int x;
	public int y;
	public Tile tileCheck;
	int runCounter;
	private Comparator<Node> nodeSorter = new Comparator<Node>() {
		public int compare(Node n0, Node n1) {
			if (n1.fCost < n0.fCost)
				return +1; // Moves position in array
			if (n1.fCost > n0.fCost)
				return -1;
			return 0;
		}
	};

	public Vector2i() {
		set(0, 0);

	}

	public Vector2i(Vector2i vector) {
		set(vector.x, vector.y);
	}

	public Vector2i(int x, int y) {
		set(x, y);
	}

	public void set(int x, int y) {
		this.x = x;
		this.y = y;

	}

	public int getX() {
		return x;

	}

	public int getY() {
		return y;
	}

	public Vector2i add(Vector2i vector) {
		this.x += vector.x;
		this.y += vector.y;
		return this;

	}

	public Vector2i addCustomAmount(int value) {
		this.x += value;
		this.y += value;
		return this;
	}
	public Vector2i subtract(Vector2i vector) {
		this.x -= vector.x;
		this.y -= vector.y;
		return this;
	}
	public Vector2i setX(int x) {
		this.x = x;
		return this;
	}

	public Vector2i setY(int y) {
		this.y = y;
		return this;
	}

	public static double getDistance(Vector2i v0, Vector2i v1) {
		double x = v0.getX() - v1.getX();
		double y = v0.getY() - v1.getY();
		return Math.sqrt(x * x + y * y);
	}

	public boolean equals(Object object) {
		if (!(object instanceof Vector2i))
			return false;
		Vector2i vec = (Vector2i) object;
		if (vec.getX() == this.getX() && vec.getY() == this.getY())
			return true;
		return false;

	}

	public List<Node> findPath(Vector2i start, Vector2i goal, Level level) {

		List<Node> openList = new ArrayList<Node>();
		List<Node> closedList = new ArrayList<Node>();

		Node currentNode = new Node(start, null, 0, getDistance(start, goal));

		openList.add(currentNode);

		while (openList.size() > 0) {

			Collections.sort(openList, nodeSorter);

			currentNode = openList.get(0);

			if (currentNode.tile.equals(goal)) {

				List<Node> path = new ArrayList<Node>();

				while (currentNode.parent != null) {
					System.out.println(openList.size() + "holy fuck");
					path.add(currentNode);
					currentNode = currentNode.parent;
				}
				openList.clear();
				closedList.clear();

				return path;
			}
			openList.remove(currentNode);
			closedList.add(currentNode);

			for (int tileCount = 0; tileCount < 9; tileCount++) {

				if (tileCount == 4)
					continue;

				int xTileCoordinate = currentNode.tile.getX();
				int yTileCoordinate = currentNode.tile.getY();
				int xTileCoordOffset = (tileCount % 3) - 1;
				int yTileCoordOffset = (tileCount / 3) - 1;

				//tileCheck = new Tile();
				tileCheck = level.getTile(xTileCoordinate + xTileCoordOffset, yTileCoordinate + yTileCoordOffset,
						level);

				if (tileCheck == null)
					continue;
				// System.out.println("tileCheck.solid()" + tileCheck.solid() + " count = " +
				// tileCount);
				if (tileCheck.solid())
					continue;

				Vector2i moveTile = new Vector2i(xTileCoordinate + xTileCoordOffset,
						yTileCoordinate + yTileCoordOffset);

				double gCost = currentNode.gCost + (getDistance(currentNode.tile, moveTile) == 1 ? 1 : .95);
				double hCost = getDistance(moveTile, goal);

				Node node = new Node(moveTile, currentNode, gCost, hCost);

				if (vecInList(closedList, moveTile) && gCost >= node.gCost)
					continue;
				if (!vecInList(openList, moveTile) || gCost < node.gCost)
					openList.add(node);
				runCounter++;				
			}
		}
		System.out.println("Run Counter Check Tile block = " + closedList.size() + " run counter = " + runCounter);
		closedList.clear();
		return null;
	}

	private boolean vecInList(List<Node> list, Vector2i vector) {
		for (Node n : list) {
			if (n.tile.equals(vector))
				return true;
		}
		return false;
	}

//	private double getDistance(Vector2i tile, Vector2i goal) {
//		double dx = tile.getX() - goal.getX();
//		double dy = tile.getY() - goal.getY();
//		return Math.sqrt(dx * dx + dy * dy);
//	}

}
