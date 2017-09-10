package ir.phgint.Server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SubjectHandler extends Thread implements Subjecet {

    private Collection<Observer> observersCollection = new ArrayList<Observer>();
    private BlockingQueue<MessageDataModel> blockingQueue = new LinkedBlockingQueue<MessageDataModel>();
    private Boolean isRunning = true;

    public void setObserversCollection(Observer observersCollection) {
        this.observersCollection.add(observersCollection);
    }

    public Collection<Observer> getObserversCollection() {
        return observersCollection;
    }

    public void run() {
        while (isRunning) {
            String msg = null;
            try {
                MessageDataModel dataModel = blockingQueue.take();
                Iterator<Observer> it = observersCollection.iterator();
                while (it.hasNext()) {
                    Observer observer = it.next();
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
    public void setMessage(String msg, Observer observer) {
        MessageDataModel dataModel = new MessageDataModel();
        dataModel.msg = msg;
        dataModel.observer = observer;
        blockingQueue.offer(dataModel);
    }

    public void remove(Observer observer) {
        observersCollection.remove(observer);
    }

    class MessageDataModel {
        String msg;
        Observer observer;
    }


}
