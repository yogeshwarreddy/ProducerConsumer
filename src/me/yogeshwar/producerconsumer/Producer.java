package me.yogeshwar.producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;

public class Producer implements AbstractProducer {
	ArrayBlockingQueue<Object> queue;
	Thread producerThread;

	static Producer[] createNProducers(int n) {
		Producer[] producers = new Producer[n];
		for (int i = 0; i < n; i++)
			producers[i] = new Producer();
		return producers;
	}

	@Override
	public void setQueue(ArrayBlockingQueue<Object> queue) {
		this.queue = queue;
	}

	@Override
	public void startProduction(int seconds) {
		producerThread = new Thread(() -> {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					System.out.println("Producer:" + Thread.currentThread().getName() + "is ready for production");
					Object element = new Object() {
						public String toString() {
							return "Unit " + this.hashCode();
						}
					};
					queue.put(element);
					System.out.println(
							"Producer:" + Thread.currentThread().getName() + " produced " + element.toString());
					Thread.sleep(seconds * 1000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}

			}
			System.out.println("Producer:" + Thread.currentThread().getName() + " terminating");
		});
		producerThread.start();
	}

	@Override
	public void stopProduction() {
		producerThread.interrupt();

	}

}
