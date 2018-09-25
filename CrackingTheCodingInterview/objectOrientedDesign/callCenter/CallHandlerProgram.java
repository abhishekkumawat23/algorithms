package objectOrientedDesign.callCenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CallHandlerProgram {
	
	private HashMap<JobTitle, List<Employee>> employees = new HashMap<JobTitle, List<Employee>>();
	private Map<JobTitle, Queue<Call>> callQueues = new HashMap<JobTitle, Queue<Call>>();
	
	private static CallHandlerProgram instance;

	public static CallHandlerProgram getInstance() {
		if (instance == null) {
			instance = new CallHandlerProgram();
		}
		return instance;
	}
	
	public void dispatchCall(Call call) {
		dispatchCall(call, JobTitle.Respondent);
	}

	private void dispatchCall(Call call, JobTitle level) {
		Employee handler = findHandlerForCall(call, level);
		if (handler != null) {
			dispatchCall(handler, call);
		}
		else {
			addCallInQueue(call, level);
		}
	}
	
	// This method should be called in background thread as it takes time for call to complete.
	private void dispatchCall(Employee handler, Call call) {
		call.setHandler(handler);
		handler.receiveCall();
	}

	// Call this method every few seconds.
	public void assignCallsInQueue() {
		for (Map.Entry<JobTitle, Queue<Call>> levelCallQueues : callQueues.entrySet()) {
			Employee handler = findHandlerForCall(levelCallQueues.getValue().peek(), levelCallQueues.getKey());
			if (handler != null) {
				dispatchCall(handler, levelCallQueues.getValue().poll());
			}
		}
	}
	
	public void escalateCall(Call call, JobTitle currentLevel) {
		JobTitle[] jobTitles = JobTitle.values();
		JobTitle nextLevel = jobTitles[(currentLevel.ordinal()+1) % jobTitles.length];
		dispatchCall(call, nextLevel);
	}

	private void addCallInQueue(Call call, JobTitle level) {
		callQueues.get(level).add(call);
	}

	private Employee findHandlerForCall(Call call, JobTitle level) {
		List<Employee> levelEmployees = employees.get(level);
		for (Employee employee: levelEmployees) {
			if (employee.isFree()) {
				return employee;
			}
		}
		return null;
	}

}
