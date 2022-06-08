import os
import json

def score(user_player, src_player_assess) -> float:
    score = 0
    score += pow(float(user_player['shooting']) - 100*src_player_assess['shooting'], 2)
    score += pow(float(user_player['leading']) - 100*src_player_assess['leading'], 2)
    score += pow(float(user_player['defensive']) - 100*src_player_assess['defensive'], 2)
    score += pow(float(user_player['breaking']) - 100*src_player_assess['breaking'], 2)
    score += pow(float(user_player['rebounding']) - 100*src_player_assess['rebounding'], 2)

    return score

def rate(user_player) -> list:
    all_results = []

    paths = os.walk('./src')
    for _, _, files in paths:
        for file in files:
            f = open(os.path.join('./src', file), 'r')
            src_player = json.load(f)
            
            if src_player['pos'] not in user_player['pos']:
                f.close()
                continue
            else:
                src_player_assess = src_player['assessment']
                all_results.append([src_player, score(user_player, src_player_assess)])
                f.close()
            
    all_results.sort(key=lambda x: x[1])

    with open('results.json', 'w', encoding='utf-8') as f:
        json.dump(all_results[:3], f, ensure_ascii=False, indent=4)

    return all_results[:3]