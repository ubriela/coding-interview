
public class TaskScheduler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(scheduleLenth("ABCABC", 3));
	}

	
	// FB interview: Task Scheduler
	//
	// ABCABC 3
	// ABC_ABC --> 7
	//
	// AABBCC 2
	// A__AB__BC__C --> 12
	// complexity = O(N*gap), N is the number of tasks
	public static int scheduleLenth(String tasks, int gap) {  // tasks = ABCABC, gap = 3
	  String outSchedule;  // ABC_ABC
	  if (tasks == null || tasks.length() ==0)
	    return -1;
	  // out = ABC
	  outSchedule = new String(tasks.substring(0,1));  // out = "A"
	  for (int i = 1; i < tasks.length(); i++) { // i = 3 
	    int j = outSchedule.length()-1;  // j = 2
	    int tmp = j;
	    while (j >= tmp-gap && j >=0 ) {
	        if (outSchedule.charAt(j) == tasks.charAt(i)) {  // A =? B
	        	String gapString = "";
	        	for (int k = 0; k < gap+j-tmp; k++) 
	        		gapString = gapString + "_";
	        	outSchedule = outSchedule + gapString;
	          break;
	        }
	      j--;
	    }
	    outSchedule = outSchedule + tasks.charAt(i);  // out = "AB" --> "ABC_A"
	  }
	  
	  System.out.println(outSchedule);
	  return outSchedule.length();
	}
}
