module Functions where

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
foldl' :: (a -> a -> a) -> a -> [a] -> a
foldl' f acc [e] = f acc e
foldl' f acc (head : tail) = f acc (foldl' f head tail)

-- Performs right fold with given accumulator
foldr' :: (a -> a -> a) -> a -> [a] -> a
foldr' f acc [e] = f e acc
foldr' f acc list = f lastElement (foldr' f acc firstElements)
  where
    lastElement = last list
    firstElements = init list

-- Returns sum of all numbers in the list
sum' :: (Num a) => [a] -> a
sum' list = foldl' (\acc e -> acc + e) 0 list

-- Sorts a given list with quicksort algorithm
quicksort :: (Ord a) => [a] -> [a]
quicksort [] = []
quicksort (head : tail) =
  quicksort (filter' (<= head) (tail)) ++ [head] ++ quicksort (filter' (> head) (tail))