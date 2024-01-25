package coaching.asynchronous;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TimerTest {

    @Test
    void testTimer() throws Exception {
        var timer = new Timer(Duration.of(10, ChronoUnit.MILLIS));
        assertFalse(timer.hasReached());
        Thread.sleep(Duration.of(15, ChronoUnit.MILLIS));
        assertTrue(timer.hasReached());
    }

    @Test
    void testTimer_longerTime() {
        var firstInstant = Instant.now();
        Clock constantClock = Clock.fixed(firstInstant, ZoneId.systemDefault());

        Clock fiveDays = Clock.offset(constantClock, Duration.ofDays(5));
        Clock elevenDays = Clock.offset(constantClock, Duration.ofDays(11));

        var middleInstant = Instant.now(fiveDays);
        var lastInstant = Instant.now(elevenDays);

        try (MockedStatic<Instant> mockedStatic = mockStatic(Instant.class)) {
            mockedStatic.when(Instant::now)
                    .thenReturn(firstInstant)
                    .thenReturn(middleInstant)
                    .thenReturn(lastInstant);

            var timer = new Timer();
            assertFalse(timer.hasReachedWithCheck());
            assertTrue(timer.hasReachedWithCheck());
        }
    }

    @Test
    void testTimer_waiting() throws Exception {
        var timer = new Timer(Duration.of(10, ChronoUnit.MILLIS));
        assertFalse(timer.hasReached());

        Awaitility.await().until(timer::hasReached);
    }

}