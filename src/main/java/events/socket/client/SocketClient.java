package events.socket.client;

import events.domain.EventOuter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

@Component
public class SocketClient {

    @Value("${port}")
    private int PORT ;

    @Value("${host}")
    private String HOST;

    public void sendMessageViaSocket(EventOuter.EventProto message) throws IOException {
        Socket socket = new Socket(HOST, PORT);
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        System.out.println("Sending request to Socket Server");
        bos.write(message.toByteArray());
        bos.close();
    }
}
