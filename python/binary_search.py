def binary_search(array, item):
    lo = 0
    hi = len(array) - 1
    while lo < hi:
        mid = (lo + hi) // 2
        if array[mid] == item:
            return mid
        if array[mid] < item:
            lo = mid + 1
        if array[mid] > item:
            hi = mid - 1
    return -1


arr = [1, 2, 4, 6, 8, 9, 10, 12, 15, 17, 18]
result = binary_search(arr, 17)
print(result)
