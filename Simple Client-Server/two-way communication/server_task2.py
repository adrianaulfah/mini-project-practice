import socket

HOST = "0.0.0.0"
PORT = 5001

def main():
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind((HOST, PORT))
    server_socket.listen(1)
    print(f"[SERVER] Listening at {HOST}:{PORT} ...")

    conn, addr = server_socket.accept()
    print(f"[SERVER] connected by {addr}")

    while True:
        data = conn.recv(1024).decode('utf-8')
        if not data:
            print("[SERVER] client disconnected")
            break
        print(f"[CLIENT]: {data}")

        reply = input("[YOU (SERVER)]: ")
        conn.send(reply.encode('utf-8'))

        if reply.lower() == "bye":
            break

    conn.close()
    server_socket.close()

if __name__ == "__main__":
    main()
