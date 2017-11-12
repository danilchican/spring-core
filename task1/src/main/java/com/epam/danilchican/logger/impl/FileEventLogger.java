package com.epam.danilchican.logger.impl;

import com.epam.danilchican.Event;
import com.epam.danilchican.logger.EventLogger;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    private String filename;
    private File file;

    public FileEventLogger(String filename) {
        this.filename = filename;
    }

    public boolean init() throws IOException {
        this.file = new File(filename);

        return file.canWrite();
    }

    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(new File(filename), event.toString(),"utf-8", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
