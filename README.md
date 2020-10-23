# Spring-Inbound-Interceptor
Perform logging or other logic on all inbound HTTP requests to a Spring MVC App using OncePerRequestFilter

Use the `Inbound-Interceptor.postman_collection.json` to interact with the App. For demonstration purposes, all logs are printed on the console. However, any arbitrary logic may be placed here instead in order to log or modify all inbound HTTP calls. 

* Allows capturing request/response body via ContentCachingWrappers. Prevents empty response as the default HttpServletResponse can be consumed only once
* Works for all content-types and HTTP methods (unlike the https://www.baeldung.com/spring-http-logging method which works only for Content-Type:application/x-www-form-urlencoded and Method-Type:POST)
 
Example of logs generated:
```
**LOG** 12:12:21        Request URL     : /post200
**LOG** 12:12:21        Request Method  : POST
**LOG** 12:12:21        Request Type    : application/json
**LOG** 12:12:21        Request Header  : {Cookie=[JSESSIONID=node01srjbzrk7iwwb4zutfwf3u61n0.node0], Accept=[*/*], Cache-Control=[no-cache], User-Agent=[PostmanRuntime/7.26.
5], Connection=[keep-alive], Postman-Token=[5a54c834-d2bf-45e5-ac29-90a0076534d7], Host=[localhost:8080], Accept-Encoding=[gzip, deflate, br], Content-Length=[57], Content-Ty
pe=[application/json;charset=UTF-8]}
**LOG** 12:12:22        Request Body
: {
    "name": "test_json",
    "request": "POST 200"
}

**LOG** 12:12:22        Response Type   : text/html;charset=iso-8859-1
**LOG** 12:12:22        Response Headers: {}
**LOG** 12:12:22        Response Body   :
<html>
<body>
    <h2 style="color:blue">SUCCESS/[POST]</h2>
    <h4>POST Success - 12:12:20
{
    "name": "test_json",
    "request": "POST 200"
}</h4>
    <a href="localhost:8080">HOME</a>
</body>
</html>
**LOG** 12:12:22        Response Code: 200
```
