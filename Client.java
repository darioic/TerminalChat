import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {

    public static void main(String[] args) {
        MySocket sock = new MySocket("localhost", 65432);
        new Thread() {
            public void run() {
                String line;
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                try {
                    while ((line = in.readLine()) != null) {
                        sock.print(line);
                    }
                    sock.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }.start();
        new Thread() {
            public void run() {
                String line;
                while ((line = sock.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }.start();
    }
}
