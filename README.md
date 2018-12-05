# redis-flush
Sample application to clear redis cache

# To run the application from application home
```
gradle clean build
java -jar build/libs/redis-flush-0.1.0.jar
```

# To test
`curl -X DELETE -H "refresh-key: somekey" "http://localhost:7770/cache/all-cached"`

# Configurations
Refer to `application.yml`. All configurations can be overridden using jvm runtime parameters ex `-Dserver.port=XXXX`
