king, rock, m_time = input().split()
m_time = int(m_time)                    
move = [input() for _ in range(m_time)]

king_x = int(ord(king[0])) - int(ord('A')) + 1  
king_y = int(king[1])

rock_x = int(ord(rock[0])) - int(ord('A')) + 1
rock_y = int(rock[1])

move_way = ['R', 'L', 'T', 'B', 'RT', 'RB', 'LT', 'LB']
dx = [1, -1, 0, 0, 1, 1, -1, -1]
dy = [0, 0, 1, -1, 1, -1, 1, -1]

for i in range(m_time):
    index = move_way.index(move[i])

    after_king_x = king_x + dx[index]
    after_king_y = king_y + dy[index]

    if 0 < after_king_x <= 8 and 0 < after_king_y <= 8:
        if after_king_x == rock_x and after_king_y == rock_y:
            after_rock_x = rock_x + dx[index]
            after_rock_y = rock_y + dy[index]
            if 0 < after_rock_x <= 8 and 0 < after_rock_y <= 8:
                king_x = after_king_x
                king_y = after_king_y
                rock_x = after_rock_x
                rock_y = after_rock_y
        else: 
            king_x = after_king_x
            king_y = after_king_y

print(f'{chr(king_x + 64)}{king_y}')
print(f'{chr(rock_x + 64)}{rock_y}')




