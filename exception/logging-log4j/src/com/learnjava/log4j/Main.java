package com.learnjava.log4j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;

/**
 * Log4j Logger.
 *
 * @author lsrong
 */
public class Main {

  static final Log log = LogFactory.getLog(Main.class);

  public static void main(String[] args) {
    log.info("Start process...");
    try {
      "".getBytes("invalidCharsetName");
    } catch (UnsupportedEncodingException e) {
      log.error("Invalid encoding.", e);
    }
    log.info("Process end.");
  }
}
