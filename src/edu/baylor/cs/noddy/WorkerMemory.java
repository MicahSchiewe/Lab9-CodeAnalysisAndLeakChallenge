package edu.baylor.cs.noddy;

import java.util.Set;

public class WorkerMemory extends Worker {

	public WorkerMemory() {
		super();
	}

	public WorkerMemory(String name, Set<Worker> finishSet) {
		super(name, finishSet);
	}

	@Override
	public void run() {
		try {
			// Cache to avoid repeat unnecessary calls to getName
			final String NAME = getName();
			String s = "SSSSSSSSSSSSSSSSSSSSSSSSSSSSSS";

			// Create easily editable version of s.
			StringBuilder buildS = new StringBuilder(s);

			if (true) {
				for (int i = 0; i < 20; i++) {
					buildS.append(s);
					buildS.append(NAME);
				}
			}

			// Stand-in for performing work with the result of the concatenations
			System.out.println("Perform an action with:");
			System.out.println(buildS.toString());

			Thread.sleep(Integer.MAX_VALUE);
			getFinishSet().add(this);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	@Override
	public Worker clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
