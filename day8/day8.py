#!/usr/bin/python3
from collections import defaultdict

def execute_instruction(instruction, registers):
    instr, condition = instruction.split(" if ")
    condition_register = condition.split(" ")[0]
    if eval(condition.replace(condition_register, str(registers[condition_register]))):
        register_to_change, inc_or_dec, value = instr.split(" ")
        if inc_or_dec == "inc":
            registers[register_to_change] += int(value)
        else:
            registers[register_to_change] -= int(value)


if __name__ == '__main__':
    registers = defaultdict(int)
    with open('input.txt') as f:
        for instruction in f:
            execute_instruction(instruction, registers)
        print(max(registers.values()))
