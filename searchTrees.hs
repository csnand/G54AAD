-- Advanced Algorithms and Data Structures
--  Venanzio Capretta, 2019-2020

-- Binary Search Trees

{- A Search structure is a data constructor that allows us to store
   values from an ordered type, so it is efficient to search them,
   find the minimum and maximum, insert and delete elements.
-}

module SearchTrees where

class Search s where
  emptyS :: s a
  isEmptyS :: s a -> Bool
  searchS :: Ord a => a -> s a -> Bool
  minS :: Ord a => s a -> Maybe a
  maxS :: Ord a => s a -> Maybe a
  insertS :: Ord a => a -> s a -> s a
  deleteS :: Ord a => a -> s a -> s a

  -- default implementations of conversion with lists
  -- specific instances may have their own efficient version
  
  fromList :: Ord a => [a] -> s a
  fromList [] = emptyS
  fromList (x:xs) = insertS x (fromList xs)
  
  toList :: Ord a => s a -> [a]
  toList d = case minS d of
               Nothing -> []
               Just x  -> x : toList (deleteS x d)

-- We can use fromList and toList to implement a sorting algorithm
--    sort :: Ord a => [a] -> [a]
--    sort = toList . fromList
-- This has to be defined specifically for every instance
--  Because the data constructor doesn't appear in the type

{- A Binary Search Tree stores elements in the nodes so that
   the root is larger than all the elements in the left child
           and smaller than all the elements in the right child.
-}

data BTree a = Nil | Node a (BTree a) (BTree a)
  deriving (Show,Eq)

instance Search BTree where
  emptyS = Nil

  isEmptyS Nil = True
  isEmptyS _   = False

  searchS _ Nil = False
  searchS k (Node x l r) =
     (k == x) || if (k < x) then searchS k l
                            else searchS k r

  minS Nil = Nothing
  minS (Node x Nil _) = Just x
  minS (Node x l _) = minS l
  
  maxS Nil = Nothing
  maxS (Node x _ Nil) = Just x
  maxS (Node x _ r) = maxS r

  insertS k Nil = Node k Nil Nil
  insertS k (Node x l r) = 
    if k <= x then Node x (insertS k l) r
              else Node x l (insertS k r) 

  deleteS k Nil = Nil
  deleteS k (Node x l r) =
    if k == x
    then if isEmptyS l then r
         else if isEmptyS r then l
              else let (m,l') = getMax l
                   in Node m l' r
    else if k < x then (Node x (deleteS k l) r)
                  else (Node x l (deleteS k r))

  fromList [] = emptyS
  fromList (x:xs) = Node x (fromList [y | y <- xs, y<=x])
                           (fromList [z | z <- xs, z>x])

  toList Nil = []
  toList (Node x l r) = toList l ++ x : toList r


-- Find the maximum and delete it
getMax :: Ord a => BTree a -> (a,BTree a)
getMax (Node a l Nil) = (a,l)
getMax (Node a l r) = (m, Node a l r')
                      where (m,r') = getMax r

sortBT :: Ord a => [a] -> [a]
sortBT xs = toList ((fromList :: Ord a => [a] -> BTree a) xs)


-- To give meaning to successor and predecessor of an element we need
-- Pointers to specific elements in a tree
-- We use paths in the tree: lists of booleans to decend the tree
--  the path is Nothing if the pointer is uninitialized

type BPath = Maybe [Bool]

type BTEl a = (BTree a, BPath)

key :: BTEl a -> Maybe a
key (Nil, _) = Nothing
key (_,Nothing) = Nothing 
key (Node x l r, Just []) = Just x
key (Node x l r, Just (b:bs)) = if b then key (r,Just bs) else key (l,Just bs)

isNil :: Eq a => BTEl a -> Bool
isNil x = key x == Nothing

left :: BTEl a -> BTEl a
left (t,Just bs) = (t,Just (bs++[False]))
left (t,Nothing) = (t,Nothing)

right :: BTEl a -> BTEl a
right (t,Just bs) = (t,Just (bs++[True]))
right (t,Nothing) = (t,Nothing)

parent :: BTEl a -> BTEl a
parent (t,Nothing) = (t,Nothing)
parent (t,Just []) = (t,Nothing)
parent (t,Just bs) = (t,Just (drop 1 bs))

-- Search

tSearch :: Ord a => BTEl a -> a -> BTEl a
tSearch x k = 
  if isNil x || Just k == key x 
  then x
  else if Just k < key x
       then tSearch (left x) k
       else tSearch (right x ) k

tMin :: Ord a => BTEl a -> BTEl a
tMin x = if isNil (left x) then x else tMin (left x)

tMax :: Ord a => BTEl a -> BTEl a
tMax x = if isNil (right x) then x else tMax (right x)

-- Successor of an element

tSucc :: Ord a => BTEl a -> BTEl a
tSucc x = if isNil (right x)
          then upFirstLeft x
          else tMin (right x)

upFirstLeft :: BTEl a -> BTEl a
upFirstLeft (t,Nothing) = (t,Nothing)
upFirstLeft (t,Just bs) = (t,bs')
  where bs' = cutL (cutRs (reverse bs))
        cutRs (True:bs) = cutRs bs
        cutRs bs = bs
        cutL (False:bs) = Just (reverse bs)
        cutL _ = Nothing

-- Predecessor of an element

tPred :: Ord a => BTEl a -> BTEl a
tPred x = if isNil (left x)
          then upFirstRight x
          else tMax (left x)

upFirstRight :: BTEl a -> BTEl a
upFirstRight (t,Nothing) = (t,Nothing)
upFirstRight (t,Just bs) = (t,bs')
  where bs' = cutL (cutRs (reverse bs))
        cutRs (False:bs) = cutRs bs
        cutRs bs = bs
        cutL (True:bs) = Just (reverse bs)
        cutL _ = Nothing

