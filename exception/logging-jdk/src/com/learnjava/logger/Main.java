package com.learnjava.logger;

import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

/**
 * Java Built-in Logging
 *
 * @author lsrong
 */
public class Main {

  public static void main(String[] args) {
    Logger logger = Logger.getLogger(Main.class.getName());
    logger.info("Start process...");
    try {
      "".getBytes("invalidCharsetName");
    } catch (UnsupportedEncodingException e) {
      // 使用logger.severe()打印异常
      logger.severe("--- UnsupportedEncodingException ---");
      logger.severe(e.getMessage());
      logger.severe(e.toString());
    }

    logger.info("Process end.");
  }
}
