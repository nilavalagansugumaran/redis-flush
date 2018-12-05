package com.tools.redisutils.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cache")
@Slf4j
public class CacheController {

  @Value("${refresh.key:123}")
  private String key;

  @Autowired
  RedisTemplate redisTemplate;

  @DeleteMapping("/all-cached")
  public ResponseEntity<String> deleteAllCached(@RequestHeader("refresh-key") String refreshKey) {
    if (key.equals(refreshKey)) {
      redisTemplate.getConnectionFactory().getConnection().flushAll();
    } else {
      return badRequest("invalid id or header");
    }
    return success("Redis cache flushed");
  }

  private ResponseEntity<String> badRequest(String body) {
    return new ResponseEntity(body, HttpStatus.BAD_REQUEST);
  }

  private ResponseEntity<String> success(String body) {
    return new ResponseEntity(body, HttpStatus.OK);
  }
}
