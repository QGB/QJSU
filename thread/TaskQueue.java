package qgb.thread;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import qgb.*;

public class TaskQueue {
	public static void main(String[] args) {
		TaskQueue dbRun=new TaskQueue();
		for (int i = 0; i < 99; i++) {
			final int index=i;
			Runnable run=new Runnable() {
				@Override
				public void run() {
					U.print("%d at %s start!",index,U.getCurrentThreadName());
					U.sleep(2000);
					U.print("%d at %s stop!",index,U.getCurrentThreadName());
				}
			};
			dbRun.invoke(run);	
			U.msgbox(i);
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
		if (aqr==null)U.argsError(aqr);
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