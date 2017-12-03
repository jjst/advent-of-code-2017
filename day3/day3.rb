Inf = Float::INFINITY

def nearest_square(n)
  (1..Inf).step(2).each_with_index do |num, index|
    return [[index, index], num.to_i ** 2] if num.to_i ** 2 >= n
  end
end

def position_of_num(n)
  nearest_square_coord, nearest_square_value = nearest_square(n)
  delta = nearest_square_value - n

  max_dist = nearest_square_coord.inject(&:+)
  
  if delta.zero?
    return max_dist
  end

  temp = nearest_square_value
  while temp >= n
    temp -= max_dist
  end

  delta2 = [n - temp, (temp + max_dist) - n].min

  return max_dist - delta2
end
