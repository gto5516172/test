import java.util.concurrent.locks.ReentrantLock;

public class TestThread implements Runnable {
	
	private String name;
	
	private static final ReentrantLock lock = new ReentrantLock();
	
	public TestThread(String name) {
		this.name = name;
	}
	

	@Override
	public void run() {
		System.out.println("start================================="+name);
//		for(int i = 0; i < 200; i++) {
//			System.out.println(name + "======a" + i);
//		}
		lock.lock();
		for(int i = 0; i < 50; i++) {
			System.out.println(name + "======b" + i);
		}
		lock.unlock();
		System.out.println("end===================================="+name);

	}

}
