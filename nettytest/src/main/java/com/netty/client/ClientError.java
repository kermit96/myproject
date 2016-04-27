package com.netty.client;

public interface ClientError {
  void run(Throwable cause);
}
