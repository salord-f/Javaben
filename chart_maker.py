# -*- coding: utf-8 -*-
"""
Created on Tue Oct  1 17:46:11 2019

@author: Quentin GUIMARD
"""

import glob
import numpy as np
import seaborn as sns
import pandas as pd
import matplotlib.pyplot as plt
from operator import methodcaller

files = [file for file in glob.iglob('**/results.si5', recursive=True)]

sns.set(style='whitegrid')

data = pd.DataFrame(columns=['Alg', 'Opt', 'Family', 'Size', 'Score', 'Time'])

for file in files:
    lines = [line.strip() for line in open(file, 'r', encoding='utf-8')]
    lines = np.array([line.split(' ') for line in lines])

    _, alg, opt = file.split('\\')[0].split('_')

    families, sizes = zip(*[input_name.split('_') for input_name in lines[:, 0] if '_' in input_name])
    sizes = list(map(float, sizes))

    n = len(families)

    scores = lines[:, 1][:n].astype(float)
    times = lines[:, 2][:n].astype(float)

    data = data.append(
        pd.DataFrame(data={'Alg': [alg] * n, 'Opt': [opt] * n, 'Family': families, 'Size': sizes,
                           'Score': scores, 'Time': times})).reset_index(drop=True)

for family in data.Family.unique():
    for opt in data.Opt.unique():
        data_f = data[(data['Family'] == family) & (data['Opt'] == opt)]

        fig, ax = plt.subplots(figsize=(7, 7))
        plt.title('Scores for %s family with%s optimization' % (family, '' if opt == 'true' else 'out'))
        ax.set_xscale('log', basex=10)
        ax.set_yscale('log', basey=10)
        sns.lineplot(x='Size', y='Score', hue='Alg', data=data_f, ax=ax)
        ax.set(xlabel='Size (number of vertices)', ylabel='Score (sum of penalties)')
        plt.savefig('scores_%s_%s.png' % (family, opt))

        fig, ax = plt.subplots(figsize=(7, 7))
        plt.title('Times for %s family with%s optimization' % (family, '' if opt == 'true' else 'out'))
        ax.set_xscale('log', basex=10)
        ax.set_yscale('log', basey=10)
        sns.lineplot(x='Size', y='Time', hue='Alg', data=data_f, ax=ax)
        ax.set(xlabel='Size (number of vertices)', ylabel='Time (ns)')
        plt.savefig('times_%s_%s.png' % (family, opt))

# name = 'raw' if 'Without' in file_cat else 'optimized'
#
# g = sns.catplot(x='Input', y='Score', hue='Alg', data=data,
#                 height=6, kind='bar', palette='muted', ci=None, legend=False)
# g.despine(left=True)
# g.axes[0, 0].set_yscale('log')
# g.axes[0, 0].set_ylim(10, 10 ** 6)
# g.set_ylabels('Score')
# plt.legend(loc='upper right')
# plt.savefig('scores_%s.png' % name)
#
# g = sns.catplot(x='Input', y='Time', hue='Alg', data=data,
#                 height=6, kind='bar', palette='muted', ci=None, legend=False)
# g.despine(left=True)
# g.axes[0, 0].set_yscale('log')
# g.axes[0, 0].set_ylim(10 ** 3, 10 ** 8)
# g.set_ylabels('Time (ns)')
# plt.legend(loc='upper left')
# plt.savefig('times_%s.png' % name)
