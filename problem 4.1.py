# problem 4.1

from hashlib import md5
secret_key  = 'bgvyzdsv'


for i in range(200000000000000000000000000000000000000):
    h = md5(bytes(secret_key + str(i), "utf-8")).hexdigest()
    if h[:5] == '00000':
        print(i)
        break


