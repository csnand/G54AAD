module RBTree.Parser(Op(..), eval) where

{-# LANGUAGE RankNTypes #-}
import Data.Char
import Control.Applicative

-- tree means what has parsed and string means remaining string
-- it will return a list to indicates the result of parsing
-- singleton list means success and empty means failure

-- parser with a dummy constructor
newtype Parser a = P (String -> [(a, String)])

-- apply constructor
parse :: Parser a -> String -> [(a,String)]
parse (P p) str = p str

-- parse string result first item and rest as result
-- or empty list as fail
item :: Parser Char
item = P(\str -> case str of
           []     -> []
           (x:xs) -> [(x,xs)])
-- make Parser functor, applicative and monad to combine several together
-- fmap applies function to the result of a parser
instance Functor Parser where
  -- fmap :: (a -> b) -> Parser a -> Parser b
  fmap g p = P (\str -> case parse p str of
                   [] -> []
                   [(a, rest)] -> [(g a, rest)])


instance Applicative Parser where
  -- pure :: a -> Parser a
  pure a = P (\str -> [(a, str)])

  -- <*> :: Parser (a -> b) -> Parser a-> Parser b
  pa <*> pb = P (\str -> case parse pa str of
                    [] -> []
                    [(a, rest)] -> parse (fmap a pb) rest)

instance Monad Parser where
  -- >>= :: Parser a -> (a -> Parser b) -> Parser b
  p >>= f = P (\str -> case parse p str of
                  [] -> []
                  [(a, rest)] -> parse (f a) rest)

-- this part is about making chioces -> combine parser together for
-- more complex behaviours

instance Alternative Parser where
  -- empty :: Parser a
  empty = P (\_ -> [])

  pa <|> pb = P (\str -> case parse pa str of
                    [] -> parse pb str
                    [(a,rest)] -> [(a,rest)])

-- check if the input str satisfiy the predicate p
sat :: (Char -> Bool) -> Parser Char
sat p = do x <- item
           if p x then return x else empty

-- parser for single digit
digit :: Parser Char
digit = sat isDigit

-- parser for special chars
char :: Char -> Parser Char
char c = sat (== c)

-- parser for string
string :: String -> Parser String
string [] = return []
string (c:cs) = do char c
                   string cs
                   return (c:cs)


-- parser for natural numbers
nat :: Parser Int
nat = do xs <- some digit
         return (read xs)

-- parser for integers based on nat parser
int :: Parser Int
int = do char '-'
         n <- nat
         return (-n)
      <|> nat

-- parser for spaces (space tab newline) and return empty tuple
space :: Parser ()
space = do many (sat isSpace)
           return ()

-- handling spaces
token :: Parser a -> Parser a
token p = do space
             n <- p
             space
             return n

--parser that ignore spaces around integers
integer :: Parser Int
integer = token int

-- parser that ignore spaces around special symbols
symbol :: String -> Parser String
symbol str = token (string str)

-- op ::= expr nat
-- expr ::= insert | delete | search
-- nat ::= 0 | 1 | ... | 9

data Op = Insert Int | Search Int | Delete Int deriving Show

op :: Parser Op
op = insertOp <|> searchOp <|> deleteOp


insertOp :: Parser Op
insertOp = do _ <- symbol "insert"
              k <- integer
              return (Insert k)

searchOp :: Parser Op
searchOp = do _ <- symbol "search"
              k <- integer
              return (Search k)

deleteOp :: Parser Op
deleteOp = do _ <- symbol "delete"
              k <- integer
              return (Delete k)

-- the [] case means parsing error which indicates that input string
-- contains illegal characters.
-- parsing will stop where first illegal character occurs
eval :: String -> [Op]
eval str = case parse op str of
             [(a,[])]    -> [a]
             [(a, as)]   -> a:eval as
             []          -> error ("Parsing Error: Input contains illegal characters")
