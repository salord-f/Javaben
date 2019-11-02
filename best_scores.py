# -*- coding: utf-8 -*-
"""
Created on Fri Nov  1 18:05:46 2019

@author: Quentin GUIMARD
"""

import glob
from collections import Counter
from shutil import copyfile

scores = {}


def score(input_file, output_file):
    input_lines = [line.strip() for line in open(input_file, 'r') if line.strip()]
    output_lines = [line.strip() for line in open(output_file, 'r') if line.strip()]

    v, e = map(int, input_lines[0].split(' '))

    assert len(output_lines) == v
    assert len(input_lines) == e + 1

    edges = [tuple(map(int, line.split(' '))) for line in input_lines[1:]]
    coords = [tuple(map(int, line.split(' '))) for line in output_lines]

    overlaps = {k: v for k, v in Counter(coords).items() if v > 1}

    res = 0

    for n in overlaps.values():
        res += (3 * ((n - 1) ** 2))

    for v1, v2 in edges:
        x1, y1 = coords[v1]
        x2, y2 = coords[v2]
        res += (2 * (((abs(x2 - x1) + abs(y2 - y1)) - 1) ** 2))

    x, y = zip(*coords)

    res += max(max(x) - min(x), max(y) - min(y)) ** 2

    return res


for output_file in glob.iglob('**/*.ans', recursive=True):
    if output_file.startswith('final'):
        continue
    alg, output_name = output_file.split('\\')
    input_file = 'inputs\\%sin' % output_name[:-3]
    s = score(input_file, output_file)
    try:
        prev_s = scores[output_name][0]
        if s < prev_s:
            scores[output_name] = (s, alg)
    except KeyError:
        scores[output_name] = (s, alg)

print(scores)

for s in scores.keys():
    copyfile('%s\\%s' % (scores[s][1], s), 'final\\%s' % s)

for output_file in glob.iglob('final/*.ans', recursive=True):
    _, output_name = output_file.split('\\')
    input_file = 'inputs\\%sin' % output_name[:-3]
    assert score(input_file, output_file) == scores[output_name][0]
