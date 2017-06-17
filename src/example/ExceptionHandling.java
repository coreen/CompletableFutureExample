package example;

import java.util.concurrent.CompletableFuture;

public class ExceptionHandling {
	public static void main(String[] args) {
		CompletableFuture
			.supplyAsync(() -> {
				throw new RuntimeException("burned");
			})
			.exceptionally((throwable) -> {
				String errMsg = throwable.getCause().getMessage();
				if (errMsg.equals("burned")) {
					System.out.println("pizza was overcooked");
				}
				return null;
			})
			.join();
	}
}
