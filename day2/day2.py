#!/usr/bin/python3
import itertools

def part1(spreadsheet):
    return sum((max(row) - min(row) for row in spreadsheet))

def part2(spreadsheet):
    def even_division(n1, n2):
        if n1 % n2 == 0:
            return n1 // n2
        elif n2 % n1 == 0:
            return n2 // n1
        else:
            return 0

    return sum(sum(even_division(n1,n2) for (n1,n2) in itertools.combinations(row, 2)) for row in spreadsheet)


if __name__ == '__main__':
    with open('input.txt') as f:
        spreadsheet = [[int(i) for i in row.split('\t')] for row in f]
        print(part1(spreadsheet))
        print(part2(spreadsheet))

