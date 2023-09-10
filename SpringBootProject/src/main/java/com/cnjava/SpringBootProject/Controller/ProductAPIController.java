package com.cnjava.SpringBootProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnjava.SpringBootProject.Model.Product;
import com.cnjava.SpringBootProject.Service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductAPIController {

	@Autowired
	private ProductService productService;
	
	// Định nghĩa phương thức để lấy tất cả sản phẩm
    @GetMapping
    public Page<Product> getAllProducts(Pageable pageable) {
        return productService.getProductsPage(pageable.getPageNumber());
    }

    // Định nghĩa phương thức để lấy sản phẩm theo ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    // Định nghĩa phương thức để tạo mới sản phẩm
    @PostMapping
    public void createProduct(@RequestBody Product product) {
        productService.save(product);
    }

    // Định nghĩa phương thức để cập nhật sản phẩm
    @PutMapping("/{id}")
    public void updateProduct(@PathVariable int id, @RequestBody Product product) {
        Product existingProduct = productService.getProductById(id);
        if (existingProduct != null) {
            // Cập nhật thông tin sản phẩm
            existingProduct.setProductName(product.getProductName());
            existingProduct.setPrice(product.getPrice());
            // Cập nhật các trường khác tùy ý
            productService.save(existingProduct);
        }
    }

    // Định nghĩa phương thức để xóa sản phẩm theo ID
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteById(id);
    }
}
