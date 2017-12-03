
pub fn digits(input: &str) -> Vec<u32> {
    input.chars().map(|c| c.to_digit(10).unwrap()).collect()
}

pub fn captcha1(digits: &[u32]) -> u32 {
    identical_digit_sum(digits, 1)
}

pub fn captcha2(digits: &[u32]) -> u32 {
    identical_digit_sum(digits, digits.len() / 2)
}

pub fn identical_digit_sum(digits: &[u32], skipahead: usize) -> u32 {
    digits
        .iter()
        .zip(digits.iter().cycle().skip(skipahead))
        .filter_map(|(n1,n2)| if n1 == n2 { Some(n1) } else { None })
        .sum()
}
