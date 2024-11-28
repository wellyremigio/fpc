import threading
import time
import random

semaphore = threading.Semaphore(1)

def thread_function(name):
    semaphore.acquire()
    try:
        print(f"A thread {name} decrementou o semaforo")
        time.sleep(random.randint(1,10))
    finally:
        print(f"A thread {name} vai incrementar o semaforo")
        semaphore.release()

if __name__ == "__main__":
    threads = []
    for i in range(3):
        thread = threading.Thread(target=thread_function, args=(f"Thread={i}",))
        threads.append(thread)
        thread.start()

    for thread in threads:
        thread.join()