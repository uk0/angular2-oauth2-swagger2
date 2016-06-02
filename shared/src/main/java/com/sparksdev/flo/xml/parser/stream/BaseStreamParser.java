/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.sparksdev.flo.xml.parser.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;

/**
 * @author bengill
 */
public abstract class BaseStreamParser {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Parse an ODM document.
     *
     * @param inputStream
     * @throws XMLStreamException
     */
    public void parse(final InputStream inputStream) {


        XMLInputFactory factory = XMLInputFactory.newFactory();
        StreamSource xml = new StreamSource(inputStream);
        XMLStreamReader xsr = null;
        try {
            xsr = factory.createXMLStreamReader(xml);
            processEvents(xsr);

        } catch (XMLStreamException e) {
            e.printStackTrace();
        } finally {
            try {
                xsr.close();
            } catch (XMLStreamException e) {
                logger.error("Error parsing odm file", e);
            }
        }
    }


    /**
     *
     * @param xmlStreamReader
     * @throws XMLStreamException
     */
    protected abstract void processEvents(final XMLStreamReader xmlStreamReader) throws XMLStreamException;
}
