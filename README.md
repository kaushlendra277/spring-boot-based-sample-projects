# spring-boot-based-sample-projects - Poc 2 - Integrating unique-request-id using slf4j and spring interceptors
## Problem Statement
To caputure unique request id in the logs and send it via response in order to track logs whenever any exception occurs.

## Solution Achived
To solve this we used servlet filter **HandlerInterceptorAdapter** and create its child <br /> 
**ksc.poc.spring.unique.request.id.interceptors.Slf4jMDCInterceptor**<br />
There we have overridden *preHandle()* to update **org.slf4j.MDC** object.
**NOTE:** <br />
### org.slf4j.MDC
This is [slf4j utility class](http://www.slf4j.org/api/org/slf4j/MDC.html) which is used to read pattern of log and replace placefolder with the actual values<br />
for eg <br /> *logging.pattern.console= %d{yyyy-MM-dd HH:mm:ss} [%thread] %X{uniqueRequestId} - %msg%n* <br />
the actual log will be somethin like this *2019-11-08 15:26:22 [http-nio-8080-exec-1] F09045C0530D4E138513124FF24F07C6 - HelloWorld* <br /> <br />

w/h  uniqueRequestId = F09045C0530D4E138513124FF24F07C6 is generated in the servlet filter.<br />

## Overcoming the problems of using servelet filters
1. Spring interceptors run after application context loads for the request hence any project specfic error response can be easily sent <br /> 
if we are using interceptors i.e. @ControllerAdvice can capture any exception thrown by interceptors.

2. Makes spring application loosely coupled to servelet filter and its technology.

# Poc control flow
request -> preHandle() of Slf4jMDCInterceptor -> preHandle() of ValidatingUniqueRequestIdInterceptor -> <br /> 
controller ->  postHandle() of Slf4jMDCInterceptor -> ...
