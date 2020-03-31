package edu.baylor.cs.noddy;

import java.util.Set;

public abstract class Worker implements Runnable, Cloneable {

	private String name;
	private Set<Worker> finishSet;

	public Worker() {
		this.setName("unknown");
	}

	public Worker(String name, Set<Worker> finishSet) {
		this.setName(name);
		this.setFinishSet(finishSet);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Worker> getFinishSet() {
		return finishSet;
	}

	public void setFinishSet(Set<Worker> finishSet) {
		this.finishSet = finishSet;
	}

	@Override
	public Worker clone() throws CloneNotSupportedException {
		Worker w = (Worker) super.clone();
		w.finishSet = Set.copyOf(finishSet);

		// Since strings are immutable, nothing needs done with name
		return w;
	}

}
