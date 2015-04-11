package qgb.thread;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import qgb.T;
/**每个TaskQueue对象将会占用一个新线程</br>
 * TODO:添加【关闭并销毁TaskQueue对象】功能</br>
 * 不能通过extends ExeThread 来避免二次出现 invoke方法。***/
public class TaskQueue {
	public static void main(String[] args) {
		TaskQueue dbRun=new TaskQueue();
		for (int i = 0; i < 99; i++) {
			final int index=i;
			Runnable run=new Runnable() {
				@Override
				public void run() {
					T.print("%d at %s start!",index,T.getCurrentThreadName());
					T.sleep(2000);
					T.print("%d at %s stop!",index,T.getCurrentThreadName());
				}
			};
			dbRun.invoke(run);	
			T.msgbox(i);
		}
		
	}	
	////////////////////////////////////////////
	private final ExeThread gExe=new ExeThread();
	public TaskQueue() {
		new Thread(gExe).start();
	}	
	
	public boolean invoke(Runnable aqr) {
		return gExe.invoke(aqr);
	}	
}

class ExeThread implements Runnable{
	LinkedBlockingQueue<Runnable> gQRunQueue=new LinkedBlockingQueue<Runnable>();	
	public boolean invoke(Runnable aqr) {
		if (aqr==null)T.argsError(aqr);
		return gQRunQueue.add(aqr);
	}

	@Override
	public void run() {
		Runnable qRun=null;
		try {
			qRun=gQRunQueue.poll(99999,TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		qRun.run();
		run();
	}
}