package example;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class AsyncSyncMix {
	public static void main(String[] args) {
		List<Integer> integers = new ArrayList<Integer>();
		integers.add(1);
		integers.add(2);
		integers.add(3);
		
		integers
			.stream()
			.map(integer -> {
				System.out.println("processing integer: " + integer);
				return isEven(integer)
						.thenAccept(result -> {
							System.out.println("integer is " + (result ? "even" : "odd"));
						})
						.join();
			})
			.collect(Collectors.toList());
	}

	private static CompletableFuture<Boolean> isEven(int integer) {
		return CompletableFuture.completedFuture(integer % 2 == 0);
	}
}
