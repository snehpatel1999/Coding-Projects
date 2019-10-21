
/**
 * TODO Put here a description of what this class does.
 *
 * @author sneh.
 *         Created Sep 9, 2019.
 */
public class Instructor {
	private Section[] sections;
}

class Room {
	private Section[] sections;
}

class Course {
	private Section[] sections;
}

class Section {
	private Course course;
	private Room room;
	private Instructor instructor;
}