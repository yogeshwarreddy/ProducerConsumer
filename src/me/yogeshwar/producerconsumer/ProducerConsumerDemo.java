package me.yogeshwar.producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;

public class ProducerConsumerDemo {

	public static void main(String[] args) {
		ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(5);
		Producer[] productiveProducers = Producer.createNProducers(3);
		Producer[] lazyProducers = Producer.createNProducers(4);
		Consumer[] hungrierConsumers = Consumer.createNConsumers(4);
		Consumer[] lessHungryConsumers = Consumer.createNConsumers(6);
		for (Producer producer : productiveProducers) {
			producer.setQueue(queue);
			producer.startProduction(2);
		}
		for (Consumer consumer : lessHungryConsumers) {
			consumer.setQueue(queue);
			consumer.startConsumption(3);
		}
		for (Consumer consumer : hungrierConsumers) {
			consumer.setQueue(queue);
			consumer.startConsumption(2);
		}
		for (Producer producer : lazyProducers) {
			producer.setQueue(queue);
			producer.startProduction(3);
		}
		try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (Producer producer : productiveProducers) {
			producer.stopProduction();
		}
		for (Consumer consumer : lessHungryConsumers) {
			consumer.stopConsumtion();
		}
		for (Consumer consumer : hungrierConsumers) {
			consumer.stopConsumtion();
		}
		for (Producer producer : lazyProducers) {
			producer.stopProduction();
		}
	}

}
