package example;

import java.util.concurrent.CompletableFuture;

public class ApplyAll {
	public static void main(String[] args) {
		// 1. Define the steps to set a table.
		CompletableFuture[] steps = new CompletableFuture[] {
				CompletableFuture.supplyAsync(() -> setPlates()),
				CompletableFuture.supplyAsync(() -> setNapkins())
		};
		/*
		 * Note that supplier function must return a non-Void result.
		 * 
		 * Time to complete each task identical so can see that the
		 * order returned can vary based on which thread finishes
		 * execution first.
		 */
		// 2. Wait for all to complete before reporting ready.
		CompletableFuture.allOf(steps).join();
		System.out.println("setTable complete");
	}
	
	/**
	 * Simulates time to set plates on table.
	 */
	private static boolean setPlates() {
		try {
			Thread.sleep(2000L); // 2 sec
			System.out.println("Plates are set");
			return true;
		} catch(InterruptedException e) {
			System.out.println("Couldn't set plates");
		}
		return false;
	}

	/**
	 * Simulates time to set napkins on table.
	 */
	private static boolean setNapkins() {
		try {
			Thread.sleep(2000L); // 2 sec
			System.out.println("Napkins are set");
			return true;
		} catch(InterruptedException e) {
			System.out.println("Napkins flew away");
		}
		return false;
	}
}
