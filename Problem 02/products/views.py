from django.shortcuts import render
from .models import ProductList
import math


# Create your views here.
def index(response):
    productList = ProductList.objects.all().values()
    productLists = []
    length = math.ceil(len(productList)/3)
    for i in range(length):
        mn = i * 3
        mx = (i+1) * 3
        productLists.append(productList[mn:mx])
    return render(response, "products/test.html", {'productLists': productLists})
