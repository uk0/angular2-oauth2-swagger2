/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.sparksdev.flo.io.file;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author bengill
 */
public class ZipFileWriter {

    private OutputStream os;
    private ZipOutputStream zout;
    private int fileSize = 0;


    /**
     *
     * @param os
     */
    public ZipFileWriter(final OutputStream os) {
        this.os = os;
        zout = new ZipOutputStream(os);
    }

    /**
     *
     * @param zipEntryName
     * @param zipEntry
     */
    public void writeEntry(final String zipEntryName, final byte[] zipEntry) {

        try {
            ZipEntry ze = new ZipEntry(zipEntryName);
            zout.putNextEntry(ze);
            zout.write(zipEntry);
            fileSize += ze.getCompressedSize();
            zout.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
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
            zout.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
