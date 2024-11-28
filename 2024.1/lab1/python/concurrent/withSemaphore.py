import threading
import time
import random


semaphore = threading.Semaphore(2)

def thread_function(name):
    with semaphore:
        print(f"A thread {name} decrementou o semaforo")
        time.sleep(random.randint(1,10))
        print(f"A thread {name} vai incrementar o semaforo")




if __name__ == "__main__":
    threads = []
    for i in range(3):
        thread = threading.Thread(target=thread_function, args=(f"Thread={i}",))
        threads.append(thread)
        thread.start()

    for thread in threads:
        thread.join()