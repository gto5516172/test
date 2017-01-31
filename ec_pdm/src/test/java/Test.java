import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
	
	public static void main(String[] args) {
		BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
		ThreadPoolExecutor tpe = new ThreadPoolExecutor(5, 15, 600, TimeUnit.DAYS, queue);
		for(int i = 0; i < 100; i++) {
			tpe.execute(new TestThread("na" + i));
		}
	}
}
