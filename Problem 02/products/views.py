from django.shortcuts import render, redirect
from .models import ProductList
import math


def index(request, type_=0):
    if request.method == 'POST':
        text = request.POST.get('text')
        flag = len(text)
        productList = ProductList.objects.filter(name__startswith=text).values()
        print(productList)
    else:
        text = ''
        flag = len(text)
        productList = getSortedList(type_)
    productLists = []
    length = math.ceil(len(productList) / 3)
    for i in range(length):
        mn = i * 3
        mx = (i + 1) * 3
        productLists.append(productList[mn:mx])
    return render(request, "products/index.html", {'productLists': productLists, 'length': len(productLists), 'text': text, 'flag': flag})


def getSortedList(type_):
    global productList
    if type_ == 0:
        productList = ProductList.objects.all().values()
    elif type_ == 1:
        productList = ProductList.objects.all().values()
        productList = sorted(productList, key=lambda k: k['name'])
    elif type_ == 2:
        productList = ProductList.objects.all().values()
        productList = sorted(productList, key=lambda k: k['name'], reverse=True)
    elif type_ == 3:
        productList = ProductList.objects.all().values()
        productList = sorted(productList, key=lambda k: k['price'])
    elif type_ == 4:
        productList = ProductList.objects.all().values()
        productList = sorted(productList, key=lambda k: k['price'], reverse=True)
    return productList


def ascName(request):
    return index(request, type_=1)


def dscName(request):
    return index(request, type_=2)


def ascPrice(request):
    return index(request, type_=3)


def dscPrice(request):
    return index(request, type_=4)
