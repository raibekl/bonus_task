# Bonus Task -- Caching Layer Implementation

## 1. Overview

As a bonus enhancement, an in-memory caching layer was implemented to
improve performance and reduce unnecessary database calls.

The cache stores frequently requested data (e.g., list of cars) to avoid
repeated queries to the database. This improves response time and
demonstrates knowledge of backend performance optimization.

The caching mechanism is implemented manually using:

-   Singleton Pattern
-   ConcurrentHashMap (in-memory storage)
-   Service-layer integration
-   Cache invalidation on data modification

------------------------------------------------------------------------

## 2. Why Caching Was Implemented

Without caching: - Every GET request calls the database - Repeated
requests increase load - Performance degrades under heavy traffic

With caching: - First request → data fetched from DB - Next requests →
data returned from memory - Faster response time

------------------------------------------------------------------------

## 3. Implementation Details

### 3.1 Singleton Pattern

The cache is implemented as a Singleton to ensure only one shared cache
instance exists:

Example idea:

-   Private constructor
-   Static instance
-   Public getInstance() method

This guarantees consistent cached data across the application.

------------------------------------------------------------------------

### 3.2 In-Memory Storage

The cache uses:

-   ConcurrentHashMap

Why? - Thread-safe - Suitable for multi-threaded Spring Boot
applications - Lightweight and fast

------------------------------------------------------------------------

### 3.3 Cached Endpoint

The following endpoint is cached:

GET /api/cars

Inside CarService:

-   The system first checks if data exists in cache.
-   If found → returns cached data.
-   If not found → loads from database and stores in cache.

------------------------------------------------------------------------

## 4. Cache Invalidation Strategy

To prevent stale data, the cache is cleared when:

-   A car is created
-   A car is updated
-   A car is deleted

This ensures database consistency.

------------------------------------------------------------------------

## 5. Time-To-Live (TTL)

Each cached entry has an expiration time (e.g., 10 minutes).

If expired: - Entry is removed automatically - Fresh data is loaded from
the database

This prevents outdated information.

------------------------------------------------------------------------

## 6. Architecture Compliance

The caching layer respects:

-   Layered Architecture (Controller → Service → Repository)
-   SOLID Principles
-   Clean Code separation

Important: - Cache logic is placed inside the Service layer. -
Controller does not access cache or database directly. - Repository
remains responsible only for data access.

------------------------------------------------------------------------

## 7. Performance Demonstration

To demonstrate caching:

1.  Call GET /api/cars (first time → database hit)
2.  Call GET /api/cars again (second time → returned from cache)

This confirms performance optimization is working.

------------------------------------------------------------------------

## 8. Why Manual Caching Instead of @Cacheable?

Manual implementation was chosen to:

-   Demonstrate understanding of design patterns
-   Explicitly implement Singleton
-   Show manual cache invalidation control
-   Demonstrate architectural understanding

------------------------------------------------------------------------

# Key Concepts Demonstrated

-   Singleton Pattern
-   Thread-safe caching
-   Cache invalidation
-   Performance optimization
-   Clean layered architecture
