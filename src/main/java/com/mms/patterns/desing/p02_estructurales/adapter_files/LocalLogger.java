package com.mms.patterns.desing.p02_estructurales.adapter_files;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

public class LocalLogger {

    private final String file;

    public LocalLogger(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public void writeLog(String msg) {
        System.out.println("[" + file + " Log] " + msg);
    }

    public void writeError(String msg) {
        System.out.println("[" + file + " error] " + RED + msg + RESET);
    }

    public void writeWarning(String msg) {
        System.out.println("[" + file + " warning] " + YELLOW + msg + RESET);
    }
}
