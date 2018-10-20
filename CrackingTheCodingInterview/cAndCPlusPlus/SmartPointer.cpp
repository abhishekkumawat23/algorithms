/**
 * Write a smart pointer class.
 * A smart pointer is a data type, usually implemented with templates, that simulates a pointer while also providing automatic garbage collection.
 * It automatically coutns the number of references to a SmartPointer<T*> object and fress the object of type T when the reference count hits zero.
 * 
 * Important points:
 * 1. Smart pointer is same as normal pointer, but it provides safetu via automatic memory management.
 * 2. It avoids issues like dangling pointers, memory leaks abd allocation failures.
 * 3. The smart pointer will maintain a single reference count for all references to a given object.
 */
 
template <class T>class SmartPointer {
    // These must be pointers since the reference count is tracked across multiple smart pointers to one object.
    T * ref;
    unsigned * ref_count;
    
    public:
        SmartPointer(T * ptr) {
            ref = ptr;
            ref_count = (unsigned*)malloc(sizeof(unsigned));
            *ref_count = 1;
        }
    
        SmartPointer(SmartPointer<T>& sptr) {
            ref = sptr.ref;
            ref_count = sptr.ref_count;
            ++(*ref_count);
        }
        
        SmartPointer<T> & operator=(SmartPointer<T> & sptr) {
            if (this ==  &sptr) return *this;
            
            // If already assigned to an object, remove one reference.
            if (*ref_count > 0) {
                remove();
            }
            
            ref = sptr.ref;
            ref_count = sptr.ref_count;
            ++(*ref_count);
            return *this;
        }
        
        ~SmartPointer() {
            remove(); // Remove one reference to object
        }
        
        T getValue() {
            return *ref;
        }
        
    protected:
        void remove() {
            --(*ref_count);
            if (*ref_count == 0){
                delete ref;
                free(ref_count);
                ref = NULL;
                ref_count = NULL;
            }
        }
}

