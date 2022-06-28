# 基于模糊逻辑的 NBA 球员匹配专家系统

文件结构
```
code
|-- source ... 数据
|   |-- 2021-2022 NBA Player Stats-Playoffs.csv ... 源数据
|   |-- players_input ... 各球员处理后的单独信息
|   |-- source_process.py ... 源数据处理
|   `-- source_analysis.ipynb ... 源数据分析
|-- NBAExpert ... 模糊专家系统
|   |-- bin
|   |-- lib
|   |-- input ... link to code/source/players_input
|   |-- NBA.fcl ... 定义了模糊规则
|   |-- result ... 经过模糊系统处理后的各球员信息
|   `-- src
|       `-- NBA.java ... 运行模糊系统
`-- website
    |-- web.py ... 网页运行
    |-- src ... link to code/NBAExpert/result
    |-- static
    `-- templates
```
