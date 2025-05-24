from itertools import product

def solution(users, emoticons):
    emg_len = len(emoticons)
    answer = [0, 0]
    
    for p in product(range(10, 41, 10), repeat=emg_len):
        counts, money = 0, 0
        for i in range(len(users)):
            user_discount = users[i][0]
            total = 0
            
            for j in range(len(p)):
                if user_discount <= p[j]:
                    total += emoticons[j] * (100 - p[j]) * 0.01
            
            if total >= users[i][1]:
                counts += 1
            
            else:
                money += total 
                
        if answer[0] > counts:
            continue
        
        if answer[0] == counts and answer[1] > money:
            continue
            
        answer = [counts, money]
    
    return answer