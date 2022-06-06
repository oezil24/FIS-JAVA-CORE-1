package com.fis.java;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * com.fis.java.Main
 * Author TungHuynh - sondt18@fpt.com.vn
 * Date 09-Mar-22 08:40 AM
 */
public class Main {

    public static void main(String[] args) {
        Collection<String> collection = new Queue<>();
//        Collection<String> collection = new Stack<>();
        collection.push("abc");
        collection.push("123");
        collection.push("xyz");
        collection.push("456");

        while (collection.hasNext()){
            System.out.println(collection.pop());
        }
    }


}