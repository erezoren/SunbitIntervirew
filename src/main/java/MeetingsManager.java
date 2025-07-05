import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class MeetingsManager {

  TreeMap<Integer, Integer> rooms = new TreeMap<>();
  int minRooms = 0;
  boolean isOverlap = false;


  public void addMeeting(int start, int end) {
    rooms.put(start + 1, rooms.getOrDefault(start + 1, 0) + 1);
    rooms.put(end + 1, rooms.getOrDefault(end + 1, 0) - 1);
    int maxCount = 0;
    int count = 0;
    for (Map.Entry<Integer, Integer> entry : rooms.entrySet()) {
      count += entry.getValue();
      if (count > maxCount) {
        maxCount = count;
      }
    }
    minRooms = maxCount;
    if (minRooms > 1) {
      isOverlap = true;
    }
  }

  public boolean hasOverlappingTimes() {
    return isOverlap;
  }

  public int minMeetingRooms() {
    return minRooms;
  }

  public static void main(String[] args) {
    MeetingsManager meetingsManager = new MeetingsManager();

    meetingsManager.addMeeting(1, 4);

    System.out.println(meetingsManager.hasOverlappingTimes());
    System.out.println(meetingsManager.minMeetingRooms());
  }
}


/*

Example: Suppose we use interval (start, end) to denote the start and end time of the meeting, we have the following meeting times: (1,4), (4,5), (6,8), (2,6)

In the above example, we should return true for the first question since (1, 4) and (2, 6) have overlaps. The minimum number of rooms for the example is 2.

MeetingsManager meetingsManager = new MeetingsManager();
meetingsManager.addMeeting({1, 4});
meetingsManager.addMeeting({4, 5});
meetingsManager.addMeeting({6, 8});

meetingsManager.addMeeting({2, 6}); 1
meetingsManager.addMeeting({7, 9}); 2


System.out.println("Do any of the meetings overlap? " + meetingsManager.hasOverlappingTimes();
System.out.println("Minimum number of rooms required: " + meetingsManager.minMeetingRooms();

Do any of the meetings overlap? true
Minimum number of rooms required: 2
 */