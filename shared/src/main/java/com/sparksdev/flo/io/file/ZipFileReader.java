/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.sparksdev.flo.io.file;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author bengill
 */
public class ZipFileReader {

    private ZipInputStream zis;
    private int fileSize = 0;

    public ZipFileReader(final ZipInputStream is) {
        this.zis = is;
    }

    public List<ZipEntry> readEntries() {

        try {
            List<ZipEntry> zipEntries = new ArrayList();

            ZipEntry ze = zis.getNextEntry();

            while (ze != null) {
                fileSize += ze.getCompressedSize();

                zipEntries.add(ze);
                ze = zis.getNextEntry();
            }
            return zipEntries;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * File size compressed
     * @return
     */
    public int getFileSize() {
        return fileSize;
    }

    public void close() {
        try {
            zis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
