package events.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import events.socket.client.SocketClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTests {

    private final long TIMESTAMP = 1518609008;
    private final long USER_ID = 1123;
    private final String EVENT = "2 hours of downtime occured due to the release of version 1.0.5 of the system";

    private final String JSON_EVENT = "{\n" +
            "  \"timestamp\": " + TIMESTAMP + ",\n" +
            "  \"userId\": " + USER_ID + ",\n" +
            "  \"event\": \"" + EVENT + "\"\n" +
            "}";
    @Mock
    SocketClient socketClient;

    @InjectMocks
    EventController eventController;

    private MockMvc mockMvc;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
    }

    @Test
    public void shouldSucceedWhenEventIsSent() throws Exception {

        String expectedResult = String.format("event with time stamp %s and user id %s received successfully and sent to be stored in file", TIMESTAMP, USER_ID);
        doNothing().when(socketClient).sendMessageViaSocket(any());

        this.mockMvc.perform(post("/event")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON_EVENT))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResult));
    }

    @Test
    public void shouldFailWhenEventSendingProduceAnException() throws Exception {

        String expectedResult = String.format("error while sending event time stamp %s and user id %s , please try again later", TIMESTAMP, USER_ID);
        doThrow(IOException.class).when(socketClient).sendMessageViaSocket(any());
        this.mockMvc.perform(post("/event")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON_EVENT))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResult));
    }
}
