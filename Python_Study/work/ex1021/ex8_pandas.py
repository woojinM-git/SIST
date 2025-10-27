from pandas import Series, DataFrame

comp1 = Series([30,20,40], index=['sk','kt','lg'])
comp2 = Series([10,50,40], index=['kt','lg','sk'])

hap = comp1 + comp2
print(hap)
print(hap['lg'])