def insertion_sort(array):
    for i in range(2, len(array)):
        if array[i - 1] > array[i]:
            j = i
            while j > 0 and array[j - 1] > array[j]:
                temp = array[j]
                array[j] = array[j - 1]
                array[j - 1] = temp
                j -= 1


def selection_sort(array):
    for i in range(len(array)):
        min_elem = array[i]
        min_pos = i
        for j in range(i, len(array)):
            if array[j] < min_elem:
                min_elem = array[j]
                min_pos = j
        temp = a[i]
        array[i] = min_elem
        array[min_pos] = temp


a = [2, 3, 4, 1, 9, 8, 5, 7, 6, 0]

isort(a)

print(a)
