package mathAndLogicPuzzles;

/**
 * There are three ants on different vertices of a triangle.
 * What is the probability of collision (between any two or all of them) if they start walking on the sides on the triangle?
 * Assume that each ant randomly picks a direction, with either direction being equally likely to be chosen, and that they walk at same speed.
 * Similarly, find the probability of collision with n ants on an n-vertex polygon.
 */
public class AntsOnTriangle {

	/**
	 * There will be no collision when all move in same direction.
	 * So P(no collision) = 1/2*1/2*1/2 for clockwise + 1/2*1/2*1/2 for anti-clockwise = 1/4
	 * So P(collision) = 1 - 1/4 = 3/4
	 * 
	 * P(collision) = 1- (1/2^(n-1))
	 */
}
