import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import lombok.AllArgsConstructor;
import lombok.Data;

public class MeetingsManager2 {

  class MeetingRoom {

    Set<Meeting> meetings = new HashSet<>();

    public MeetingRoom(Meeting meeting) {
      meetings.add(meeting);
    }

    public void book(Meeting meeting) {
      meetings.add(meeting);
    }

    public boolean isOverLap(Meeting meeting) {
      for (Meeting m : meetings) {
        if (m.start < meeting.end && meeting.start < m.end) {
          return true;
        }
      }

      return false;
    }
  }

  @Data
  @AllArgsConstructor
  static class Meeting {

    int start;
    int end;
  }

  Set<MeetingRoom> rooms = new HashSet<>();

  public void addMeeting(Meeting meeting) {
    if (rooms.isEmpty()) {
      rooms.add(new MeetingRoom(meeting));
      return;
    }

    for (MeetingRoom room : rooms) {
      if (!room.isOverLap(meeting)) {
        room.book(meeting);
        return;
      }
    }

    rooms.add(new MeetingRoom(meeting));
  }

  public boolean hasOverlappingTimes() {
    return rooms.size() > 1;
  }

  public int minMeetingRooms() {
    return rooms.size();
  }

  public static void main(String[] args) {
    MeetingsManager2 meetingsManager = new MeetingsManager2();

    meetingsManager.addMeeting(new Meeting(1, 4));
    meetingsManager.addMeeting(new Meeting(1, 4));

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