package com.omercan.gateway_app.controller;

import com.google.gson.JsonElement;
import com.omercan.gateway_app.model.service.AbstractProductService;
import com.omercan.gateway_app.model.service.AbstractTransactionService;
import com.omercan.gateway_app.security.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//localhost/4010 gateway product dediğimiz productrestapi sine gidecepiniz
@RestController
@RequestMapping("gateway/product")
public class ProductController
{

    @Autowired
    private AbstractProductService productService;

    @Autowired
    private AbstractTransactionService transactionService;

    @PostMapping
    public ResponseEntity<JsonElement> save(@RequestBody JsonElement product ,@AuthenticationPrincipal UserPrincipal user)
    {
        //

        JsonElement savedProduct = productService.save(product);
        if(savedProduct!=null){
            JsonElement transactionData = transactionService.createTransactionData(savedProduct, user);
            transactionService.save(transactionData);

        }
        return ResponseEntity.ok(savedProduct);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteByID(@PathVariable(name = "id") Integer productId)
    {

        JsonElement product = productService.findById(productId);
        if(product!=null)
        {
            productService.deleteById(productId);
            return new ResponseEntity<>("Product(product ID: " + productId +") has been deleted.",
                    HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Product(product ID: " + productId +") has been not found.",
                    HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<JsonElement> findByID(@PathVariable(name = "id") Integer productId)
    {
        JsonElement productFound = productService.findById(productId);
        return productFound != null ?new ResponseEntity<>( productFound, HttpStatus.OK)
                : new ResponseEntity<>(null , HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<JsonElement>> getAll()
    {
        return    ResponseEntity.ok(productService.getAll());
    }

}
