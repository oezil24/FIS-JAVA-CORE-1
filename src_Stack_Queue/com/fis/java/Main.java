package com.fis.java;

public class Main {

    public static void main(String[] args) {
        Collection<String> collection = new Queue<>();
        collection.push("123");
        collection.push("qwe");
        collection.push("asd");
        collection.push("zxc");

        while (collection.hasNext()){
            System.out.println(collection.pop());
        }
    }


}