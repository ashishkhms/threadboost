# ThreadBoost: High-Concurrency Spring Boot Architecture

![Java](https://img.shields.io/badge/Java-17-orange) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0-green) ![Docker](https://img.shields.io/badge/Docker-Enabled-blue) ![Performance](https://img.shields.io/badge/Performance-High-red)

**"I stopped guessing and started measuring."**

We often read about multithreading and non-blocking I/O in textbooks, but **ThreadBoost** is an experiment to quantify exactly how much performance these concepts unlock on real hardware.

This project demonstrates how switching to an asynchronous architecture squeezed **76% more performance** out of the same hardware compared to a traditional synchronous approach.

---

## 📊 Benchmark Results (Theory vs Reality)

I compared two implementations doing identical work on a machine with **12 logical cores** running Dockerized MySQL.

**Load Test Conditions:**
* **Tool:** Apache JMeter
* **Load:** 500 Concurrent Users
* **Duration:** 2 Minutes continuous load

| Metric | Synchronous (Blocking) | ThreadBoost (Async) | Improvement |
| :--- | :--- | :--- | :--- |
| **Throughput** | ~453 req/sec | **~799 req/sec** | **🚀 +76%** |
| **Avg Response Time** | ~1073 ms | **~610 ms** | **⚡ 43% Faster** |
| **99th % (Tail Latency)** | ~2273 ms | **~838 ms** | **Stability 63% Better** |
| **APDEX Score** | 0.481 | **0.593** | **Improved UX** |

> **Verdict:** Same machine. Same DB. Same workload. The only change was how we used threads. The Async architecture allowed threads to release immediately while waiting for I/O, preventing the "plateau" seen in the synchronous version.

### Performance Evidence

**Synchronous Baseline:**
![Sync Report Screenshot](assets/sync_report.png)

**Asynchronous Optimized:**
![Async Report Screenshot](assets/async_report.png)

---

## 🏗️ Architecture & Implementation

### 1. The Setup
* **SyncController:** Traditional blocking model. Each request holds a Servlet thread until the Database or Disk I/O completes.
* **AsyncController:** Optimized model using `CompletableFuture`, `@Async`, and a custom-tuned `ThreadPoolTaskExecutor`.

To highlight the difference, the Web Server (Tomcat) threads were explicitly limited to simulate resource constraints under load.

### 2. The Operations
The test profiled a mix of real-world blocking operations:
* `addUser` (Database Write - Transactional)
* `getUser` (Database Read - High Concurrency)
* `writeFile` (Disk I/O - Blocking)

### 3. The Optimization
We moved away from the "One Thread Per Request" model to a "Fire and Forget" handoff model:

```java
@Async("threadBoostExecutor")
public CompletableFuture<User> getUserAsync(Long id) {
    // The Tomcat thread is freed immediately.
    // This worker thread handles the blocking DB wait.
    return CompletableFuture.completedFuture(userRepository.findById(id));
}