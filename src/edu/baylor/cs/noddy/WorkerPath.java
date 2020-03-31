package edu.baylor.cs.noddy;

import java.util.Set;

public class WorkerPath extends Worker {

	public WorkerPath() {
		super();
	}

	public WorkerPath(String name, Set<Worker> finishSet) {
		super(name, finishSet);
	}

	@Override
	public void run() {
		try {
			Thread.sleep(10000);
			a();
			for (int i = 0; i < 10; i++) {
				c();
			}

			getFinishSet().add(this);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	private void a() throws InterruptedException {
		Thread.sleep(1000);
		b();
	}

	private void b() throws InterruptedException {
		Thread.sleep(5000);
		c();
	}

	private void c() throws InterruptedException {
		Thread.sleep(7000);
	}

	@Override
	public Worker clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
