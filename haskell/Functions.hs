module Functions where

-- Checks whether a given element is in the given list
isElem :: (Eq a) => a -> [a] -> Bool
isElem _ [] = False
isElem elem (head : tail) = if elem == head then True else isElem elem tail

-- Checks whether a given element is not in the given list
isNotElem :: (Eq a) => a -> [a] -> Bool
isNotElem _ [] = True
isNotElem elem (head : tail) = if elem == head then False else isNotElem elem tail

-- Takes number A and object B and returns list of B repeated A times
replicate' :: (Integral i) => i -> e -> [e]
replicate' 0 _ = []
replicate' i e = e : replicate' (i - 1) e

-- Takes i first elements from list [e]
take' :: (Integral i) => i -> [e] -> [e]
take' 0 _ = []
take' i (head : tail) = head : take' (i - 1) tail

-- Reverses the given list
reverse' :: [e] -> [e]
reverse' [] = []
reverse' (head : tail) = reverse tail ++ [head]

-- Repeats element e infinite amount of times
repeat' :: e -> [e]
repeat' e = e : repeat' e

-- Merges elements from two lists into one with given function
zipWith' :: (a -> b -> c) -> [a] -> [b] -> [c]
zipWith' _ [] _ = []
zipWith' _ _ [] = []
zipWith' f (head1 : tail1) (head2 : tail2) = (f head1 head2) : zipWith' f tail1 tail2

-- Returns a list where all elements satisfy given predicate
filter' :: (a -> Bool) -> [a] -> [a]
filter' _ [] = []
filter' f (head : tail)
  | f (head) = head : filter' f tail
  | otherwise = filter' f tail

-- Transforms elements in a list from one type to another
map' :: (a -> b) -> [a] -> [b]
map' _ [] = []
map' f (head : tail) = f head : map' f tail

-- Performs left fold with given accumulator
foldLeft :: (a -> a -> a) -> a -> [a] -> a
foldLeft f acc [e] = f acc e
foldLeft f acc (head : tail) = f acc (foldLeft f head tail)

-- Performs right fold with given accumulator
foldRight :: (a -> a -> a) -> a -> [a] -> a
foldRight f acc [e] = f e acc
foldRight f acc list = f (last list) (foldRight f acc $ init list)

-- Returns sum of all numbers in the given list
sum' :: (Num a) => [a] -> a
sum' list = foldLeft (\acc e -> acc + e) 0 list

-- Returns average of the given list
avg :: (Fractional a) => [a] -> a
avg list = (sum' list) / (fromIntegral $ length list)

-- Takes elements from the given list using given predicate while predicate is met
takeWhile' :: (a -> Bool) -> [a] -> [a]
takeWhile' _ [] = []
takeWhile' f (head : list)
  | f head = head : takeWhile' f list
  | otherwise = []

-- Drops elements from the given list using given predicate while predicate is met
dropWhile' :: (a -> Bool) -> [a] -> [a]
dropWhile' _ [] = []
dropWhile' f list
  | f first = dropWhile' f elementsLeft
  | otherwise = list
  where
    first = head list
    elementsLeft = tail list

-- Returns list that contains all elements that are in the first given list, but not in the second
difference :: (Eq a) => [a] -> [a] -> [a]
difference [] _ = []
difference (head : tail) other
  | head `isNotElem` other = head : difference tail other
  | otherwise = difference tail other

-- Generage an infinite list applying a given function to given value, then to new value, etc
iterate' :: (a -> a) -> a -> [a]
iterate' f a = a : (iterate' f $ f a)

-- Splits a given list into two groups: ones that satisfy given predicate, and ones that don't
partition :: (Eq a) => (a -> Bool) -> [a] -> ([a], [a])
partition _ [] = ([], [])
partition f list = (satisfiedElements, notSatisfiedElements)
  where
    satisfiedElements = filter f list
    notSatisfiedElements = filter (not . f) list

-- Sorts a given list with quicksort algorithm
quicksort :: (Ord a) => [a] -> [a]
quicksort [] = []
quicksort (head : tail) =
  quicksort (filter' (<= head) (tail)) ++ [head] ++ quicksort (filter' (> head) (tail))

-- Returns a factorial of the given number
factorial :: Integer -> Integer
factorial 0 = 1
factorial n = n * factorial (n - 1)