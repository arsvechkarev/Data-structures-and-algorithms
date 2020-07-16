def insertion_sort(array):
    for i in range(2, len(array)):
        if array[i - 1] > array[i]:
            j = i
            while j > 0:
                if array[j - 1] > array[j]:
                    temp = array[j]
                    array[j] = array[j - 1]
                    array[j - 1] = temp
                j -= 1


a = [2, 7, 5, 4, 6, 1, 3]

insertion_sort(a)

assert a == [1, 2, 3, 4, 5, 6, 7]
