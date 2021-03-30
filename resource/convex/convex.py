# coding=utf-8
import matplotlib.pyplot as plt

fig = plt.figure()
ax1 = fig.add_subplot()
# 设置标题
ax1.set_title('convex hull')
# 设置X轴标签
plt.xlabel('X')
# 设置Y轴标签
plt.ylabel('Y')
# 画散点图
for i, j in [(7, 9), (-8, -1), (-3, -1), (1, 4), (-3, 9), (6, -4), (7, 5), (6, 6), (-6, 10), (0, 8)]:
    ax1.scatter(i, j, c='r', marker='o')
    plt.text(i, j, (i, j), ha='center', va='bottom', fontsize=10)
# 设置图标
plt.legend('x1')
# 显示所画的图
plt.show()
