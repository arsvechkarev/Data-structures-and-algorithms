#include <iostream>
#include <stdexcept>
#include <string>

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
  int _size = 0;

public:
  int size()
  {
    return _size;
  }


  void addFirst(E element)
  {
    if (head == NULL)
    {
      Node *node = new Node(element);
      head = node;
      tail = node;
    }
    else if (_size == 1)
    {
      head = new Node(element);
      tail->previous = head;
      head->next = tail;
    }
    else
    {
      Node *nodeAfter = head;
      head = new Node(element);
      nodeAfter->previous = head;
      head->next = nodeAfter;
    }
    _size++;
  }

  void addLast(E element)
  {
    if (head == NULL)
    {
      Node *node = new Node(element);
      head = node;
      tail = node;
    }
    else if (_size == 1)
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
    _size++;
  }

  E get(int index)
  {
    if (head == NULL)
    {
      throw std::invalid_argument("No elements in the list");
    }
    if (index >= _size || index < 0)
    {
      throw std::invalid_argument("Index is less than 0 or greater then or equal to size");
    }

    if (index < _size / 2)
    {
      int counter = _size - 1;
      Node *current = tail;
      while (counter > index)
      {
        current = current->previous;
        counter--;
      }
      return current->data;
    }
    else
    {
      int counter = 0;
      Node *current = head;
      while (counter < index)
      {
        current = current->next;
        counter++;
      }
      return current->data;
    }
  }

  E remove(int index)
  {
    if (head == NULL)
    {
      throw std::invalid_argument("No elements in the list");
    }
    if (index >= _size || index < 0)
    {
      throw std::invalid_argument("Index is less than 0 or greater then or equal to size");
    }

    E result;

    if (_size == 1)
    {
      result = head->data;
      head = tail = NULL;
      _size--;
      return result;
    }

    if (index < _size / 2)
    {
      int counter = _size - 1;
      Node *current = tail;
      while (counter > index)
      {
        current = current->previous;
        counter--;
      }

      result = current->data;
      Node *previous = current->previous;
      if (previous == NULL)
      {
        current->next->previous = NULL;
      }
      else
      {
        previous->next = current->next;
        current->next->previous = previous;
      }
      current = NULL;
    }
    else
    {
      int counter = 0;
      Node *current = head;
      while (counter < index)
      {
        current = current->next;
        counter++;
      }

      result = current->data;
      Node *next = current->next;
      if (next == NULL)
      {
        current->previous->next = NULL;
      }
      else
      {
        next->previous = current->previous;
        current->previous->next = next;
      }
      current = NULL;
    }
    _size--;
    return result;
  }
};

void assert(bool condition)
{
  if (!condition)
  {
    throw std::runtime_error("Error happened");
  }
}

int main()
{
  DoublyLinkedList<int> list;

  assert(list.size() == 0);

  list.addFirst(5);

  assert(list.size() == 1);
  assert(list.get(0) == 5);
  assert(list.remove(0) == 5);
  assert(list.size() == 0);

  list.addFirst(2);
  list.addFirst(1);
  list.addLast(3);
  list.addLast(4);

  assert(list.size() == 4);
  assert(list.get(0) == 1);
  assert(list.get(3) == 4);

  assert(list.remove(1) == 2);
  assert(list.remove(1) == 3);
  assert(list.remove(1) == 4);
  assert(list.remove(0) == 1);
  assert(list.size() == 0);
  return 0;
}