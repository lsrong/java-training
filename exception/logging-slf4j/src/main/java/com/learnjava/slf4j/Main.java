package com.learnjava.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * Slf4j Logger.
 *
 * @author lsrong
 */
public class Main {

  static final Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    logger.info("Start process {}...", Main.class.getName());
    try {
      "".getBytes("invalidCharsetName");
    } catch (UnsupportedEncodingException e) {
      // 使用logger.error(String, Throwable)打印异常
      logger.error(e.getMessage(), e);
    }
    logger.info("Process end.");
  }
}
