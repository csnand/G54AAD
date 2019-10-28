-- Advanced Algorithms and Data Structures
--  Venanzio Capretta, 2019-2020

-- Review of sorting algorithms

import System.Random  -- for testing


-- Insertion Sort
-- Various versions, they all run in O(n^2)

-- inserting an element into an ordered list
insert :: Ord a => a -> [a] -> [a]
insert x [] = [x]
insert x (y:ys) = if x<=y then x:y:ys else y:insert x ys

ins_sort :: Ord a => [a] -> [a]
ins_sort [] = []
ins_sort (x:xs) = insert x (ins_sort xs)

-- Other equivalent formulations

-- one-line version using foldr
ins_sort1 :: Ord a => [a] -> [a]
ins_sort1 = foldr insert []

-- using an accumulator
ins_sort2 :: Ord a => [a] -> [a]
ins_sort2 xs = ins_sort_acc xs []
  where ins_sort_acc [] ys = ys
        ins_sort_acc (x:xs) ys = ins_sort_acc xs (insert x ys)

-- one-line version using foldl
-- flip swaps the order of arguments of a 2-args function
ins_sort3 :: Ord a => [a] -> [a]
ins_sort3 = foldl (flip insert) []


-- Merge Sort
-- It runs in O(n(log n))

merge :: Ord a => [a] -> [a] -> [a]
merge [] ys = ys
merge xs [] = xs
merge xs@(x:xs') ys@(y:ys') = if x<=y then x: merge xs' ys
                                      else y: merge xs ys'

merge_sort :: Ord a => [a] -> [a]
merge_sort [] = []
merge_sort [x] = [x]
merge_sort xs = let (ys,zs) = splitAt (length xs `div` 2) xs
                in merge (merge_sort ys) (merge_sort zs)


-- Quicksort
-- Although quick sort is not O(n(log n)) in worst case
--  it performs slightly better than merge sort on random lists

splitWith :: Ord a => a -> [a] -> ([a],[a])
splitWith x = foldl swAdd ([],[])
  where swAdd (xs,ys) z = if z<=x then (z:xs,ys)
                                  else (xs,z:ys)
  
quick_sort :: Ord a => [a] -> [a]
quick_sort [] = []
quick_sort [x] = [x]
quick_sort (x:xs) = let (ys,zs) = splitWith x xs
                    in (quick_sort ys) ++ x:(quick_sort zs)
                       

-- Test using lists of random numbers

-- generates a list of n random elements between 0 and 10*n

random_list n = do
  let range = 10*n
  let length = n
  l <- sequence $ take length $ repeat (randomRIO (0,range))
  return l
  
-- In ghci do
-- :set +s                   to time computations
-- l <- random_list (10^4)   generate a list of 10,000 numbers
-- last $ ins_sort l         check how long it take to sort it
--                            (without printing it all)
-- last $ merge_sort l
-- last $ quick_sort l

