package edu.baylor.cs.noddy;

import java.util.HashSet;
import java.util.Set;

public class Main {

	private static final int USERS = 10;

	public static void main(String[] args) throws Exception {

		if (args == null || args.length == 0) {
			System.out.println("Usage: <1|2|3|4>");
			return;
		}

		String option = args[0];

		Worker worker = null;
		if ("1".equals(option)) {
			worker = new WorkerCPU();
		} else if ("2".equals(option)) {
			worker = new WorkerMemory();
		} else if ("3".equals(option)) {
			worker = new WorkerFileHandleLeak();
		} else {
			worker = new WorkerPath();
		}
		Set<Worker> finishSet = new HashSet<>();

		Thread.sleep(5000);
		for (int i = 1; i < USERS + 1; i++) {
			Thread.sleep(1000);
			
			Worker actual = worker.clone();
			actual.setName("user_" + i);
			actual.setFinishSet(finishSet);
			Thread t = new Thread(actual);
			t.start();
		}

		Thread.sleep(Integer.MAX_VALUE);

	}
}
