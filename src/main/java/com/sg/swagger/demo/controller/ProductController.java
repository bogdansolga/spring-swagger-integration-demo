package com.sg.swagger.demo.controller;

import com.sg.swagger.demo.dto.ProductDTO;
import com.sg.swagger.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * A Spring {@link RestController} used to showcase the modeling of a REST controller for CRUD operations
 *
 * @author bogdan.solga
 */
@RestController
@RequestMapping(
        path = "/product",
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    /**
     * Creates a {@link com.sg.swagger.demo.model.Product} from the referenced {@link ProductDTO}
     *
     * @param productDTO the {@link com.sg.swagger.demo.model.Product} to be created
     *
     * @return a {@link ResponseEntity} with the appropriate {@link HttpStatus}
     */
    @RequestMapping(
            method = RequestMethod.POST,
            path = ""
    )
    public ResponseEntity create(@RequestBody @Valid ProductDTO productDTO) {
        productService.create(productDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * Reads the {@link com.sg.swagger.demo.model.Product} with the specified id
     *
     * @param id the id of the requested {@link com.sg.swagger.demo.model.Product}
     *
     * @return the serialized {@link com.sg.swagger.demo.model.Product}
     */
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/{id}"
    )
    public ProductDTO getProduct(@PathVariable final int id) {
        return productService.get(id);
    }

    /**
     * Returns all the existing {@link com.sg.swagger.demo.model.Product}s
     */
    @RequestMapping(
            method = RequestMethod.GET,
            path = ""
    )
    public List<ProductDTO> getAll() {
        return productService.getAll();
    }

    /**
     * Updates the {@link com.sg.swagger.demo.model.Product} with the specified ID with the details from the
     * referenced {@link com.sg.swagger.demo.model.Product}
     *
     * @param id the ID of the updated {@link com.sg.swagger.demo.model.Product}
     * @param productDTO the new {@link com.sg.swagger.demo.model.Product} details
     *
     * @return a {@link ResponseEntity} with the appropriate {@link HttpStatus}
     */
    @RequestMapping(
            method = RequestMethod.PUT,
            path = "/{id}"
    )
    public ResponseEntity update(@PathVariable final int id, @RequestBody ProductDTO productDTO) {
        productService.update(id, productDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * Deletes the {@link com.sg.swagger.demo.model.Product} with the specified ID
     *
     * @param id the ID of the deleted {@link com.sg.swagger.demo.model.Product}
     *
     * @return a {@link ResponseEntity} with the appropriate {@link HttpStatus}
     */
    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/{id}"
    )
    public ResponseEntity delete(@PathVariable final int id) {
        productService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
