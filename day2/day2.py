#!/usr/bin/python3

if __name__ == '__main__':
    with open('input.txt') as f:
        spreadsheet = [[int(i) for i in row.split('\t')] for row in f]
        print(sum((max(row) - min(row) for row in spreadsheet)))

