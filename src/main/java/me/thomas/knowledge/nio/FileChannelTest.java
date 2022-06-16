/**
 * LY.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package me.thomas.knowledge.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * FileChannelTest
 *
 * @author xinsheng2.zhao
 * @version Id: FileChannelTest.java, v 0.1 10/24/21 2:06 PM xinsheng2.zhao Exp $
 */
public class FileChannelTest {

    public static void main(String[] args) throws Exception {
        FileChannel fileChannel = null;
        try {
            fileChannel = new RandomAccessFile("/tmp/fileChannel.out", "rw").getChannel();
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 1024 * 1024);
        } finally {
            if (fileChannel != null) {
                fileChannel.close();
            }
        }
    }
}
