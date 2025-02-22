def solution(key, lock):
    
    
    def rotate(a):
        n = len(a)
        result = [[0] * n for _ in range(n)]
        for i in range(n):
            for j in range(n):
                result[j][n-i-1] = a[i][j]
        return result
    
    def check(upscale_lock):
        lock_length = len(upscale_lock) // 3
        for i in range(lock_length, lock_length * 2):
            for j in range(lock_length, lock_length * 2):
                if upscale_lock[i][j] != 1:
                    return False
        return True
    
    n = len(lock)
    m = len(key)
    
    upscale_lock = [[0]*(n*3) for _ in range(n * 3)]
    for i in range(n):
        for j in range(n):
            upscale_lock[i+n][j+n] = lock[i][j]
    
    for _ in range(4):
        key = rotate(key)
        for x in range(n*2):
            for y in range(n*2):
                
                for i in range(m):
                    for j in range(m):
                        upscale_lock[x+i][y+j] += key[i][j]
                
                if check(upscale_lock) == True:
                    return True
                
                for i in range(m):
                    for j in range(m):
                        upscale_lock[x+i][y+j] -= key[i][j]
    return False