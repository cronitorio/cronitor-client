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

        verify(cronitorPinger).ping(Command.RUN.getValue(), "d3x0c1", "anApiKey", "anEnv", null, null, null, true);
    }

    @Test
    public void can_start_monitor_with_message() throws Exception {

        client.run(monitorKey, "customRunMessage");
        verify(cronitorPinger).ping(Command.RUN.getValue(), "d3x0c1", "anApiKey", "anEnv", "customRunMessage", null,
                null, true);
    }

    @Test
    public void can_start_monitor_with_message_and_series() throws Exception {

        String series = "foo1234";

        client.run(monitorKey, "customRunMessage", null, series);
        verify(cronitorPinger).ping(Command.RUN.getValue(), "d3x0c1", "anApiKey", "anEnv", "customRunMessage", null,
                series, true);
    }

    @Test
    public void can_complete_monitor_with_minimal_requirements() throws Exception {

        client.complete(monitorKey);
        verify(cronitorPinger).ping(Command.COMPLETE.getValue(), "d3x0c1", "anApiKey", "anEnv", null, null, null, true);
    }

    @Test
    public void can_tick_monitor_with_minimal_requirements() throws Exception {

        client.tick(monitorKey);
        verify(cronitorPinger).ping(null, "d3x0c1", "anApiKey", "anEnv", null, null, null, true);
    }

    @Test
    public void can_tick_monitor_monitor_with_message() throws Exception {

        client.tick(monitorKey, "customCompleteMessage");
        verify(cronitorPinger).ping(null, "d3x0c1", "anApiKey", "anEnv", "customCompleteMessage", null, null, true);
    }

    @Test
    public void can_reset_monitor_with_minimal_requirements() throws Exception {

        client.reset(monitorKey);
        verify(cronitorPinger).ping(Command.OK.getValue(), "d3x0c1", "anApiKey", "anEnv", null, null, null, true);
    }

    @Test
    public void can_reset_monitor_with_message() throws Exception {

        client.reset(monitorKey, "customCompleteMessage");
        verify(cronitorPinger).ping(Command.OK.getValue(), "d3x0c1", "anApiKey", "anEnv", "customCompleteMessage", null,
                null, true);
    }

    @Test
    public void can_complete_monitor_with_message() throws Exception {

        client.complete(monitorKey, "customCompleteMessage");
        verify(cronitorPinger).ping(Command.COMPLETE.getValue(), "d3x0c1", "anApiKey", "anEnv", "customCompleteMessage",
                null, null, true);
    }

    @Test
    public void can_fail_monitor_with_minimal_requirements() throws Exception {

        client.fail(monitorKey);
        verify(cronitorPinger).ping(Command.FAIL.getValue(), "d3x0c1", "anApiKey", "anEnv", null, null, null, true);
    }

    @Test
    public void can_fail_monitor_with_message() throws Exception {

        client.fail(monitorKey, "customFailMessage");

        verify(cronitorPinger).ping(Command.FAIL.getValue(), "d3x0c1", "anApiKey", "anEnv", "customFailMessage", null,
                null, true);
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
    public void can_complete_with_metrics() throws Exception {
        Map<String, Integer> metrics = new HashMap<String, Integer>() {
            {
                put("count", 100);
                put("error_count", 5);
            }
        };
        client.complete(monitorKey, null, metrics);
        verify(cronitorPinger).ping(Command.COMPLETE.getValue(), "d3x0c1", "anApiKey", "anEnv", null, metrics, null, true);
    }

    @Test
    public void can_complete_with_message_and_metrics() throws Exception {
        Map<String, Integer> metrics = new HashMap<String, Integer>() {
            {
                put("count", 100);
                put("error_count", 5);
            }
        };
        client.complete(monitorKey, "customCompleteMessage", metrics);
        verify(cronitorPinger).ping(Command.COMPLETE.getValue(), "d3x0c1", "anApiKey", "anEnv", "customCompleteMessage",
                metrics, null, true);
    }

    @Test
    public void can_complete_with_message_and_metrics_and_series() throws Exception {
        Map<String, Integer> metrics = new HashMap<String, Integer>() {
            {
                put("count", 100);
                put("error_count", 5);
            }
        };
        String series = "foo1234";
        client.complete(monitorKey, "customCompleteMessage", metrics, series);
        verify(cronitorPinger).ping(Command.COMPLETE.getValue(), "d3x0c1", "anApiKey", "anEnv", "customCompleteMessage",
                metrics, series, true);
    }

    @Test
    public void can_tick_with_metrics() throws Exception {
        Map<String, Integer> metrics = new HashMap<String, Integer>() {
            {
                put("count", 100);
                put("error_count", 5);
            }
        };
        client.tick(monitorKey, null, metrics);
        verify(cronitorPinger).ping(null, "d3x0c1", "anApiKey", "anEnv", null, metrics, null, true);
    }

    @Test
    public void can_tick_with_message_and_metrics() throws Exception {
        Map<String, Integer> metrics = new HashMap<String, Integer>() {
            {
                put("count", 100);
                put("error_count", 5);
            }
        };
        client.tick(monitorKey, "customTickMessage", metrics);
        verify(cronitorPinger).ping(null, "d3x0c1", "anApiKey", "anEnv", "customTickMessage",
                metrics, null, true);
    }

    @Test
    public void can_tick_with_message_and_metrics_and_series() throws Exception {
        Map<String, Integer> metrics = new HashMap<String, Integer>() {
            {
                put("count", 100);
                put("error_count", 5);
            }
        };
        String series = "foo1234";
        client.tick(monitorKey, "customTickMessage", metrics, series);
        verify(cronitorPinger).ping(null, "d3x0c1", "anApiKey", "anEnv", "customTickMessage",
                metrics, series, true);
    }

}
