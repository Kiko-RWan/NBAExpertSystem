import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
import json
import os

dataset = pd.read_csv('./2021-2022 NBA Player Stats - Playoffs.csv',encoding="Windows-1252", delimiter=";")
dataset.rename(columns=lambda s: s.replace("2", "two").replace("3", "three").replace("%", "p"), inplace=True)

result_dir = './players_input'
for _, row in dataset.iterrows():
    row_json = row.to_json()
    row_json = json.dumps(json.loads(row_json), indent=4)


    filename = row['Player'] + '.json'
    with open(os.path.join(result_dir, filename), 'w') as f:
        f.write(row_json)