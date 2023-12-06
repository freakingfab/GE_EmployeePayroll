package com.bridgelabz.empoloyeepayroll;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardWatchEventKinds.*;

/*
    * @desc: class for watch service
 */
public class Java8WatchService {
    private final WatchService watcher;
    private final Map<WatchKey, Path> dirWatchers;

    /*
        @desc: creates watchservice and registers the given directory
        @params: path
        @return: none
     */
    public Java8WatchService(Path dir) throws IOException{
        this.watcher = FileSystems.getDefault().newWatchService();
        this.dirWatchers = new HashMap<WatchKey, Path>();
        scanAndRegisterDirectories(dir);
    }

    /*
        @desc: register given directory
        @params: Path
        @return: void
     */
    private void registerDirWatchers(Path dir) throws IOException {
        WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        dirWatchers.put(key, dir);
    }

    /*
        @desc: register directory and all its subdirectories
        @params: Path
        @return: void
     */
    private void scanAndRegisterDirectories(final Path start) throws IOException {
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                registerDirWatchers(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /*
        @desc: process all events for key queued to watchers
        @params: none
        @return: void
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    void processEvents() {
        while (true) {
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }
            Path dir = dirWatchers.get(key);
            if (dir == null) continue;
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();
                Path name = ((WatchEvent<Path>) event).context();
                Path child = dir.resolve(name);
                System.out.format("%s: %s\n", event.kind().name(), child);

                if (kind == ENTRY_CREATE) {
                    try {
                        if (Files.isDirectory(child)) {
                            scanAndRegisterDirectories(child);
                        }
                    } catch (IOException x) {
                        // Handle IOException
                    }
                } else if (kind.equals(ENTRY_DELETE)) {
                    if (Files.isDirectory(child)) {
                        dirWatchers.remove(key);
                    }
                }
            }

            boolean valid = key.reset();
            if (!valid) {
                dirWatchers.remove(key);
                if (dirWatchers.isEmpty()) {
                    break;
                }
            }
        }
    }
}
