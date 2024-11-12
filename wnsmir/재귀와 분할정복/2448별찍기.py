def draw_star(n):
    if n == 3:
        return ['  *  ', ' * * ', '*****']

    stars = draw_star(n // 2)
    result = []

    for star in stars:
        result.append(' ' * (n // 2) + star + ' ' * (n // 2))
    
    for star in stars:
        result.append(star + ' ' + star)

    return result

N = int(input())
result = draw_star(N)
for line in result:
    print(line)