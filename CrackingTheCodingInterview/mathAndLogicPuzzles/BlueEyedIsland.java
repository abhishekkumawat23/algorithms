package mathAndLogicPuzzles;

/**
 * A bunch of people are living on an island, when a visitor comes with a strange order:
 * all blue-eyes people must leave the island ASAP.
 * There will be a flight out at 8:00 pm every evening.
 * Each person can see everyone else's eye color, but they don not know their own (nor is anyone allowed to tell them)
 * Additionally, they do not know how many people have blue eyes, although they do know that atleast one person does.
 * How many days will it take the blue-eyed people to leave? 
 */
public class BlueEyedIsland {

	/**
	 * Lets say there are c people out of n people who are blue eyed.
	 * A person can see all other c-1 blue eyed persons.
	 * If c is 2, he can see 1 blue eyed. He will wait one evening to see if that person leaves.
	 * If that people doesn't leave in evening, then this means he will assume that he is the remaining blue eyed and he will leave that evening.
	 * So, c people will wait for c-1 evenings that other people will leave. If not, all c people will leave on cth day.
	 */
}
