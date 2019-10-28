module RBTree.RBTree where

data Color = Red | Black deriving (Show, Eq)
data RBTree a = Leaf | Node Color (RBTree a) a (RBTree a) deriving (Eq, Show)


-- this function checks if the RBTree satisfies Red-Black Tree properties
-- four properties (first of all RBTree is BST)
-- a every node is either red or black
-- b the root and leaves are black
-- c the children of red node are black
-- d for each node, every path from it to a leaf
--     has the same number of black nodes
checkRB :: Ord a => RBTree a -> Bool
checkRB Leaf = True
checkRB t = chechSearchTree t &&
            checkBlackRoot t &&
            checkChildrenOfRed t &&
            checkBlackHeight t

checkBlackRoot :: RBTree a -> Bool
checkBlackRoot Leaf               = True
checkBlackRoot (Node Black _ _ _) = True
checkBlackRoot _                  = False

checkChildrenOfRed :: RBTree a -> Bool
checkChildrenOfRed Leaf = True
checkChildrenOfRed (Node Black l _ r) = checkChildrenOfRed l &&
                                        checkChildrenOfRed r
checkChildrenOfRed (Node Red l _ r)   = if lnode == Black && rnode == Black
                                        then checkChildrenOfRed l &&
                                             checkChildrenOfRed r
                                        else False
  where
    lnode = nodeColor l
    rnode = nodeColor r

nodeColor :: RBTree a -> Color
nodeColor Leaf           = Black
nodeColor (Node c _ _ _) = c

checkBlackHeight :: RBTree a -> Bool
checkBlackHeight Leaf = True
checkBlackHeight (Node _ l _ r) = checkBlackHeight l &&
                                  checkBlackHeight r &&
                                  bhll == bhlr &&
                                  bhrl == bhrr &&
                                  bhll == bhrr
  where
    bhll = blackHeightL l
    bhlr = blackHeightL r
    bhrl = blackHeightR l
    bhrr = blackHeightR r



blackHeightL :: RBTree a -> Int
blackHeightL Leaf = 1
blackHeightL (Node c l _ _) = case c of
                                Black -> 1 + blackHeightL l
                                _     -> blackHeightL l

blackHeightR :: RBTree a -> Int
blackHeightR Leaf = 1
blackHeightR (Node c _ _ r) = case c of
                                Black -> 1 + blackHeightR r
                                _     -> blackHeightR r


chechSearchTree :: Ord a => RBTree a -> Bool
chechSearchTree Leaf                 = True
chechSearchTree (Node _ Leaf _ Leaf) = True
chechSearchTree (Node _ Leaf x r)    = getTreeElem r > x &&
                                       chechSearchTree r
chechSearchTree (Node _ l x Leaf)    = getTreeElem l < x &&
                                       chechSearchTree l
chechSearchTree (Node _ l x r) = getTreeElem l < x &&
                                 getTreeElem r > x &&
                                 chechSearchTree l &&
                                 chechSearchTree r
getTreeElem :: RBTree a -> a
getTreeElem (Node _ _ x _) = x
getTreeElem Leaf           = error "Leaf is Nil"



-- this balance function is ti deal with two consecutive red nodes
balance :: Color -> RBTree a -> a -> RBTree a -> RBTree a
balance Black (Node Red (Node Red t1 x1 t2) x2 t3) x3 t4 = Node Red
                                                           (Node Black t1 x1 t2)
                                                           x2
                                                           (Node Black t3 x3 t4)

balance Black (Node Red t1 x1 (Node Red t2 x2 t3)) x3 t4 = Node Red
                                                           (Node Black t1 x1 t2)
                                                           x2
                                                           (Node Black t3 x3 t4)

balance Black t1 x1 (Node Red (Node Red t2 x2 t3) x3 t4) = Node Red
                                                           (Node Black t1 x1 t2)
                                                           x2
                                                           (Node Black t3 x3 t4)

balance Black t1 x1 (Node Red t2 x2 (Node Red t3 x3 t4)) = Node Red
                                                           (Node Black t1 x1 t2)
                                                           x2
                                                           (Node Black t3 x3 t4)
balance c t1 x t2 = Node c t1 x t2

balance' :: RBTree a -> RBTree a
balance' Leaf = Leaf
balance' (Node c l x r) = balance c l x r

-- basic dynamic operations -- search insert delete
search :: Ord a => a -> RBTree a -> Bool
search _ Leaf = False
search k (Node _ l x r)
  | k < x = search k l
  | k > x = search k r
  | otherwise = True


ins :: Ord a => a -> RBTree a -> RBTree a
ins k Leaf = Node Red Leaf k Leaf
ins k t@(Node c l x r)
  | k < x = balance c (ins k l) x r
  | k > x = balance c l x (ins k r)
  | otherwise = t


insert :: Ord a => a -> RBTree a -> RBTree a
insert k t = blackRoot (ins k t)
  where
    blackRoot (Node _ l x r) = (Node Black l x r)


-- (delete x t) looks for key x in the tree t; if it finds it, it deletes it
-- and rebalances the tree so the R-B properties hold
delete :: Ord a => a -> RBTree a -> RBTree a
delete x t = case del x t of
               (Node _ a y b) -> Node Black a y b
               _ -> Leaf


-- Preliminary version of delete: the result satisfies the R-B properties
-- except it may have two consecutive red nodes at the top
del :: Ord a => a -> RBTree a -> RBTree a
del _ Leaf = Leaf
del k (Node _ l x r)
  | k < x = delL k l x r
  | k > x = delR k l x r
  | otherwise = fuse l r

-- Deletes an element from the left child
delL :: Ord a => a -> RBTree a -> a -> RBTree a -> RBTree a
delL k a@(Node Black _ _ _) y b = balR (del k a) y b
delL k a y b = Node Red (del k a) y b

-- Rebalances a tree when the black-height of the left child is one
-- shorter than the right
balL :: RBTree a -> a -> RBTree a -> RBTree a
balL (Node Red t1 lx t2) x t3                  = Node Red (Node Black t1 lx t2) x t3
balL t1 x (Node Black t2 rx t3)                = balance Black t1 x (Node Red t2 rx t3)
balL t1 x (Node Red (Node Black t2 y t3) z t4) = Node Red (Node Black t1 x t2)
                                                 y
                                                 (balance Black t3 z (paintRed t4))
balL t1 x t2 = Node Black t1 x t2


-- Like delL and balL, but on the right
delR :: Ord a => a -> RBTree a -> a -> RBTree a -> RBTree a
delR k a y b@(Node Black _ _ _) = balR a y (del k b)
delR k a y b = Node Red a y (del k b)

balR :: RBTree a -> a -> RBTree a -> RBTree a
balR t1 x (Node Red t2 rx t3)                  = Node Red t1 x (Node Black t2 rx t3)
balR (Node Black t1 lx t2) x t3                = balance Black (Node Red t1 lx t2) x t3
balR (Node Red t1 x (Node Black t2 y t3)) z t4 = Node Red
                                                 (balance Black (paintRed t1) x t2)
                                                 y
                                                 (Node Black t3 z t4)
balR t1 x t2 = Node Black t1 x t2


paintRed :: RBTree a -> RBTree a
paintRed Leaf = Leaf
paintRed (Node Black l x r) = Node Red l x r
paintRed t = t

-- merges two trees t1 and t2 when all elements of t1 are smaller than
-- all elements of t2
fuse :: RBTree a -> RBTree a -> RBTree a
fuse Leaf x = x
fuse x Leaf = x
fuse (Node Red t1 x t2) (Node Red t3 y t4) =
  case fuse t2 t3 of
          Node Red s1 z s2 -> Node Red (Node Red t1 x s1) z (Node Red s2 y t4)
          s -> Node Red t1 x (Node Red s y t4)
fuse (Node Black t1 x t2) (Node Black t3 y t4) =
  case fuse t2 t3 of
    Node Red t5 z t6 -> Node Red (Node Black t1 x t5) z (Node Black t6 y t4)
    s -> balL t1 x (Node Black s y t4)
fuse t1 (Node Red t2 x t3) = Node Red (fuse t1 t2) x t3
fuse (Node Red t1 x t2) t3 = Node Red t1 x (fuse t2 t3)
