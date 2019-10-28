-- G54AAD: Advanced Algorithms and Data Structures
-- The Maximum Subarray Problem
--  Venanzio Capretta, Autumn 2019

import System.Random  -- for testing

-- NAIVE QUADRATIC ALGORITHM: check all subarrays
maxSubNQ :: (Ord a, Num a) => [a] -> (Int,Int,a)
maxSubNQ [x] = (0,0,x)
maxSubNQ xs = if s0>=s then (0,j0,s0) else (i+1,j+1,s)
  where (j0,s0) = argMax snd (sums xs)
        (i,j,s) = maxSubNQ (tail xs)

-- All sums starting at the beginning, with ending index
sums :: (Num a) => [a] -> [(Int,a)]
sums xs = accSums 0 0 xs

accSums :: (Num a) => Int -> a -> [a] -> [(Int,a)]
accSums j s [] = []
accSums j s (x:xs) = let s'=x+s in (j,s') : accSums (j+1) s' xs

-- Element of a list maximizing a function
argMax :: Ord b => (a->b) -> [a] -> a
argMax _ [x] = x
argMax f (x:xs) = let y = argMax f xs in
                  if (f x)>(f y) then x else y


-- DIVIDE-AND-CONQUER ALGORITHM (IA 4.1)
-- The maximum subarray is either
-- the maximum of the left half, the maximum of the right half
-- or the largest "crossing" subarray

maxSubDC :: (Ord a, Num a) => [a] -> (Int,Int,a)
maxSubDC [x] = (0,0,x)
maxSubDC xs = let mid = length xs `div` 2
                  (xs1,xs2) = splitAt mid xs
                  (i1,j1,max1) = maxSubDC xs1
                  (i2,j2,max2) = maxSubDC xs2
                  (i3,j3,max3) = maxCross xs1 xs2
             in if max1 >= max2 && max1 >= max3
                then (i1,j1,max1)
                else if max2 >= max3 
                     then (i2+mid,j2+mid,max2)
                     else (i3,j3+mid,max3)

-- Maximum crossing subarray
maxCross :: (Ord a, Num a) => [a] -> [a] -> (Int,Int,a)
maxCross xs1 xs2 = let (i,max1) = leftMax xs1
                       (j,max2) = rightMax xs2
                    in (i,j,max1+max2)

-- Maximum of left part
leftMax :: (Ord a, Num a) => [a] -> (Int,a)
leftMax xs = (i,max)
  where (i,max,_) = leftMaxSum xs
        -- leftMaxSum returns: max index, max sum, present sum
        leftMaxSum [x] = (0,x,x)
        leftMaxSum (x:xs) =
          let (i,max,sum) = leftMaxSum xs
              newsum = sum+x
          in if newsum > max
             then (0,newsum,newsum)
             else (i+1,max,newsum)

-- Maximum of right part
rightMax :: (Ord a, Num a) => [a] -> (Int,a)
rightMax xs = let (i,max) = leftMax (reverse xs)
               in (length xs - i - 1, max)


-- LINEAR TIME ALGORITHM (Kadane, IA Ex.4.1-5)
-- Starting from the end of the list and going left, recursively compute
-- the maximum subarray so far and the maximum subarray starting at
-- the present point and stretching right
maxSubLT ::  (Ord a, Num a) => [a] -> (Int,Int,a)
maxSubLT xs = (i,j,max)
  where (i,j,max,_,_) = maxSubAux xs

-- Extended function returning (i,j,x,k,y) where
-- (i,j,x) indices and sum of maximum subarray
-- (k,y) index of right end and sum of maximum leftmost subarray 
maxSubAux :: (Ord a, Num a) => [a] -> (Int,Int,a,Int,a)
maxSubAux [x] = (0,0,x,0,x)
maxSubAux (x:xs) = 
  let (imax,jmax,max,jrun,run) = maxSubAux xs
      (newjr,newr) = if run <= 0 then (0,x) else (jrun+1,x+run)
  in if newr >= max
     then (0,newjr,newr,newjr,newr)
     else (imax+1,jmax+1,max,newjr,newr)


-- USING FOLD LEFT
maxSubFL :: (Ord a, Num a) => [a] -> (Int,Int,a)
maxSubFL = snd . foldl f ((0,-1,0),(-1,-1,0)) 
  where  f ((i,j,s),sofar) x = (a,b) 
           where a = maxInd (j+1,j+1,x) (i, j+1, s + x)
                 b = maxInd sofar a
         maxInd x0@(_,_,s0) x1@(_,_,s1) = if s0>=s1 then x0 else x1

-- A couple of additional functions

-- Get a sublist between indices i and j
subList :: Int -> Int -> [a] -> [a]
subList i j = take (j-i+1) . drop i

-- All nonempty sublists of a list
allSubs :: [a] -> [[a]]
allSubs l = [subList i j l | i <- [0..length l-1], j <- [i..length l -1]]


-- TESTING

-- Generates a random list of numbers between -10 and 10
randList :: Int -> IO [Integer]
randList 0 = return []
randList n = do x <- randomRIO (-10,10) :: IO Integer
                xs <- randList (n-1)
                return (x:xs)

{- Test it with these commands ( >:set +s to time it )
> xs <- randList 10000
> maxSubNQ xs
> maxSubDC xs
> maxSubLT xs
> maxSubFL xs
-}

