# problem 7.1





# and
# lshift
# not
# or
# rshift






circuit = [
    '123 -> x', '456 -> y', 'x AND y -> d', 'x OR y -> e', 'x LSHIFT 2 -> f', 'y RSHIFT 2 -> g', 'NOT x -> h',
    'NOT y -> i',
]

from collections import namedtuple

Operation = namedtuple('Operation', ['operator', 'x', 'y', 'z'])  # operator; operand 1; operand 2; operand 3

values = {}


def parse_operation(op):
    data = op.split()
    operator = None
    x = None
    y = None
    z = None

    if 'AND' in op:
        operator = 'AND'
        x = data[0]
        y = data[2]
        z = data[4]

    elif 'OR' in op:
        operator = 'OR'
        x = data[0]
        y = data[2]
        z = data[4]

    elif 'LSHIFT' in op:
        operator = 'LSHIFT'
        x = data[0]
        y = data[2]
        z = data[4]

    elif 'RSHIFT' in op:
        operator = 'RSHIFT'
        x = data[0]
        y = data[2]
        z = data[4]

    elif 'NOT' in op:
        operator = 'NOT'
        x = data[1]
        z = data[3]

    else:
        # simple assign
        x = data[0]
        z = data[2]

    return Operation(operator, x, y, z)


for op in circuit:
    operation = parse_operation(op)
    print('operation: ', operation, end=' || ')
    if 'AND' == operation.operator:
        x = values[operation.x]
        y = values[operation.y]
        z = x & y
        values[operation.z] = z

    elif 'OR' == operation.operator:
        x = values[operation.x]
        y = values[operation.y]
        z = x | y
        values[operation.z] = z

    elif 'LSHIFT' == operation.operator:
        x = values[operation.x]
        y = int(operation.y)
        z = x << y
        values[operation.z] = z

    elif 'RSHIFT' == operation.operator:
        x = values[operation.x]
        y = int(operation.y)
        z = x >> y
        values[operation.z] = z

    elif 'NOT' == operation.operator:
        x = values[operation.x]
        z = ~x
        values[operation.z] = z

    else:
        values[operation.z] = int(operation.x)

    print('values: ', values)

# print(values)


# sort alphabetically
for key, value in sorted(values.items()):
    if value < 0:
        print(key, 2 ** 16 - value)
    elif value > 0:
        print(key, value)

d
72
e
507
f
492
g
114
h
65660
i
65993
x
123
y
456

# d: 72
# e: 507
# f: 492
# g: 114
# h: 65412
# i: 65079
# x: 123
# y: 456
