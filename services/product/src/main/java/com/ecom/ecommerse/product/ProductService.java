package com.ecom.ecommerse.product;

import java.util.List;

public interface ProductService {
    Integer createProduct(ProductRequest productRequest);

    List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requests);

    ProductResponse findById(Integer productId);

    List<ProductResponse> getAllProduct();
}
