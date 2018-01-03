package me.yogeshwar.producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;

public interface AbstractConsumer {
	void setQueue(ArrayBlockingQueue<Object> queue);

	/**
	 * starts consumption from the queue tries to consume within a given time period
	 * in seconds
	 * 
	 * @param secs
	 */
	void startConsumption(int seconds);

	void stopConsumtion();
}
