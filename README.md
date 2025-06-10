# 🚀 Cache Benchmark Suite — Redis vs Caffeine

A Java Spring Boot application to **benchmark and compare the performance** of two widely-used caching strategies:
- 🧠 **Caffeine (In-Memory)**
- 📡 **Redis (Remote, Serialized)**

This project simulates real-world usage of caching layers using **large payloads**, allowing teams to evaluate:
- Latency
- Throughput
- Serialization overhead
- Scalability implications

---

## 📌 Why This Project?

Modern systems rely on caching for high performance. But:
- Should you use a local in-memory cache like **Caffeine**?
- Or a distributed cache like **Redis**?

This suite gives you **empirical benchmarks** and answers with **realistic data payloads** (e.g., 10KB blobs) to help you decide.

---

## 🛠️ Tech Stack

| Component         | Technology                    |
|------------------|-------------------------------|
| Language         | **Java 21**                   |
| Framework        | Spring Boot                   |
| Cache Backends   | Redis, Caffeine                |
| Benchmarking     | Custom test harness (loops, timers) |
| Serialization    | Jackson (for object <-> JSON)  |
| Packaging        | Maven                          |

---

## 📦 Features

- ✅ Benchmark both Redis and Caffeine using identical payloads
- 📄 Supports `String` and custom `Object` types
- 📊 Collects execution time for reads & writes
- 🔄 Plug-and-play modular cache services
- 📚 Delegator pattern for cleaner cache invocation

---

## 🏁 How to Run

### Prerequisites
- **Java 21**
- Redis installed and running (`localhost:6379`)
- Docker (if Redis is containerized)

### Start the App
```bash
./mvnw spring-boot:run
