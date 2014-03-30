package FutureAndCallable;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import javax.net.ssl.HttpsURLConnection;


public class MyHttpsRequest implements Runnable {
    
    private final HttpsURLConnection con;
    private final Object lock = new Object();
    private InputStream is;
    
	public MyHttpsRequest(String url) throws IOException{
	    URL u = new URL(url);
        con = (HttpsURLConnection)u.openConnection();
        is = con.getInputStream();
	}
    
	@Override
	public void run() {
		
        con.setConnectTimeout(3000);
        con.setReadTimeout(3000);

        ByteBuffer buf = ByteBuffer.allocate(1024);
        ReadableByteChannel c = Channels.newChannel(is);
        
        try {
            synchronized (lock) {
                while (!Thread.interrupted()) {
                    c.read(buf);
                    String v = new String(buf.array(), "UTF-8");
                    System.out.println(v);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

	}
	public static void main(String[] args)
            throws IOException, InterruptedException {
	    
        MyHttpsRequest reader = new MyHttpsRequest("https://easylist-downloads.adblockplus.org/easylist.txt");
        Thread thread = new Thread(reader);
        thread.start();
        Thread.sleep(10);
        thread.interrupt();
	}
    
}
