module Main where
import RBTree.RBTree
import RBTree.Parser
import System.Environment

-- main programm -> searchtree
-- it must take as input the name of a text file containing a sequence of dynamic
-- set operations, each consisting of the name of the operation (search, insert,
-- delete) and a number

main :: IO ()
main = do args <- getArgs
          case args of
            [] -> error "please provide a valid test file"
            _  -> do input <- (readFile . head) args
                     let (t, searchResult) = app Leaf [] (eval input)
                     printSearch searchResult

-- apply operations read from inout file
app :: RBTree Int -> [String] -> [Op] -> (RBTree Int, [String])
app t str []        = (t, str)
app t str (Insert a:xs) = app (insert a t) str xs
app t str (Delete a:xs) = app (delete a t) str xs
app t str (Search a:xs) = case search a t of
                            True  -> app t trueStr  xs
                            False -> app t falseStr xs
  where
    trueStr  = str ++ ["searching for " ++ (show a) ++ " found"]
    falseStr = str ++ ["searching for " ++ (show a) ++ " not found"]

-- print results of search
printSearch :: [String] -> IO ()
printSearch [] = return ()
printSearch (x:xs) = do putStrLn x
                        printSearch xs
