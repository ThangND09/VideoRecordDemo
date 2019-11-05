package thangND15.threadPool;

public class TestScriptRunner implements Runnable {
	
	public void run() {
		while (ThreadImplement.counter.get() < ThreadImplement.sumFunction) {
			try {
				Thread.sleep(111);
			} catch (InterruptedException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}
			incrementCounter();
		}
	}

	static synchronized void incrementCounter() {
		
		ThreadImplement.counter.getAndIncrement();

		// System.out.println(Thread.currentThread().getName() + ": " +
		// ThreadImplement.counter.getAndIncrement());

		//ThreadImplement.counter.set(ThreadImplement.counter.get() + 1);

		System.out.println(Thread.currentThread().getName() + ": " + ThreadImplement.counter.get());

		// For 25%

		if (ThreadImplement.counter.get() == ThreadImplement.sumFunction / 4) {

			sendMail(ThreadImplement.counter.get());

		} else if (ThreadImplement.counter.get() == ThreadImplement.sumFunction / 2) {

			sendMail(ThreadImplement.counter.get());

		}

	}

	static synchronized void sendMail(int number) {

		System.out.println(Thread.currentThread().getName() + " " + number
				+ " roi ------------------------------------------------------------");

	}
}
