package ir.phgint;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SubjectHandler extends Thread implements Subjecet {

    private Collection<Observer> observers = new ArrayList<Observer>();
    private BlockingQueue<MessageDataModel> blockingQueue = new LinkedBlockingQueue<MessageDataModel>();
    private Boolean isRunning = true;

    public void setObservers(Observer observers) {
        this.observers.add(observers);
    }

    public void run() {

        while (isRunning) {
            String msg = null;
            try {
                MessageDataModel dataModel = blockingQueue.take();
                for (Observer observer : observers) {
                    if (observer != dataModel.observer) {
                        msg = dataModel.msg;
                        observer.write(msg);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setMessage(String msg , Observer observer) {
        MessageDataModel dataModel = new MessageDataModel();
        dataModel.msg = msg;
        dataModel.observer = observer;
        blockingQueue.offer(dataModel);
    }

    public void remove(Observer observer)
    {
        blockingQueue.remove(observer);
    }

    class MessageDataModel{
        String msg;
        Observer observer;
    }
}
