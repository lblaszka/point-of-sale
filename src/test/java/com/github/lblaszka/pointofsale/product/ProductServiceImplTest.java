package com.github.lblaszka.pointofsale.product;

import com.github.lblaszka.pointofsale.barcode.BarCodeContainer;
import javafx.beans.binding.When;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;

@RunWith( MockitoJUnitRunner.class )
public class ProductServiceImplTest
{
    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductConverter productConverter;

    private ProductService productService;

    @Before
    public void setUp()
    {
        productService = new ProductServiceImpl( productRepository, productConverter );
    }

    @Test
    public void successFindProduct()
    {
        //GET
        String barCode = new String("BAR-CODE");
        BarCodeContainer barCodeContainer = new BarCodeContainer( barCode );
        Product product = new Product();
        ProductDTO productDTO = new ProductDTO( "Product Label", new BarCodeContainer( barCode ), new BigDecimal( 10 ) );
        Optional<Product> productOptional = Optional.of( product );

        Mockito.when( productRepository.findByBarCode( barCode ) )
                    .thenReturn( productOptional );

        Mockito.when( productConverter.convert( product ) ).thenReturn( productDTO );

        //WHEN
        Optional<ProductDTO> productDTOFromService = productService.findByBarCode( barCodeContainer );

        //THEN
        Assert.assertNotNull( productDTOFromService );
        Assert.assertTrue( productDTOFromService.isPresent() );
        Assert.assertThat( productDTOFromService.get().getLabel(), is( productDTO.getLabel()) );
        Assert.assertThat( productDTOFromService.get().getBarCodeContainer().getBarCodeObject(), is( productDTO.getBarCodeContainer().getBarCodeObject()) );
        Assert.assertThat( productDTOFromService.get().getPrice(), is( productDTO.getPrice()) );
    }

    @Test
    public void failedFindProduct()
    {
        //GET
        String barCode = new String("NotExistBarCode");
        BarCodeContainer barCodeContainer = new BarCodeContainer( barCode );
        Optional<Product> productOptional = Optional.empty();
        Mockito.when( productRepository.findByBarCode( barCode ) )
                .thenReturn( productOptional );

        //WHEN
        Optional<ProductDTO> productDTO = productService.findByBarCode( barCodeContainer );

        //THEN
        Assert.assertNotNull( productDTO );
        Assert.assertFalse( productDTO.isPresent() );
    }

    @Test
    public void repositoryReturnNull()
    {
        //GET
        String barCode = new String("NotExistBarCode");
        BarCodeContainer barCodeContainer = new BarCodeContainer( barCode );

        //WHEN
        Optional<ProductDTO> productDTO = productService.findByBarCode( barCodeContainer );

        //THEN
        Assert.assertNotNull( productDTO );
        Assert.assertFalse( productDTO.isPresent() );
    }

    @Test
    public void getNullBarCode()
    {
        //WHEN
        Optional<ProductDTO> productDTO = productService.findByBarCode( null );

        //THEN
        Assert.assertNotNull( productDTO );
        Assert.assertFalse( productDTO.isPresent() );
    }

    @Test
    public void productConverterThrow()
    {
        //GET
        String barCode = new String("BAR-CODE");
        BarCodeContainer barCodeContainer = new BarCodeContainer( barCode );
        Product product = new Product();
        Optional<Product> productOptional = Optional.of( product );

        Mockito.when( productRepository.findByBarCode( barCode ) )
                .thenReturn( productOptional );

        Mockito.when( productConverter.convert( product ) ).thenThrow( ProductConverterException.class );

        //WHEN
        Optional<ProductDTO> productDTO = productService.findByBarCode( barCodeContainer );

        //THEN
        Assert.assertNotNull( productDTO );
        Assert.assertFalse( productDTO.isPresent() );
    }


}