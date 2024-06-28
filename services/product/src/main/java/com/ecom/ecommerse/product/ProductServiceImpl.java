package com.ecom.ecommerse.product;

import com.ecom.ecommerse.excptions.EntityNotFoundException;
import com.ecom.ecommerse.excptions.ProductPurchaseException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;
    @Override
    public Integer createProduct(ProductRequest productRequest) {
        Product product = requestToProduct(productRequest);

        return productRepository.save(product).getId();
    }

    @Override
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requests) {
        List<Integer> productIdList = requests.stream()
                .map(ProductPurchaseRequest::getProductId)
                .collect(Collectors.toList());
        List<Product> storedProducts = productRepository.findAllByIdInOrderById(productIdList);
        if(productIdList.size() != storedProducts.size()){
            throw new ProductPurchaseException("One or more product not exists");
        }
        List<ProductPurchaseRequest> storedRequest = requests.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::getProductId))
                .collect(Collectors.toList());
        List<ProductPurchaseResponse> productPurchaseResponseList= new ArrayList<>();

        for(int i=0;i<storedProducts.size();i++){
            Product product = storedProducts.get(i);
            ProductPurchaseRequest productPurchaseRequest = storedRequest.get(i);

            if(product.getAvailableQuantity()< productPurchaseRequest.getQuantity()){
                throw new ProductPurchaseException("Insufficient stock quantity for product : "+product.getId());
            }
            double newAvailbleQuant = product.getAvailableQuantity() - productPurchaseRequest.getQuantity();
            product.setAvailableQuantity(newAvailbleQuant);
            productRepository.save(product);

            productPurchaseResponseList.add(this.productToProductResponse(product));

        }

        return productPurchaseResponseList;
    }

    @Override
    public ProductResponse findById(Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
        ProductResponse productResponse = this.productToResponse(product);
        return productResponse;
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        return productRepository.findAll()
                .stream().map(this::productToResponse)
                .collect(Collectors.toList());
    }

    private Product requestToProduct(ProductRequest productRequest){
        return modelMapper.map(productRequest,Product.class);
    }

    private ProductPurchaseResponse productToProductResponse(Product product){
        return modelMapper.map(product,ProductPurchaseResponse.class);
    }

    private ProductResponse productToResponse(Product product){
        return modelMapper.map(product,ProductResponse.class);
    }
}
