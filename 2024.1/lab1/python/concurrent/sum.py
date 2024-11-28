
import sys
import threading

semaphore = threading.Semaphore(1)
total_sum = 0

def do_sum(path):
    global total_sum
    _sum = 0
    with open(path, 'rb',buffering=0) as f:
        byte = f.read(1)
        while byte:
            _sum += int.from_bytes(byte, byteorder='big', signed=False)
            byte = f.read(1)

    semaphore.acquire()
    try:
        total_sum += _sum
    finally:
        semaphore.release()
    
    print(path + " : " + str(_sum))
    


if __name__ == "__main__":
    paths = sys.argv[1:]
    threads = []
    for path in paths:
        thread = threading.Thread(target=do_sum, args=(path,))
        threads.append(thread)
        thread.start()

    for thread in threads:
        thread.join()

    print(f"Soma total de todos os arquivos: {total_sum}")
