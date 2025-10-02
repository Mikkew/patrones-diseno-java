package com.mms.patterns.desing.p02_estructurales.adapter_files;

import java.util.logging.*;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

interface ILoggerAdapter {
    String getFile();

    void writeLog(String msg);

    void writeWarning(String msg);

    void writeError(String msg);
}

public class LocalAdapter implements ILoggerAdapter {

    private static Logger LOGGER = Logger.getLogger(LocalLogger.class.getName());

    // Agregar este código al inicio de la clase LocalAdapter
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tF %1$tT] [%4$-7s] %5$s%n");
        LOGGER.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter() {
            @Override
            public String format(LogRecord record) {
                String color = "";
                if (record.getLevel() == Level.SEVERE) color = RED;
                else if (record.getLevel() == Level.WARNING) color = YELLOW;
                else if (record.getLevel() == Level.INFO) color = GREEN;

                return color + super.format(record) + RESET;
            }
        });
        LOGGER.addHandler(handler);
    }

    private String file;

    public LocalAdapter(String file) {
        this.file = file;
    }

    @Override
    public String getFile() {
        return file;
    }

    @Override
    public void writeLog(String msg) {
        LOGGER.info(GREEN + "[" + file + " Log] " + msg + RESET);
    }

    @Override
    public void writeWarning(String msg) {
        LOGGER.warning(YELLOW + "[" + file + " Warning] " + msg + RESET);
    }

    @Override
    public void writeError(String msg) {
        LOGGER.severe(RED + "[" + file + " Error] " + msg + RESET);
    }
}
