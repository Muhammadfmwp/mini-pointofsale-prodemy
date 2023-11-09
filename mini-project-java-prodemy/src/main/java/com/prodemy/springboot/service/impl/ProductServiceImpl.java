package com.prodemy.springboot.service.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.prodemy.springboot.model.Product;
import com.prodemy.springboot.repository.ProductRepository;
import com.prodemy.springboot.service.ProductService;
import com.prodemy.springboot.web.dto.ProductDto;

import io.micrometer.common.util.StringUtils;

@Service
public class ProductServiceImpl implements ProductService {
	
	private ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}


	@Override
	public Product updateProduct(Long id, ProductDto productDto, MultipartFile mainImg, MultipartFile extraImg1, MultipartFile extraImg2, MultipartFile extraImg3) {
		Product product = productRepository.getById(id);
		product.setProductName(productDto.getProductName());
		product.setPrice(productDto.getPrice());
		productDto.setMainImage(mainImg);
		productDto.setExtraImage1(extraImg1);
		productDto.setExtraImage2(extraImg2);
		productDto.setExtraImage3(extraImg3);
		product.setMainImage(productDto.getMainImage().getOriginalFilename());
		product.setExtraImage1(productDto.getExtraImage1().getOriginalFilename());
		product.setExtraImage2(productDto.getExtraImage2().getOriginalFilename());
		product.setExtraImage3(productDto.getExtraImage3().getOriginalFilename());
		product.setDescription(productDto.getDescription());
		Product uploadData = productRepository.save(product);
		
		if(uploadData!=null) {
			try {
				
				File saveFile = new ClassPathResource("static/img").getFile();
				
				Path pathMain = Paths.get(saveFile.getAbsolutePath()+File.separator+productDto.getMainImage().getOriginalFilename());
				Path pathExtra1 = Paths.get(saveFile.getAbsolutePath()+File.separator+productDto.getExtraImage1().getOriginalFilename());
				Path pathExtra2 = Paths.get(saveFile.getAbsolutePath()+File.separator+productDto.getExtraImage2().getOriginalFilename());
				Path pathExtra3 = Paths.get(saveFile.getAbsolutePath()+File.separator+productDto.getExtraImage3().getOriginalFilename());
				Files.copy(productDto.getMainImage().getInputStream(), pathMain, StandardCopyOption.REPLACE_EXISTING);
				Files.copy(productDto.getExtraImage1().getInputStream(), pathExtra1, StandardCopyOption.REPLACE_EXISTING);
				Files.copy(productDto.getExtraImage2().getInputStream(), pathExtra2, StandardCopyOption.REPLACE_EXISTING);
				Files.copy(productDto.getExtraImage3().getInputStream(), pathExtra3, StandardCopyOption.REPLACE_EXISTING);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return uploadData;
	}

	@Override
	public Product getByProduct(Long id) {
		Optional<Product> optional = productRepository.findById(id);
		Product product = null;
		if(optional.isPresent()) {
			product = optional.get();
		}
		else {
			throw new RuntimeException("Product tidak ditemukan");
			}
		return product;
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
		
	}


	@Override
	public Product save(ProductDto productDto,MultipartFile mainImg, MultipartFile extraImg1, MultipartFile extraImg2, MultipartFile extraImg3) {
		Product product = new Product();
		product.setProductName(productDto.getProductName());
		product.setPrice(productDto.getPrice());
		productDto.setMainImage(mainImg);
		productDto.setExtraImage1(extraImg1);
		productDto.setExtraImage2(extraImg2);
		productDto.setExtraImage3(extraImg3);
		product.setMainImage(productDto.getMainImage().getOriginalFilename());
		product.setExtraImage1(productDto.getExtraImage1().getOriginalFilename());
		product.setExtraImage2(productDto.getExtraImage2().getOriginalFilename());
		product.setExtraImage3(productDto.getExtraImage3().getOriginalFilename());
		product.setDescription(productDto.getDescription());
		Product uploadData = productRepository.save(product);
		
		if(uploadData!=null) {
			try {
				
				File saveFile = new ClassPathResource("static/img").getFile();
				
				Path pathMain = Paths.get(saveFile.getAbsolutePath()+File.separator+productDto.getMainImage().getOriginalFilename());
				Path pathExtra1 = Paths.get(saveFile.getAbsolutePath()+File.separator+productDto.getExtraImage1().getOriginalFilename());
				Path pathExtra2 = Paths.get(saveFile.getAbsolutePath()+File.separator+productDto.getExtraImage2().getOriginalFilename());
				Path pathExtra3 = Paths.get(saveFile.getAbsolutePath()+File.separator+productDto.getExtraImage3().getOriginalFilename());
				Files.copy(productDto.getMainImage().getInputStream(), pathMain, StandardCopyOption.REPLACE_EXISTING);
				Files.copy(productDto.getExtraImage1().getInputStream(), pathExtra1, StandardCopyOption.REPLACE_EXISTING);
				Files.copy(productDto.getExtraImage2().getInputStream(), pathExtra2, StandardCopyOption.REPLACE_EXISTING);
				Files.copy(productDto.getExtraImage3().getInputStream(), pathExtra3, StandardCopyOption.REPLACE_EXISTING);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return uploadData;
	}


	@Override
	public Page<Product> paginatedPage(int pageNo, int pageSize, String sortField, String productName, Double min,
			Double max, String sortDirection)  {
	Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
		Sort.by(sortField).descending();
	String product_name = productName;
	
	Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
	if(productName != null && min == null && max == null) {
		return this.productRepository.findByProductName(productName, pageable);
	}
	else if(productName == null && min != null && max != null) {
		return this.productRepository.findByProductsPrice(min, max, pageable);
	}
	else if(productName != null && min != null && max != null) {
		return this.productRepository.findProducts(product_name, min, max, pageable);
	}
	return this.productRepository.findAll(pageable);
	}





}
