def solution(phone_book):
    unique_phone_book = set(phone_book) #해시테이블을 사용하기 위함
    for phone in phone_book:
        temp = ''
        for num in phone[:-1]:
            temp += num #큰 숫자의 일부를 확인하여 작은숫자와 일치하는 부분이 있는지 확인
            if temp in unique_phone_book:
                return False
    return True