#!/usr/bin/python3

if __name__ == '__main__':
    with open('input.txt') as f:
        passphrases = [row.split() for row in f]
        valid_passphrases = sum(len(set(passphrase)) == len(passphrase) for passphrase in passphrases)
        print(valid_passphrases)
        passphrases = [list(map(lambda p: tuple(sorted(p)), p)) for p in passphrases]
        valid_passphrases = sum(len(set(passphrase)) == len(passphrase) for passphrase in passphrases)
        print(valid_passphrases)

