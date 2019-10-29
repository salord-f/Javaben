# -*- coding: utf-8 -*-
'''
Created on Tue Oct  1 17:46:11 2019

@author: Quentin GUIMARD
'''

import os
import numpy as np
import seaborn as sns
import pandas as pd
import matplotlib.pyplot as plt

files = [file for file in os.listdir('.') if file.endswith('.si5')]
files_cat = {
        'With post-optimization': [file for file in files if file.endswith('true.si5')],
        'Without post-optimization': [file for file in files if file.endswith('false.si5')]
}

sns.set(style='whitegrid')

for file_cat in files_cat.keys():
    fig, ax = plt.subplots(figsize=(7, 7))
    plt.title(file_cat)

    data = pd.DataFrame(columns=['Alg', 'Input', 'Score', 'Time'])
    for f in files_cat[file_cat]:
        lines = [line.strip() for line in open(f, 'r', encoding='utf-8')]
        lines = np.array([line.split(' ') for line in lines if line])

        alg = f[:-4].split('_')[0]

        inputs = lines[:, 0]
        times = lines[:, 1].astype(float)
        scores = lines[:, 2].astype(float)
        n = len(inputs)

        data = data.append(pd.DataFrame(data={'Alg': [alg] * n, 'Input': inputs, 'Score': scores, 'Time': times})).reset_index(drop=True)

    name = 'optimized' if 'Without' in file_cat else ''

    g = sns.catplot(x='Input', y='Score', hue='Alg', data=data,
                    height=6, kind='bar', palette='muted', ci=None, legend=False)
    for ax in g.axes.flat:
        mdr = ax.yaxis
        ax.yaxis._set_scale('linear', basey=10)
    g.despine(left=True)
    g.set_ylabels('Score')
    plt.legend(loc='upper right')
    plt.savefig('scores_%s.png' % name)

    g = sns.catplot(x='Input', y='Time', hue='Alg', data=data,
                    height=6, kind='bar', palette='muted', ci=None, legend=False)
    for ax in g.axes.flat:
        ax.yaxis._set_scale('log', basey=10)
    g.despine(left=True)
    g.set_ylabels('Time')
    plt.legend(loc='upper right')
    plt.savefig('times_%s.png' % name)
