from pandas import Series, DataFrame

emp_dict = [
    {'empno':100, 'name':'마루치', 'job':'IT_PROG'},
    {'empno':120, 'name':'아라치', 'job':'Analysis'},
    {'empno':345, 'name':'을불', 'job':'IT_PROG'},
    {'empno':210, 'name':'창조리', 'job':'Sales'},
    {'empno':349, 'name':'홍길동', 'job':'IT_PROG'},
    {'empno':345, 'name':'을불', 'job':'IT_PROG'},
    {'empno':445, 'name':'마이클', 'job':'IT_PROG'},
    {'empno':410, 'name':'어두일미', 'job':'Analysis'}
]
df = DataFrame(emp_dict)
print(df)
print("----- 중복 확인 -----")
print(df.duplicated())

print("----- 중복 data 삭제 -----")
print(df.drop_duplicates())