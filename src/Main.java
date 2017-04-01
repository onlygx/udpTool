import com.ruitu.tools.udp.CommandManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) {
        try {
            CommandManager.init("M0001020",new Integer[]{192,168,2,193});
            run();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    public static void run(){
        // Send packet thread
        new Thread(() -> {
            System.out.println("初始化成功");
            while (true) {
                try {
                    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
                    String in = stdin.readLine();
                    if("conn".equals(in)){
                        CommandManager.conn("S00010203040");
                    }else if("off".equals(in)){
                        CommandManager.off();
                    }else if("start".equals(in)){
                        //
                    }else if("bye".equals(in)){
                        break;
                    }else{

                    }
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
