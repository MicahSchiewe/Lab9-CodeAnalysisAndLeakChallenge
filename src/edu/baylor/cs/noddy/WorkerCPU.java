package edu.baylor.cs.noddy;

import java.util.Set;

public class WorkerCPU extends Worker {

	public WorkerCPU() {
		super();
	}

	public WorkerCPU(String name, Set<Worker> finishSet) {
		super(name, finishSet);
	}

	@Override
	public void run() {
		try {
// This was not highlighted by SpotBugs, but it code does nothing and wastes processing power
//			if (true) {
//				for (;;) {
//				}
//			}
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
