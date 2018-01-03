package me.yogeshwar.producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;

public interface AbstractProducer {
	void setQueue(ArrayBlockingQueue<Object> queue);

	/**
	 * starts production to the queue 
	 * with set time of production in seconds
	 * @param secs
	 */
	void startProduction(int seconds);

	void stopProduction();
}
