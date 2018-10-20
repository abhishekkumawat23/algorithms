/**
 * Write an aligned malloc and free function that supports allocating memory such that the memory address returned is divisible by specific power of 2.
 * Example:
 * aligned_malloc(1000, 128) will return a memory address that is multiple of 128 and that points to memory of size 1000 bytes.
 * aligned_free() will free memory allocated by alogn_malloc.
 */

#include <iostream>

using namespace std;

/**
 * Important points:
 * 1. We want memory address which is divisible by `alignment` where alignment is specific power of 2. We need `required_bytes` size of that memory
 * 2. To achieve this, we need to take `required_bytes+alignment-1` size from memory as then it will be guarantted that we can start our memory address from multiple `alignment`.
 * 3. Once we get the `required_bytes+alignment-1` size memory, we need to find point which is divisible by `alignment`
 * 4. The extra memory should be stored in final returned pointer as we need to free that as well on freeing pointer memory.
 */
void* aligned_malloc(size_t required_bytes, size_t alignment) {
    int offset = alignment - 1 + sizeof(void*);
    void* p = (void*)malloc(required_bytes + offset);
    void* q = (p+offset) & ~(offset);
    ((void **)q)[-1] = p;
    return q;
}

void* aligned_free(void *q) {
    void *p = ((void **)q)[-1];
    free(p);
}