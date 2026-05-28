from collections import Counter
import string
import sys

lines = [line.strip() for line in open(sys.argv[1], 'r').readlines()]
template = lines[0]
rules = [rule.split(' ') for rule in lines[2:]]
rules = {a: (a[0]+c,c+a[1]) for a,b,c in rules}
pairs = [''.join(p) for p in zip(template, template[1:])]

# total the pairs created by substitution
def run(steps):
    ctr = Counter(pairs)
    for i in range(steps):
        newCtr = {key : 0 for key in rules.keys()}
        for key, value in ctr.items():
            newCtr[rules[key][0]] += value
            newCtr[rules[key][1]] += value
        ctr = newCtr

    letterTotals = {letter : 0 for letter in list(string.ascii_uppercase)}
    for key, value in ctr.items():
        letterTotals[key[0]] += value

    # the last character in the template gets another count
    letterTotals[template[-1]] += 1

    lmax = max(letterTotals.values())
    lmin = min([value for value in letterTotals.values() if value > 0])
    return lmax - lmin

print(f"2021 day14: p_ans_1: {run(10)}")
print(f"2021 day14: p_ans_2: {run(40)}")
