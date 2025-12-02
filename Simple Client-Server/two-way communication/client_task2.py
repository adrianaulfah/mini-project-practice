import socket

SERVER_IP = "127.0.0.1"
SERVER_PORT = 5001

def main():
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_socket.connect((SERVER_IP, SERVER_PORT))
    print("[CLIENT] connected. type message.")

    while True:
        message = input("[YOU (CLIENT)]: ")
        client_socket.sendall(message.encode('utf-8'))

        if message.lower() == "bye":
            break

        data = client_socket.recv(1024)
        if not data:
            print("[CLIENT] server closed the connection.")
            break

        reply = data.decode("utf-8")
        print(f"[SERVER]: {reply}")

        if reply.lower() == "bye":
            break

    client_socket.close()

if __name__ == "__main__":
    main()
