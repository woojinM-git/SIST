"""
딕셔너리를 가지고 한 사원의 정보를 만들어보자!
"""
from pandas import Series, DataFrame

emp = [{"empno": 100, "ename":"마루치", "job":"DEV"},
    {"empno": 220, "ename":"아라치", "job":"DEV"},
    {"empno": 210, "ename":"마동탁", "job":"DEV"}]

df = DataFrame(emp_list)
print(df)