package task_4;

import java.util.logging.*;

public class CustomLogHandler extends Handler {

    @Override
    public void publish(LogRecord record) {
        if (record.getLevel().intValue() >= Level.SEVERE.intValue()) {
            System.out.println(getFormatter().format(record));
        }
    }

    @Override
    public void flush() {
        // No action needed
    }

    @Override
    public void close() throws SecurityException {
        // No action needed
    }

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(CustomLogHandler.class.getName());
        CustomLogHandler handler = new CustomLogHandler();
        handler.setFormatter(new CustomFormatter());
        logger.addHandler(handler);
        logger.setLevel(Level.ALL);

        logger.severe("This is a severe message");
        logger.warning("This is a warning message");
    }
}

