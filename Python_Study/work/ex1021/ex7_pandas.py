import pandas as pd
from pandas import Series, DataFrame

ko = Series([96200,92450,95465,92300])
print(ko)
print("----------------- index값 변경 ------------------")

ko = Series([96200,92450,95465,92300], index=['10-02', '10-02', '10-02', '10-02'])
print(ko)

print("----------------- index값들만 출력 ------------------")
for day in ko.index:
    print(day) # index들만 출력이 됌

print("----------------- value값들만 출력 ------------------")
for value in ko.values:
    print(value)