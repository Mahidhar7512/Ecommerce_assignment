# Ecommerce_assignment

<img width="1440" alt="Screenshot 2025-03-02 at 11 29 36 AM" src="https://github.com/user-attachments/assets/fca25fa6-1fd6-4c24-9714-ff84969e5b06" />
<img width="1440" alt="Screenshot 2025-03-02 at 11 29 59 AM" src="https://github.com/user-attachments/assets/a1e977bc-f836-431c-bb91-fd2da9703bdb" />
<img width="1440" alt="Screenshot 2025-03-02 at 11 53 14 AM" src="https://github.com/user-attachments/assets/2f765598-859e-4754-bb02-fcaf4ce24482" />




This backend system was designed to be **modular, scalable, and maintainable**, ensuring smooth order management and processing in an e-commerce platform. Below is an explanation of the **design decisions** made, along with their **trade-offs**.

---------------------------------

System Architecture
Decision:
- The system follows a layered architecture: 
  1. **Controller Layer** (REST APIs)  
  2. **Service Layer** (Business Logic)  
  3. **Repository Layer** (Database Interaction)  
  4. **Queue Layer** (Asynchronous Processing)

Trade-offs:
Pros:
- Separation of Concerns (SoC):Each layer has a distinct responsibility, making the system modular.  
- Scalability:Adding new features (e.g., payments, discounts) is easier without modifying existing code.  

Cons:
- Increased Complexity: More files and components make the system slightly harder to navigate.  
- Slight Performance Overhead:Additional method calls between layers introduce a minor latency.



Database Choice: MySQL with JPA
Decision
- MySQL was chosen for its reliability and ACID compliance.  
- Spring Data JPA simplifies database operations using an ORM (Hibernate).  
- The ordersdb table stores order details persistently.

Trade-offs: 
Pros:  
- Structured Queries & Indexing: Ensures fast retrieval and integrity of data.  
- Automatic Table Creation (DDL Auto Update): Reduces manual schema management.  

Cons: 
- ORM Overhead: Hibernate adds some performance overhead due to SQL abstraction.  
- Limited Flexibility:Relational databases require schema changes for new fields.

---

RESTful API Design
Decision:
- POST `/orders` → Accepts new orders.  
- GET `/orders/{orderId}/status` → Fetches the status of an order.  

 Trade-offs:
Pros:  
- RESTful Standardization: Easy for frontend apps & third-party integrations.  
- Scalability: Stateless design makes load balancing simpler.

Cons:  
- Higher Latency: Each HTTP request adds slight network overhead.  
- Security Considerations: Requires authentication & input validation.

---

Asynchronous Order Processing (Queue)
Decision:
- Used an **in-memory queue** (`BlockingQueue<Order>`) for processing orders asynchronously.
- A **background thread** processes orders, simulating real-world behavior.

Trade-offs: 
Pros:
- Faster Response Time:Orders are immediately acknowledged as "Pending" instead of blocking the request.  
- Scalability:The queue can be replaced with **RabbitMQ/Kafka** for distributed processing.

Cons: 
- Memory-Based Queue: Orders will be lost if the server crashes before processing.  
- Single Worker Thread: Can handle **only one order at a time**, limiting throughput.

Future Enhancements
Persistent Queue* 
   - Replace in-memory queue with **Kafka, RabbitMQ, or Redis** for reliability.  
Authentication & Security
   - Add **JWT authentication** to secure APIs.  
Better Failure Handling 
   - Implement a **retry mechanism** for failed orders.  
Scalability Improvements 
   - Deploy to **AWS/GCP** with **load balancing & autoscaling**.  

---

Conclusion
This design **balances simplicity and performance** while ensuring modularity and scalability. Future enhancements can improve **fault tolerance, security, and scalability**. 
