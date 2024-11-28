import threading
import time

def thread_function():
    time.sleep(2)

if __name__ == "__main__":
    thread = threading.Thread(target=thread_function)
    thread.start()