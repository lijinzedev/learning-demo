package com.curiosity;

import cn.hutool.core.util.RuntimeUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Classname Main
 * @Description
 * @Date 2021/5/14 14:13
 * @Created by curiosity
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
//        final String pwd = RuntimeUtil.execForStr("ls -all ", "pwd");--+
//        System.out.println(pwd);
        int[] ints = {1, 2,3};
        Arrays.stream(ints).sorted()
                .map(i ->
                {
                    System.out.println("我是map打印的" + i);
                    return i;
                })
                .filter(i ->
                {
                    System.out.println("我是filter打印的" + i);
                    return i > 0;
                })
                .map(i ->
                {
                    System.out.println("我是map2打印的" + i);
                    return i;
                })
                .forEach(i ->
                {
                    System.out.println("我是forEach打印的" + i);
                });

    }
}
