import model.Task;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class ReminderService {
    private Timer timer = new Timer();

    public void scheduleReminder(Task task, Runnable callback) {
        long delay = Duration.between(LocalDateTime.now(), task.getDeadline()).toMillis();
        if (delay > 0) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    callback.run();
                }
            }, delay);
        }
    }

    public void stop() {
        timer.cancel();
    }
}
