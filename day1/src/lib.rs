
pub fn solution(input: &str) -> u32 {
    let digits: Vec<u32> = input.chars().map(|c| c.to_digit(10).unwrap()).collect();
    println!("{:?}", digits);
    digits
        .iter()
        .zip(digits.iter().cycle().skip(1))
        .filter_map(|(n1,n2)| if n1 == n2 { Some(n1) } else { None })
        .sum()
}
