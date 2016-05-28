# prometheus-webapp
spring-boot prometheus exporter example

## Run

```
> gradle bootRun
```

- HTTP request

```
> curl http://localhost:8080/echo
hello!!%
> curl http://localhost:8080/echo
hello!!% > curl http://localhost:8080/fuga/hoge
{"timestamp":1464447609888,"status":404,"error":"Not Found","message":"No message available","path":"/fuga/hoge"}%
 > curl http://localhost:8080/fuga/hoge
{"timestamp":1464447618234,"status":404,"error":"Not Found","message":"No message available","path":"/fuga/hoge"}%
> curl http://localhost:8080/hoge/fuga
hello!!%                                                                                                                                                                          > curl http://localhost:8080/echo\?echo\=error
{"timestamp":1464447631273,"status":500,"error":"Internal Server Error","exception":"java.lang.IllegalArgumentException","message":"error","path":"/echo"}%

```

## prometheus

```
> curl http://localhost:8080/prometheus
```

- Results

```
# HELP jvm_gc_collection_seconds Time spent in a given JVM garbage collector in seconds.
# TYPE jvm_gc_collection_seconds summary
jvm_gc_collection_seconds_sum{gc="PS Scavenge",} 0.128
jvm_gc_collection_seconds_count{gc="PS Scavenge",} 7.0
jvm_gc_collection_seconds_sum{gc="PS MarkSweep",} 0.056
jvm_gc_collection_seconds_count{gc="PS MarkSweep",} 1.0
# HELP status_200_hoge_fuga status.200.hoge.fuga
# TYPE status_200_hoge_fuga gauge
status_200_hoge_fuga 1.0
# HELP jvm_threads_current Current thread count of a JVM
# TYPE jvm_threads_current gauge
jvm_threads_current 20.0
# HELP jvm_threads_daemon Daemon thread count of a JVM
# TYPE jvm_threads_daemon gauge
jvm_threads_daemon 18.0
# HELP jvm_peak_threads Peak thread count of a JVM
# TYPE jvm_peak_threads gauge
jvm_threads_peak 20.0
# HELP jvm_threads_started_total Started thread count of a JVM
# TYPE jvm_threads_started_total counter
jvm_threads_started_total 24.0
# HELP status_200_echo status.200.echo
# TYPE status_200_echo gauge
status_200_echo 2.0
# HELP status_404_star_star status.404.star-star
# TYPE status_404_star_star gauge
status_404_star_star 2.0
# HELP timer_echo_execution_millis Generated from dropwizard metric import (metric=timer_echo_execution_millis, type=com.codahale.metrics.Timer)
# TYPE timer_echo_execution_millis summary
timer_echo_execution_millis{quantile="0.5",} 0.016
timer_echo_execution_millis{quantile="0.75",} 0.11
timer_echo_execution_millis{quantile="0.95",} 0.11
timer_echo_execution_millis{quantile="0.98",} 0.11
timer_echo_execution_millis{quantile="0.99",} 0.11
timer_echo_execution_millis{quantile="0.999",} 0.11
timer_echo_execution_millis_count 2.0
timer_echo_execution_millis_sum 0.126
# HELP jvm_memory_bytes_used Used bytes of a given JVM memory area.
# TYPE jvm_memory_bytes_used gauge
jvm_memory_bytes_used{area="heap",} 9.7112512E7
jvm_memory_bytes_used{area="nonheap",} 4.9150504E7
# HELP jvm_memory_bytes_committed Committed (bytes) of a given JVM memory area.
# TYPE jvm_memory_bytes_committed gauge
jvm_memory_bytes_committed{area="heap",} 2.0447232E8
jvm_memory_bytes_committed{area="nonheap",} 5.0266112E7
# HELP jvm_memory_bytes_max Maximum (bytes) of a given JVM memory area.
# TYPE jvm_memory_bytes_max gauge
jvm_memory_bytes_max{area="heap",} 1.908932608E9
jvm_memory_bytes_max{area="nonheap",} -1.0
# HELP jvm_memory_pool_bytes_used Used bytes of a given JVM memory pool.
# TYPE jvm_memory_pool_bytes_used gauge
jvm_memory_pool_bytes_used{pool="Code Cache",} 1.1709888E7
jvm_memory_pool_bytes_used{pool="Metaspace",} 3.330932E7
jvm_memory_pool_bytes_used{pool="Compressed Class Space",} 4131488.0
jvm_memory_pool_bytes_used{pool="PS Eden Space",} 7.3952048E7
jvm_memory_pool_bytes_used{pool="PS Survivor Space",} 1.2049352E7
jvm_memory_pool_bytes_used{pool="PS Old Gen",} 1.1111112E7
# HELP jvm_memory_pool_bytes_committed Limit (bytes) of a given JVM memory pool.
# TYPE jvm_memory_pool_bytes_committed gauge
jvm_memory_pool_bytes_committed{pool="Code Cache",} 1.1993088E7
jvm_memory_pool_bytes_committed{pool="Metaspace",} 3.3947648E7
jvm_memory_pool_bytes_committed{pool="Compressed Class Space",} 4325376.0
jvm_memory_pool_bytes_committed{pool="PS Eden Space",} 1.3631488E8
jvm_memory_pool_bytes_committed{pool="PS Survivor Space",} 1.2058624E7
jvm_memory_pool_bytes_committed{pool="PS Old Gen",} 5.6098816E7
# HELP jvm_memory_pool_bytes_max Max (bytes) of a given JVM memory pool.
# TYPE jvm_memory_pool_bytes_max gauge
jvm_memory_pool_bytes_max{pool="Code Cache",} 2.5165824E8
jvm_memory_pool_bytes_max{pool="Metaspace",} -1.0
jvm_memory_pool_bytes_max{pool="Compressed Class Space",} 1.073741824E9
jvm_memory_pool_bytes_max{pool="PS Eden Space",} 6.8943872E8
jvm_memory_pool_bytes_max{pool="PS Survivor Space",} 1.2058624E7
jvm_memory_pool_bytes_max{pool="PS Old Gen",} 1.431830528E9
# HELP status_500_echo status.500.echo
# TYPE status_500_echo gauge
status_500_echo 1.0

```
