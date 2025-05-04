const fs = require('fs');

class Node {
    constructor(element) {
      this.element = element;
      this.next = null;
    }
  }
  
  class LinkedList {
    constructor() {
      this.head = null;
      this.count = 0;
    }
  
    push(element) {
      const node = new Node(element);
      let current;
  
      if (this.head === null) {
        this.head = node;
      } else {
        current = this.head;
        while (current.next !== null) {
          current = current.next;
        }
        current.next = node;
      }
      this.count++;
    }
  
    insertAt(element, position) {
        if (position < 0 || position > this.count) {
            return false;
          }
      
          const node = new Node(element);
          if (position === 0) {
            node.next = this.head;
            this.head = node;
          } else {
            let previous = this.getElementAt(position - 1);
            node.next = previous.next;
            previous.next = node;
          }
          this.count++;
          return true;
        }
  
    remove(element) {
      const index = this.indexOf(element);
      return this.removeAt(index);
    }
  
    getElementAt(position) {
      if (position < 0 || position >= this.count) return undefined;
  
      let current = this.head;
      for (let i = 0; i < position; i++) {
        current = current.next;
      }
      return current;
    }
  
    indexOf(element) {
      let current = this.head;
      let index = 0;
  
      while (current !== null) {
        if (current.element === element) return index;
        current = current.next;
        index++;
      }
      return -1;
    }
  
    removeAt(position) {
      if (position < 0 || position >= this.count) {
        return null; 
      }
  
      let current = this.head;
      if (position === 0) {
        this.head = current.next;
      } else {
        let previous = this.getElementAt(position - 1);
        current = previous.next;
        previous.next = current.next;
      }
      this.count--;
      return current.element;
  }
  
    isEmpty() {
      return this.count === 0;
    }
  
    size() {
      return this.count;
    }

    toString() {
        if (this.head === null) return '';
        
        let current = this.head;
        let result = '';
      
        while (current !== null) {
          result += current.element + (current.next ? ' -> ' : '');
          current = current.next;
        }
      
        return result;
      }
      
  }

  fs.readFile('arq-novo.txt', 'utf-8', (err, data) => {
    if (err) throw err;
  
    const lines = data.trim().split('\n');
  
    const commandStart = lines.findIndex(line => /^\d+$/.test(line.trim()));
    const totalCommands = parseInt(lines[commandStart]);
    const actions = lines.slice(commandStart + 1, commandStart + 1 + totalCommands);
  
    const list = new LinkedList();
  
    const initialNumbers = lines[0].split(' ').map(Number);
    initialNumbers.forEach(num => list.push(num));
  
    console.time('Execução');
  
    for (let line of actions) {
      line = line.trim();
      if (line === '') continue;
  
      if (line === 'P') {
        console.log(list.toString());
      } else if (line.startsWith('A')) {
        const [, valueStr, posStr] = line.split(' ');
        const value = parseInt(valueStr);
        const pos = parseInt(posStr);
        list.insertAt(value, pos);
      } else if (line.startsWith('R')) {
        const [, posStr] = line.split(' ');
        const pos = parseInt(posStr);
        list.removeAt(pos);
      }
    }
  
    console.timeEnd('Execução');
  });
  