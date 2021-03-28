from django.urls import path
from . import views

urlpatterns = [
    path("", views.index, name="index"),
    path("ascnm", views.ascName, name="nameasc"),
    path("dscnm", views.dscName, name="namedsc"),
    path("ascpr", views.ascPrice, name="priceasc"),
    path("dscpr", views.dscPrice, name="pricedsc"),
]