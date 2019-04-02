package events.controllers;

import events.domain.Event;
import events.domain.EventOuter;
import events.socket.client.SocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class EventController {

    @Autowired
    SocketClient socketClient ;

    @PostMapping("/event")
    public String storeEvent(@RequestBody Event event) {
        // send to other app ...
        try {
            EventOuter.EventProto eventProto = EventOuter.EventProto.newBuilder()
                    .setTimestamp(event.getTimestamp())
                    .setUserId(event.getUserId())
                    .setEvent(event.getEvent())
                    .build();
            socketClient.sendMessageViaSocket(eventProto);
        } catch (IOException e) {
            e.printStackTrace();
            return String.format("error while sending event time stamp %s and user id %s , please try again later", event.getTimestamp(), event.getUserId());
        }
        return String.format("event with time stamp %s and user id %s received successfully and sent to be stored in file", event.getTimestamp(), event.getUserId());
    }
}
