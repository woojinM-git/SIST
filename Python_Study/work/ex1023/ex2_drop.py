from pandas import Series, DataFrame

emp_dict = [
    {'empno':100, 'name':'마루치', 'job':'IT_PROG'},
    {'empno':120, 'name':'아라치', 'job':'Analysis'},
    {'empno':345, 'name':'을불', 'job':'IT_PROG'},
    {'empno':210, 'name':'창조리', 'job':'Sales'},
    {'empno':349, 'name':'홍길동', 'job':'IT_PROG'},
    {'empno':345, 'name':'쌍용', 'job':'IT_PROG'},
    {'empno':445, 'name':'마이클', 'job':'IT_PROG'},
    {'empno':410, 'name':'어두일미', 'job':'Analysis'}
]
df = DataFrame(emp_dict)
print(df)
print("----- 중복 확인 -----")
print(df.duplicated(['empno'])) # empno가 중복된 값 찾기

print("----- 중복 data 삭제 -----")
print(df.drop_duplicates('empno', keep="first")) # 첫번째 중복값 유지
print(df.drop_duplicates('empno', keep="last")) # 마지막 중복값 유지

print("----- 중복 data 모두 삭제 -----")
print(df.drop_duplicates('empno', keep=False)) # 중복값 모두 삭제