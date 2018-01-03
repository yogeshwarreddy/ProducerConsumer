/**
 * 
 */
package me.yogeshwar.producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Yogeshwar
 *
 */
public class Consumer implements AbstractConsumer {

	ArrayBlockingQueue<Object> queue;
	Thread consumerThread;

	static Consumer[] createNConsumers(int n) {
		Consumer[] consumers = new Consumer[n];
		for (int i = 0; i < n; i++)
			consumers[i] = new Consumer();
		return consumers;
	}

	@Override
	public void setQueue(ArrayBlockingQueue<Object> queue) {
		this.queue = queue;
	}

	@Override
	public void startConsumption(int seconds) {
		consumerThread = new Thread(() -> {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					System.out.println("Consumer:" + Thread.currentThread().getName() + "is Hungry");
					Object element = queue.take();
					System.out.println(
							"Consumer:" + Thread.currentThread().getName() + " consumed " + element.toString());
					Thread.sleep(seconds * 1000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}

			}
			System.out.println("Consumer:" + Thread.currentThread().getName() + " terminating");
		});
		consumerThread.start();
	}

	@Override
	public void stopConsumtion() {
		consumerThread.interrupt();
	}

}
