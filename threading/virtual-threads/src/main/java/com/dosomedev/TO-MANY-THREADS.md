# The Downsides of Too Many Threads

Creating an excessive number of threads has negative consequences:

1. Increased Context-Switching Overhead: Context switching isn't free. It consumes CPU cycles that could have been used for actual work. With thousands of threads, the OS can spend a significant amount of time just switching between them.
2. Increased Memory Consumption: Every thread in Java has its own stack (typically 256KB to 1MB by default). 1,000 threads could consume 1GB of memory just for their stacks, before they even do any work. This can lead to OutOfMemoryError.
3. Resource Thrashing: You can exhaust other system resources, like file handles or network ports.