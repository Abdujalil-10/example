package task_4;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

class CustomFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();
        sb.append(record.getLevel()).append(": ").append(record.getMessage()).append("\n");
        if (record.getThrown() != null) {
            Throwable t = record.getThrown();
            sb.append("Exception: ").append(t.toString()).append("\n");
            for (StackTraceElement element : t.getStackTrace()) {
                sb.append("\tat ").append(element.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}