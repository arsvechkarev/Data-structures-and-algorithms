#include <iostream>
#include <stdexcept>

template <class E>
class DoublyLinkedList
{

private:
  struct Node
  {
    E data;
    Node *next = NULL;
    Node *previous = NULL;

    Node(E element)
    {
      data = element;
    }
  };

  Node *head = NULL;
  Node *tail = NULL;

public:
  int size = 0;

  void add(E element)
  {
    if (head == NULL)
    {
      Node *node = new Node(element);
      head = node;
      tail = node;
    }
    else if (size == 1)
    {
      tail = new Node(element);
      head->next = tail;
      tail->previous = head;
    }
    else
    {
      Node *previousNode = tail;
      tail = new Node(element);
      previousNode->next = tail;
      tail->previous = previousNode;
    }
    size++;
  }

  E get(int index)
  {
    if (head == NULL)
    {
      throw std::invalid_argument("No elements in the list");
    }
    if (index >= size || index < 0)
    {
      throw std::invalid_argument("Index is less than 0 or greater then or equal to size");
    }

    int counter = 0;
    Node *current = head;
    while (counter <= index - 1)
    {
      current = current->next;
      counter++;
    }
    return current->data;
  }
};

int main()
{
  DoublyLinkedList<int> *list = new DoublyLinkedList<int>;
  std::cout << "Size = " << list->size << std::endl;
  std::cout << "Second = " << list->get(0) << std::endl;
  return 0;
}