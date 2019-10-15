# -*- coding: utf-8 -*-
"""
Created on Tue Oct  1 17:46:11 2019

@author: Quentin GUIMARD
"""

import os
import numpy as np
import seaborn as sns
import pandas as pd
import matplotlib.pyplot as plt

files = [file[:] for file in os.listdir('.') if file.endswith('.si5')]
files_cat = {
        "Array random read": [file for file in files if file.endswith('get-random_unsorted.si5')],
        "Array random write": [file for file in files if file.endswith('set-random_unsorted.si5')],
        "Array sequential read": [file for file in files if file.endswith('get-sequential_unsorted.si5')],
        "Array sequential write": [file for file in files if file.endswith('set-sequential_unsorted.si5')],
        "AVL search": [file for file in files if file.endswith('search_unsortedset.si5')],
        "AVL insert (random input)": [file for file in files if file.endswith('insert_unsortedset.si5')],
        "AVL insert (sorted ascending input)": [file for file in files if file.endswith('insert_sortedasc.si5')],
        "AVL insert (sorted descending input)": [file for file in files if file.endswith('insert_sorteddesc.si5')],
        "AVL delete": [file for file in files if file.endswith('delete_unsortedset.si5')],
        "Heap add (random input)": [file for file in files if file.endswith('add_unsorted.si5')],
        "Heap add (sorted ascending input)": [file for file in files if file.endswith('add_sortedasc.si5')],
        "Heap add (sorted ascending input)": [file for file in files if file.endswith('add_sorteddesc.si5')],
        "Heap pop": [file for file in files if file.endswith('heap_pop_unsorted.si5')],
        "Queue enqueue": [file for file in files if file.endswith('enqueue_unsorted.si5')],
        "Queue dequeue": [file for file in files if file.endswith('dequeue_unsorted.si5')],
        "Stack push": [file for file in files if file.endswith('enqueue_unsorted.si5')],
        "Stack pop": [file for file in files if file.endswith('stack_pop_unsorted.si5')]
}

for file_cat in files_cat.keys():
    fig, ax = plt.subplots(figsize=(7,7))
    plt.title(file_cat)
    
    data = pd.DataFrame(columns=['Size', 'Time', 'Sort'])
    for f in files_cat[file_cat]:
        lines = [line.strip() for line in open(f, 'r', encoding='utf-8')]
        lines = np.array([line.split(' ') for line in lines if line]).astype(float)
        
        sizes = lines[:, 0]
        times = lines[:, 1]
        data = data.append(pd.DataFrame(data={'Size': sizes, 'Time': times, 'Sort': f.split('_')[0]}))
        
    data.reset_index()
    
    ax.set_xscale('log', basex=2)
    ax.set_yscale('log', basey=10)
    sns.lineplot(x='Size', y='Time', hue='Sort', data=data, ax=ax)
    plt.savefig('%s.png' % file_cat)
