package cn.demo.thread;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {

	public static void main(String[] args) {
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		final Exchanger exchanger2 = new Exchanger();
		final Exchanger exchanger = new Exchanger();


		cachedThreadPool.execute(new Runnable(){
			public void run() {
				try {				

					String data1 = "123";
					System.out.println("线程" + Thread.currentThread().getName() +
					"放入" + data1);
					Thread.sleep((long)(Math.random()*10000));
					String data2 = (String)exchanger2.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName() +
					"得到" + data2);
				}catch(Exception e){
					
				}
			}	
		});
		cachedThreadPool.execute(new Runnable(){
			public void run() {
				try {				

					String data1 = "456";
					System.out.println("线程" + Thread.currentThread().getName() +
					"放入" + data1);
					Thread.sleep((long)(Math.random()*10000));					
					String data2 = (String)exchanger.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName() +
					"得到" + data2);
				}catch(Exception e){
					
				}				
			}	
		});


		cachedThreadPool.execute(new Runnable(){
			public void run() {
				try {

					String data1 = "abc";
					System.out.println("线程" + Thread.currentThread().getName() +
							"放入" + data1);
					Thread.sleep((long)(Math.random()*10000));
					String data2 = (String)exchanger.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName() +
							"得到" + data2);
				}catch(Exception e){

				}
			}
		});

		cachedThreadPool.execute(new Runnable(){
			public void run() {
				try {

					String data1 = "def";
					System.out.println("线程" + Thread.currentThread().getName() +
							"放入" + data1);
					Thread.sleep((long)(Math.random()*10000));
					String data2 = (String)exchanger2.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName() +
							"得到" + data2);
				}catch(Exception e){

				}
			}
		});
	}
}
