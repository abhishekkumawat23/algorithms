package recursionAndDynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * Imagine a robot sitting on the upper left corner of maze with r rows and c columns.
 * The robot can only move in two directions, right and down, but certain cells are "off limits" such that the robot can't step on them.
 * Design an algorithm to find a path for the robot from top left to bottom right.
 */
public class RobotInAGrid {

	/**
	 * Time: O(2^(r*c)); Space: O(r+c)
	 * 
	 * Important points:
	 * 1. We find first path and return it.
	 * 2. Add node in path and remove it again if path is not possible.
	 * 3. Here path(i,j) ~= add(path(i+1, j), element(i,j)) or add(path(i, j+1), element(i,j))
	 *    i.e. (3,4) depends on (4,4) and (3,5). Also, (4,3) depends on (5,3) and (4,4). Thus there is overlapping.
	 * 4. As we are doing nothing about overlapping, we are being inefficient.
	 * 
	 * findPath(i, j, maze, path):
	 *   if i >= maze.length || j >= maze[0].length || !maze[i][j]
	 *     return false
	 *   path.add(point(i,j))
	 *   if (i == maze.length-1 && j == maze[0].length-1) || findPath(i, j+1, maze, path) || findPath(i+1, j, maze, path)
	 *     return true
	 *   else
	 *     path.removeLast
	 *     return false
	 */
	public boolean findPath_recursion(int i, int j, boolean[][] maze, ArrayList<Point> path){
		if (i >= maze.length || j >= maze[0].length || !maze[i][j]) {
			return false;
		}
		
		path.add(new Point(i, j));
		if ((i == maze.length-1 && j == maze.length-1) ||
				findPath_recursion(i, j+1, maze, path) ||
				findPath_recursion(i+1, j, maze, path)) {
			return true;
		}
		else {
			path.remove(path.size()-1);
			return false;
		}
	}
	
	/**
	 * Time: O(r*c); Space: O(r+c)
	 * 
	 * Important points:
	 * 1. After memoization, we need to calculate a node path once only. Thus, time is O(r*c)
	 * 2. In top down approach, we are adding the point in path without knowing whether its a valid path or not. If path is not valid, then we remove those path points from list.
	 *    In case we use bottom up approach, we will add path in point only if point is part of valid path. Thus bottom up approach is little better.
	 * 
	 * findPath(i, j, maze, path, pathNotAccessible):
	 *   if i >= maze.length || j >= maze[0].length || !maze[i][j] || pathNotAccessible[i][j]
	 *     return false
	 *   path.addLast(point(i,j))
	 *   if (i == maze.length-1 && j == maze[0].length-1) || findPath(i, j+1, maze, path, pathNotAccessible) || findPath(i+1, j, maze, path, pathNotAccessible)
	 *     return true
	 *   else
	 *     path.removeLast
	 *     pathNotAccessible[i][j] = true
	 *     return false
	 */
	public boolean findPath_topDown_memoization(int i, int j, boolean[][] maze, ArrayList<Point> path, boolean[][] pathNotAccessible){
		if (i >= maze.length || j >= maze[0].length || !maze[i][j] || pathNotAccessible[i][j]) {
			return false;
		}
		path.add(new Point(i, j));
		if ((i == maze.length-1 && j == maze[0].length-1) ||
				findPath_topDown_memoization(i, j+1, maze, path, pathNotAccessible) ||
				findPath_topDown_memoization(i+1, j, maze, path, pathNotAccessible)) {
			return true;
		}
		else {
			path.remove(path.size()-1);
			pathNotAccessible[i][j] = true;
			return false;
		}
	}
	
	/**
	 * Time: O(r*c); Space: O(r+c)
	 * 
	 * Important points:
	 * 1. Time complexity of top down and bottom up for this problem is same.
	 * 2. But bottom up is still better as we don't need to add and then remove invalid points. We are adding only valid points in path.
	 * findPath(i, j, maze, path, pathNotAccessible):
	 *   if i < 0 || j < 0 || !maze[i][j] || pathNotAccessible[i][j]
	 *     return false
	 *   if (i == 0 && j == 0) || findPath(i-1, j, maze, path, pathNotAccessible) || findPath(i, j-1, maze, path, pathNotAccessible)
	 *     path.addLast(point(i, j)
	 *     return true
	 *   else
	 *     pathNotAccessible[i][j] = true
	 *     return false
	 */
	public boolean findPath_bottomUp_dp(int i, int j, boolean[][] maze, ArrayList<Point> path, boolean[][] pathNotAccessible){
		if (i < 0 || j < 0 || !maze[i][j] || pathNotAccessible[i][j]) {
			return false;
		}
		if ((i == 0 && j == 0) ||
				findPath_bottomUp_dp(i-1, j, maze, path, pathNotAccessible) ||
				findPath_bottomUp_dp(i, j-1, maze, path, pathNotAccessible)) {
			path.add(new Point(i, j));
			return true;
		}
		else {
			pathNotAccessible[i][j] = true;
			return false;
		}
	}
	
	private class Point {
		private int i;
		private int j;
		
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
