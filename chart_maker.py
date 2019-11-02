# -*- coding: utf-8 -*-
"""
Created on Tue Oct  1 17:46:11 2019

@author: Quentin GUIMARD
"""

import glob
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
import seaborn as sns

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

        _, ax = plt.subplots(figsize=(7, 7))
        plt.title('Scores for %s family with%s optimization' % (family, '' if opt == 'true' else 'out'))
        ax.set_xscale('log', basex=10)
        ax.set_yscale('log', basey=10)
        sns.lineplot(x='Size', y='Score', hue='Alg', data=data_f, ax=ax)
        ax.set_xlim([10 ** 1, 10 ** 7])
        ax.set_ylim([10 ** 1, 10 ** 19])
        ax.set(xlabel='Size (number of vertices)', ylabel='Score (sum of penalties)')
        plt.savefig('scores_%s_%s.png' % (family, opt))

        _, ax = plt.subplots(figsize=(7, 7))
        plt.title('Times for %s family with%s optimization' % (family, '' if opt == 'true' else 'out'))
        ax.set_xscale('log', basex=10)
        ax.set_yscale('log', basey=10)
        sns.lineplot(x='Size', y='Time', hue='Alg', data=data_f, ax=ax)
        ax.set_xlim([10 ** 1, 10 ** 7])
        ax.set_ylim([10 ** 3, 10 ** 11])
        ax.set(xlabel='Size (number of vertices)', ylabel='Time (ns)')
        plt.savefig('times_%s_%s.png' % (family, opt))
