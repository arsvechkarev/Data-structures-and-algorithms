def merge_sort(array):
    if len(array) > 1:
        middle = len(array) // 2
        L = array[:middle]
        R = array[middle:]
        merge_sort(L)
        merge_sort(R)

        i = j = k = 0

        while i < len(L) and j < len(R):
            if L[i] <= R[j]:
                array[k] = L[i]
                i += 1
            else:
                array[k] = R[j]
                j += 1
            k += 1

        while i < len(L):
            array[k] = L[i]
            i += 1
            k += 1

        while j < len(R):
            array[k] = R[j]
            j += 1
            k += 1


a = [2, 1, 4, 3, 7, 8, 6, 11, 0, 2]

merge_sort(a)

print(a)
