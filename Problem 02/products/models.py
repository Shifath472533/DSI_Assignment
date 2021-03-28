from django.db import models


# Create your models here.
class ProductList(models.Model):
    name = models.CharField(max_length=400, name="name")
    img_src = models.CharField(max_length=400, name="image_source")
    price = models.FloatField(max_length=100, default=0.0, name="price")
    source = models.CharField(max_length=400, name="source")

    def __str__(self):
        return self.name



# some shell operations
# from products.models import ProductList
# proList = ProductList(name="PlayStation 4 Pro 1TB Console", price=479.99, source = "https://www.amazon.com/PlayStation-4-Pro-1TB-Console/dp/B01LOP8EZC/?_encoding=UTF8&pd_rd_w=Tyzrd&pf_rd_p=ae204524-9feb-47cc-8936-b890d679e0d6&pf_rd_r=ZZQHQMD3CKR9HSV6QQ7M&pd_rd_r=803b319f-3319-4bad-a8bb-4ac2e1609680&pd_rd_wg=T34xM&ref_=pd_gw_unk")
# proList.save()
# ProductList.objects.all()
# ProductList.objects.get(id=1)
# ProductList.objects.get(name="PlayStation 4 Pro 1TB Console")