USING JFRAME

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Main {

  public static void main(String[] args) {
    new Main();
  }

  public Main() {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    frame.add(new TestPane());
    frame.pack();
    frame.setVisible(true);
  }
}

class TestPane extends JPanel {
  JLabel label;
  Timer timer;
  int count;
  public TestPane() {
    label = new JLabel("...");
    setLayout(new GridBagLayout());
    add(label);
    timer = new Timer(500, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        count++;
        if (count < 100000) {
          label.setText(Integer.toString(count));
        } else {
          ((Timer) (e.getSource())).stop();
        }
      }
    });
    timer.setInitialDelay(0);
    timer.start();
  }
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(200, 200);
  }
}


USING MULTITHREADING

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Counter implements Runnable {
    private static int counter = 0;
    private static final int limit = 1000;
    private static final int threadPoolSize = 5;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
        for (int i = 0; i < threadPoolSize; i++) {
            executorService.submit(new Counter());
        }
        executorService.shutdown();
    }

    @Override
    public void run() {
        while (counter < limit) {
            increaseCounter();
        }
    }

    private void increaseCounter() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " : " + counter);
            counter++;
        }
    }
}
