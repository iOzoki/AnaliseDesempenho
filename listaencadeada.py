import time

class Node:
    def __init__(self, value):
        self.value = value
        self.next = None

class LinkedList:
    def __init__(self):
        self.head = None

    def add_to_end(self, value):
        new_node = Node(value)
        if self.head is None:
            self.head = new_node
        else:
            current = self.head
            while current.next is not None:
                current = current.next
            current.next = new_node

    def insert_at_position(self, value, position):
        size = self.size()
        if position < 0 or position > size:
            return  

        new_node = Node(value)
        if position == 0:
            new_node.next = self.head
            self.head = new_node
        else:
            current = self.head
            for _ in range(position - 1):
                current = current.next
            new_node.next = current.next
            current.next = new_node

    def remove_number(self, number):
        if self.head is None:
            return
        if self.head.value == number:
            self.head = self.head.next
            return

        current = self.head
        while current.next is not None:
            if current.next.value == number:
                current.next = current.next.next
                return
            current = current.next

    def show_list(self):
        if self.head is None:
            print("Empty list!")
            return

        current = self.head
        while current is not None:
            print(f"{current.value} -> ", end="")
            current = current.next
        print("END")

    def size(self):
        count = 0
        current = self.head
        while current is not None:
            count += 1
            current = current.next
        return count

def main():
    start_time = time.time()
    commands = []
    try:
        with open('arq-novo.txt', 'r') as file:
            for line in file:
                line = line.strip()
                if line and not line.startswith('#'):
                    commands.append(line)
    except:
        print("Error reading the file!")
        return

    if len(commands) < 2:
        print("Invalid file!")
        return

    initial_numbers = list(map(int, commands[0].split()))
    total_commands = int(commands[1])
    actions = commands[2:2 + total_commands]

    my_list = LinkedList()
    for num in initial_numbers:
        my_list.add_to_end(num)

    for command in actions:
        parts = command.split()
        if not parts:
            continue

        try:
            if parts[0] == 'A':
                value = int(parts[1])
                position = int(parts[2])
                my_list.insert_at_position(value, position)

            elif parts[0] == 'R':
                value = int(parts[1])
                my_list.remove_number(value)

            elif parts[0] == 'P':
                my_list.show_list()
        except (ValueError, IndexError):
            print(f"Invalid command ignored: {command}")
    end_time = time.time()
    print(f"Tempo de execução: {end_time - start_time:.4f} segundos")
if __name__ == "__main__":
    main()
