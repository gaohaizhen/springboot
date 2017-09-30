package cn.demo.thread;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
	public static void main(String[] args) {
		final BlockingQueue queue = new ArrayBlockingQueue(3);
		//两个线程放数据，另外两个线程取数据
		for(int i=0;i<2;i++){
			new Thread(){
				public void run(){
					while(true){
						try {
							Thread.sleep((long)(Math.random()*1000));
							System.out.println(Thread.currentThread().getName() + "开始放入数据");
							queue.put(1);
							System.out.println(Thread.currentThread().getName() + "已经存放进去数据，" +
										"当前队列大小为" + queue.size());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
				}
				
			}.start();
		}

		new Thread(){
			public void run(){
				while(true){
					try {
						Thread.sleep(1000);
						System.out.println(Thread.currentThread().getName() + "开始获取数据");
						queue.take();
						System.out.println(Thread.currentThread().getName() + "获取到数据，" +
								"当前队列大小为" + queue.size());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}.start();
	}
}
