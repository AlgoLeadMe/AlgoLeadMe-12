while True:
    n = int(input())
    if (n == -1):
        break
    else:
        sum = 0
        factors = []
        
        for i in range(1, n):
            if (n % i == 0):
                factors.append(i)
                sum += i
                
        
        if (sum == n):
            print(f"{n} =", end=' ')
            for i in range(len(factors)):
                print(factors[i], end=' ')
                if (i != (len(factors) - 1)):
                    print("+", end=' ')
                else:
                    print(end='\n')
        else:
            print(f"{n} is NOT perfect.")
                
            




