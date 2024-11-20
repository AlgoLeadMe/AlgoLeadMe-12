def fibonacci(n, m):
    
    period = [0, 1]
      
    for i in range(2, m*m+1):
        new_value = (period[i-1] + period[i-2]) % m
        period.append(new_value)
        
        if period[i] == 1 and period[i-1] == 0:
            period = period[:-2]
            break
    length = len(period)
    return period[n % length]
        
n = int(input())
m = 1000000

result = fibonacci(n,m)
print(result)