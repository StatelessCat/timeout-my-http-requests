package FutureAndCallable;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public final class SocketReader implements Runnable {
    private final SocketChannel sc;
    private final Object lock = new Object();
   
    public SocketReader(String host, int port) throws IOException {
      sc = SocketChannel.open(new InetSocketAddress(host, port));
    }
   
    @Override public void run() {
      ByteBuffer buf = ByteBuffer.allocate(1024);
      try {
        synchronized (lock) {
          while (!Thread.interrupted()) {
            sc.read(buf);
            // ...
          }
        }
      } catch (IOException ie) {
        // Forward to handler
      }
    }
   
    public static void main(String[] args)
                            throws IOException, InterruptedException {
      SocketReader reader = new SocketReader("google.com", 80);
      Thread thread = new Thread(reader);
      thread.start();
      Thread.sleep(1000);
      System.out.println(reader.sc.toString());
      thread.interrupt();
    }
  }