/**
 * Implement a function void reverse(char* str) in C or C++ which reverses a null-terminated string.
 */

#include <iostream>

using namespace std;

/**
 * Important points:
 * 1. We move one pointer to end and one pointer to start.
 * 2. Swap chars from start with chars from end.
 */
void reverse(char* str) {
    if (!str){
        return;
    }
    
    // Find end of string
    char* end = str;
    while (*end) {
        end++;
    }
    end--; // Set one char back, as last char is null
    
    // Swap chars from start and chars from end
    char temp;
    while (str < end) {
        temp = *str;
        *str = *end;
        *end = temp;
        str++;
        end--;
    }
}

int main() {
    return 0;
}