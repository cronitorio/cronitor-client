package io.cronitor.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;

@RunWith(PowerMockRunner.class)
public class MonitorWithIdentificationTest {

    @InjectMocks
    private CronitorClient client = new CronitorClient("anApiKey", "anEnv");
    @Mock
    private CronitorPinger cronitorPinger;

    private String monitorKey = "d3x0c1";

    @Test
    public void can_start_monitor_with_minimal_requirements() throws Exception {

        client.run(monitorKey);

        verify(cronitorPinger).ping(Command.RUN.getValue(), "d3x0c1", "anApiKey", null, "anEnv", true);
    }

    @Test
    public void can_start_monitor_with_message() throws Exception {

        client.run(monitorKey, "customRunMessage");
        verify(cronitorPinger).ping(Command.RUN.getValue(), "d3x0c1", "anApiKey", "customRunMessage", "anEnv", true);
    }

    @Test
    public void can_complete_monitor_with_minimal_requirements() throws Exception {

        client.complete(monitorKey);
        verify(cronitorPinger).ping(Command.COMPLETE.getValue(), "d3x0c1", "anApiKey", null, "anEnv", true);
    }

    @Test
    public void can_complete_monitor_with_message() throws Exception {

        client.complete(monitorKey, "customCompleteMessage");
        verify(cronitorPinger).ping(Command.COMPLETE.getValue(), "d3x0c1", "anApiKey", "customCompleteMessage", "anEnv",
                true);
    }

    @Test
    public void can_fail_monitor_with_minimal_requirements() throws Exception {

        client.fail(monitorKey);
        verify(cronitorPinger).ping(Command.FAIL.getValue(), "d3x0c1", "anApiKey", null, "anEnv", true);
    }

    @Test
    public void can_fail_monitor_with_message() throws Exception {

        client.fail(monitorKey, "customFailMessage");

        verify(cronitorPinger).ping(Command.FAIL.getValue(), "d3x0c1", "anApiKey", "customFailMessage", "anEnv", true);
    }

    @Test
    public void can_pause_monitor() throws Exception {

        client.pause(monitorKey, 5);
        verify(cronitorPinger).pause(monitorKey, 5, "anApiKey");
    }

    @Test
    public void can_unpause_monitor() throws Exception {

        client.unpause(monitorKey);
        verify(cronitorPinger).pause(monitorKey, 0, "anApiKey");
    }

    @Test
    public void can_metrics_monitor() throws Exception {
        Map<String, Integer> metrics = new HashMap<String, Integer>() {
            {
                put("count", 100);
                put("error_count", 5);
            }
        };
        client.metrics(monitorKey, metrics);
        verify(cronitorPinger).metrics(monitorKey, "anApiKey", "anEnv", metrics, true);
    }

}
