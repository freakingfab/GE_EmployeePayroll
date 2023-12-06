package com.bridgelabz.empoloyeepayroll;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
    @desc: class for testing java8watchservice
 */
public class WatchServiceTest {
    /*
        @desc: test for given directory when watched list all activities
        @params: none
        @return: void
     */
    @Test
    public void givenADirectoryWhenWatchedListsAllTheActivities() throws IOException{
        String HOME = System.getProperty("user.home");
        String PLAY_WITH_NIO = "TempPlayGround";
        Path dir = Paths.get(HOME + "/" + PLAY_WITH_NIO);
        Files.list(dir).filter(Files::isRegularFile).forEach(System.out::println);
        new Java8WatchService(dir).processEvents();
    }
}
