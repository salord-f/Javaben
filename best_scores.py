# -*- coding: utf-8 -*-
"""
Created on Fri Nov  1 18:05:46 2019

@author: Quentin GUIMARD
"""

import glob
from collections import Counter
from os import mkdir
from shutil import copyfile, rmtree

scores = {}

names_map = {  # Because custom names were set to run them in size order
    'GG_00000024.': '01GG_00000024.',
    'TR_00000024.': '02TR_00000024.',
    'TG_00000024.': '03TG_00000024.',
    'DG_00000033.': '04DG_00000033.',
    'TR_00000077.': '05TR_00000077.',
    'GG_00000077.': '06GG_00000077.',
    'TG_00000077.': '07TG_00000077.',
    'DG_00000124.': '08DG_00000124.',
    'TR_00000275.': '09TR_00000275.',
    'GG_00000275.': '10GG_00000275.',
    'TG_00000275.': '11TG_00000275.',
    'DG_00000489.': '12DG_00000489.',
    'TR_00001035.': '13TR_00001035.',
    'GG_00001035.': '14GG_00001035.',
    'TG_00001035.': '15TG_00001035.',
    'DG_00001948.': '16DG_00001948.',
    'TR_00004014.': '17TR_00004014.',
    'GG_00004014.': '18GG_00004014.',
    'TG_00004014.': '19TG_00004014.',
    'DG_00007785.': '20DG_00007785.',
    'TR_00015809.': '21TR_00015809.',
    'GG_00015809.': '22GG_00015809.',
    'TG_00015809.': '23TG_00015809.',
    'DG_00031132.': '24DG_00031132.',
    'TR_00062747.': '25TR_00062747.',
    'GG_00062747.': '26GG_00062747.',
    'TG_00062747.': '27TG_00062747.',
    'DG_00124521.': '28DG_00124521.',
    'TR_00250011.': '29TR_00250011.',
    'GG_00250011.': '30GG_00250011.',
    'TG_00250011.': '31TG_00250011.',
    'DG_00498076.': '32DG_00498076.',
    'TR_00998094.': '33TR_00998094.',
    'GG_00998094.': '34GG_00998094.',
    'TG_00998094.': '35TG_00998094.',
    'DG_01992297.': '36DG_01992297.',
}

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
    if output_file.startswith('final') or len(output_file.split('\\')[-1]) != 15:
        continue
    alg, output_name = output_file.split('\\')
    input_file = 'src\\main\\resources\\mapping\\%sin' % names_map[output_name[:-3]]
    s = score(input_file, output_file)
    try:
        prev_s = scores[output_name][0]
        if s < prev_s:
            scores[output_name] = (s, alg)
    except KeyError:
        scores[output_name] = (s, alg)

print(scores)

rmtree('final\\', ignore_errors=True)
mkdir('final\\')

for s in scores.keys():
    copyfile('%s\\%s' % (scores[s][1], s), 'final\\%s' % s)

for output_file in glob.iglob('final/*.ans', recursive=True):
    _, output_name = output_file.split('\\')
    input_file = 'src\\main\\resources\\mapping\\%sin' % names_map[output_name[:-3]]
    assert score(input_file, output_file) == scores[output_name][0]
