import threading
import time
import random

barrier = threading.Barrier(2)

def thread_function(name):
    print(f"A thread {name} esperando pela barreira")
    time.sleep(random.randint(1,10))
    barrier.wait()
    print(f"A thread {name} passou pela barreira")





if __name__ == "__main__":
    threads = []
    for i in range(2):
        thread = threading.Thread(target=thread_function, args=(f"Thread={i}",))
        threads.append(thread)
        thread.start()

    for thread in threads:
        thread.join()