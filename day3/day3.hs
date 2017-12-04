type Coords = (Int, Int)
type Vector = (Int, Int)

origin :: Coords
origin = (0, 0)

manhattanDistance :: Coords -> Coords -> Int
manhattanDistance (x1,y1) (x2,y2) = (abs x2 - x1) + (abs y2 - y1)

up, down, left, right :: Vector
up    = ( 0,  1)
down  = ( 0, -1)
left  = (-1, 0 )
right = ( 1, 0 )

directions :: [Vector]
directions = [right, up, left, down]

move :: Coords -> Vector -> Coords
move (x,y) (dx,dy) = (x + dx, y + dy)

coords :: [(Coords)]
coords = scanl move origin path
  where path = concat $ zipWith (\d n -> replicate n d) (cycle directions) ([1..] >>= (replicate 2))

part1 = manhattanDistance origin (coords !! (347991 - 1))
