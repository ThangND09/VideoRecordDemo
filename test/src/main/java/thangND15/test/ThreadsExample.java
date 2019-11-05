package thangND15.test;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadsExample implements Runnable {
	static AtomicInteger counter = new AtomicInteger(0);
	static boolean check = false;

	public ThreadsExample() {
	}

	static void incrementCounter() {
		System.out.println(Thread.currentThread().getName() + ": " + counter.incrementAndGet());
		if (counter.get() == 250) {
			sayGoodbye(counter.get());
			check = false;
		}
		if (counter.get() == 500) {
			sayGoodbye(counter.get());
		}
		
	}
	
	public void run() {
		while (counter.get() < 1000) {
            incrementCounter();
        }
	}

	public static void main(String[] args) {
		ThreadsExample te = new ThreadsExample();
		Thread thread1 = new Thread(te);
		Thread thread2 = new Thread(te);
		Thread thread3 = new Thread(te);
		Thread thread4 = new Thread(te);
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
	}
	
	public static synchronized void sayGoodbye(int number) {
		if (check == false) {
			System.out.println(number + " roi ------------------------------------------------------------");
			check = true;
		}
		
	}

	

}
