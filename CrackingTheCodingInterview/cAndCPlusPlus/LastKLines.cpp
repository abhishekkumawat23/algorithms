/**
* Write a method to print the last K lines of an input file using c++.
*/

#include <iostream>
#include <fstream>

using namespace std;

/**
* Important points:
* 1. We can store k lines in an array.
* 2. Array will be a circular array.
* 3. There will be pointer in array which will point which element to point.
* 4. This way we can replace that element with new line on readLine. 
*/
void printLastKLines(const char* fileName, const int k) {
    ifstream file;
    string lines[k];
    int size = 0;
    
    // Read file line by line in circular array
    file.open(fileName);
    while (file.peek() != EOF) {
        getline(file, lines[size%k]);
        size++;
    }
    
    int start = size > k ? (size%k) : 0;
    int count = min(k, size);
    
    // Print elements in the order they were read.
    for (int i = 0; i < count; i++) {
        cout << lines[(start + i)%k] << endl;
    }
    
    file.close();
}

int main() {
    return 0;
}