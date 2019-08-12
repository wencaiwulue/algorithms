import numpy as np
import matplotlib.pyplot as plt
#产生测试数据
x = np.arange(10)
y = x
fig = plt.figure()
ax1 = fig.add_subplot(111)
#设置标题
ax1.set_title('Scatter Plot')
#设置X轴标签
plt.xlabel('X')
#设置Y轴标签
plt.ylabel('Y')
#画散点图
a=1
for i,j in [(0,59),(-1*100,102),(0,172),(1*100,214),(2*100,230),(3*100,225),(4*100,201),(5*100,163),(6*100,115),(7*100,59)]:
    ax1.scatter(i,j,c = 'r',marker = 'o')
#设置图标
plt.legend('x1')
#显示所画的图
plt.show()
